package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private TextField nameField;
    @FXML
    private Button btnHello;
    @FXML
    private Button btnBye;
    @FXML
    private CheckBox ourCheckBox;
    @FXML
    private Label ourLabel;

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

        // Way to update ui elements that take a while to process (db for example) without locking/freezing the ui
        Runnable task = new Runnable() {
            @Override
            public void run() {
                try {
                    String s = (Platform.isFxApplicationThread()) ? "UI Thread" : "Background Thread";
                    System.out.println("I'm going to sleep on the: " + s);
                    Thread.sleep(10000);
                    Platform.runLater(new Runnable() { // has to be done this way to run on the ui thread
                        @Override
                        public void run() {
                            String s = (Platform.isFxApplicationThread()) ? "UI Thread" : "Background Thread";
                            System.out.println("I'm updating the label on the: " + s);
                            ourLabel.setText("We did something");
                        }
                    });
                } catch (InterruptedException e) {
                    // we do not care about this
                }
            }
        };

        new Thread(task).start();

        // End ui-thread part

        if(ourCheckBox.isSelected()) {
            nameField.clear();
            btnHello.setDisable(true);
            btnBye.setDisable(true);
        }
    }

    @FXML
    public void handleKeyReleased() {
        String text = nameField.getText();
        boolean disableButton = text.isEmpty() || text.trim().isEmpty();
        btnHello.setDisable(disableButton);
        btnBye.setDisable(disableButton);
    }

    @FXML
    public void handleChanged() {
        System.out.println("The checkobox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
    }
}
