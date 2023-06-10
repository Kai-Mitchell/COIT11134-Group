package managementsystempackage.model;
/**
 *
 * @author gomez
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.time.LocalDate;

public class Task implements Serializable{
    private int taskID;

    private int taskEventID;
    private String taskName;
    private LocalDate dueDate;
    private boolean completed;
    ArrayList<User> taskUserList;
    private static int nextTaskID;
    
    public Task(){}

    public Task(String taskName, int taskEventID,LocalDate dueDate)
    {
        this.taskName = taskName;
        this.taskEventID = taskEventID;//Event to task refrence
        this.dueDate =dueDate;
        this.completed = false;
        this.taskUserList = new ArrayList<>();//User to task reference
        setTaskID();
    }

    public ArrayList<User> getTaskUserList() {
        return taskUserList;
    }

    public void setTaskUserList(ArrayList<User> taskUserList) {
        this.taskUserList = taskUserList;
    }

    public int getTaskEventID() {
        return taskEventID;
    }

    public void setTaskEventID(int taskEventID) {
        this.taskEventID = taskEventID;
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

    public LocalDate getDueDate() {
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

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    
    public void setUserList(ArrayList<User> taskUserList) {
        this.taskUserList = taskUserList;
    }
}
