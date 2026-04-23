package org.hotel;

import java.math.BigDecimal;

public class SpaTreatment extends HotelService {

    private BigDecimal luxuryTaxRate;

    public SpaTreatment(String serviceId, String description, BigDecimal baseCost, BigDecimal luxuryTaxRate) {
        super(serviceId, description, baseCost);
        this.luxuryTaxRate = luxuryTaxRate;

    }

    @Override
    public BigDecimal calculateFinalCost() {
        BigDecimal tax = baseCost.multiply(luxuryTaxRate);
        return baseCost.add(tax);
    }

    @Override
    public String toString() {
        return "SpaTreatment{" +
                "serviceId='" + getServiceId() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", baseCost=" + getBaseCost() +
                ", luxuryTaxRate=" + luxuryTaxRate +
                ", finalCost=" + calculateFinalCost() +
                '}';
    }
}
