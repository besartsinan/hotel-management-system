package org.hotel;

import java.time.LocalDate;

public class BookingManager {


    public void validateBookingDates(String[] checkInDates, String[] checkOutDates)
            throws InvalidBookingDatesException {
        for (int i = 0; i < checkInDates.length; i++) {
            LocalDate checkIn = LocalDate.parse(checkInDates[i]);
            LocalDate checkOut = LocalDate.parse(checkOutDates[i]);
            if (!checkOut.isAfter(checkIn)) {
                throw new InvalidBookingDatesException(
                        "Invalid dates at index " + i +
                                ": check-out " + checkOutDates[i] +
                                " is not after check-in " + checkInDates[i]);
            }
        }
        System.out.println("All booking dates are valid.");
    }

    public Room getFirstUnbookedRoom (Room[] rooms){
        for (int i = 0; i < rooms.length ; i++) {
            if (rooms[i].isAvailable()) {
                return rooms[i];
            }
        }
        System.out.println("All rooms occupied!");
        return null;
    }

    public void printBookingUserNames(Room[] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            if (rooms[i].getBookedBy() != null) {
                System.out.println("Room " + rooms[i].getRoomNumber() +
                        " booked by: " + rooms[i].getBookedBy().getName());
            }
        }
    }

}
