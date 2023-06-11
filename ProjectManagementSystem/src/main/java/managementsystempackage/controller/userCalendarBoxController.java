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


//Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B
//The Class controls each box in user's calendar to display the events they are assigned to and need to complete
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
    
    //If box is clicked
    @FXML
    private void click(MouseEvent mouseEvent){
        listenerInterface.onClickEvent(box);
        
    }
    //set the box's infomation
    public void setBoxInfo(CalendarBox box, IEventListener listenerInterface){
        this.box = box;
        this.listenerInterface = listenerInterface;
        this.txtNumber.setText(String.valueOf(box.getBoxNumber()));
        //Grabs all the events
        boxEventArray = FileManager.eventList;
        loadTitle();

    }
    
    public void changeColor(){
        txtNumber.getStyleClass().add("sunday-color");

    }

    private void loadTitle(){
        if(!boxEventArray.isEmpty()){
            executorService = Executors.newCachedThreadPool();//
            for(Events event : boxEventArray){
                //for every event

                executorService.execute(() -> {
                    try{
                        //if user is assiged to a task inside the event that is not compeleted display the start and end date ont the calender
                        if((event.getStart().equals(box.getDate()) && FileManager.userIsAnEventsTask(event) && !FileManager.DoesEventHaveCompletedTasks(event)) || (event.getEnd().equals(box.getDate())  && FileManager.userIsAnEventsTask(event) && !FileManager.DoesEventHaveCompletedTasks(event))){
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
