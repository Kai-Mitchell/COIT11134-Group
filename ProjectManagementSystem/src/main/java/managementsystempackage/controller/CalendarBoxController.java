/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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


public class CalendarBoxController implements Initializable {
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
            executorService = Executors.newCachedThreadPool();//
            for(Events event : boxEventArray){

                executorService.execute(() -> {
                    try{
                        if((event.getStart().equals(box.getDate())  && !FileManager.DoesEventHaveCompletedTasks(event)) || (event.getEnd().equals(box.getDate())   && !FileManager.DoesEventHaveCompletedTasks(event))){

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
