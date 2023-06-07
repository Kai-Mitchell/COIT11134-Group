/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;
import managementsystempackage.App;
import managementsystempackage.model.Events;
import managementsystempackage.model.IEventListener;
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
    @FXML
    private AnchorPane sidePane1;
    
    private boolean sidePane1IsShowing;
    private ArrayList<Events> eventArray = new ArrayList<>();
    private IEventListener<Events> iEventListener;
    private boolean disableAnimation;
    private Events currentEvent;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        disableAnimation = false; // Disable user interaction during animation
        sidePane1IsShowing = true;
        sidePane1.setVisible(!sidePane1IsShowing);
        showSidePane1();
        fillEventArray();
        addItems();
    } 
    
     
   
    
    private void addItems(){
        ExecutorService executorService = Executors.newCachedThreadPool();//
        
        
        if(!eventArray.isEmpty()){
           // getChosenCardView(cardViewArray.get(0));
            iEventListener = new IEventListener<>() {

                @Override
                public void onClickEvent(Events event) {
                    getClickedEvent(event);
                    if(currentEvent != null && currentEvent.equals(event) || !sidePane1IsShowing){
                        sidePane1.setVisible(true);

                        showSidePane1();
                        
                    }
                    System.out.println("SidePane is Showing: "+ sidePane1IsShowing);
                    currentEvent = event;
                    
                }

                @Override
                public void onClickDelete(Events event) {
                    //ToDo - Implement delete functionally
                    System.out.println("Deleting...");
                }
                
                
            };
            
            for(Events e : eventArray ){
                final Events event = e;
                //execute task on a separete thread
                 executorService.execute(() -> {
                    try{
                    FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventCard.fxml")); //selecting fxml file
                    Pane pane = loader.load(); //loading file
                    EventCardController controller = loader.getController();//get fxml controller
                    controller.setCardData(event, iEventListener); //send data to controller

                     Platform.runLater(() -> {

                       vbDisplay.getChildren().add(pane);
                    });

                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }


                });

            }
            executorService.shutdown();
        }

    }
    
    private void showSidePane1() {
        if(!disableAnimation){
            disableAnimation = true;
            translationAnimation(0.5, sidePane1, 764, sidePane1IsShowing, () -> disableAnimation = false);
            sidePane1IsShowing = !sidePane1IsShowing;
        
        }
    }

    private void translationAnimation(double duration, Node node, double width, boolean isOpen, Runnable onFinished) {
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node);
        translateTransition.setByX(isOpen ? width : -width);
        translateTransition.setOnFinished(event -> {
            //prevent animation from breaking
            if (onFinished != null) {
                onFinished.run();//make pane animetable
            }
        });
        translateTransition.play();
    }
    
    private void getClickedEvent(Events event){
        
        System.out.println("Clicked "+event.getEventName());
    }
    
    //Temporary func that represents data
    private void fillEventArray(){
        final LocalDate currentDate =  LocalDate.now();
        for(int i = 0; i < 10; i++){
            eventArray.add(new Events("Hello, World!! "+i, currentDate, currentDate));
        }
    }
    
    
    
}
