package org.hotel;

public abstract class Staff {

    private String staffId;
    protected String name;
    protected String department;
    private int tasksCompleted;

    public Staff(String staffId, String name, String department) {
        this.staffId = staffId;
        this.name = name;
        this.department = department;

    }

    public void incrementTasksCompleted() {
        tasksCompleted++;
    }

    public int getTasksCompleted() {
        return tasksCompleted;
    }

    public int getHighestPriorityTaskIndex (int[] taskPriorities){
        int bestIndex = 0;

        for (int i =1; i < taskPriorities.length; i++){
            if (taskPriorities[i] < taskPriorities[bestIndex]) {
                bestIndex = i;
            }

        }
        return bestIndex;
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
