package com.moteefe.ondemandservice.dto.request;

import com.moteefe.ondemandservice.dto.common.ItemDto;

import java.util.ArrayList;
import java.util.List;

public class BasketDto {
    private List<ItemDto> items;

    public BasketDto() {
        items = new ArrayList<>();
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
