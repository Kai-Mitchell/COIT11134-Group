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
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import javafx.scene.text.Text;
import managementsystempackage.App;
import managementsystempackage.model.Calendar;
import managementsystempackage.model.CalendarBox;
import managementsystempackage.model.Events;
import managementsystempackage.model.FileManager;
import managementsystempackage.model.IEventListener;

public class userCalendarController implements Initializable {
    @FXML
    private Label usernameCurrent;
    
    @FXML
    private Button btnNext;

    @FXML
    private Button btnPrevious;

    @FXML
    private Button btnToday;

    @FXML
    private GridPane calendarGrid;

    @FXML
    private Text txtMonthAndYear;
    private IEventListener<CalendarBox> iEventListener;

    private Calendar calendar;
    private CalendarBox[] boxArray;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        usernameCurrent.setText(FileManager.currentUsername());
        
        calendar = new Calendar();
        calendarGrid.getChildren().clear();
        boxArray = calendar.getCurrentMonthInfo();
        txtMonthAndYear.setText(calendar.getMonthAndYear());

        iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(CalendarBox box) {
                   
                    try {
                        
                        SceneNavigation<userShowSelectedDateController> sceneNav = new SceneNavigation<>("userShowSelectedDate");//load file
                        sceneNav.getController().setData(box);//Send data
                        sceneNav.gotoScene();//goto file
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    
                }

                @Override
                public void onClickDelete(CalendarBox box) {
                    //ToDo - Implement delete functionally
                    System.out.println("Deleting...");
                }
                
                
            };
        
        loadBoxes();
//        calendarGrid.getChildren().clear();

         
        
        
    }    
    
//    @FXML
//    private void gotoSelectedDate(MouseEvent event) throws IOException {
//        App.setRoot("view/adminShowSelectedDate");
//    }
    
    @FXML
    private void gotoCalendar() throws IOException{
        SceneNavigation.gotoUserCalendar();
    }
   @FXML
   private void gotoAssignedEvents() throws IOException{
       SceneNavigation.gotoAssignedEvents();
   }
   @FXML
   private void gotoUserCompletedEvents() throws IOException{
       SceneNavigation.gotoUserCompletedEvents();
   }
   
   @FXML
   private void gotoLoginPage() throws IOException{
       SceneNavigation.gotoLoginPage();
   }
    
   @FXML
   private void next() throws IOException{
        calendarGrid.getChildren().clear();
        boxArray = calendar.getNextMonthInfo();   
        txtMonthAndYear.setText(calendar.getMonthAndYear());
        loadBoxes();
   }
   
   @FXML
   private void previous() throws IOException{
        calendarGrid.getChildren().clear();
        boxArray = calendar.getPreviousMonthInfo(); 
        txtMonthAndYear.setText(calendar.getMonthAndYear());

        loadBoxes();
   }
   
   
   @FXML
   private void current() throws IOException{
        calendarGrid.getChildren().clear();
        boxArray = calendar.getCurrentMonthInfo();   
        txtMonthAndYear.setText(calendar.getMonthAndYear());

        loadBoxes();
   }
   

    public void setArray(CalendarBox[] boxArray){
        this.boxArray = boxArray;
    }
    
    private void loadBoxes(){
        int columns = 0;
        int rows = 1;
            try {
                //Load cards into the grid
                for(CalendarBox box : boxArray){
            
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/userCalendarBox.fxml"));
                    Pane pane = fxmlLoader.load();
                    userCalendarBoxController controller = fxmlLoader.getController();
                    controller.setBoxInfo(box, iEventListener);

                    if(columns == 6){
                        controller.changeColor();
                    }

                    if(columns == 7){
                        columns =0;
                        rows++;

                    }

                    calendarGrid.add(pane, columns++, rows);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
