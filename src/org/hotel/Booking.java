package org.hotel;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private String bookingId;
    private Room room;
    private Guest guest;
    private LocalDate checkIn;
    private  LocalDate checkOut;

    public Booking (String bookingId, Room room, Guest guest, LocalDate checkIn, LocalDate checkOut){
        this.bookingId = bookingId;
        this.room = room;
        this.guest = guest;
        this.checkIn = checkIn;
        this.checkOut = checkOut;

    }

    public String getBookingId() {
        return bookingId;
    }

    public Room getRoom() {
        return room;
    }

    public Guest getGuest() {
        return guest;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingId='" + bookingId + '\'' +
                ", room=" + room +
                ", guest=" + guest +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(bookingId, booking.bookingId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bookingId);
    }
}
