package neko;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Neko using FXML.
 */
public class Main extends Application {

    // Creates a new Neko instance and save data into file path.
    private Neko neko = new Neko("data/neko.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main
                    .class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setNeko(neko); // inject the Neko instance
            stage.show();

            fxmlLoader.<MainWindow>getController().showGreetingBox();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
