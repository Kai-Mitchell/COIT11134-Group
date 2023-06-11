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
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
/**
 * FXML Controller class
 *
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class AdminShowSelectedDateController implements Initializable {


    
     
    @FXML
    private DatePicker dpEndDateCreateEvent;
    @FXML
    private DatePicker dpStartDateCreateEvent;

    @FXML
    private DatePicker dpTaskDueDate1;
    @FXML
    private DatePicker dpEndDate;

    @FXML
    private DatePicker dpStartDate;

    @FXML
    private DatePicker dpTaskDueDate;

    @FXML
    private AnchorPane sidePane1;

    @FXML
    private AnchorPane sidePane2;
    
    @FXML
    private AnchorPane sidePane3;
    
    @FXML
    private AnchorPane sidePaneCreate;



    @FXML
    private VBox taskDisplay;
    

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
    
    private boolean sidePane1IsShowing;
    private boolean sidePane2IsShowing;
    private boolean sidePane3IsShowing;
    private boolean sidePaneCreateTaskIsShowing;
    private ArrayList<Events> eventArray = new ArrayList<>();
    private IEventListener<Events> iEventListener;
    private IEventListener<Task> iTaskListener;
    private IEventListener<User> iUserListener;
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
        /*
        
        // Disable user interaction during animation
        
        */
        disableAnimation = false; 
        disableAnimation1 = false;
        disableAnimation2 = false;
        disableAnimation3 = false;
        sidePane1IsShowing = true;
        sidePane2IsShowing = true;
        sidePane3IsShowing = true;
        sidePaneCreateTaskIsShowing = true;
        sidePane1.setVisible(!sidePane1IsShowing);
        sidePane2.setVisible(!sidePane2IsShowing);
        sidePane3.setVisible(!sidePane3IsShowing);
        sidePaneCreate.setVisible(!sidePaneCreateTaskIsShowing);
        showSidePane1();
        showSidePane2();
        showSidePane3();
        showSidePaneCreate();
        
        
        //set date format
        formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        
        //add listeners on date pickers to make sure values are between range
        dpTaskDueDate.setOnAction(event ->  checkDateRange(dpTaskDueDate.getValue(), currentEvent.getStart(), currentEvent.getEnd()));
        dpTaskDueDate1.setOnAction(event ->  checkDateRange(dpTaskDueDate1.getValue(), currentEvent.getStart(), currentEvent.getEnd()));
        
        //Interface to handle click event from User cards
        iUserListener = new IEventListener<User>() {
               @Override
               public void onClickEvent(User user) {
                   try {
                       //add user and save
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
//                      
                       //look for user, remove and save file
                       for(User u : currentTask.getUserList()){
                           if(u.getUserID() == user.getUserID()){
                                currentTask.getUserList().remove(u);
                           }
                       }
                       FileManager.saveAllFiles();
                   } catch (IOException ex) {
                       ex.printStackTrace();
                   } catch (ClassNotFoundException ex) {
                       ex.printStackTrace();
                   }
               }
           };
           try{
           
           //Interface handle click events for Event Cards
            iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(Events event) throws IndexOutOfBoundsException {
                    //open sidepane 1 if event is not empty or not showing
                    if(currentEvent != null && currentEvent.equals(event) || !sidePane1IsShowing ){
                        sidePane1.setVisible(true);
                        showSidePane1();
                       
                        
                    }
                    
                    if(sidePane1IsShowing && currentEvent != null){
                        
                        taskDisplay.getChildren().clear(); //clear task display

                        for(Task task : FileManager.taskList ){
                            if(task.getTaskEventID() == currentEvent.getEventID()){

                                try{
                                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                                    Pane pane = loader.load(); //loading file
                                    TaskCardController controller = loader.getController();//get fxml controller
                                    controller.setCardData(task, iTaskListener); //send data to controller


                                    taskDisplay.getChildren().add(pane);

                                }
                                catch(IOException ioe){
                                    ioe.printStackTrace();
                                }

                            }
                        }       
                    }
                    
                    currentEvent = event; //set current event
                    loadPane1Info();
                    
                    
                }

                @Override
                public void onClickDelete(Events event) {
                    try {
                        
                        try{
                            //remove task in event
                            for(int i = 0; i < FileManager.taskCount; i++){
                                if(event.getEventID() == FileManager.taskList.get(i).getTaskEventID()){
                                   FileManager.taskList.remove(i);
                                }
                            }
                        }catch(IndexOutOfBoundsException e){
                            
                        }
                        //then remove event
                        FileManager.eventList.remove(event);
                        vbDisplay.getChildren().clear();
                        clearEventDisplay();
                        
                        FileManager.saveAllFiles();
                        
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    } catch (ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                    
                    
                }
                
                
            };
            
           }catch(NullPointerException e){
               e.printStackTrace();
           }
            
            //Interface handle click event for TAsk Card
            iTaskListener = new IEventListener<Task>() {
            @Override
            public void onClickEvent(Task task) {

                if(currentTask != null && currentTask.equals(task) || !sidePane2IsShowing){
                    //animate side pane 2
                    sidePane2.setVisible(true);
                    showSidePane2();
                   

                }
                currentTask = task;

                
                if(currentTask != null){
                    //set text field data
                   tfDescription.setText(currentTask.getTaskName());
                   dpTaskDueDate.setValue(currentTask.getDueDate());

                   //clear display
                   vbDisplayUsers.getChildren().clear();
                   
                   
                   //check if is assigned to task
                   for(User user : FileManager.userList){
                       String btnName = "Add User";
                       for(User userTask : currentTask.getUserList()){
                           if(userTask.getUserID() == user.getUserID()){
                                btnName = "Remove user";

                            }


                        }
                        try{
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/userCard.fxml")); //selecting fxml file
                            Pane pane = loader.load(); //loading file
                            UserCardController controller = loader.getController();//get fxml controller
                            controller.setDate(user, iUserListener, btnName); //send data to controller


                            vbDisplayUsers.getChildren().add(pane);

                       }
                       catch(IOException ioe){
                           ioe.printStackTrace();
                       }

                   }
                }

            }

                @Override
                public void onClickDelete(Task data) {
                    System.out.println("Task clicked!");

                }
            };
            
        
    } 
   
    /*
    
    Navigation Functions
    
    */
     
   @FXML
   private void gotoCalendar() throws IOException{
       SceneNavigation.gotoCalendar();
   }
   @FXML
   private void gotoPlannedEvent() throws IOException{
       SceneNavigation.gotoPlannedEvent();
   }
   @FXML
   private void gotoCompletedEvents() throws IOException{
       SceneNavigation.gotoCompletedEvents();
   }
   
   @FXML
   private void gotoLoginPage() throws IOException{
       SceneNavigation.gotoLoginPage();
   }
   
   @FXML
   private void deleteTask() throws ClassNotFoundException, IOException{
       //delete task
        FileManager.taskList.remove(currentTask);
        clearTaskDisplay();//clear display
        showPanel2();//animate panel2
        clearEventDisplay();//clear event display
        FileManager.saveAllFiles();//save
      
   
   }
   @FXML
   private void showPanel3(){
        sidePane3.setVisible(true);
        showSidePane3();
        if(sidePaneCreateTaskIsShowing){
            showSidePaneCreate();
        
        }
   }
   @FXML
   private void showPanel1(){
        sidePane1.setVisible(true);
        showSidePane1();
        if(sidePane2IsShowing){
            showSidePane2();
        
        }else if(sidePaneCreateTaskIsShowing){
            showSidePaneCreate();
        }

        
        
   }
   
   @FXML
   private void showPanel2(){
        sidePane2.setVisible(true);
        showSidePane2();
        if(sidePane3IsShowing){
            showSidePane3();
        
        }
   }
   
   @FXML 
   private void showPaneCreateTask(){
       sidePaneCreate.setVisible(true);
       showSidePaneCreate();
       
   }
   
   //Show panel three
   @FXML
   private void createEvent(ActionEvent event){

        sidePane3.setVisible(true);
        showSidePane3();

        
         
   }
   
   
    @FXML
    void createNewTask(ActionEvent event) throws IOException, ClassNotFoundException {    
        try{
            if(!FileManager.isEmpty(tfDescription1.getText()) && currentEvent.getStart().minusDays(1).isBefore(dpTaskDueDate1.getValue()) && currentEvent.getEnd().plusDays(1).isAfter(dpTaskDueDate1.getValue()) && dpTaskDueDate1.getValue() != null){
               
                FileManager.addNewtask(tfDescription1.getText(), currentEvent.getEventID(), dpTaskDueDate1.getValue());
                FileManager.saveAllFiles();
                //get last created task and set it as current
                Task newTask = FileManager.taskList.get(FileManager.taskCount-1);
                sidePaneCreate.setVisible(false);
                sidePane1.setVisible(true);
                showSidePaneCreate();
                currentTask = FileManager.taskList.get(FileManager.taskCount-1);
                
                //set text field with current task
                tfDescription1.setText(currentTask.getTaskName());
                dpTaskDueDate1.setValue(currentTask.getDueDate());
                
                //load and display task card
                FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml"));
                Pane pane = loader.load();
                TaskCardController controller = loader.getController();
                controller.setCardData(newTask, iTaskListener);
                Platform.runLater(()->{
                    taskDisplay.getChildren().add(pane);
                });
                clearEventDisplay();
                
                
            
            }
        }
        catch(NullPointerException e){
            //show error is datepicker is empty
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error - Empty Field");
            alert.setContentText("Date picker must contain a value");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
   
    
    
   @FXML
   private void addNewEvent(ActionEvent event) throws IOException, ClassNotFoundException{
       //Validate inputs
       if(!tfTitleCreateEvent.getText().isBlank() && !dpStartDateCreateEvent.getValue().toString().isBlank() && !dpEndDateCreateEvent.getValue().toString().isBlank()){
            FileManager.addNewEvent(tfTitleCreateEvent.getText(), dpStartDateCreateEvent.getValue(), dpEndDateCreateEvent.getValue()); // create new Event
            FileManager.saveAllFiles();//save event
            sidePane3.setVisible(false); //hide create event panel
            sidePane1.setVisible(true);// showing create event panel
            showPanel3();
            
            currentEvent = FileManager.eventList.get(FileManager.eventCount-1);//get current event
            showSidePane1();
            //set panel1 info
            tfEventTitle.setText(currentEvent.getEventName());
            dpStartDate.setValue(currentEvent.getStart());
            dpEndDate.setValue(currentEvent.getEnd());
            //add tasks in event
            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/EventCard.fxml"));
            Pane pane = loader.load();
            EventCardController controller = loader.getController();
            controller.setCardData(currentEvent, iEventListener);
            Platform.runLater(() ->{
                vbDisplay.getChildren().add(pane);
            });
            
            
            
            
            
            
       }
    
       
   }
   
   private void clearEventDisplay(){
       
       vbDisplay.getChildren().clear();//clear display
       for(Events e : FileManager.eventList){
            if(e.getStart().equals(box.getDate()) || e.getEnd().equals(box.getDate())){

                try{
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventCard.fxml")); //selecting fxml file
                    Pane pane = loader.load(); //loading file
                    EventCardController controller = loader.getController();//get fxml controller
                    controller.setCardData(e, iEventListener); //send data to controller
                    Platform.runLater(() -> {
                        //add event card
                        vbDisplay.getChildren().add(pane);

                    });

                }
                catch(IOException ioe){
                    ioe.printStackTrace();
                }

            }
        }
   }
   
   private void clearTaskDisplay() throws IndexOutOfBoundsException{
       taskDisplay.getChildren().clear();//clear
        if(!FileManager.taskList.isEmpty()){
        
            for(Task task : FileManager.taskList){
                if(task.getTaskEventID() == currentEvent.getEventID()){
                    try{
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                        Pane pane = loader.load(); //loading file
                        TaskCardController controller = loader.getController();//get fxml controller
                        controller.setCardData(task, iTaskListener); //send data to controller
                        Platform.runLater(() -> {
                            taskDisplay.getChildren().add(pane);
                        });

                        }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        }
   }
    
    private void addItems(){
        
        
        if(!eventArray.isEmpty()){
           
            
            for(Events e : eventArray ){
                final Events event = e;
                    try{
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventCard.fxml")); //selecting fxml file
                        Pane pane = loader.load(); //loading file
                        EventCardController controller = loader.getController();//get fxml controller
                        controller.setCardData(event, iEventListener); //send data to controller

                        Platform.runLater(() -> {
                            vbDisplay.getChildren().add(pane);
                        });
                        

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
  
    private void loadPane1Info(){
        //collect and set data
        tfEventTitle.setText(currentEvent.getEventName());
        dpStartDate.setValue(currentEvent.getStart());
        dpEndDate.setValue(currentEvent.getEnd());
        
        taskDisplay.getChildren().clear();
        try{
            for(int i = 0; i < FileManager.taskCount; i++){
                if(FileManager.taskList.get(i).getTaskEventID() == currentEvent.getEventID()){
                    try{
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/taskCard.fxml")); //selecting fxml file
                        Pane pane = loader.load(); //loading file
                        TaskCardController controller = loader.getController();//get fxml controller
                        controller.setCardData(FileManager.taskList.get(i), iTaskListener); //send data to controller
                        Platform.runLater(() -> {
                            taskDisplay.getChildren().add(pane);
                        });
                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                }
            }
        }catch(IndexOutOfBoundsException e){
            currentTask = null;
            
        }
        
        
    }
    
    /*
    Animations
    */
    
    private void showSidePane1() {
        System.out.println("Show Panel3 : "+sidePane1IsShowing);
        if(!disableAnimation){
            disableAnimation = true;
            translationAnimation(0.5, sidePane1, 764, sidePane1IsShowing, () -> disableAnimation = false);
            sidePane1IsShowing = !sidePane1IsShowing;
        
        }
    }
    private void showSidePane2() {
        if(!disableAnimation1){
            disableAnimation1 = true;
            translationAnimation(0.5, sidePane2, 580, sidePane2IsShowing, () -> disableAnimation1 = false);
            sidePane2IsShowing = !sidePane2IsShowing;

        }
    }
    
     private void showSidePane3() {
        if(!disableAnimation2){
            disableAnimation2 = true;
            translationAnimation(0.5, sidePane3, 764, sidePane3IsShowing, () -> disableAnimation2 = false);
            sidePane3IsShowing = !sidePane3IsShowing;

        }
    }
     
     private void showSidePaneCreate() {
        if(!disableAnimation3){
            disableAnimation3 = true;
            translationAnimation(0.5, sidePaneCreate, 580, sidePaneCreateTaskIsShowing, () -> disableAnimation3 = false);
            sidePaneCreateTaskIsShowing = !sidePaneCreateTaskIsShowing;

        }
    }
    private void translationAnimation(double duration, Node node, double width, boolean isOpen, Runnable onFinished) {
        //move panel from left to right or right to right basing whether it's open or not 
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
    
   
    
    
    public void setData(CalendarBox box){
        
        //collect and set data
        this.eventArray = box.eventArray;
        this.box = box;
        this.txtDate.setText(box.getDate().format(formatter));
        addItems();


    }
    
    
    private void checkDateRange(LocalDate selectedDate, LocalDate startDate, LocalDate endDate) {
        //check if the selected date of the task is between start and end date of the event 
        if (selectedDate != null && startDate != null && endDate != null) {
           if (!(selectedDate.isAfter(startDate.minusDays(1)) && selectedDate.isBefore(endDate.plusDays(1)))) {
               //show alert box if selected date is after or before the due date
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Selected date is not within the range.");
                alert.setContentText("Date: Must be between "+ currentEvent.getStart().toString() + " And "+currentEvent.getEnd().toString());
                Optional<ButtonType> result = alert.showAndWait();
                dpTaskDueDate1.setValue(null);
                dpTaskDueDate.setValue(null);
                
            }
        } 
        
        
}
    
    
    
}
