/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import managementsystempackage.App;
import managementsystempackage.model.CalendarBox;
import managementsystempackage.model.Events;
import managementsystempackage.model.FileManager;
import managementsystempackage.model.IEventListener;



public class userCalendarBoxController implements Initializable {
    @FXML
    private Text txtNumber;

    @FXML
    private VBox vbDisplay;
    private CalendarBox box;
    private ArrayList<Events> boxEventArray = new ArrayList<>();
    private IEventListener listenerInterface;
    private ExecutorService executorService;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        

       
        
    }
    
    
    @FXML
    private void click(MouseEvent mouseEvent){
        listenerInterface.onClickEvent(box);
        
    }
    public void setBoxInfo(CalendarBox box, IEventListener listenerInterface){
        this.box = box;
        this.listenerInterface = listenerInterface;
        this.txtNumber.setText(String.valueOf(box.getBoxNumber()));
        
        boxEventArray = FileManager.eventList;
        loadTitle();

    }
    
    public void changeColor(){
        txtNumber.getStyleClass().add("sunday-color");

    }
    
    public boolean userIsAnEventsTask(Events event){
        //For All tasks
        for(int i = 0; i<FileManager.taskCount; i++){
            //if task's eventID = event eventID
            if(FileManager.taskList.get(i).getTaskEventID() == event.getEventID()){
                //For all Users in task's userList
                for(int a=0; a<FileManager.taskList.get(i).getTaskUserList().size(); a++){
                    //if user = current user then return true 
                    if(FileManager.taskList.get(i).getTaskUserList().get(a).getUserID()==FileManager.currentUser){
                        System.out.println("I have an event");
                        System.out.println();
                        return true;
                    }
                }
            } 
        }
        //otherwise return false
        return false;
    }
    
    

    
    private void loadTitle(){
        if(!boxEventArray.isEmpty()){
            executorService = Executors.newCachedThreadPool();//
            for(Events event : boxEventArray){

                executorService.execute(() -> {
                    try{
                        if((event.getStart().equals(box.getDate()) && userIsAnEventsTask(event))|| (event.getEnd().equals(box.getDate())  && userIsAnEventsTask(event))){
                            box.eventArray.add(event);

                            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventTitle.fxml")); //selecting fxml file
                            Pane pane = loader.load(); //loading file
                            EventTitleController controller = loader.getController();//get fxml controller
                            controller.setTitle(event.getEventName());
                            Platform.runLater(() -> {

                               vbDisplay.getChildren().add(pane);
                            });
                        }
                        
                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                });
            }
            executorService.shutdown();

        }
    }
}
