package managementsystempackage.model;

import java.util.Date;

public class Task extends User {

    private static final String password = "" ;
    private static final String username = "";
    private static final int userID = 0;
    private int taskID;
    private String taskName;
    private Date dueDate;
    private boolean completed;

    public Task(int taskID,String taskName,Date dueDate)
    {
        super(userID,username,password);

        this.taskID = taskID;
        this.taskName = taskName;
        this.dueDate =dueDate;
        this.completed = false;
    }

    public int getTaskID() {
        return taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setTaskID(int taskID) {
        this.taskID = taskID;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
