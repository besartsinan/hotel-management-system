package org.hotel;

public class DuplicateGuestBookingException extends Exception {

    public DuplicateGuestBookingException() {
        super("Duplicate guest booking found ");
    }

    public DuplicateGuestBookingException(String message) {
        super(message);
    }
}
