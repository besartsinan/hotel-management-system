package org.hotel;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        Hotel hotel = new Hotel("Hilton Hotel");

        Room r1 = new Room("701", "Standard", new BigDecimal("50.00"));
        Room r2 = new Room("702", "Deluxe", new BigDecimal("100.00"));
        Room r3 = new Room("703", "Suite", new BigDecimal("200.00"));
        hotel.addRoom(r1);
        hotel.addRoom(r2);
        hotel.addRoom(r3);

        Guest g1 = new Guest("G1", "Besart", "Sinani", "besartsinan@gmail.com");
        Guest g2 = new Guest("G2", "Meriton", "Ademi", "meritonademi@gmail.com");
        Guest g3 = new Guest("G3", "Ali", "Amzai", "alaamz@gmail.com");

        hotel.makeBooking("B1", r1, g1,
                LocalDate.of(2026, 4, 22),
                LocalDate.of(2026, 4, 27));

        hotel.makeBooking("B2", r2, g2,
                LocalDate.of(2026, 4, 15),
                LocalDate.of(2026, 4, 20));

        hotel.makeBooking("B3", r1, g3,
                LocalDate.of(2026, 4, 23),
                LocalDate.of(2026, 4, 25));

        hotel.cancelBooking("B1");

        hotel.makeBooking("B4", r1, g3,
                LocalDate.of(2026, 4, 22),
                LocalDate.of(2026, 4, 27));

        hotel.displayRooms();
        hotel.displayBookings();



        Booking testB1 = new Booking("B1", r1, g1,
                LocalDate.of(2026, 4, 22),
                LocalDate.of(2026, 4, 27));
        System.out.println("B1 total cost (5 nights x 50.00): " + testB1.calculateTotalCost());

        hotel.makeBooking("B5", r3, g1,
                LocalDate.of(2026, 6, 10),
                LocalDate.of(2026, 6, 5));
    }
}
