package managementsystempackage.model;


import java.util.Date;

public class ProjectManager extends User {

private boolean isAdmin;



    public ProjectManager(int userID, String username, String password) {
        super(userID, username, password);
    }


    public void createEvent(String eventName, Date start,Date end)
    {

    }
    public void updateEvent(int eventID)
    {

    }
    public void deleteEvent(int eventID)
    {

    }
    public void displayPlannedEvent()
    {

    }
    public void displayCompleteEvents()
    {

    }
    public void createTask(int eventID,String taskName,Date dueDate)
    {

    }
    public void updateTask(int taskID)
    {

    }
    public void deleteTask(int taskID)
    {

    }
    public void assignTask(int taskID)
    {

    }
    public void unassignTask(int taskID)
    {

    }

}
