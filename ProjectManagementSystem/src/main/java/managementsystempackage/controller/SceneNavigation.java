/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managementsystempackage.controller;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import managementsystempackage.App;

/**
 *
 * @author renza
 */
public class SceneNavigation <T>{
    private Pane pane;
    private T controller;
    private String fileName;

    public SceneNavigation(String fileName) throws IOException {
        this.fileName = fileName;
        setController();
    }
    
    private void setController() throws IOException {
        FXMLLoader loader = new FXMLLoader(App.class.getResource("view/"+this.fileName+".fxml"));
        this.pane = loader.load();
        this.controller = loader.getController();
        
    }
    
    public T getController(){
        return this.controller;
    }
    
    public void gotoScene() throws IOException{
        App.gotoScene(pane);
    }
    public static void gotoCalendar() throws IOException{
        App.setRoot("view/calendar");
    }
    
    public static void gotoPlannedEvent() throws IOException{
        App.setRoot("view/_adminPlannedEvents");
    }
    
    public static void gotoCompletedEvents() throws IOException{
        App.setRoot("view/adminCompletedEvents");
    }
    
    public static void gotoLoginPage() throws IOException{
        App.setRoot("view/loginPage");
    }
    
}
