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


    public void checkWeightLimit(double[] itemWeights) {
    double total = 0;
    for (int i = 0; i < itemWeights.length; i++) {
        total = total + itemWeights[i];
    }
    if (total > 20) {
        System.out.println("Warning! U canot exeed the limit!");
    } else {
        System.out.println("Total weight " + total + "kg is within the limit.");
    }

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
