package org.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private String name;
    private List<Room> rooms;
    private List<Booking> bookings;
    private List<HotelService> services;
    private List<Staff> staff;

    public Hotel(String name) {
        this.name = name;
        this.rooms = new ArrayList<>();
        this.bookings = new ArrayList<>();
        this.services = new ArrayList<>();
        this.staff = new ArrayList<>();
    }

    public void addRoom(Room room) {
        this.rooms.add(room);
    }

    public void displayRooms() {
        System.out.println("Rooms in " + this.name);

        for(Room room : this.rooms) {
            System.out.println(room);
        }

    }

    public void displayBookings() {
        System.out.println("Active Bookings in " + this.name);

        for(Booking booking : this.bookings) {
            System.out.println(booking);
        }

    }

    public void makeBooking(String bookingId, Room room, Guest guest, LocalDate checkIn, LocalDate checkOut) {
        if (!checkOut.isAfter(checkIn)) {
            System.out.println("Invalid dates! Check-out must be after check-in");
        } else if (!this.isRoomAvailableForDates(room, checkIn, checkOut)) {
            System.out.println("Room " + room.getRoomNumber() + " is not available for the selected dates.");
        } else {
            Booking booking = new Booking(bookingId, room, guest, checkIn, checkOut);
            this.bookings.add(booking);
            room.setAvailable(false);
            System.out.println("Booking confirmed: " + bookingId);
        }
    }

    public void cancelBooking(String bookingId) {
        for(Booking booking : this.bookings) {
            if (booking.getBookingId().equals(bookingId)) {
                this.bookings.remove(booking);
                booking.getRoom().setAvailable(true);
                System.out.println("Booking " + bookingId + " cancelled.");
                return;
            }
        }

        System.out.println("Booking " + bookingId + " not found.");
    }

    public String getName() {
        return this.name;
    }

    public List<HotelService> getServices() {
        return services;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    private boolean isRoomAvailableForDates(Room room, LocalDate checkIn, LocalDate checkOut) {
        for(Booking booking : this.bookings) {
            if (booking.getRoom().equals(room)) {
                boolean overlaps = checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn());
                if (overlaps) {
                    return false;
                }
            }
        }

        return true;
    }

    public void addService(HotelService service) {
        services.add(service);
    }

    public void displayServices() {
        System.out.println("=== Services in " + name + " ===");
        for (HotelService service : services) {
            System.out.println(service);
        }
    }

    public void addStaff(Staff member) {
        staff.add(member);
    }

    public void displayStaff() {
        System.out.println("=== Staff in " + name + " ===");
        for (Staff member : staff) {
            System.out.println(member);
        }
    }

    public BigDecimal calculateTotalCharges(List<Chargeable> items) {
        BigDecimal total = BigDecimal.ZERO;
        for (Chargeable item : items) {
            total = total.add(item.getCost());
        }
        return total;
    }

}
