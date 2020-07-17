package com.moteefe.ondemandservice.controllers;

import com.moteefe.ondemandservice.dto.response.DeliveryDto;
import com.moteefe.ondemandservice.dto.request.OrderDto;
import com.moteefe.ondemandservice.dto.response.SuppliesDto;
import com.moteefe.ondemandservice.service.OrderDeliveryService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
public class OrderController {

    private OrderDeliveryService orderDeliveryService;

    public OrderController(OrderDeliveryService orderDeliveryService) {
        this.orderDeliveryService = orderDeliveryService;
    }

    @PostMapping(path = "/order")
    public DeliveryDto placeOrder(@RequestBody OrderDto order){

        return orderDeliveryService.getOrderDeliveryDetails(order);

    }
}
