package managementsystempackage.model;

import java.util.ArrayList;
import java.util.Date;

public class ProjectManager extends User {

    private static final int userID=0;
    private static final String username="";
    private static final String password = "";
    private ArrayList<Events> eventsList;
    private boolean isAdmin;



    public ProjectManager(ArrayList<Events> eventsList) {
        super(userID, username, password);
        this.eventsList = eventsList;
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
