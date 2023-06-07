package managementsystempackage.controller;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */


import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.VPos;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;
import managementsystempackage.App;
import managementsystempackage.model.Calendar;
import managementsystempackage.model.CalendarBox;
import managementsystempackage.model.Events;
import managementsystempackage.model.IEventListener;

public class CalendarController implements Initializable {
    @FXML
    private GridPane calendarGrid;
    private IEventListener<CalendarBox> iEventListener;

    private Calendar calendar;
    private CalendarBox[] boxArray;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        calendar = new Calendar();
        boxArray = calendar.getCurrentMonthInfo();
        iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(CalendarBox box) {
                   
                    System.out.println("click ");
                    
                    
                }

                @Override
                public void onClickDelete(CalendarBox box) {
                    //ToDo - Implement delete functionally
                    System.out.println("Deleting...");
                }
                
                
            };
       
         int columns = 0;
        int rows = 1;
            try {
                //Load cards into the grid
                for(CalendarBox box : boxArray){
            
                    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("view/calendarBox.fxml"));
                    Pane pane = fxmlLoader.load();
                    CalendarBoxController controller = fxmlLoader.getController();
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
    
//    @FXML
//    private void gotoSelectedDate(MouseEvent event) throws IOException {
//        App.setRoot("view/adminShowSelectedDate");
//    }

    public void setArray(CalendarBox[] boxArray){
        this.boxArray = boxArray;
    }
    
}
