package org.hotel;

import java.math.BigDecimal;

public class RoomService extends HotelService {

    private BigDecimal deliveryFee;

    public RoomService(String serviceId, String description, BigDecimal baseCost, BigDecimal deliveryFee) {
        super(serviceId, description, baseCost);
        this.deliveryFee = deliveryFee;

    }

    public void completeAllSteps(char[] steps) {
        int i = 0;
        while (i < steps.length) {
        steps[i] ='X';
        i++;
        }
        System.out.println("All steps completed.");

    }

    @Override
    public BigDecimal calculateFinalCost() {
        return baseCost.add(deliveryFee);
    }

    @Override
    public String toString() {
        return "RoomService{" +
                "serviceId='" + getServiceId() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", baseCost=" + getBaseCost() +
                ", deliveryFee=" + deliveryFee +
                ", finalCost=" + calculateFinalCost() +
                '}';
    }
}
