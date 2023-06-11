/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;
//imports
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import managementsystempackage.App;
import managementsystempackage.model.CalendarBox;
import managementsystempackage.model.Events;
import managementsystempackage.model.FileManager;
import managementsystempackage.model.IEventListener;
import managementsystempackage.model.Task;
import managementsystempackage.model.User;
//Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B
//The Class controls the view User Completed Events View.
public class UserCompletedEventsController implements Initializable {
    //FXML Imports
    @FXML
    private BorderPane TaskBorderPane;

    @FXML
    private Button btnAddTask;

    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnDeleteTask1;

    @FXML
    private DatePicker dpEndDate;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpTaskDueDate;

    @FXML
    private AnchorPane sidePane1;

    @FXML
    private VBox taskDisplay;

    @FXML
    private TextField tfDescription;

    @FXML
    private TextField tfEventTitle;

    @FXML
    private Text txtDate;

    @FXML
    private VBox vbDisplay;

    @FXML
    private VBox vbDisplayUsers;
    @FXML
    private Label usernameCurrent;
    //Instance Varaiables
    //Array to take in all events for this user
    private ArrayList<Events> eventArray = new ArrayList<>();
    private IEventListener<Events> iEventListener;
    private IEventListener<Task> iTaskListener;
    private boolean sidePane1IsShowing;
    private boolean sidePane2IsShowing;
    private boolean disableAnimation;
    private boolean disableAnimation1;
    private Events currentEvent;
    private Task currentTask;
    private CalendarBox box;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set the username in the right hand corner next to login to the logged in user.
        usernameCurrent.setText(FileManager.currentUsername());
        //Runs a method to get all event data for that user
        setData();
        //Hides and perps all sidepanels
        disableAnimation = false; // Disable user interaction during animation
        disableAnimation1 = false;
        sidePane1IsShowing = true;
        sidePane1.setVisible(!sidePane1IsShowing);
        showSidePane1();
        
    } 
    
    //Buttons to change views   
   @FXML
   private void gotoCalendar() throws IOException{
       SceneNavigation.gotoUserCalendar();
   }
   @FXML
   private void gotoPlannedEvent() throws IOException{
       SceneNavigation.gotoAssignedEvents();
   }
   @FXML
   private void gotoCompletedEvents() throws IOException{
       SceneNavigation.gotoUserCompletedEvents();
   }
   
   @FXML
   private void gotoLoginPage() throws IOException{
       SceneNavigation.gotoLoginPage();
   }
    //method to create event and tasks cards from the data in the event array
    private void addItems(){
        ExecutorService executorService = Executors.newCachedThreadPool();//
        
        //if event array is not empty
        if(!eventArray.isEmpty()){
           //Create new Event Listner;
            iEventListener = new IEventListener<>() {
                 //Listner checks if an event has been clicked
                @Override
                public void onClickEvent(Events event) {
                  //runs getClickedEvent() method for the given event=
                    getClickedEvent(event);
                    System.out.println(sidePane1IsShowing);
                    //If current event is diffrent from the selected event or if the event side panel is hidden
                    if(currentEvent != null && currentEvent.equals(event) || !sidePane1IsShowing){
                        //show sidePanel for the event
                        sidePane1.setVisible(true);
                        showSidePane1();
                    }
                    //if the side pane is showing
                    if(sidePane1IsShowing){
                        //For every task in global taskList   
                        for(Task task : FileManager.taskList ){
                            try{
                                //For every User in the task's userList
                                for (User u: task.getTaskUserList()){
                                    //Check if the user mathes the logged in user
                                    if(u.getUserID()==FileManager.currentUser){
                                        //Display tasks
                                        taskDisplay.getChildren().clear();
                                        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                                        Pane pane = loader.load(); //loading file
                                        TaskCardController controller = loader.getController();//get fxml controller
                                        controller.setCardData(task, iTaskListener); //send data to controller
                                        taskDisplay.getChildren().add(pane);  
                                    }
                                }
                            }
                            catch(IOException ioe){
                                ioe.printStackTrace();
                            }
                        }       
                    }else{
//                        showSidePane2();
                    }
                    currentEvent = event; 
                }
                //Function not used however the overide is need for event listner to work
                public void onClickDelete(Events event) {
                }
            };
            //Listner to check if task have been clicked
            iTaskListener = new IEventListener<Task>() {
            @Override
            public void onClickEvent(Task task) {
                currentTask = task;
            }
            //Function not used however the overide is need for event listner to work
                @Override
                public void onClickDelete(Task data) {
                }
            };
            //for every event in event array
            for(Events e : eventArray ){
                final Events event = e;
                    try{
                        //Display event
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/userEventCard.fxml")); //selecting fxml file
                        Pane pane = loader.load(); //loading file
                        userEventCardController controller = loader.getController();//get fxml controller
                        controller.setCardData(event, iEventListener); //send data to controller
                        vbDisplay.getChildren().add(pane);
                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
            }
        }
        else{
            System.out.println("No data!!");        
        }
    }
    //If SidePanel 1 is hidden show, else hide
    private void showSidePane1() {
        if(!disableAnimation){
            disableAnimation = true;
            translationAnimation(0.5, sidePane1, 764, sidePane1IsShowing, () -> disableAnimation = false);
            sidePane1IsShowing = !sidePane1IsShowing;
        
        }
    }
    //Button runs the showSidePanel1() method
    @FXML
   private void showPanel1(){
        sidePane1.setVisible(true);
        showSidePane1();
   }
    //Sliding animation
    private void translationAnimation(double duration, Node node, double width, boolean isOpen, Runnable onFinished) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(isOpen ? width : -width);
        translateTransition.setOnFinished(event -> {
            //prevent animation from breaking
            if (onFinished != null) {
                onFinished.run();//make pane animetable
            }
        });
        translateTransition.play();
    }
    
    private void getClickedEvent(Events event){
        
        System.out.println("Clicked "+event.getEventName());
    }
    
    //method to get all event data for that user
    public void setData(){
        System.out.println("___________Set Data____________");
        //For every event in global event list
        for (Events event: FileManager.eventList){
            //check if the user has been assigned tasks in that event and check if Event has no Tasks for the User to compelete
            if(FileManager.userIsAnEventsTask(event) && FileManager.DoesEventHaveCompletedTasks(event)){
                //then add events to this views event Array
                this.eventArray.add(event);
                System.out.println(event);
            }
        }
        //runs a method to create event and tasks cards from the data in the event array
            addItems();

    }
    
    
    
}
