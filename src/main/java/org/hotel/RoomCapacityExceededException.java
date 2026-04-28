package org.hotel;

import java.util.concurrent.ExecutionException;

public class RoomCapacityExceededException extends Exception {

    public RoomCapacityExceededException() {
        super("Room capacity exceded");
    }

    public RoomCapacityExceededException(String message) {
        super(message);
    }
}
