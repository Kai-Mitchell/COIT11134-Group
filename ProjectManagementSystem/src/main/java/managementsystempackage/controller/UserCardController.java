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
/**
 * FXML Controller class
 *
 * @author renza
 */
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
    
    @FXML
    private void addOrRemoveUser(ActionEvent event) {
        if(btnAddUser.getText().equals("Add User")){
            iUserListener.onClickEvent(user);
            btnAddUser.setText("Remove User");
            
        }else{
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
