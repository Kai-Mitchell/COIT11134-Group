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
 * @author renza
 */
public class AdminShowSelectedDateController implements Initializable {


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
    private AnchorPane sidePane2;
    
    @FXML
    private AnchorPane sidePane3;
    
    @FXML
    private AnchorPane sidePaneCreate;



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
        // TODO
        disableAnimation = false; // Disable user interaction during animation
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
        formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

        
    } 
    
     
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
   private void addUser(){
   
   
   }
   @FXML
   private void showPanel3(){
        sidePane3.setVisible(true);
        showSidePane3();
        
   }
   @FXML
   private void showPanel1(){
        sidePane1.setVisible(true);
        showSidePane1();
        showSidePane2();
        
        
   }
   
   @FXML
   private void showPanel2(){
        sidePane2.setVisible(true);
        showSidePane2();
        
   }
   
   @FXML 
   private void showPaneCreateTask(){
       sidePaneCreate.setVisible(true);
       showSidePaneCreate();
       
   }
   
   //Create New Event
   @FXML
   private void createEvent(ActionEvent event){

        sidePane3.setVisible(true);
        showSidePane3();
        
        
         
   }
   
   
    @FXML
    void createNewTask(ActionEvent event) throws IOException, ClassNotFoundException {        
        if(!FileManager.isEmpty(tfDescription1.getText()) && !FileManager.isEmpty(dpTaskDueDate1.getValue().toString())){
            FileManager.addNewtask(tfDescription1.getText(), currentEvent.getEventID(), dpTaskDueDate1.getValue());
            FileManager.saveAllFiles();
            sidePaneCreate.setVisible(false);
            sidePane3.setVisible(true);
            showSidePaneCreate();
            currentTask = FileManager.taskList.get(FileManager.taskCount-1);
            showSidePane3();
            
            tfDescription1.setText(currentTask.getTaskName());
            dpTaskDueDate1.setValue(currentTask.getDueDate());
            
            
            
        }
    }
   
   @FXML
   private void addNewEvent(ActionEvent event) throws IOException, ClassNotFoundException{
       System.out.print("Clicko1");
       //Valid inputs
       if(!tfTitleCreateEvent.getText().isBlank() && !dpStartDateCreateEvent.getValue().toString().isBlank() && !dpEndDateCreateEvent.getValue().toString().isBlank()){
            FileManager.addNewEvent(tfTitleCreateEvent.getText(), dpStartDateCreateEvent.getValue(), dpEndDateCreateEvent.getValue()); // create new Event
            System.out.print("Clicko");
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
            
            
             //Display created event   
            try{
                FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventCard.fxml")); //selecting fxml file
                Pane pane = loader.load(); //loading file
                EventCardController controller = loader.getController();//get fxml controller
                controller.setCardData(currentEvent, iEventListener); //send data to controller
                vbDisplay.getChildren().add(pane);

            }
            catch(IOException ioe){
                ioe.printStackTrace();
            }



            
            
       }
    
       
   }
    
    private void addItems(){
        ExecutorService executorService = Executors.newCachedThreadPool();//
        
        
        if(!eventArray.isEmpty()){
           // getChosenCardView(cardViewArray.get(0));
           //User
           iUserListener = new IEventListener<User>() {
               @Override
               public void onClickEvent(User user) {
                   currentTask.getUserList().add(user);
               }

               @Override
               public void onClickDelete(User user) {
                   currentTask.getUserList().remove(user);
               }
           };
           
           //Event
            iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(Events event) {
                    
//                    getClickedEvent(event);
//                    System.out.println(sidePane1IsShowing);
                    if(currentEvent != null && currentEvent.equals(event) || !sidePane1IsShowing){
                        sidePane1.setVisible(true);
                        
                        
                        showSidePane1();
                        if(currentEvent != null){
                            loadPane1Info();
                        }
                        
                    }
                    
                    if(sidePane1IsShowing){
                        
                                
                        for(Task task : FileManager.taskList ){
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
                    }else{
                        showSidePane2();
                    }
                    
                    currentEvent = event;
                    loadPane1Info();
                    
                    
                }

                @Override
                public void onClickDelete(Events event) {
                    //ToDo - Implement delete functionally
                    System.out.println("Deleting...");
                }
                
                
            };
            
            //Task
            iTaskListener = new IEventListener<Task>() {
            @Override
            public void onClickEvent(Task task) {
                currentTask = task;

                if(currentTask != null && currentTask.equals(task) || !sidePane2IsShowing){

                    sidePane2.setVisible(true);
                    showSidePane2();
                   

                }else{
                
                }
                
                 if(currentEvent != null){
                    tfDescription.setText(currentTask.getTaskName());
                    dpEndDate.setValue(currentTask.getDueDate());

                    vbDisplayUsers.getChildren().clear();
                    for(User user : FileManager.userList){
                        String btnName = "Add User";
                        if(currentTask.getUserList().contains(user)){
                            btnName = "Remove user";
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
            
            for(Events e : eventArray ){
                final Events event = e;
                    try{
                        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventCard.fxml")); //selecting fxml file
                        Pane pane = loader.load(); //loading file
                        EventCardController controller = loader.getController();//get fxml controller
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
  
    private void loadPane1Info(){
        tfEventTitle.setText(currentEvent.getEventName());
        dpStartDate.setValue(currentEvent.getStart());
        dpEndDate.setValue(currentEvent.getEnd());
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
    
    //Temporary func that represents data
    public void setData(CalendarBox box){
            this.eventArray = box.eventArray;
            this.box = box;
            this.txtDate.setText(box.getDate().toString());
            addItems();
            
            for(User user: FileManager.userList){
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/userCard.fxml")); //selecting fxml file
                    try {
                        Pane pane = loader.load(); //loading file
                        UserCardController controller = loader.getController();
                        if(!user.getIsAdmin()){
                            controller.setDate(user, iUserListener, "Add User");
                            vbDisplayUsers.getChildren().add(pane);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
            }

    }
    
    
    
}
