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
import managementsystempackage.model.IEventListener;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
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
 * @author renza
 */
public class userEventCardController implements Initializable {


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

        formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");

    }

    
    @FXML
    private void click(MouseEvent mouseEvent){
        listenerInterface.onClickEvent(event);
    }
    
    public void setCardData(Events event, IEventListener listener){
        completedTasks = 0;
        this.event = event;
        this.listenerInterface = listener;
        txtEventTitle.setText(event.getEventName());
        txtTotalTasks.setText(String.valueOf(event.getNumberOfTasks()));
        txtEventDueDate.setText(event.getEnd().format(formatter));
        for (Task t : FileManager.taskList){
            if (t.getTaskEventID()==event.getEventID() && t.isCompleted()){
                completedTasks++;
            }
        }
        txtCompletedTasks.setText(Integer.toString(completedTasks));
        
    }
    
}