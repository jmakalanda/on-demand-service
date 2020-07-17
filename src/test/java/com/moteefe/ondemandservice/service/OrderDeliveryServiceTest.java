package com.moteefe.ondemandservice.service;

import com.moteefe.ondemandservice.dto.common.ItemDto;
import com.moteefe.ondemandservice.dto.request.BasketDto;
import com.moteefe.ondemandservice.dto.request.OrderDto;
import com.moteefe.ondemandservice.dto.response.DeliveryDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderDeliveryServiceTest {

    @Autowired
    OrderDeliveryService orderDeliveryService;

    @Test
    void getOrderDeliveryDetails() {
        OrderDto orderDto = new OrderDto();
        BasketDto basketDto = new BasketDto();
        basketDto.setItems(new ArrayList<>(Arrays.asList(new ItemDto("black_mug",1), new ItemDto("pink_t-shirt",2))));
        orderDto.setBasket(basketDto);
        orderDto.setRegion("us");

        DeliveryDto orderDeliveryDetails = orderDeliveryService.getOrderDeliveryDetails(orderDto);

        assertEquals(orderDeliveryDetails.getShipments().get(0).getSupplier(),"Shirts4U");
    }
}