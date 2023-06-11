package managementsystempackage.model;
/**
 *
 * @author gomez
 */

import java.io.Serializable;
import java.util.Date;
//Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B
//This class is the Super Class User and has the subclass ProjectManager
public class User implements Serializable{
    private int userID;
    private String username;
    private String password;
    private boolean isAdmin;
    private static int nextUserID = 1001;

    public User (String username, String password) { 
        this.username = username;
        this.password = password;
        this.isAdmin = false;
        setUserID();
    }


    
    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }
    
    public int getNextUserID(){
        return nextUserID;
    }

    public void setUserID() {
        userID = nextUserID; // set id to next available id
        nextUserID++;
    }

    public static void setNextUserID(int nextUserID)
   {
      User.nextUserID = nextUserID;
   }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsAdmin(boolean admin) {
        isAdmin = admin;
    }
       
    public void completeTask()
    {

    }
    public void Login()
    {

    }
   
}
