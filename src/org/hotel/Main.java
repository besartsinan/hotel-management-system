package org.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            System.out.println("Booking failed : " + e.getMessage());
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
        Staff manager = new Manager("ST3", "John", "Management", 10);
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


        // HW3

// Task 1 — hasThreeConsecutiveVacantDays
        System.out.println("\n=== Task 1: Consecutive Vacant Days ===");
        boolean[] week = {true, false, false, false, true, true, false};
        System.out.println("3 consecutive vacant days: " + r1.hasThreeConsecutiveVacantDays(week));

// Task 2 — buildGreeting
        System.out.println("\n=== Task 2: Build Greeting ===");
        User u1 = new User("U1", "Besart", "Manager");
        char[] template = {'H', 'e', 'l', 'l', 'o', ' ', '*', '#'};
        System.out.println(u1.buildGreeting(template));

// Task 3
        System.out.println("\n=== Task 3: Highest Priority Task ===");
        int[] priorities = {3, 1, 4, 1, 5};
        System.out.println("Highest priority index: " + frontDesk.getHighestPriorityTaskIndex(priorities));

// Task 4 — applyDiscountCodes
        System.out.println("\n=== Task 4: Discount Codes ===");
        StandardRoom sr = new StandardRoom("801", new BigDecimal("100.00"));
        char[] discounts = {'A', 'B', 'C'};
        sr.applyDiscountCodes(discounts);

// Task 5 — printBookedRoomsForUser
        System.out.println("\n=== Task 5: Booked Rooms For User ===");
        User u2 = new User("G3", "Ali", "Guest");
        hotel.printBookedRoomsForUser(u2);

// Task 6 — getFirstUnbookedRoom
        System.out.println("\n=== Task 6: First Unbooked Room ===");
        BookingManager bm = new BookingManager();
        Room[] roomArray = {r1, r2, r3};
        Room firstUnbooked = bm.getFirstUnbookedRoom(roomArray);
        System.out.println("First unbooked: " + (firstUnbooked != null ? firstUnbooked : "None"));

// Task 7 — calculateUserServiceCost
        System.out.println("\n=== Task 7: User Service Cost ===");
        roomService.setAssignedUserId("U1");
        HotelService[] serviceArray = {roomService, spa, laundry};
        hotel.calculateUserServiceCost(serviceArray, u1);

// Task 8 — countDirtyRooms
        System.out.println("\n=== Task 8: Dirty Rooms ===");
        HousekeepingStaff hs = new HousekeepingStaff("ST5", "Eve", "Housekeeping");
        char[] floorStatus = {'C', 'D', 'D', 'C', 'D'};
        System.out.println("Dirty rooms: " + hs.countDirtyRooms(floorStatus));

// Task 9 — handleComplaints
        System.out.println("\n=== Task 9: Handle Complaints ===");
        String[] complaints = {"wifi", "noise", "cleanliness", "food"};
        frontDeskStaff.handleComplaints(complaints);

// Task 10 — checkCapacityViolation
        System.out.println("\n=== Task 10: Capacity Violation ===");
        DeluxeRoom dr = new DeluxeRoom("802", new BigDecimal("150.00"), 3);
        int[] occupancy = {2, 3, 4, 1};
        System.out.println("Capacity violated: " + dr.checkCapacityViolation(occupancy));

// Task 11 — checkWeightLimit
        System.out.println("\n=== Task 11: Weight Limit ===");
        LaundryService ls = new LaundryService("S4", "Express Laundry",
                new BigDecimal("15.00"), 3, new BigDecimal("3.00"));
        double[] weights = {5.0, 8.0, 10.0};
        ls.checkWeightLimit(weights);

// Task 12 — completeAllSteps
        System.out.println("\n=== Task 12: Complete Steps ===");
        RoomService rs = new RoomService("S5", "Dinner",
                new BigDecimal("30.00"), new BigDecimal("5.00"));
        char[] steps = {'-', 'P', 'C', '-'};
        rs.completeAllSteps(steps);

// Task 13 — addRoomSafe
        System.out.println("\n=== Task 13: Add Room Safe ===");
        try {
            hotel.addRoomSafe(new Room("701", "Standard", new BigDecimal("50.00")));
        } catch (DuplicateRoomException e) {
            System.out.println("Error: " + e.getMessage());
        }

// Task 14 — printRoomsWithInvalidPricing
        System.out.println("\n=== Task 14: Invalid Pricing ===");
        Room[] checkRooms = {
                new Room("901", "Standard", BigDecimal.ZERO),
                new Room("902", "Deluxe", new BigDecimal("100.00"))
        };
        hotel.printRoomsWithInvalidPricing(checkRooms);

// Task 15 — validateDiscountCodes
        System.out.println("\n=== Task 15: Validate Discount Codes ===");
        char[] codes = {'A', 'b', 'Z', '3', 'M'};
        roomService.validateDiscountCodes(codes);

// Task 16 — printBookingUserNames
        System.out.println("\n=== Task 16: Booking User Names ===");
        r1.setBookedBy(u1);
        r2.setBookedBy(new User("U2", "Meriton", "Guest"));
        bm.printBookingUserNames(roomArray);

// Task 17 — checkRoomCapacities
        System.out.println("\n=== Task 17: Room Capacities ===");
        Room[] capacityRooms = {
                new Room("701", "Standard", new BigDecimal("50.00"), 2),
                new Room("702", "Deluxe", new BigDecimal("100.00"), 5)
        };
        try {
            hotel.checkRoomCapacities(capacityRooms, 3);
        } catch (RoomCapacityExceededException e) {
            System.out.println("Error: " + e.getMessage());
        }

// Task 18 — validateBookingDates
        System.out.println("\n=== Task 18: Validate Booking Dates ===");
        String[] checkIns = {"2026-05-01", "2026-06-10"};
        String[] checkOuts = {"2026-05-07", "2026-06-05"};
        try {
            bm.validateBookingDates(checkIns, checkOuts);
        } catch (InvalidBookingDatesException e) {
            System.out.println("Error: " + e.getMessage());
        }

// Task 19 — applyTierDiscounts (static)
        System.out.println("\n=== Task 19: Tier Discounts ===");
        double[] costs = {100.0, 200.0, 150.0};
        char[] tiers = {'A', 'B', 'C'};
        HotelService.applyTierDiscounts(costs, tiers);

// Task 20 — checkDuplicateGuestBookings
        System.out.println("\n=== Task 20: Duplicate Guest Bookings ===");
        Room[] dupRooms = {r1, r2, r3};
        r1.setBookedBy(u1);
        r2.setBookedBy(u1);  // same user — should trigger exception
        try {
            hotel.checkDuplicateGuestBookings(dupRooms);
        } catch (DuplicateGuestBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }



            //hw4
        Guest g4 = new Guest("G4", "Sara", "Krasniqi", "sara@gmail.com");
        hotel.registerGuest(g4);
        hotel.registerGuest(g1);
        hotel.registerGuest(g4);
        System.out.println("Total unique guests: " + hotel.getTotalNumberOfGuests());


        System.out.println("\n=== Available Rooms ===");
        List<Room> available = hotel.getAllAvailableRooms();
        for (Room r : available) {
            System.out.println(r);
        }

        System.out.println("\n=== Available Rooms May 1-7 ===");
        List<Room> availableForDates = hotel.getAvailableRooms(
                LocalDate.of(2026, 5, 1),
                LocalDate.of(2026, 5, 7));
        for (Room r : availableForDates) {
            System.out.println(r);
        }

        System.out.println("\n=== Standard Rooms ===");
        List<Room> standardRooms = hotel.getRoomsByType("Standard");
        for (Room r : standardRooms) {
            System.out.println(r);
        }


        System.out.println("\n=== Booking Lookup ===");
        Booking found = hotel.getBookingById("B2");
        System.out.println("Found: " + found);

        Booking notFound = hotel.getBookingById("B999");
        System.out.println("Not found: " + notFound);

        System.out.println("\n=== All Guest Names ===");
        List<String> names = hotel.getAllGuestNames();
        for (String name : names) {
            System.out.println(name);
        }

        System.out.println("\n=== Total Revenue ===");
        System.out.println("Total: $" + hotel.calculateTotalRevenue());

        System.out.println("\n=== Bookings By Guest ===");
        Map<Guest, List<Booking>> byGuest = hotel.getBookingsByGuest();
        for (Map.Entry<Guest, List<Booking>> entry : byGuest.entrySet()) {
            System.out.println(entry.getKey().getFullName() +
                    " has " + entry.getValue().size() + " booking(s)");
        }


        System.out.println("\n=== Most Frequent Room Type ===");
        System.out.println("Most booked: " + hotel.getMostFrequentRoomTypeBooked());

        System.out.println("\n=== Guests With Multiple Bookings ===");
        Set<Guest> multipleBookers = hotel.getGuestsWithMultipleBookings();
        if (multipleBookers.isEmpty()) {
            System.out.println("No guests with multiple bookings.");
        } else {
            for (Guest g : multipleBookers) {
                System.out.println(g.getFullName());
            }
        }

        System.out.println("\n=== Room Occupancy ===");
        System.out.println("Room 701 available Apr 22-27: " +
                r1.isAvailableForDates(
                        LocalDate.of(2026, 4, 22),
                        LocalDate.of(2026, 4, 27)));
        System.out.println("Room 701 available May 1-7: " +
                r1.isAvailableForDates(
                        LocalDate.of(2026, 5, 1),
                        LocalDate.of(2026, 5, 7)));






        System.out.println("\n=== Staff Task Counts ===");
        frontDesk.incrementTasksCompleted();
        frontDesk.incrementTasksCompleted();
        housekeeper.incrementTasksCompleted();
        Map<Staff, Integer> taskCounts = hotel.getStaffTaskCounts();
        for (Map.Entry<Staff, Integer> entry : taskCounts.entrySet()) {
            System.out.println(entry.getKey().getName() +
                    ": " + entry.getValue() + " tasks");
        }

        System.out.println("\n=== Dynamic Pricing  ===");
        roomService.addDiscountCode("SUMMER26", 0.20);
        roomService.addDiscountCode("VIP10", 0.10);
        System.out.println("Before discount: " + roomService.getBaseCost());
        roomService.applyDiscount("SUMMER26");
        System.out.println("After SUMMER20: " + roomService.getBaseCost());
        roomService.applyDiscount("INVALID");



        System.out.println("\n=== Rooms With No Bookings ===");
        List<Room> roomsWithNoBookings = hotel.getRoomsWithNoBookings();

        for (Room room : roomsWithNoBookings) {
            System.out.println(room);
        }



        System.out.println("\n=== Earliest Unbooked Room ===");

        Room earliest = bm.getEarliestUnbookedRoom(
                roomArray,
                LocalDate.of(2026, 5, 1)
        );

        System.out.println("Earliest free room: " +
                (earliest != null ? earliest : "None"));

    }






}