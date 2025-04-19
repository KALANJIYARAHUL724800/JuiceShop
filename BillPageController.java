package FrontEnd;

import static FrontEnd.BillScreenController.bill;
import PojoClass.Pojo_Billing;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 * FXML Controller class
 *
 * @author KL RAHUL
 */
public class BillPageController implements Initializable {

    @FXML
    private AnchorPane BillData;
    @FXML
    private ScrollPane data;

    private VBox billContainer; // 👈 Declare Here

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        billContainer = new VBox();
        billContainer.setSpacing(5);
        data.setContent(billContainer);
        addBillItems();
    }

    private void addBillItems() {
/*        ArrayList<Pojo_Billing> bill = BillScreenController.bill;

        String line = "--------------------------------------";
        addLabel(line, true);
        addLabel(String.format("%-15s %-10s %s", "Item", "Qty", "Amount"), true);
        addLabel(line, true);

        for (Pojo_Billing item : bill) {
            String billLine = String.format("%-15s %-10d %.2f", item.getJuice_name(), item.getQty(), item.getNetvalue());
            addLabel(billLine, false);
        }

        addLabel(line, true);*/
        
         ArrayList<Pojo_Billing> bill = BillScreenController.bill;

    double totalAmount = 0; // 👈 Total amount store panna variable

    String line = "--------------------------------------";
    addLabel(line, true);
    addLabel(String.format("%-15s %-10s %s", "Item", "Qty", "Amount"), true);
    addLabel(line, true);

    for (Pojo_Billing item : bill) {
        double itemTotal = item.getNetvalue();
        totalAmount += itemTotal; // 👈 Total amount ku add pannuren
        
        String billLine = String.format("%-15s %-10d %.2f", item.getJuice_name(), item.getQty(), itemTotal);
        addLabel(billLine, false);
    }
    
    addLabel(line, true);
    addLabel(String.format("Total Amount: ₹%.2f", totalAmount), true); // 👈
    }

    private void addLabel(String text, boolean bold) {
        Label label = new Label(text);
        label.setFont(Font.font("Courier New", bold ? FontWeight.BOLD : FontWeight.NORMAL, 14)); // 👈 Monospace Font
        billContainer.getChildren().add(label);
    }

    @FXML
    private void OK(ActionEvent event) {
        System.out.println("Bill Confirmed!");
    }

    @FXML
    private void Cancel(ActionEvent event) {
        System.out.println("Bill Cancelled!");
    }
}
