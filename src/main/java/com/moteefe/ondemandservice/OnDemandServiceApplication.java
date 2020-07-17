package com.moteefe.ondemandservice;

import com.moteefe.ondemandservice.model.Product;
import com.moteefe.ondemandservice.model.Supplier;
import com.moteefe.ondemandservice.repository.SupplierRepository;
import com.moteefe.ondemandservice.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class OnDemandServiceApplication extends SpringBootServletInitializer {


    public static void main(String[] args) {
        SpringApplication.run(OnDemandServiceApplication.class, args);


    }

}
