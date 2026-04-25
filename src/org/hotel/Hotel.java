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
        rooms.add(room);
    }

    public void addRoomSafe(Room room) throws DuplicateRoomException {
        for (Room existing : rooms) {
            if (existing.getRoomNumber().equals(room.getRoomNumber())) {
                throw new DuplicateRoomException(
                        "Room " + room.getRoomNumber() + " already exists.");
            }
        }
        rooms.add(room);
        System.out.println("Room " + room.getRoomNumber() + " added successfully.");
    }

    public void makeBooking(String bookingId, Room room, Guest guest,
                            LocalDate checkIn, LocalDate checkOut)
            throws InvalidBookingDatesException, RoomUnavailableException {

        //custom exceptions (bonus)
        if (!checkOut.isAfter(checkIn)) {
            throw new InvalidBookingDatesException(
                    "Invalid dates for booking " + bookingId +
                            ": check-out must be after check-in.");
        }

        if (!isRoomAvailableForDates(room, checkIn, checkOut)) {
            throw new RoomUnavailableException(
                    "Room " + room.getRoomNumber() +
                            " is not available for the selected dates.");
        }

        Booking booking = new Booking(bookingId, room, guest, checkIn, checkOut);
        bookings.add(booking);
        room.setAvailable(false);
        System.out.println("Booking confirmed: " + bookingId);

        // returning (before bonus)
        // if (!checkOut.isAfter(checkIn)) {
        //     System.out.println("Invalid dates! Check-out must be after check-in");
        //     return;
        // }
        // if (!isRoomAvailableForDates(room, checkIn, checkOut)) {
        //     System.out.println("Room " + room.getRoomNumber() + " is not available.");
        //     return;
        // }
        // Booking booking = new Booking(bookingId, room, guest, checkIn, checkOut);
        // bookings.add(booking);
        // room.setAvailable(false);
        // System.out.println("Booking confirmed: " + bookingId);
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

    public HotelService getServiceById(String serviceId)
            throws ServiceNotFoundException {
        for (HotelService service : services) {
            if (service.getServiceId().equals(serviceId)) {
                return service;
            }
        }
        throw new ServiceNotFoundException(
                "Service with ID '" + serviceId + "' not found.");
    }

    public void addService(HotelService service) {
        services.add(service);
    }

    public void addStaff(Staff member) {
        staff.add(member);
    }

    public void displayRooms() {
        System.out.println("=== Rooms in " + name + " ===");
        for (Room room : rooms) {
            System.out.println(room);
        }
    }

    public void displayBookings() {
        System.out.println("=== Active Bookings in " + name + " ===");
        for (Booking booking : bookings) {
            System.out.println(booking);
        }
    }

    public void printBookedRoomsForUser(User user) {
        System.out.println("=== Booked rooms for " + user.getName() + " ===");

        for (Booking booking : bookings) {
            if (booking.getGuest().getGuestId().equals(user.getUserId())) {
                System.out.println(booking.getRoom());
            }
        }
    }

    public BigDecimal calculateUserServiceCost(HotelService[] services, User user) {
        BigDecimal total = BigDecimal.ZERO;
        for (int i = 0; i < services.length; i++) {
            if (services[i].getAssignedUserId() != null &&
                    services[i].getAssignedUserId().equals(user.getUserId())) {
                total = total.add(services[i].calculateFinalCost());
            }
        }
        System.out.println("Total service cost for " + user.getName() + ": " + total);
        return total;
    }

    public void displayServices() {
        System.out.println("=== Services in " + name + " ===");
        for (HotelService service : services) {
            System.out.println(service);
        }
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

    public String getName() {
        return name;
    }

    public List<HotelService> getServices() {
        return services;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    private boolean isRoomAvailableForDates(Room room,
                                            LocalDate checkIn,
                                            LocalDate checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoom().equals(room)) {
                boolean overlaps = checkIn.isBefore(booking.getCheckOut())
                        && checkOut.isAfter(booking.getCheckIn());
                if (overlaps) {
                    return false;
                }
            }
        }
        return true;
    }
}