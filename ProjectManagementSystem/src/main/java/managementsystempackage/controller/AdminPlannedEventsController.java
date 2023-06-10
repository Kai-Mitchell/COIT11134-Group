/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

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
/**
 * FXML Controller class
 *
 * @author renza
 */
public class AdminPlannedEventsController implements Initializable {


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
    private AnchorPane sidePane2;

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
    
    private boolean sidePane1IsShowing;
    private boolean sidePane2IsShowing;
    private ArrayList<Events> eventArray = new ArrayList<>();
    private IEventListener<Events> iEventListener;
    private IEventListener<Task> iTaskListener;
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
        
        disableAnimation = false; // Disable user interaction during animation
        disableAnimation1 = false;
        sidePane1IsShowing = true;
        sidePane2IsShowing = true;
        sidePane1.setVisible(!sidePane1IsShowing);
        sidePane2.setVisible(!sidePane2IsShowing);
        showSidePane1();
        showSidePane2();
        
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
    
    private void addItems(){
        ExecutorService executorService = Executors.newCachedThreadPool();//
        
        
        if(!eventArray.isEmpty()){
           // getChosenCardView(cardViewArray.get(0));
            iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(Events event) {
                  
                    
                    getClickedEvent(event);
                    System.out.println(sidePane1IsShowing);
                    if(currentEvent != null && currentEvent.equals(event) || !sidePane1IsShowing){
                        sidePane1.setVisible(true);

                        showSidePane1();
                        
                        
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
//                        showSidePane2();
                    }
                    
                    currentEvent = event;
                    
                }

                @Override
                public void onClickDelete(Events event) {
                    //ToDo - Implement delete functionally
                    System.out.println("Deleting...");
                }
                
                
            };
            
            
            iTaskListener = new IEventListener<Task>() {
            @Override
            public void onClickEvent(Task task) {

                if(currentTask != null && currentTask.equals(task) || !sidePane2IsShowing){

                    sidePane2.setVisible(true);
                    showSidePane2();

                }
                currentTask = task;
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
    
    private void showSidePane1() {
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

    }
    
    
    
}
