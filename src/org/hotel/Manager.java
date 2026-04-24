package org.hotel;

public class Manager extends Staff {

    private int staffCount;

    public Manager(String staffId, String name, String department, int staffCount) {
        super(staffId, name, department);
        this.staffCount = staffCount;
    }


    @Override
    public void performDuties() {
        System.out.println("Hi, I'm " + name + ". I manage the "
                + department + " department with " + staffCount + " staff.");
    }

    public void conductMeeting(String topic) {
        System.out.println(name + " is running a team meeting on: " + topic);
    }

    @Override
    public String toString() {
        return "Manager{" +
                "staffId='" + getStaffId() + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", staffCount=" + staffCount +
                '}';
    }
}
