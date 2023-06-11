/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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

/**
 * FXML Controller class
 *
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class CalendarBoxController {
    @FXML
    private Text txtNumber;

    @FXML
    private VBox vbDisplay;
    private CalendarBox box;
    private ArrayList<Events> boxEventArray = new ArrayList<>();
    private IEventListener listenerInterface;
    private ExecutorService executorService;
    
    @FXML
    private void click(MouseEvent mouseEvent){
        //send box data
        listenerInterface.onClickEvent(box);
        
    }
    public void setBoxInfo(CalendarBox box, IEventListener listenerInterface){
        //get and set box data
        this.box = box;
        this.listenerInterface = listenerInterface;
        this.txtNumber.setText(String.valueOf(box.getBoxNumber()));
        
        boxEventArray = FileManager.eventList;
        loadTitle();

    }
    
    public void changeColor(){
        
        txtNumber.getStyleClass().add("sunday-color");

    }
    
    public boolean DoesEventHaveCompletedTasks(Events event){
        //For All tasks
        for(int i = 0; i<FileManager.taskCount; i++){
            //if task's eventID = event eventID
            if(FileManager.taskList.get(i).getTaskEventID() == event.getEventID()){
                if(!FileManager.taskList.get(i).isCompleted()){
                    event.setIsComplete(false);
                    return false;  
                }
            }
        }
        //otherwise return false
        event.setIsComplete(true);
        return true;
    }

    
    private void loadTitle(){
        if(!boxEventArray.isEmpty()){
            executorService = Executors.newCachedThreadPool();//initialize thread
            //for each event get name
            for(Events event : boxEventArray){
                //run thread
                executorService.execute(() -> {
                    
                    try{
                        //make sure the event is not complete
                        if((event.getStart().equals(box.getDate())  && !FileManager.DoesEventHaveCompletedTasks(event)) || (event.getEnd().equals(box.getDate())   && !FileManager.DoesEventHaveCompletedTasks(event))){
                            
                            box.eventArray.add(event);//Add the event to box array
                            //load title card
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventTitle.fxml")); //selecting fxml file
                            Pane pane = loader.load(); //loading file
                            EventTitleController controller = loader.getController();//get fxml controller
                            controller.setTitle(event.getEventName());
                            Platform.runLater(() -> {
                                //display title card
                               vbDisplay.getChildren().add(pane);
                            });
                        }
                        
                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                });
            }
            //restore resources
            executorService.shutdown();

        }
    }
}
