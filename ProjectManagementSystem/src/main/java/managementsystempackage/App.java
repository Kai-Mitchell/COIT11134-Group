package managementsystempackage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.layout.Pane;

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

    public static void main(String[] args) {
        launch();
    }

}
