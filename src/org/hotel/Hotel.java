package org.hotel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Booking> bookings;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();


    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void displayRooms() {
        System.out.println("Rooms in " + name);
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void displayBookings() {
        System.out.println("Active Bookings in " + name);
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void makeBooking(String bookingId, Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        if (!room.isAvailable()) {
            System.out.println("Room " + room.getRoomNumber() + " is not available");
            return;
        }
        Booking booking = new Booking(bookingId, room, guest, checkIn, checkOut);
        bookings.add(booking);
        room.setAvailable(false);
        System.out.println("Booking confirmed: " + bookingId);
    }

    public void cancelBooking(String bookingId) {
        for (Booking booking : bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                bookings.remove(booking);
                booking.getRoom().setAvailable(true);
                System.out.println("Booking " + bookingId + " cancelled.");
                return;
            }
        }
        System.out.println("Booking " + bookingId + " not found.");
    }

    public String getName() {
        return name;
    }
}
