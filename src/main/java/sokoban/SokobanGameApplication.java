package sokoban;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Responsible for launching the application.
 */
public class SokobanGameApplication extends Application {
    /**
     * Starting the application.
     * @param stage where to start the application
     * @throws IOException if any IOException occurs
     */
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/opener.fxml"));
        stage.setTitle("Sokoban");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
