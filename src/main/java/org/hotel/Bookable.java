package org.hotel;

import java.time.LocalDate;

public interface Bookable {
    boolean isBookedForDates(LocalDate checkIn, LocalDate checkOut);
    void markAsBooked();
}
