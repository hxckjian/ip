package neko;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Neko neko;

    private Image userImage = new Image(this.getClass()
            .getResourceAsStream("/images/normalsmileyandere.png"));
    private Image nekoImage = new Image(this.getClass()
            .getResourceAsStream("/images/normalsmileMC.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Neko instance */
    public void setNeko(Neko d) {
        neko = d;
    }

    public void showGreetingBox() {
        dialogContainer.getChildren().addAll(
                DialogBox.getNekoDialog(neko.getGreeting(), nekoImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getNekoDialog(neko.run(input), nekoImage)
        );
        userInput.clear();

        if (neko.hasExited()) {
            closeWindowWithDelay(3.0);
        }
    }

    private void closeWindowWithDelay(double seconds) {
        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> {
            Stage stage = (Stage) userInput.getScene().getWindow();
            stage.close();
        });
        delay.play();
    }
}
