/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package managementsystempackage.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import managementsystempackage.App;
import static managementsystempackage.model.FileManager.userCount;
import static managementsystempackage.model.FileManager.userList;
import managementsystempackage.model.*;

/**
 * FXML Controller class
 *
 * @author renza
 */
public class LoginPageController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;
    
    @FXML
    private void gotoCalendar(ActionEvent event) throws IOException {
        
        for (int i = 0; i < userCount; i++){
            if (userList.get(i).getUsername().equals(usernameField.getText()) && userList.get(i).getPassword().equals(passwordField.getText())){
                FileManager.currentUser = userList.get(i).getUserID();
                System.out.println(userList.get(i).getUsername());
                if (userList.get(i).getIsAdmin() == true ){
                    App.setRoot("view/calendar");
                }                    
            }
        }
        if (FileManager.currentUser == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Login Information");
            alert.setContentText("No users have that Login Information. Please try again with the correct login information");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
    
}
