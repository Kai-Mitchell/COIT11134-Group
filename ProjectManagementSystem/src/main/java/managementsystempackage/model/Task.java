package managementsystempackage.model;
/**
 *
 * @author gomez
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Task implements Serializable{
    private int taskID;
    private int taskEventID;
    private String taskName;
    private Date dueDate;
    private boolean completed;
    ArrayList<User> taskUserList = new ArrayList<>();
    private static int nextTaskID;

    public Task(String taskName, int taskEventID,Date dueDate, ArrayList<User> taskUserList)
    {
        this.taskName = taskName;
        this.taskEventID = taskEventID;//Event to task refrence
        this.dueDate =dueDate;
        this.completed = false;
        this.taskUserList = taskUserList;//User to task reference
        setTaskID();
    }

    public int getTaskID() {
        return taskID;
    }
    
    public int getNextTaskID(){
        return nextTaskID;
    }
    
    public ArrayList<User> getUserList(){
        return taskUserList;
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

    public void setTaskID() {
        taskID = nextTaskID; // set id to next available id
        nextTaskID++;
    }
    
    public static void setNextTaskID(int nexttaskID)
   {
      Task.nextTaskID = nextTaskID;
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
    
    public void setUserList(ArrayList<User> taskUserList) {
        this.taskUserList = taskUserList;
    }
}
