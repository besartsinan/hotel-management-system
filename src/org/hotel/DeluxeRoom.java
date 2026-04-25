package org.hotel;

import java.math.BigDecimal;

public class DeluxeRoom extends Room {

    private int bedCapacity;

    public DeluxeRoom(String roomNumber, BigDecimal rate, int bedCapacity) {
        super(roomNumber, "Deluxe", rate);
        this.bedCapacity = bedCapacity;
    }


    public boolean checkCapacityViolation(int[] occupancyPerNight) {

        for (int i = 0; i < occupancyPerNight.length; i++) {
            if (occupancyPerNight[i] > bedCapacity) {
            }
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "DeluxeRoom{" +
                "roomNumber='" + getRoomNumber() + '\'' +
                ", rate=" + getRate() +
                ", bedCapacity=" + bedCapacity +
                ", available=" + isAvailable() +
                '}';
    }
}