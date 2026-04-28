package org.hotel;

public class RoomUnavailableException extends Exception {

    public RoomUnavailableException() {
        super("Room is not available for the selected dates.");
    }

    public RoomUnavailableException(String message) {
        super(message);
    }
}