package managementsystempackage.model;

import java.util.Date;

public class Task  {

    private int taskID;
    private String taskName;
    private Date dueDate;
    private boolean completed;

    public Task(int taskID,String taskName,Date dueDate)
    {
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
