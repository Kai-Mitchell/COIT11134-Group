/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import managementsystempackage.App;
import managementsystempackage.model.CalendarBox;
import managementsystempackage.model.Events;
import managementsystempackage.model.IEventListener;


public class CalendarBoxController implements Initializable {
    @FXML
    private Text txtNumber;

    @FXML
    private VBox vbDisplay;
    private CalendarBox box = new CalendarBox();
     private ArrayList<Events> boxEventArray = new ArrayList<>();
    private IEventListener listenerInterface;
    ExecutorService executorService;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillEventArray();
        
        if(!boxEventArray.isEmpty()){
            executorService = Executors.newCachedThreadPool();//
            for(Events event : boxEventArray){

                executorService.execute(() -> {
                    try{
                        if(event.getStart().equals(this.box.getDate())){
                            FXMLLoader loader = new FXMLLoader(App.class.getResource("view/eventTitle.fxml")); //selecting fxml file
                            Pane pane = loader.load(); //loading file
//                            EventCardController controller = loader.getController();//get fxml controller

                            Platform.runLater(() -> {

                               vbDisplay.getChildren().add(pane);
                            });
                        }
                        
                    }
                    catch(IOException ioe){
                        ioe.printStackTrace();
                    }
                });
            }
            executorService.shutdown();

        }
       
        
    }
    
    @FXML
    private void click(MouseEvent mouseEvent){
        listenerInterface.onClickEvent(box);
        
    }
    public void setBoxInfo(CalendarBox box, IEventListener listenerInterface){
        this.box = box;
        this.listenerInterface = listenerInterface;
        this.txtNumber.setText(String.valueOf(box.getBoxNumber()));
    }
    
    public void changeColor(){
        txtNumber.getStyleClass().add("sunday-color");

    }

     //Temporary func that represents data
    private void fillEventArray(){
        final LocalDate currentDate = LocalDate.now();
        for(int i = 0; i < 9; i++){
            boxEventArray.add(new Events( "Hello, World!! "+i, currentDate, currentDate));
        }
    }
}
