package managementsystempackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import managementsystempackage.model.FileManager;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Pane;
import managementsystempackage.model.FileManager;
import static managementsystempackage.model.FileManager.userCount;
import static managementsystempackage.model.FileManager.userList;

//Made by Kai Mitchell (12160908), Francis Renzaho (12170110), Carlos Gomez Mendez (12116658) COIT11134 Assignment 3B
//This class runs the application
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("view/loginPage"), 1000, 717);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        loadingFiles();
    }
    
    public static void gotoScene(Pane pane){
        scene.setRoot(pane);
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    private static void loadingFiles(){
        try{
            FileManager.readAllFiles(); 
        }catch (IOException | ClassNotFoundException ex) {
        }
        String a = "";
        //Checks each ArrayList if it has data and if so, state the ArrayList.Size() and the last entry into the Array
        if (FileManager.userList.isEmpty()){
            a = String.format("No User Data Found");
        } else{
            a = String.format("Loaded Users: " + FileManager.userList.size());
        }
        if (FileManager.eventList.isEmpty()){
            a = String.format(a + "%nNo Event Data Found");
        } else{
            a = String.format(a + "%nLoaded Events: " + FileManager.eventList.size());
        }
        if (FileManager.taskList.isEmpty()){
            a = String.format(a + "%nNo Task Data Found");
        } else{
            a = String.format(a + "%nLoaded Sales: " + FileManager.taskList.size());
        }
        //popup with Each ArrayList's Data Load Status
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loaded Files");
        alert.setContentText(a);
        Optional<ButtonType> result = alert.showAndWait();
            
    }

    public static void main(String[] args) {
        launch();
    }

}
      