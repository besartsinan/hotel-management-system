package org.hotel;

import java.math.BigDecimal;

public class RoomService extends HotelService {

    private BigDecimal deliveryFee;

    public RoomService(String serviceId, String description, BigDecimal baseCost, BigDecimal deliveryFee) {
        super(serviceId, description, baseCost);
        this.deliveryFee = deliveryFee;

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
