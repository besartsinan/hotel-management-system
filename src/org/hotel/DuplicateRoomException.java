package org.hotel;

public class DuplicateRoomException extends Exception {

    public DuplicateRoomException() {
        super("A room with this number already exists.");
    }

    public DuplicateRoomException(String message) {
        super(message);
    }
}