package FrontEnd;

import PojoClass.Pojo_Billing;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KL RAHUL
 */
public class LoginScreenController implements Initializable {

    @FXML
    private TextField username_Entry;
    @FXML
    private TextField password_Entry;
    @FXML
    private Label username_label;
    @FXML
    private Label password_label;
    @FXML
    private Label login_label;
    @FXML
    private DatePicker login_date;
    @FXML
    private ProgressBar progressdata;
    public static String date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        progressdata.setProgress(0.0); // Initial progress set to 0
    }

    @FXML
    private void LOGIN(ActionEvent event) {
        if (login_date.getValue() == null) { // Null check
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Login Warning");
            alert.setHeaderText(null);
            alert.setContentText("Please select a date before proceeding!");
            alert.showAndWait();
        } else {
            LoginScreenController.date = login_date.getValue().toString();
            PojoClass.Pojo_Billing obj_pojo = new Pojo_Billing();
            obj_pojo.setDate(login_date.getValue().toString());
            progressdata.setProgress(0.0); // Reset progress

            new Thread(() -> {
                for (int i = 1; i <= 10; i++) {
                    try {
                        Thread.sleep(300); // Simulating login process
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    double progressValue = i / 10.0;

                    // UI update must be on the JavaFX Application Thread
                    Platform.runLater(() -> progressdata.setProgress(progressValue));
                }

                // Opening BillScreen.fxml inside Platform.runLater()
                Platform.runLater(() -> {
                    try {
                        Parent root = FXMLLoader.load(getClass().getResource("BillScreen.fxml"));
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

            }).start();
        }
    }
}
