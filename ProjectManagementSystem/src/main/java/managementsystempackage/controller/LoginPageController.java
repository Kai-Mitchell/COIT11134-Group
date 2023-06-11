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
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class LoginPageController {

    

    @FXML
    private TextField passwordField;

    @FXML
    private TextField usernameField;
    
    @FXML
    private void gotoCalendar(ActionEvent event) throws IOException {
        //check if entered credantials are correct
        for (int i = 0; i < userCount; i++){
            //check if the entered values are correct and are valid
            if (userList.get(i).getUsername().equals(usernameField.getText()) && userList.get(i).getPassword().equals(passwordField.getText())){
                FileManager.currentUser = userList.get(i).getUserID();
                System.out.println(userList.get(i).getUsername());
                
                //check if User is admin and navigate to apropriate scene
                if (userList.get(i).getIsAdmin() == true ){
                    App.setRoot("view/calendar");
                }
                else {
                    App.setRoot("view/userCalendar");
                }
            }
        }
        //if the credantial are wrong, show an error massage
        if (FileManager.currentUser == -1){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Incorrect Login Information");
            alert.setContentText("No users have that Login Information. Please try again with the correct login information");
            Optional<ButtonType> result = alert.showAndWait();
        }
    }
}
