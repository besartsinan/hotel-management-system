package org.hotel;

import java.math.BigDecimal;

public abstract class HotelService implements Chargeable{
    private String serviceId;
    private String description;
    protected BigDecimal baseCost;

    public HotelService(String serviceId, String description, BigDecimal baseCost) {
        this.serviceId = serviceId;
        this.description = description;
        this.baseCost = baseCost;

    }

    @Override
    public BigDecimal getCost(){
        return calculateFinalCost();
    }


    public abstract BigDecimal calculateFinalCost();



    public String getServiceId() {
        return serviceId;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getBaseCost() {
        return baseCost;
    }

    @Override
    public String toString() {
        return "HotelService{" +
                "serviceId='" + serviceId + '\'' +
                ", description='" + description + '\'' +
                ", baseCost=" + baseCost +
                '}';
    }
}
