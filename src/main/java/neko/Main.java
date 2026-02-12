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
    private static final String FXML_PATH = "/view/MainWindow.fxml";
    private static final String DATA_FILE = "data/neko.txt";
    // Creates a new Neko instance and save data into file path.
    private final Neko neko = new Neko(DATA_FILE);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = createLoader();
            AnchorPane root = loadRoot(loader);
            configureStage(stage, root);
            initializeController(loader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private FXMLLoader createLoader() {
        return new FXMLLoader(
                Main.class.getResource(FXML_PATH));
    }

    private AnchorPane loadRoot(FXMLLoader loader) throws IOException {
        return loader.load();
    }

    private void configureStage(Stage stage, AnchorPane root) {
        stage.setScene(new Scene(root));
        stage.show();
    }

    private void initializeController(FXMLLoader loader) {
        MainWindow controller = loader.getController();
        controller.setNeko(neko);
        controller.showGreetingBox();
    }
}
