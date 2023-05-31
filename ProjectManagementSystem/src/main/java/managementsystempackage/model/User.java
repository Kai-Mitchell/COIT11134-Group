package managementsystempackage.model;
public class User {

    private int userID;
    private String username;
    private String password;
    private boolean isAdmin;

    public User(int userID,String username,String password)
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.isAdmin = false;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

}