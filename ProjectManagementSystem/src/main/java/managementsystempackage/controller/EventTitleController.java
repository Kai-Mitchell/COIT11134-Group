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
 * FXML Controller class
 *
 * Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B

 */
public class EventTitleController{
     @FXML
    private Text txtTitle;
    
    //set data
    public void setTitle(String title){
        //check length and display text basing on lenght
        if(title.length() <= 10){
            this.txtTitle.setText(title);
        }else{
            this.txtTitle.setText(title.substring(0, 10)+"..");
        }
    }
}
