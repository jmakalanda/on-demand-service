package com.moteefe.ondemandservice.model;

import javax.persistence.*;

@Entity
public class DeliveryTimes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String region;
    Integer days;
    @ManyToOne
    @JoinColumn(name="product_id", nullable=false)
    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public DeliveryTimes() {
    }

    public DeliveryTimes(String region, Integer days) {
        this.region = region;
        this.days = days;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    @Override
    public String toString() {
        return "DeliveryTimes{" +
                "id=" + id +
                ", region='" + region + '\'' +
                ", days=" + days +
                '}';
    }
}
