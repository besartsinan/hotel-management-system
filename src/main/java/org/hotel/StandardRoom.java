package org.hotel;

import java.math.BigDecimal;

public class StandardRoom extends Room{

    public StandardRoom(String roomNumber, BigDecimal rate){
        super(roomNumber,"Standard", rate);

    }

    public void applyDiscountCodes (char[] discountCodes) {
        for (int i=0; i <discountCodes.length; i++) {
            switch (discountCodes[i]) {
                case 'A':
                    BigDecimal discountA = getRate().multiply(new BigDecimal("0.10"));
                    setRate(getRate().subtract(discountA));
                    System.out.println("Code A applied 10% discount. New rate: " +getRate());
                    break;
                case 'B':
                    BigDecimal discountB = getRate().multiply(new BigDecimal("0.20"));
                    setRate(getRate().subtract(discountB));
                    System.out.println("Code B applied 20% discount. New rate: " +getRate());
                    break;
                case 'C':
                    BigDecimal discountC = getRate().multiply(new BigDecimal("0.30"));
                    setRate(getRate().subtract(discountC));
                    System.out.println("Code C applied 30% discount. New rate: " +getRate());
                    break;

            }

        }


    }

    @Override
    public String toString() {
        return "StandardRoom{" +
                "roomNumber='" + getRoomNumber() + '\'' +
                ", rate=" + getRate() +
                ", available=" + isAvailable() +
                '}';
    }

}
