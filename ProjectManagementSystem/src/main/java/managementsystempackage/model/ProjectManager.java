package managementsystempackage.model;
/**
 *
 * @author gomez
 */

import java.util.ArrayList;
import java.util.Date;
//Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B
//This class ProjectManager is a Subclass of User
public class ProjectManager extends User {
    private ArrayList<Events> eventsList;
    private boolean isAdmin;



    public ProjectManager(String username, String password) {
        super(username, password);
        super.setIsAdmin(true);
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
