/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package managementsystempackage.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

/**
 *
 * @author renza
 */
public class EventTitleController implements Initializable{
     @FXML
    private Text txtTitle;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void setTitle(String title){
        this.txtTitle.setText(title.substring(0,10 ) + "...");
    }
}
