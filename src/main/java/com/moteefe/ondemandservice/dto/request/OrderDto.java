package com.moteefe.ondemandservice.dto.request;

import com.moteefe.ondemandservice.dto.request.BasketDto;

public class OrderDto {
    private String region;
    private BasketDto basket;

    public OrderDto() {
    }

    public BasketDto getBasket() {
        return basket;
    }

    public void setBasket(BasketDto basket) {
        this.basket = basket;
    }


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }


}
