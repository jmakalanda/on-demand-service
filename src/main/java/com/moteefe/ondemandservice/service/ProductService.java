package com.moteefe.ondemandservice.service;

import com.moteefe.ondemandservice.model.Product;
import com.moteefe.ondemandservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void save(Product product){
        productRepository.save(product);
    }

    public List<Product> getAllByNameAndInStockNotOrderByInStockDesc(String product, Integer value){
        return productRepository.getAllByNameAndInStockNotOrderByInStockDesc(product,value);
    }
    public List<Product> getAllByProductsFromRelevantSuppliers(List<String> suppliersNames,String productName){
        List<Product> products = new ArrayList<>();
        for(String suppliersName: suppliersNames ){
            products.addAll(getAllByProductAndSupplier(productName,suppliersName));
        }
        return products;
    }

    public List<Product> getAllByProductAndSupplier(String product, String supplier){
        return productRepository.queryBy(product,supplier);
    }
}
