package managementsystempackage.model;
/**
 *
 * @author gomez
 */

import java.io.Serializable;
import java.util.Date;

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
