/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseEvent;
import managementsystempackage.model.IEventListener;
import javafx.scene.text.Text;
import managementsystempackage.model.Events;
import managementsystempackage.model.Task;
/**
 * FXML Controller class
 *
 * @author renza
 */
public class TaskCardController implements Initializable {

    private IEventListener listenerInterface;
    private Task task;
    @FXML
    private Text txtTaskName;
    @FXML
    private Text txtTaskRemainingDays;
    
    @FXML
    private RadioButton rbComplete;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(MouseEvent mouseEvent){
        listenerInterface.onClickEvent(task);
    }
    @FXML
    private void delete(MouseEvent mouseEvent){
        listenerInterface.onClickDelete(task);
    }
    
    
     public void setCardData(Task task, IEventListener listener){
        this.task = task;
        this.listenerInterface = listener;
        txtTaskName.setText(task.getTaskName());
        txtTaskRemainingDays.setText(String.valueOf(task.getNextTaskID()));
        //TODO add isComplete 
        rbComplete.setSelected(false);
    }
    
}
