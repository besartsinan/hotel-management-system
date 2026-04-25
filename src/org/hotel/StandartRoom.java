package org.hotel;

import java.math.BigDecimal;

public class StandartRoom extends Room{

    public StandartRoom(String roomNumber, BigDecimal rate){
        super(roomNumber,"Standart", rate);

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
                    System.out.println("Code A applied 10% discount. New rate: " +getRate());
                    break;
                case 'C':
                    BigDecimal discountC = getRate().multiply(new BigDecimal("0.20"));
                    setRate(getRate().subtract(discountC));
                    System.out.println("Code A applied 10% discount. New rate: " +getRate());
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
