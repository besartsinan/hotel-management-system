package org.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Room implements Bookable {
    private String roomNumber;
    private String type;
    private BigDecimal rate;
    private boolean available;

    public Room() {
        this("UNKNOWN", "Standard", BigDecimal.ZERO);
    }


    public Room(String roomNumber, String type, BigDecimal rate) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.rate = rate;
        this.available = true;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setRate(BigDecimal rate) {
        if (rate == null || rate.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Rate can't be negative");
            return;
        }
        this.rate = rate;
    }

    public void setAvailable(boolean available) {
        this.available = available;


    }

    public boolean hasThreeConsecutiveVacantDays(boolean[] weekOccupancy) {
        int count = 0;
        for (int i = 0; i < weekOccupancy.length; i++) {
            if (!weekOccupancy[i]) {
                count++;
                if (count >= 3) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }


    @Override
    public boolean isBookedForDates(LocalDate checkIn, LocalDate checkOut) {
        return !available;
    }

    @Override
    public void markAsBooked() {
        this.available = false;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber='" + roomNumber + '\'' +
                ", type='" + type + '\'' +
                ", rate=" + rate +
                ", available=" + available +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomNumber, room.roomNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roomNumber);
    }
}

