/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managementsystempackage.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;


/**
 *
 * @author Blueb
 */
public class FileManager {
    //Instantiates an ArrayList
    //The ArrayList is populated in the create""record() method
    static ArrayList<User> userList = new ArrayList<>();
    static ArrayList<Events> eventList = new ArrayList<>();
    static ArrayList<Task> taskList = new ArrayList<>();
    static int userCount = 0;
    static int eventCount = 0;
    static int taskCount = 0;
    /**
     * Initializes the controller class.
     */
     //Method to check if an boolean input in empty (CQU 2023)
    public static boolean isEmpty(String param)
    {
        if (param.equals(""))
        {
            System.out.println("Invalid response. Try again...");
            return true;
        }    
        else
            return false;
            
    }        
      
    //Method to check if all characters in an input are numeric (CQU 2023)
    public static boolean isNumeric(String param)
    {
        try{
            for (int i = 0; i < param.length(); i++){
                if (!Character.isDigit(param.charAt(i)))
                    return false;
            }
            return true;
        } catch (Exception e){
            return false;
        }
    }
    //Method to check if a interger input value is within a valid range (CQU 2023)
    public static boolean isValid(String param, int lowerLimit, int upperLimit)
    {   
        int val;
        
        try {
            val = Integer.parseInt(param);
        } //If the input is a nonnumeric value, an Exception will thrown, which will be caught in the Catch block
        catch (NumberFormatException e) {
            //Nonnumberic input is not acceptable
            System.out.println("Invalid response. Try again...");
            return false;
        }
        //We could use the isNumeric() method instead of the above Try Catch block
        
        if (val < lowerLimit || val > upperLimit) 
        {
            System.out.println("Invalid response. Try again...");
            return false;
        }   
        else
            return true;
    }
    //Create popup, saying faild to create record due not all fields being correct
    public static void faildToCreateRecord(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Missing Information");
            alert.setContentText("Can not create record as not all fields are properly filled, ensure all fields are filled before trying again.");
            Optional<ButtonType> result = alert.showAndWait();
    }
    
    //Method to check if userID is in the userList
    public static  boolean validUser(int x){
        for (int i = 0; i < userCount; i++){
            if (userList.get(i).getUserID() == x){
                return true;
            }
        }
        return false;
    }
    //Method to check if eventID is in the eventList
    public static  boolean validEvent(int x){
        for (int i = 0; i < eventCount; i++){
            if (eventList.get(i).getEventID() == x){
                return true;
            }
        }
        return false;
    }
    //Method to check if taskID is in the taskList
    public static  boolean validTask(int x){
        for (int i = 0; i < taskCount; i++){
            if (taskList.get(i).getTaskID() == x){
                return true;
            }
        }
        return false;
    } 
    
    ////Method to check if an input is a double above 0
    public static  boolean isDouble(String a){
        double x;
        try{
            x = Double.parseDouble(a);
            if (x>0){
                return true;
            }
            else{
                return false;
            }
        }
        catch (NumberFormatException e){
            return false;
        }
    }
    
    //Add new user into userList
    public static  void addNewUser(String username, String password){
        User user = new User(username, password);
        userList.add(user); //user object added to the ArrayList
        System.out.println(userList.size());
        //Alert popup with user infro
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Added");
        alert.setContentText(userList.get(userCount).toString());
        Optional<ButtonType> result = alert.showAndWait();
        //Keeps track of the number of obejcts
        userCount = userList.size();
        
    }
    //add new project manager into userList
    public static  void addNewProjectManager(String username, String password){
        ProjectManager projectManager = new ProjectManager(username, password);
        userList.add(projectManager); //Project manager object added to the ArrayList
        //Alert popup with project manager infro
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Added");
        alert.setContentText(userList.get(userCount).toString());
        Optional<ButtonType> result = alert.showAndWait();
        //Keeps track of the number of obejcts
        userCount = userList.size();
    }
    //add event into eventList
    public static  void addEventevent(String eventName,Date start,Date end){
        Events event = new Events(eventName, start, end);
        eventList.add(event);
        //Alert popup with event info
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Event Added");
        alert.setContentText(eventList.get(eventCount).toString());
        Optional<ButtonType> result = alert.showAndWait();
        //Keeps track of the number of obejcts
        eventCount = eventList.size();
    }
    //add task into taskList
    public static  void addNewtask(String taskName, int taskEventID,Date dueDate, ArrayList<User> taskUserList){
        Task task = new Task(taskName, taskEventID, dueDate, taskUserList);
        taskList.add(task);
        //Alert popup with task info
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("task Added");
        alert.setContentText(taskList.get(taskCount).toString());
        Optional<ButtonType> result = alert.showAndWait();
        //Keeps track of the number of obejcts
        taskCount = taskList.size();
    }
    //reads files
    public static void readAllFiles() throws IOException, ClassNotFoundException{
        //Get file, read objects in file, add objects to ArrayyList
        FileInputStream fisUser = new FileInputStream("users.txt");
        ObjectInputStream oisUser = new ObjectInputStream(fisUser);
        userList = (ArrayList) oisUser.readObject();
        //update count
        userCount += userList.size();
        int nextUserID =userList.size()+1001;
        User.setNextUserID(nextUserID);
        
        
        FileInputStream fisEvent = new FileInputStream("events.txt");
        ObjectInputStream oisEvent = new ObjectInputStream(fisEvent);
        eventList = (ArrayList) oisEvent.readObject();
        eventCount += eventList.size();
        int nextEventID = eventList.size()+1001;
        Events.setNextEventID(nextEventID);
        
        FileInputStream fisTask = new FileInputStream("tasks.txt");
        ObjectInputStream oisTask = new ObjectInputStream(fisTask);
        taskList = (ArrayList) oisTask.readObject();
        taskCount += taskList.size();
        int nextTaskID = taskList.size()+1001;
        Task.setNextTaskID(nextTaskID);  
    }
    //saves files
    public static void saveAllFiles() throws IOException, ClassNotFoundException{
        //Get file, read objects in file, add objects to ArrayyList
        try {
            FileOutputStream userFos = new FileOutputStream("users.txt");
            ObjectOutputStream userOos = new ObjectOutputStream(userFos);
            userOos.writeObject(userList);
            userOos.close();
        } catch (IOException ex) {
            
        }
        
        try {
            FileOutputStream eventFos = new FileOutputStream("events.txt");
            ObjectOutputStream eventOos = new ObjectOutputStream(eventFos);
            eventOos.writeObject(eventList);
            eventOos.close();
        } catch (IOException ex) {
        }
        
        try {
            FileOutputStream fosTask = new FileOutputStream("tasks.txt");
            ObjectOutputStream oosTask = new ObjectOutputStream(fosTask);
            oosTask.writeObject(taskList);
            oosTask.close();
        } catch (IOException ex) {
        } 
    }
}
