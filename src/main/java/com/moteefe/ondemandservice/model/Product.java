package com.moteefe.ondemandservice.model;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<DeliveryTimes> deliveryTimes; //holds the time taken for delivery in days per region/country
    private Integer inStock; // holds the number of items in stock
    @ManyToOne
    @JoinColumn(name="supplier_id", nullable=false)
    private Supplier supplier;

    public List<DeliveryTimes> getDeliveryTimes() {
        return deliveryTimes;
    }

    public void setDeliveryTimes(List<DeliveryTimes> deliveryTimes) {
        this.deliveryTimes = deliveryTimes;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public Product() {
    }

    public Product(String name, List<DeliveryTimes> deliveryTimes, Integer inStock) {
        this.name = name;
        this.deliveryTimes = deliveryTimes;
        this.inStock = inStock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

/*    public Map<String, Integer> getDeliveryTimes() {
        return deliveryTimes;
    }

    public void setDeliveryTimes(Map<String, Integer> deliveryTimes) {
        this.deliveryTimes = deliveryTimes;
    }*/

    public Integer getInStock() {
        return inStock;
    }

    public void setInStock(Integer inStock) {
        this.inStock = inStock;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", deliveryTimes=" + deliveryTimes +
                ", inStock=" + inStock +
                ", supplier=" + supplier +
                '}';
    }
}
