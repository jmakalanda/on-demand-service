package com.moteefe.ondemandservice.dto.response;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DeliveryDto {
    private Calendar deliveryDate;
    private List<SuppliesDto> shipments;

    public Calendar getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Calendar deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<SuppliesDto> getShipments() {
        return shipments;
    }

    public void setShipments(List<SuppliesDto> shipments) {
        this.shipments = shipments;
    }
}
