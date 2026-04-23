package org.hotel;

public class FrontDeskStaff extends Staff {
    public FrontDeskStaff(String staffId, String name, String department) {
        super(staffId, name, department);
    }

    @Override
    public void performDuties() {
        System.out.println(name + " is checking in guests and managing reservations.");
    }

    public void greet() {
        System.out.println("Hello! Welcome to the hotel. My name is " + name + ".");
    }

    public void greet(String guestName) {
        System.out.println("Hello " + guestName + "! Welcome to the hotel. My name is " + name + ".");
    }

    @Override
    public String toString() {
        return "FrontDeskStaff{" +
                "staffId='" + getStaffId() + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}

