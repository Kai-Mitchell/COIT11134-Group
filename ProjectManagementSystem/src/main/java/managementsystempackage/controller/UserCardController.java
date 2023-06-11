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

import javafx.scene.text.Text;
import managementsystempackage.model.IEventListener;
import managementsystempackage.model.User;
//Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B
//The Class controls the view User Completed Events View.
public class UserCardController implements Initializable {
    private IEventListener iUserListener;
    private User user;
    @FXML
    private Text txtName;
    
    @FXML
    private Button btnAddUser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    //Add or remove user frome event
    @FXML
    private void addOrRemoveUser(ActionEvent event) {
        //add user
        if(btnAddUser.getText().equals("Add User")){
            iUserListener.onClickEvent(user);
            btnAddUser.setText("Remove User");
            
        }else{
            //remove user
            iUserListener.onClickDelete(user);
            btnAddUser.setText("Add User");
        }
    }
    
    public void setDate(User user, IEventListener iUserListener, String btnName){
        //tODO change btn txt from task
        this.user = user;
        this.iUserListener = iUserListener;
        txtName.setText(user.getUsername());
        btnAddUser.setText(btnName);
    }

}
