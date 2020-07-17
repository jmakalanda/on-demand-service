package com.moteefe.ondemandservice.repository;

import com.moteefe.ondemandservice.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query(value = "SELECT p FROM Product p WHERE p.name = :product AND  p.supplier.name = :supplier")
    List<Product> queryBy(@Param("product") String product, @Param("supplier") String supplier);

    List<Product> getAllByNameAndInStockNotOrderByInStockDesc(String name, Integer value);

}
