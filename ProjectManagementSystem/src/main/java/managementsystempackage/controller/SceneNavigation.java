/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managementsystempackage.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import managementsystempackage.App;
import managementsystempackage.model.FileManager;

/**
 * FXML Controller class
 *
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class SceneNavigation <T>{
    private Pane pane;
    private T controller;
    private String fileName;

    public SceneNavigation(String fileName) throws IOException {
        this.fileName = fileName; //get filename
        setController();//initialize the controller and pane
    }
    //collect and load fxml file
    private void setController() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/"+this.fileName+".fxml"));
        this.pane = loader.load();
        this.controller = loader.getController();
        
    }
    //return fxml controller
    public T getController(){
        return this.controller;
    }
    //Navigate to provided fxml file
    public void gotoScene() throws IOException{
        App.gotoScene(pane);
    }
    //Navigate to Calendar
    public static void gotoCalendar() throws IOException{
        App.setRoot("view/calendar");
    }
    //Navigate to User Calendar
    public static void gotoUserCalendar() throws IOException{
        App.setRoot("view/userCalendar");
    }
    //Navigate to Admin Planned Events
    public static void gotoPlannedEvent() throws IOException{
        App.setRoot("view/_adminPlannedEvents");
    }
    //Navigate to Admin Completed Events
    public static void gotoCompletedEvents() throws IOException{
        App.setRoot("view/adminCompletedEvents");
    }
    //Navigate to user Completed Events
    public static void gotoUserCompletedEvents() throws IOException{
        App.setRoot("view/userCompletedEvents");
    }
    
    public static void gotoAssignedEvents() throws IOException{
        App.setRoot("view/userAssignedEvents");
    }
    //Navigate to login page
    public static void gotoLoginPage() throws IOException{
        FileManager.currentUser = -1;
        App.setRoot("view/loginPage");
    }
    
}
