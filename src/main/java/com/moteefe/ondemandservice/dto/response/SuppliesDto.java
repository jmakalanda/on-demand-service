package com.moteefe.ondemandservice.dto.response;

import com.moteefe.ondemandservice.dto.common.ItemDto;

import java.util.Calendar;
import java.util.List;

public class SuppliesDto {
    private String supplier;
    private Calendar deliveryDate;
    private List<ItemDto> items;

    public SuppliesDto(String supplier, Calendar deliveryDate, List<ItemDto> items) {
        this.supplier = supplier;
        this.deliveryDate = deliveryDate;
        this.items = items;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<ItemDto> getItems() {
        return items;
    }

    public void setItems(List<ItemDto> items) {
        this.items = items;
    }
}
