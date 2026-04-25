package org.hotel;

import java.math.BigDecimal;

public abstract class HotelService implements Chargeable{
    private String serviceId;
    private String description;
    protected BigDecimal baseCost;
    private String assignedUserId;


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

    public String getAssignedUserId() {
        return assignedUserId;
    }

    public void setAssignedUserId(String assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public void validateDiscountCodes(char[] codes) {
        for (int i = 0; i < codes.length; i++)
            if (codes[i] >= 'A' && codes[i] <= 'Z') {
                System.out.println("Valid code: " + codes[i]);
            } else {
                System.out.println("Invalid code: " + codes[i]);
            }
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
