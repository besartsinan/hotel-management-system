package org.hotel;

public class BookingManager {

    public Room getFirstUnbookedRoom (Room[] rooms){
        for (int i = 0; i < rooms.length ; i++) {
            if (rooms[i].isAvailable()) {
                return rooms[i];
            }
        }
        System.out.println("All rooms occupied!");
        return null;
    }

}
