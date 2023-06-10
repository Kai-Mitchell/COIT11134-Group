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
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
/**
 * FXML Controller class
 *
 * @author Blueb
 */
public class userShowSelectedDateController implements Initializable {


    @FXML
    private VBox vbDisplay;
    @FXML
    private Text txtDate;
    @FXML
    private AnchorPane sidePane1;
    @FXML
    private TextField tfEventTitle;
    @FXML
    private DatePicker dpStartDate;
    @FXML
    private DatePicker dpEndDate;
    @FXML
    private BorderPane TaskBorderPane;
    @FXML
    private VBox taskDisplay;
    @FXML
    private Button btnShowSidePaneCreate;
    @FXML
    private AnchorPane sidePane2;
    @FXML
    private TextField tfDescription;
    @FXML
    private DatePicker dpTaskDueDate;
    @FXML
    private VBox vbDisplayUsers;
    @FXML
    private Button btnDeleteTask1;
    @FXML
    private AnchorPane sidePane3;
    @FXML
    private Button btnBack;
    @FXML
    private TextField tfTitleCreateEvent;
    @FXML
    private DatePicker dpStartDateCreateEvent;
    @FXML
    private DatePicker dpEndDateCreateEvent;
    @FXML
    private BorderPane TaskBorderPane1;
    @FXML
    private VBox taskDisplay1;
    @FXML
    private Button btnAddEvent;
    @FXML
    private AnchorPane sidePaneCreate;
    @FXML
    private TextField tfDescription1;
    @FXML
    private DatePicker dpTaskDueDate1;
    @FXML
    private VBox vbDisplayUsers1;
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
    private void gotoLoginPage(ActionEvent event) {
    }

    @FXML
    private void createEvent(ActionEvent event) {
    }

    @FXML
    private void showPanel1(ActionEvent event) {
    }

    @FXML
    private void showPaneCreateTask(ActionEvent event) {
    }

    @FXML
    private void showPanel2(ActionEvent event) {
    }

    @FXML
    private void showPanel3(ActionEvent event) {
    }

    @FXML
    private void addNewEvent(ActionEvent event) {
    }

    @FXML
    private void createNewTask(ActionEvent event) {
    }

}
