package org.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        Hotel hotel = new Hotel("Hilton Hotel");


        Room r1 = new Room("701", "Standard", new BigDecimal("50.00"));
        Room r2 = new Room("702", "Deluxe", new BigDecimal("100.00"));
        Room r3 = new Room("703", "Suite", new BigDecimal("200.00"));
        hotel.addRoom(r1);
        hotel.addRoom(r2);
        hotel.addRoom(r3);


        Guest g1 = new Guest("G1", "Besart", "Sinani", "besartsinani@gmail.com");
        Guest g2 = new Guest("G2", "Meriton", "Ademi", "meritonademi@gmail.com");
        Guest g3 = new Guest("G3", "Ali", "Amzai", "aliamzai@gmail.com");

        try {


            hotel.makeBooking("B1", r1, g1,
                    LocalDate.of(2026, 4, 22),
                    LocalDate.of(2026, 4, 27));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.out.println("Bookingg failed : " + e.getMessage());
        }

        try {
            hotel.makeBooking("B2", r2, g2,
                    LocalDate.of(2026, 4, 15),
                    LocalDate.of(2026, 4, 20));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }


        // Ali tries room 701 — already booked, should fail
        try {
            hotel.makeBooking("B3", r1, g3,
                    LocalDate.of(2026, 4, 23),
                    LocalDate.of(2026, 4, 25));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        hotel.cancelBooking("B1");


        // Ali books room 701 — free after cancelation
        try {
            hotel.makeBooking("B4", r1, g3,
                    LocalDate.of(2026, 4, 22),
                    LocalDate.of(2026, 4, 27));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }


        hotel.displayRooms();
        hotel.displayBookings();

        // Cost calculation
        Booking testB1 = new Booking("B1", r1, g1,
                LocalDate.of(2026, 4, 22),
                LocalDate.of(2026, 4, 27));
        System.out.println("B1 total cost (5 nights x 50.00): "
                + testB1.calculateTotalCost());

        // fail — invalid dates
        try {
            hotel.makeBooking("B5", r3, g1,
                    LocalDate.of(2026, 6, 10),
                    LocalDate.of(2026, 6, 5));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }


        // fail — overlapping dates
        try {
            hotel.makeBooking("B6", r2, g1,
                    LocalDate.of(2026, 4, 17),
                    LocalDate.of(2026, 4, 19));
        } catch (InvalidBookingDatesException | RoomUnavailableException e) {
            System.out.println("Booking failed: " + e.getMessage());
        }

        // servicess
        HotelService roomService = new RoomService("S1", "Room Dining",
                new BigDecimal("20.00"), new BigDecimal("5.00"));
        HotelService spa = new SpaTreatment("S2", "Full Body Massage",
                new BigDecimal("80.00"), new BigDecimal("0.20"));
        HotelService laundry = new LaundryService("S3", "Laundry Service",
                new BigDecimal("10.00"), 5, new BigDecimal("2.00"));
        hotel.addService(roomService);
        hotel.addService(spa);
        hotel.addService(laundry);

        // staff
        Staff frontDesk = new FrontDeskStaff("ST1", "Alice", "Front Desk");
        Staff housekeeper = new HousekeepingStaff("ST2", "Bob", "Housekeeping");
        Staff manager = new Manager("ST3", "Jhon", "Management", 10);
        hotel.addStaff(frontDesk);
        hotel.addStaff(housekeeper);
        hotel.addStaff(manager);


        hotel.displayServices();
        hotel.displayStaff();

        /// runtime polymorphism — same method, different result per service
        System.out.println("=== Service Costs ===");
        for (HotelService service : hotel.getServices()) {
            System.out.println(service.getDescription()
                    + ": " + service.calculateFinalCost());
        }

// runtime polymorphism — each role implements performDuties differently
        System.out.println("=== Staff Duties ===");
        for (Staff member : hotel.getStaff()) {
            member.performDuties();
        }

// compile-time polymorphism — method overloading
        System.out.println("=== Greeting ===");
        FrontDeskStaff frontDeskStaff = new FrontDeskStaff("ST4", "Diana", "Front Desk");
        frontDeskStaff.greet();
        frontDeskStaff.greet("Besart");

// Chargeable interface — works with any chargeable item
        System.out.println("=== Total Charges Demo ===");
        List<Chargeable> bill = new ArrayList<>();
        bill.add((Chargeable) roomService);
        bill.add((Chargeable) spa);
        System.out.println("Room Dining + Spa total: "
                + hotel.calculateTotalCharges(bill));


        // ServiceNotFoundException demo (bonus)
        System.out.println("=== Service Lookup Demo ===");
        try {
            HotelService found = hotel.getServiceById("S999");
        } catch (ServiceNotFoundException e) {
            System.out.println("Lookup failed: " + e.getMessage());
        }
    }


}