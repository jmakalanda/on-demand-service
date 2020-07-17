package com.moteefe.ondemandservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moteefe.ondemandservice.dto.common.ItemDto;
import com.moteefe.ondemandservice.dto.request.BasketDto;
import com.moteefe.ondemandservice.dto.request.OrderDto;
import com.moteefe.ondemandservice.dto.response.DeliveryDto;
import com.moteefe.ondemandservice.service.OrderDeliveryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import static org.assertj.core.api.Assertions.assertThat;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OrderDeliveryService orderDeliveryService;

    @Test
    void placeOrderMoc() throws Exception {
        OrderDto orderDto = new OrderDto();
        BasketDto basketDto = new BasketDto();
        basketDto.setItems(new ArrayList<>(Arrays.asList(new ItemDto("black_mug",1), new ItemDto("blue_t-shirt",2))));
        orderDto.setBasket(basketDto);
        orderDto.setRegion("us");

        mockMvc.perform(post("/order")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().isOk());

    }

/** Scenario 1
    Having a list of items containing product A with delivery time 3 days and product B
    with delivery time 1 day, then the delivery time is 3.*/

    @Test
    void placeOrderScenario1() {

        OrderDto orderDto = new OrderDto();
        BasketDto basketDto = new BasketDto();
        basketDto.setItems(new ArrayList<>(Arrays.asList(new ItemDto("black_mug",1), new ItemDto("white_mug",2))));
        orderDto.setBasket(basketDto);
        orderDto.setRegion("us");

        DeliveryDto deliveryDto = orderDeliveryService.getOrderDeliveryDetails(orderDto);

        //should be the same as the highest delivery date, in this case Supplier Shirts4U supplies black_mug later than Shirts Unlimited
        assertThat(deliveryDto.getShipments().get(0).getSupplier()).isEqualTo("Shirts4U");
        assertThat(deliveryDto.getShipments().get(0).getItems().get(0).getProduct()).isEqualTo("black_mug");
        assertThat(deliveryDto.getDeliveryDate().get(Calendar.DAY_OF_MONTH)).isEqualTo(deliveryDto.getShipments().get(0).getDeliveryDate().get(Calendar.DAY_OF_MONTH));
        assertThat(deliveryDto.getShipments().get(0).getDeliveryDate().get(Calendar.DAY_OF_MONTH)).isGreaterThan(deliveryDto.getShipments().get(1).getDeliveryDate().get(Calendar.DAY_OF_MONTH));
    }

/** Scenario 2
    Having a product A from two suppliers A and B. When supplier A delivers product A
    in 3 days and supplier B delivers product A in 2 days, then delivery time for that product is 2 days.*/
    @Test
    void placeOrderScenario2() {
        OrderDto orderDto = new OrderDto();
        BasketDto basketDto = new BasketDto();
        basketDto.setItems(new ArrayList<>(Arrays.asList( new ItemDto("blue_t-shirt",15))));
        orderDto.setBasket(basketDto);
        orderDto.setRegion("us");


        DeliveryDto deliveryDto = orderDeliveryService.getOrderDeliveryDetails(orderDto);

        //should be the same as the highest delivery date, in this case Supplier Shirts4U supplies black_mug later than Shirts Unlimited
        assertThat(deliveryDto.getShipments().get(0).getSupplier()).isEqualTo("Best T-shirts");
        assertThat(deliveryDto.getDeliveryDate().get(Calendar.DAY_OF_MONTH)).isEqualTo(deliveryDto.getShipments().get(0).getDeliveryDate().get(Calendar.DAY_OF_MONTH));
        assertThat(deliveryDto.getShipments().get(0).getDeliveryDate().get(Calendar.DAY_OF_MONTH)).isLessThan(deliveryDto.getShipments().get(1).getDeliveryDate().get(Calendar.DAY_OF_MONTH));

    }

/** Scenario 3
    Having a t-shirt and hoodie in the basket. When t-shirt can be shipped from
    supplier A and B and hoodie can be shipped from supplier B and C, then deliver the
    t-shirt and hoodie from supplier B

    Edge case: It's faster to deliver it separately*/
    @Test
    void placeOrderScenario3()  {
        OrderDto orderDto = new OrderDto();
        BasketDto basketDto = new BasketDto();
        basketDto.setItems(new ArrayList<>(Arrays.asList(new ItemDto("pink_t-shirt",2), new ItemDto("black_mug",3))));
        orderDto.setBasket(basketDto);
        orderDto.setRegion("us");

        DeliveryDto deliveryDto = orderDeliveryService.getOrderDeliveryDetails(orderDto);
        //"pink_t-shirt" are done by "Best T-shirts" and "Shirts4U"
        //"black_mug" are done by "Shirts Unlimited" and "Shirts4U"
        assertThat(deliveryDto.getShipments().get(0).getSupplier()).isEqualTo("Shirts4U");
    }

/** Scenario 4
    Having a 10 t-shirt in the basket and two suppliers A and B. When there are only 6
    T-shirts from supplier A and 7 t-shirts from supplier B on stock, then there would
    be two shipments. One from supplier A with 6 t-shirts and second from supplier B
    with 4 t-shirts.

    edge case: split it into 3*/
    @Test
    void placeOrderScenario4()  {
        OrderDto orderDto = new OrderDto();
        BasketDto basketDto = new BasketDto();
        basketDto.setItems(new ArrayList<>(Arrays.asList( new ItemDto("blue_t-shirt",16))));
        orderDto.setBasket(basketDto);
        orderDto.setRegion("us");

        DeliveryDto deliveryDto = orderDeliveryService.getOrderDeliveryDetails(orderDto);
        //  "Best T-shirts" has 10 "blue_t-shirt" in stock and "Shirts4U" has 8 so the shipment should have 10 and 6 respectively
        assertThat(deliveryDto.getShipments().get(0).getSupplier()).isEqualTo("Best T-shirts");
        assertThat(deliveryDto.getShipments().get(0).getItems().get(0).getCount()).isEqualTo(10);
        assertThat(deliveryDto.getShipments().get(1).getItems().get(0).getCount()).isEqualTo(6);
    }

}