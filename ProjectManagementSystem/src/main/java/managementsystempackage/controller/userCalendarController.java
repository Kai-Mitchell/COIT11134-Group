/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Blueb
 */
public class userCalendarController implements Initializable {


    @FXML
    private Button btnPrevious;
    @FXML
    private Button btnNext;
    @FXML
    private Text txtMonthAndYear;
    @FXML
    private Button btnToday;
    @FXML
    private GridPane calendarGrid;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void gotoCalendar(ActionEvent event) {
    }

    @FXML
    private void gotoPlannedEvent(ActionEvent event) {
    }

    @FXML
    private void gotoCompletedEvents(ActionEvent event) {
    }

    @FXML
    private void previous(ActionEvent event) {
    }

    @FXML
    private void next(ActionEvent event) {
    }

    @FXML
    private void current(ActionEvent event) {
    }

}
