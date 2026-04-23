package org.hotel;

public abstract class Staff {

    private String staffId;
    protected String name;
    protected String department;

    public Staff(String staffId, String name, String department) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;

    }

    public abstract void performDuties();

    public String getStaffId() {
        return staffId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId='" + staffId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                '}';
    }
}
