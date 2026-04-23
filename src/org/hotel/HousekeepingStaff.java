package org.hotel;

public class HousekeepingStaff extends Staff {

    public HousekeepingStaff(String staffId, String name, String department) {
        super(staffId, name, department);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is cleaning rooms and restocking supplies.");
    }

    public int countRoomsCleaned(int totalRooms, int dirtyRooms) {
        System.out.println(name + " cleaned " + dirtyRooms + " out of " + totalRooms + " rooms.");
        return dirtyRooms;
    }

    @Override
    public String toString() {
        return "HousekeepingStaff{" +
                "staffId='" + getStaffId() + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}