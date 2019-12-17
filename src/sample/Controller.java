package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button btnHello;
    @FXML
    private Button btnBye;

    @FXML
    public void initialize() { //is called automatically when application is being initialized, cannot have parameters
        btnHello.setDisable(true);
        btnBye.setDisable(true);
    }

    @FXML
    public void onButtonClicked(ActionEvent e) {
        if (e.getSource().equals(btnHello)) {
            System.out.println("Hello, " + nameField.getText());
            System.out.println("The following button wa pressed: " + e.getSource());
        } else if (e.getSource().equals(btnBye)) {
            System.out.println("Bye, " + nameField.getText());
            System.out.println("The following button wa pressed: " + e.getSource());
        }
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButton = text.isEmpty() || text.trim().isEmpty();
        btnHello.setDisable(disableButton);
        btnBye.setDisable(disableButton);

    }
}
