package com.moteefe.ondemandservice.dto.common;

public class ItemDto {
    private String product;
    private Integer count;

    public ItemDto(String product, Integer count) {
        this.product = product;
        this.count = count;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


}
