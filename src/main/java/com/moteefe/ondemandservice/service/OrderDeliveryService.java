package com.moteefe.ondemandservice.service;

import com.moteefe.ondemandservice.dto.common.ItemDto;
import com.moteefe.ondemandservice.dto.request.OrderDto;
import com.moteefe.ondemandservice.dto.response.DeliveryDto;
import com.moteefe.ondemandservice.dto.response.SuppliesDto;
import com.moteefe.ondemandservice.model.DeliveryTimes;
import com.moteefe.ondemandservice.model.Product;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderDeliveryService {
    private SupplierService supplierService;
    private ProductService productService;

    int maxDeliveryDays=-1;
    int minDeliveryDays=-1;
    int productDeliveryDays=-1;

    public OrderDeliveryService(SupplierService supplierService, ProductService productService) {
        this.supplierService = supplierService;
        this.productService = productService;
    }

    public DeliveryDto getOrderDeliveryDetails(OrderDto order){

        String productName;
        int orderItemCount;
        DeliveryDto deliveryDto = new DeliveryDto();
        List<SuppliesDto> shipmentsDto = new ArrayList<>();
        List<ItemDto> itemsDto;

        //resetting all delivery days
            //maxDeliveryDays - to calculate the maximum time taken out of all the suppliers to supply a give
            //minDeliveryDays - to calculate the min date for same item provided by suppliers
            //productDeliveryDays - to calculate the delivery date for item

        maxDeliveryDays=-1;
        minDeliveryDays=-1;
        productDeliveryDays=-1;

        Calendar shipmentDeliveryDate = Calendar.getInstance();
        Calendar productDeliveryDate;

        List<ItemDto> basketItems = order.getBasket().getItems();

        // to prioritise suppliers having multiple items in the basket
        List<String> prioritySortedSupplierNames = getPrioritySortedSupplierNames(basketItems);

        for(ItemDto item : basketItems){
            productName = item.getProduct();
            orderItemCount = item.getCount();

            List<Product> products = productService.getAllByProductsFromRelevantSuppliers(prioritySortedSupplierNames,productName);

            for (Product product: products ){
                productDeliveryDate = Calendar.getInstance();
                itemsDto = new ArrayList<>();
                productDeliveryDays=-1;

                if(product.getInStock() != 0) {
                    if (product.getInStock() < orderItemCount) {
                        itemsDto.add(new ItemDto(productName, product.getInStock()));
                        orderItemCount -= product.getInStock();
                        product.setInStock(0);
                        productService.save(product);

                        updateDeliveryDays(order.getRegion(), product.getDeliveryTimes());
                        productDeliveryDate.add(Calendar.DAY_OF_MONTH, productDeliveryDays);
                        shipmentsDto.add(new SuppliesDto(product.getSupplier().getName(), productDeliveryDate, itemsDto));


                    } else {
                        itemsDto.add(new ItemDto(productName, orderItemCount));
                        product.setInStock(product.getInStock() - orderItemCount);
                        productService.save(product);

                        updateDeliveryDays(order.getRegion(), product.getDeliveryTimes());
                        productDeliveryDate.add(Calendar.DAY_OF_MONTH, productDeliveryDays);
                        shipmentsDto.add(new SuppliesDto(product.getSupplier().getName(), productDeliveryDate, itemsDto));
                        break;
                    }
                }else{
                    shipmentsDto.add(null);
                }
            }

        }
        //if its only one product requested then the shortest date would be the delivery date.
        if(basketItems.size() == 1 && minDeliveryDays != -1){
            shipmentDeliveryDate.add(Calendar.DAY_OF_MONTH, minDeliveryDays);
            deliveryDto.setDeliveryDate(shipmentDeliveryDate);
        //if more than one product is ordered then the longest date would be the delivery date.
        }else if(maxDeliveryDays != -1) {
            shipmentDeliveryDate.add(Calendar.DAY_OF_MONTH, maxDeliveryDays);
            deliveryDto.setDeliveryDate(shipmentDeliveryDate);
        //Suppliers don't have enough stock, order can't be fulfilled.
        }else{
            deliveryDto.setDeliveryDate(null);
        }
        deliveryDto.setShipments(shipmentsDto);
        return deliveryDto;

    }

    /** to prioritise suppliers having multiple items in the basket */
    private List<String> getPrioritySortedSupplierNames(List<ItemDto> itemsFromTheBasket) {
        String productName;
        Map<String, Integer> supplierProductMap = new HashMap<>();
        for(ItemDto item1 : itemsFromTheBasket){
            productName = item1.getProduct();

            //to get the products/items and intern suppliers from the DB for the basket items (0 to avoid getting products with 0 in stock after multiple runs)
            List<Product> products1 = productService.getAllByNameAndInStockNotOrderByInStockDesc(productName,0);

            for (Product product1: products1 ) {
                String supplierName = product1.getSupplier().getName();
                int value=1;
                if(supplierProductMap.get(supplierName) != null){
                    value = supplierProductMap.get(supplierName);
                    supplierProductMap.put(supplierName, ++value);
                }else{
                    supplierProductMap.put(supplierName,value);
                }
            }
        }
        return new ArrayList<>(sortByValue(supplierProductMap).keySet());
    }

/**  updating the 3 types of delivery dates
*           maxDeliveryDays - to calculate the maximum time taken out of all the suppliers to supply a give
*           minDeliveryDays - to calculate the min date for same item provided by suppliers
*           productDeliveryDays - to calculate the delivery date for item
*/
    private void updateDeliveryDays(String region, List<DeliveryTimes> deliveryTimesList) {

        int deliveryDays=0;
        for (DeliveryTimes deliveryTimes : deliveryTimesList) {
            deliveryDays=deliveryTimes.getDays();
            if (deliveryTimes.getRegion().equals(region)) {
                productDeliveryDays = deliveryDays;
                //to find the longest delivery days per product
                if (maxDeliveryDays < deliveryDays) {
                    maxDeliveryDays = deliveryDays;
                }
                //to initialize the minDeliveryDays variable
                if(minDeliveryDays == -1){
                    minDeliveryDays = deliveryDays;
                }
                //to find the shortest delivery date per product
                if(minDeliveryDays > deliveryDays){
                    minDeliveryDays = deliveryDays;
                }
            }
        }
    }
    private Map<String, Integer> sortByValue(Map<String, Integer> unsortMap) {


            //Convert Map to List of Map
            List<Map.Entry<String, Integer>> list =
                    new LinkedList<Map.Entry<String, Integer>>(unsortMap.entrySet());

            //Sort list with Collections.sort(), provide a custom Comparator
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                public int compare(Map.Entry<String, Integer> o1,
                                   Map.Entry<String, Integer> o2) {
                    return (o2.getValue()).compareTo(o1.getValue());
                }
            });

            //Loop the sorted list and put it into a new insertion order Map LinkedHashMap
            Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
            for (Map.Entry<String, Integer> entry : list) {
                sortedMap.put(entry.getKey(), entry.getValue());
            }


            return sortedMap;
        }


}
