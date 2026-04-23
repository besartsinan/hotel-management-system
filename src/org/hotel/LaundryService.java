package org.hotel;

import java.math.BigDecimal;

public class LaundryService extends HotelService {

    private int itemCount;
    private BigDecimal pricePerItem;

    public LaundryService(String serviceId, String description, BigDecimal baseCost, int itemCount, BigDecimal pricePerItem) {

        super(serviceId, description, baseCost);
        this.itemCount = itemCount;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public BigDecimal calculateFinalCost() {
        BigDecimal itemCosts = BigDecimal.valueOf(itemCount).multiply(pricePerItem);
        return baseCost.add(itemCosts);
    }

    @Override
    public String toString() {
        return "LaundryService{" +
                "serviceId='" + getServiceId() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", baseCost=" + getBaseCost() +
                ", itemCount=" + itemCount +
                ", pricePerItem=" + pricePerItem +
                ", finalCost=" + calculateFinalCost() +
                '}';
    }
}
