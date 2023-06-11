/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;

import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import managementsystempackage.model.Events;
import managementsystempackage.model.FileManager;
import managementsystempackage.model.IEventListener;
import managementsystempackage.model.Task;
/**
 * FXML Controller class
 *
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class EventCardController implements Initializable {


    @FXML
    private Text txtEventTitle;
    @FXML
    private Text txtEventDueDate;
    @FXML
    private Text txtTotalTasks;
    @FXML
    private Text txtCompletedTasks;
    @FXML
    private Pane deletePane;
    @FXML
    private ImageView deleteImage;
    
    private IEventListener listenerInterface;
    private Events event;
    private  DateTimeFormatter formatter;
    private int completedTasks;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Creating a white rectangle
        ColorInput whiteRectangle = new ColorInput(0, 0, 54, 54, Color.WHITE);

        // Using Blend effect with BlendMode to change deleteImage's color to White
        Blend blend = new Blend();
        blend.setMode(BlendMode.SRC_ATOP);
        blend.setTopInput(whiteRectangle);
        deleteImage.setEffect(blend);
        //changing date format
        formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    }

    
    @FXML
    private void click(MouseEvent mouseEvent) throws IndexOutOfBoundsException{
        
        listenerInterface.onClickEvent(event); //send event
    }
    @FXML
    private void delete(MouseEvent mouseEvent){
        listenerInterface.onClickDelete(event); //send event
    }
    
    //get and set received data
    public void setCardData(Events event, IEventListener listener){
        
        this.event = event;
        this.listenerInterface = listener;
        txtEventTitle.setText(event.getEventName());
        txtTotalTasks.setText(String.valueOf(event.getNumberOfTasks()));
        txtEventDueDate.setText(event.getEnd().format(formatter));
        //check completed tasks
        for (Task t : FileManager.taskList){
            if (t.getTaskEventID()==event.getEventID() && t.isCompleted()){
                completedTasks++;
            }
        }
        txtCompletedTasks.setText(Integer.toString(completedTasks));
    }
    
}
