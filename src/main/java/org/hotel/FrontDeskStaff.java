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

    public void handleComplaints(String[] complaints) {

        for (int i = 0; i < complaints.length; i++){

        switch (complaints[i]) {
            case "wifi":
                System.out.println("IT team notified.");
                break;
            case "noise":
                System.out.println("Security notified.");
                break;
            case "cleanliness":
                System.out.println("Housekeeping team notified.");
                break;
            default:
                System.out.println("Manager notified.");
        }

        }
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

