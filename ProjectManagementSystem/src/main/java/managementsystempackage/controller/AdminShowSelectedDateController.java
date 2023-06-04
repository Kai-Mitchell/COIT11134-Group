/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import managementsystempackage.App;
import managementsystempackage.model.Events;
/**
 * FXML Controller class
 *
 * @author renza
 */
public class AdminShowSelectedDateController implements Initializable {


    @FXML
    private Text txtDate;
    @FXML
    private VBox vbDisplay;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        addItems();
    }    
    
    private void addItems(){
        ExecutorService executorService = Executors.newCachedThreadPool();//
        final Date currentDate = new Date();
        for(int i = 0; i < 10; i++ ){
            final int index = i;
            //execute task on a separete thread
             executorService.execute(() -> {
                try{
                FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventCard.fxml")); //selecting fxml file
                Pane pane = loader.load(); //loading file
                EventCardController controller = loader.getController();//get fxml controller
                controller.setCardData(index); //send data to controller
                
                 Platform.runLater(() -> {
                    
                   vbDisplay.getChildren().add(pane);
                });

                }
                catch(IOException e){
                    e.printStackTrace();
                }
                
                
            });
             
        }
        executorService.shutdown();

    }
    
}
