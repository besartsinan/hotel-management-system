package org.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Room implements Bookable {
    private String roomNumber;
    private String type;
    private BigDecimal rate;
    private boolean available;
    private User bookedBy;
    private int capacity;
    private Map<LocalDate, Boolean> occupancyMap;


    public Room() {
        this("UNKNOWN", "Standard", BigDecimal.ZERO, 2);
    }

    public Room(String roomNumber, String type, BigDecimal rate) {
        this(roomNumber, type, rate, 2);
    }


    public Room(String roomNumber, String type, BigDecimal rate, int capacity) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.rate = rate;
        this.available = true;
        this.capacity = capacity;
        this.occupancyMap = new HashMap<>();
    }

    public User getBookedBy() {
        return bookedBy;
    }

    public void setBookedBy(User bookedBy) {
        this.bookedBy = bookedBy;
    }

    public int getCapacity() {
        return capacity;
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

    public void bookDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate date = checkIn;
        while (date.isBefore(checkOut)) {
            occupancyMap.put(date, true);
            date = date.plusDays(1);
        }
    }

    public void freeDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate date = checkIn;
        while (date.isBefore(checkOut)) {
            occupancyMap.put(date, false);
            date = date.plusDays(1);
        }
    }

    public boolean isAvailableForDates(LocalDate checkIn, LocalDate checkOut) {
        LocalDate date = checkIn;
        while (date.isBefore(checkOut)) {
            if (occupancyMap.containsKey(date) && occupancyMap.get(date)) {
                return false;
            }
            date = date.plusDays(1);
        }
        return true;
    }

    public boolean isBookedForDates(LocalDate checkIn, LocalDate checkOut) {
        return !isAvailableForDates(checkIn, checkOut);
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

