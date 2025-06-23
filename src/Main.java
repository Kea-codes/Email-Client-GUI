import java.io.File;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ClientGUI clientGUI = new ClientGUI();
        // creating the scene
        Scene scene = new Scene(clientGUI, 500, 500);
        // setting the scene
        primaryStage.setScene(scene);
        // Title of the stage
        primaryStage.setTitle("E-MAIL");
        // showing the stage
        primaryStage.show();
        
       
    }
}
