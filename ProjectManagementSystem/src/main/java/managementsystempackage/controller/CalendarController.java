package managementsystempackage.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
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
/**
 * FXML Controller class
 *
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class CalendarController implements Initializable {
    
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
    
        calendar = new Calendar();//initialize Calendar object
        calendarGrid.getChildren().clear();//clear existing items 
        boxArray = calendar.getCurrentMonthInfo(); // genereate month data
        txtMonthAndYear.setText(calendar.getMonthAndYear()); //set current month
        
        //Interface that handles click events for calendar boxes
        iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(CalendarBox box) {
                   //load adminshowselecteddate and set box data
                    try {
                        
                        SceneNavigation<AdminShowSelectedDateController> sceneNav = new SceneNavigation<>("adminShowSelectedDate");//load file
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
    
/**
 * 
 * button navigation 
 * @throws IOException 
 */
    
    @FXML
    private void gotoCalendar() throws IOException{
        SceneNavigation.gotoCalendar();
    }
   @FXML
   private void gotoPlannedEvent() throws IOException{
       
       SceneNavigation.gotoPlannedEvent();
   }
   @FXML
   private void gotoCompletedEvents() throws IOException{
       SceneNavigation.gotoCompletedEvents();
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
   
   //get and set box array
    public void setArray(CalendarBox[] boxArray){
        this.boxArray = boxArray;
    }
    
    private void loadBoxes(){
        int columns = 0;
        int rows = 1;
        try {
            //Load cards into the grid
            for(CalendarBox box : boxArray){
                //For each box load and set data
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/calendarBox.fxml"));
                Pane pane = fxmlLoader.load();
                CalendarBoxController controller = fxmlLoader.getController();
                controller.setBoxInfo(box, iEventListener);

                if(columns == 6){
                    //change color for sunday
                    controller.changeColor();
                }

                if(columns == 7){
                    //create new row
                    columns =0;
                    rows++;

                }
                //add box
                calendarGrid.add(pane, columns++, rows);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }   
    }
}
