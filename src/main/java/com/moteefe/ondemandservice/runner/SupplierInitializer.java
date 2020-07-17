package com.moteefe.ondemandservice.runner;

import com.moteefe.ondemandservice.model.DeliveryTimes;
import com.moteefe.ondemandservice.model.Product;
import com.moteefe.ondemandservice.model.Supplier;
import com.moteefe.ondemandservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SupplierInitializer implements CommandLineRunner {
    @Autowired
    private SupplierService service;


    @Override
    public void run(String... args) {
        DeliveryTimes deliveryTimesEU;
        DeliveryTimes deliveryTimesUS;
        DeliveryTimes deliveryTimesUK;

        // Shirts4U inventory initialisation
        deliveryTimesEU= new DeliveryTimes("eu",1);
        deliveryTimesUS= new DeliveryTimes("us",6);
        deliveryTimesUK= new DeliveryTimes("uk",2);
        List<DeliveryTimes> deliveryTimesBlackMug = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productBlackMug = new Product("black_mug", deliveryTimesBlackMug,3);
        deliveryTimesEU.setProduct(productBlackMug);
        deliveryTimesUS.setProduct(productBlackMug);
        deliveryTimesUK.setProduct(productBlackMug);

        deliveryTimesEU= new DeliveryTimes("eu",2);
        deliveryTimesUS= new DeliveryTimes("us",6);
        deliveryTimesUK= new DeliveryTimes("uk",3);
        List<DeliveryTimes> deliveryTimesPinkTShirt = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productPinkTShirt = new Product("pink_t-shirt", deliveryTimesPinkTShirt,8);
        deliveryTimesEU.setProduct(productPinkTShirt);
        deliveryTimesUS.setProduct(productPinkTShirt);
        deliveryTimesUK.setProduct(productPinkTShirt);

        deliveryTimesEU= new DeliveryTimes("eu",2);
        deliveryTimesUS= new DeliveryTimes("us",6);
        deliveryTimesUK= new DeliveryTimes("uk",3);
        List<DeliveryTimes> deliveryTimesBlueTShirt = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productBlueTShirt = new Product("blue_t-shirt", deliveryTimesBlueTShirt,8);
        deliveryTimesEU.setProduct(productBlueTShirt);
        deliveryTimesUS.setProduct(productBlueTShirt);
        deliveryTimesUK.setProduct(productBlueTShirt);

        Supplier supplierShirts4U = new Supplier("Shirts4U", Arrays.asList(productBlackMug,productPinkTShirt, productBlueTShirt));
        productBlackMug.setSupplier(supplierShirts4U);
        productPinkTShirt.setSupplier(supplierShirts4U);
        productBlueTShirt.setSupplier(supplierShirts4U);


        // Best T-shirts inventory initialisation
        deliveryTimesEU= new DeliveryTimes("eu",1);
        deliveryTimesUS= new DeliveryTimes("us",5);
        deliveryTimesUK= new DeliveryTimes("uk",2);
        List<DeliveryTimes> deliveryTimesBestBlueTShirt = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productBestBlueTShirt = new Product("blue_t-shirt", deliveryTimesBestBlueTShirt,10);
        deliveryTimesEU.setProduct(productBestBlueTShirt);
        deliveryTimesUS.setProduct(productBestBlueTShirt);
        deliveryTimesUK.setProduct(productBestBlueTShirt);

        deliveryTimesEU= new DeliveryTimes("eu",1);
        deliveryTimesUS= new DeliveryTimes("us",3);
        deliveryTimesUK= new DeliveryTimes("uk",2);
        List<DeliveryTimes> deliveryTimesBestPinkTShirt = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productBestPinkTShirt = new Product("pink_t-shirt", deliveryTimesBestPinkTShirt,2);
        deliveryTimesEU.setProduct(productBestPinkTShirt);
        deliveryTimesUS.setProduct(productBestPinkTShirt);
        deliveryTimesUK.setProduct(productBestPinkTShirt);

        Supplier supplierBestTShirts = new Supplier("Best T-shirts", Arrays.asList(productBestBlueTShirt,productBestPinkTShirt));
        productBestBlueTShirt.setSupplier(supplierBestTShirts);
        productBestPinkTShirt.setSupplier(supplierBestTShirts);


        //Shirts Unlimited  inventory initialisation
        deliveryTimesEU= new DeliveryTimes("eu",1);
        deliveryTimesUS= new DeliveryTimes("us",8);
        deliveryTimesUK= new DeliveryTimes("uk",2);
        List<DeliveryTimes> deliveryTimesSUnlimitedWhiteMug = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productSUnlimitedBlueTShirt = new Product("white_mug", deliveryTimesSUnlimitedWhiteMug,3);
        deliveryTimesEU.setProduct(productSUnlimitedBlueTShirt);
        deliveryTimesUS.setProduct(productSUnlimitedBlueTShirt);
        deliveryTimesUK.setProduct(productSUnlimitedBlueTShirt);

        deliveryTimesEU= new DeliveryTimes("eu",1);
        deliveryTimesUS= new DeliveryTimes("us",7);
        deliveryTimesUK= new DeliveryTimes("uk",2);
        List<DeliveryTimes> deliveryTimesSUnlimitedBlackMug = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productSUnlimitedPinkTShirt = new Product("black_mug", deliveryTimesSUnlimitedBlackMug,4);
        deliveryTimesEU.setProduct(productSUnlimitedPinkTShirt);
        deliveryTimesUS.setProduct(productSUnlimitedPinkTShirt);
        deliveryTimesUK.setProduct(productSUnlimitedPinkTShirt);

        Supplier supplierSUnlimitedTShirts = new Supplier("Shirts Unlimited", Arrays.asList(productSUnlimitedBlueTShirt,productSUnlimitedPinkTShirt));
        productSUnlimitedBlueTShirt.setSupplier(supplierSUnlimitedTShirts);
        productSUnlimitedPinkTShirt.setSupplier(supplierSUnlimitedTShirts);

        //MyT-shirt  inventory initialisation
        deliveryTimesEU= new DeliveryTimes("eu",2);
        deliveryTimesUS= new DeliveryTimes("us",5);
        deliveryTimesUK= new DeliveryTimes("uk",1);
        List<DeliveryTimes> deliveryTimesMyTShirtWhiteHoodie = new ArrayList<>(Arrays.asList(
                deliveryTimesEU,
                deliveryTimesUS,
                deliveryTimesUK));
        Product productMyTShirtWhiteHoodie = new Product("white_hoodie", deliveryTimesMyTShirtWhiteHoodie,3);
        deliveryTimesEU.setProduct(productMyTShirtWhiteHoodie);
        deliveryTimesUS.setProduct(productMyTShirtWhiteHoodie);
        deliveryTimesUK.setProduct(productMyTShirtWhiteHoodie);

        Supplier supplierMyTShirtWhiteHoodie = new Supplier("MyT-shirt", Arrays.asList(productMyTShirtWhiteHoodie));
        productMyTShirtWhiteHoodie.setSupplier(supplierMyTShirtWhiteHoodie);

        service.saveAll(Arrays.asList(supplierShirts4U,supplierBestTShirts,supplierSUnlimitedTShirts,supplierMyTShirtWhiteHoodie ));

    }
}
