/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
//The Class controls selected date view. The class displays the events the user is assigned to and needs to complete
public class userShowSelectedDateController implements Initializable {


    @FXML
    private BorderPane TaskBorderPane;
     
    @FXML
    private Button btnAddEvent;
    @FXML
    private Button btnAddTask;
    @FXML
    private DatePicker dpEndDateCreateEvent;
    @FXML
    private DatePicker dpStartDateCreateEvent;

    @FXML
    private DatePicker dpTaskDueDate1;
    
    @FXML
    private Button btnAddUser;

    @FXML
    private Button btnDeleteTask1;
    
    @FXML
    private Button btnAddTask1;

    @FXML
    private Button btnShowSidePaneCreate;
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
    private VBox vbDisplayUsers1;

    @FXML
    private TextField tfDescription;
    @FXML
    private TextField tfDescription1;

    @FXML
    private TextField tfEventTitle;
    
    @FXML
    private TextField tfTitleCreateEvent;

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
    private IEventListener<User> iUserListener;
    private boolean sidePane1IsShowing;
    private boolean sidePane2IsShowing;
    private boolean sidePane3IsShowing;
    private boolean sidePaneCreateTaskIsShowing;
    private boolean disableAnimation;
    private boolean disableAnimation1;
    private boolean disableAnimation2;
    private boolean disableAnimation3;
    private Events currentEvent;
//    private User currentUser;
    private Task currentTask;
    private CalendarBox box;
    private DateTimeFormatter formatter;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Set the username in the right hand corner next to login to the logged in user.
        usernameCurrent.setText(FileManager.currentUsername());
        //Hides and perps all sidepanels
        disableAnimation = false; // Disable user interaction during animation
        disableAnimation1 = false;
        disableAnimation2 = false;
        disableAnimation3 = false;
        sidePane1IsShowing = true;
        sidePane2IsShowing = true;
        sidePane3IsShowing = true;
        sidePaneCreateTaskIsShowing = true;
        sidePane1.setVisible(!sidePane1IsShowing);
        showSidePane1();
        //formats date
        formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        
        iUserListener = new IEventListener<User>() {
               @Override
               public void onClickEvent(User user) {
                   try {
                       currentTask.getUserList().add(user);
                       FileManager.saveAllFiles();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   } catch (ClassNotFoundException ex) {
                       ex.printStackTrace();
                   }
               }

               @Override
               public void onClickDelete(User user) {
                   try {
                       currentTask.getUserList().remove(user);
                       FileManager.saveAllFiles();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   } catch (ClassNotFoundException ex) {
                       ex.printStackTrace();
                   }
               }
           };
           try{
           
           //Event
            iEventListener = new IEventListener<>() {
                
                

                //If the event card is clicked on
                @Override
                public void onClickEvent(Events event) {
                     //If current event is diffrent from the selected event or if the event side panel is hidden
                    if(currentEvent != null && currentEvent.equals(event) || !sidePane1IsShowing ){
                        sidePane1.setVisible(true);
                        
                        
                        showSidePane1();
                        if(currentEvent != null){
                            loadPane1Info();
                        }
                        
                    }
                    //if the side pane is showing
                    if(sidePane1IsShowing){
                        
                                
                        for(Task task : FileManager.taskList ){
                           //for every task in global task list, add task card if event id matches
                            try{
                                if (task.getTaskEventID() == event.getEventID()){
                                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                                    Pane pane = loader.load(); //loading file
                                    TaskCardController controller = loader.getController();//get fxml controller
                                    controller.setCardData(task, iTaskListener); //send data to controller
                                    taskDisplay.getChildren().add(pane);  
                                }
                                

                            }
                            catch(IOException ioe){
                                ioe.printStackTrace();
                            }
                         

                        }       
                    }
                    
                    currentEvent = event;
                    loadPane1Info();
                    
                    
                }

                @Override
                public void onClickDelete(Events event) {
                    FileManager.eventList.remove(event);
                    vbDisplay.getChildren().clear();
                    clearEventDisplay();
                    
                    
                }
                
                
            };
            
           }catch(NullPointerException e){
               e.printStackTrace();
           }
            
            //Task
            iTaskListener = new IEventListener<Task>() {
                @Override
                public void onClickEvent(Task task) {
                    currentTask = task;                
                    if(currentEvent != null){
                        System.out.print("");
                    }
                }
                @Override
                public void onClickDelete(Task data) {
                    System.out.println("Task clicked!");

                }
            };
        
    } 
    
    //Buttons to go to diffrent views
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
   //run showSidePane1 method
   @FXML
   private void showPanel1(){
        sidePane1.setVisible(true);
        showSidePane1();
   }
     
   //resets the event display
   private void clearEventDisplay(){
       vbDisplay.getChildren().clear();
       for(Events e : FileManager.eventList){
            if(e.getStart().equals(box.getDate()) || e.getEnd().equals(box.getDate())){

                try{
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/userEventCard.fxml")); //selecting fxml file
                    Pane pane = loader.load(); //loading file
                    userEventCardController controller = loader.getController();//get fxml controller
                    controller.setCardData(e, iEventListener); //send data to controller
                    Platform.runLater(() -> {
                        vbDisplay.getChildren().add(pane);

                    });

                }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }

            }
        }
   }
   //Resets the tasks display
   private void clearTaskDisplay(){
       taskDisplay.getChildren().clear();
            
        for(int i = 0; i < FileManager.taskCount; i++){
            if(FileManager.taskList.get(i).getTaskEventID() == currentEvent.getEventID()){
                try{
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                    Pane pane = loader.load(); //loading file
                    TaskCardController controller = loader.getController();//get fxml controller
                    controller.setCardData(FileManager.taskList.get(i), iTaskListener); //send data to controller
                    taskDisplay.getChildren().add(pane);

                    }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }
            }
        }
   }
    //Creates an event card per event in the in the date's event array
    private void addItems(){
        ExecutorService executorService = Executors.newCachedThreadPool();//
        
        
        if(!eventArray.isEmpty()){
           // getChosenCardView(cardViewArray.get(0));
           //User
           
            
            for(Events e : eventArray ){
                final Events event = e;
                    try{
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
  //Set the events crad info
    private void loadPane1Info(){
        tfEventTitle.setText(currentEvent.getEventName());
        dpStartDate.setValue(currentEvent.getStart());
        dpEndDate.setValue(currentEvent.getEnd());
        taskDisplay.getChildren().clear();

        for(int i = 0; i < FileManager.taskCount; i++){
            if(FileManager.taskList.get(i).getTaskEventID() == currentEvent.getEventID()){
                for(User userC : FileManager.taskList.get(i).getUserList()){
                    if (userC.getUserID()==FileManager.currentUser){
                        try{
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                            Pane pane = loader.load(); //loading file
                            TaskCardController controller = loader.getController();//get fxml controller
                            controller.setCardData(FileManager.taskList.get(i), iTaskListener); //send data to controller
                            taskDisplay.getChildren().add(pane); 
                        }
                        catch(IOException ioe){
                            ioe.printStackTrace();
                        }
                    }
                }
            }
        }        
    }
    //If SidePanel 1 is hidden show, else hide
    private void showSidePane1() {
        System.out.println("Show Panel3 : "+sidePane1IsShowing);
        if(!disableAnimation){
            disableAnimation = true;
            translationAnimation(0.5, sidePane1, 764, sidePane1IsShowing, () -> disableAnimation = false);
            sidePane1IsShowing = !sidePane1IsShowing;
        
        }
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
    public void setData(CalendarBox box){
        //Grabs date from the the calendar box
            this.eventArray = box.eventArray;
            this.box = box;
            this.txtDate.setText(box.getDate().format(formatter));
            addItems();
            

    }
    
    
    
}