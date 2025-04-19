package FrontEnd;

import BackEnd.Query_Stock;
import PojoClass.Pojo_Stock;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author lenovo
 */
public class StockScreenController implements Initializable {

    @FXML
    private Button Updatebuttonname;
    @FXML
    private Button Addbuttonname;
    @FXML
    private Button Deletebuttonname;
    @FXML
    private TableView<PojoClass.Pojo_Stock> tabledata;
    @FXML
    private TableColumn<PojoClass.Pojo_Stock, Integer> table_id;
    @FXML
    private TableColumn<PojoClass.Pojo_Stock, String> table_juicename;
    @FXML
    private TableColumn<PojoClass.Pojo_Stock, Integer> table_qty;
    @FXML
    private TableColumn<PojoClass.Pojo_Stock, Double> table_price;
    @FXML
    private TextField id_entry;
    @FXML
    private TextField juicename_entry;
    @FXML
    private TextField qty_entry;
    @FXML
    private TextField price_entry;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        display();
    }

    public void display(){
        Query_Stock obj = new Query_Stock();
        ArrayList<PojoClass.Pojo_Stock> list = obj.selectall_stock();
        
        ObservableList tablelist = FXCollections.observableArrayList();
        tabledata.setItems(tablelist);
        tablelist.addAll(list);
        
        table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_juicename.setCellValueFactory(new PropertyValueFactory<>("juice_name"));
        table_qty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        table_price.setCellValueFactory(new PropertyValueFactory<>("rate"));
    }
    @FXML
    private void Updatebutton(ActionEvent event) {
        PojoClass.Pojo_Stock obj_pojo = new Pojo_Stock();
        obj_pojo.setId(Integer.parseInt(id_entry.getText()));
        obj_pojo.setJuice_name(juicename_entry.getText());
        obj_pojo.setQty(Integer.parseInt(qty_entry.getText()));
        obj_pojo.setRate(Double.parseDouble(price_entry.getText()));
        
        Query_Stock obj = new Query_Stock();
        obj.update_stock(obj_pojo);
         // Example: Show an alert when the Update button is clicked
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update Action");
        alert.setHeaderText("Update Stock");
        alert.setContentText("Update Successfully!");
        alert.showAndWait();
        display();
    }

    @FXML
    private void AddButton(ActionEvent event) {
        
        PojoClass.Pojo_Stock obj_pojo = new Pojo_Stock();
        obj_pojo.setId(Integer.parseInt(id_entry.getText()));
        obj_pojo.setJuice_name(juicename_entry.getText());
        obj_pojo.setQty(Integer.parseInt(qty_entry.getText()));
        obj_pojo.setRate(Double.parseDouble(price_entry.getText()));
        
        Query_Stock obj = new Query_Stock();
        obj.add_stock(obj_pojo);
         // Example: Show an alert when the Add button is clicked
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Add Action");
        alert.setHeaderText("Add Stock");
        alert.setContentText("Add Successfully!");
        alert.showAndWait();
        display();
    }

    @FXML
    private void Deletebutton(ActionEvent event) {
        PojoClass.Pojo_Stock obj_pojo = new Pojo_Stock();
        obj_pojo.setId(Integer.parseInt(id_entry.getText()));
        obj_pojo.setJuice_name(juicename_entry.getText());
        obj_pojo.setQty(Integer.parseInt(qty_entry.getText()));
        obj_pojo.setRate(Double.parseDouble(price_entry.getText()));
        
        Query_Stock obj = new Query_Stock();
        obj.delete_stock(obj_pojo);
         // Example: Show an alert when the Delete button is clicked
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Delete Action");
        alert.setHeaderText("Delete Stock");
        alert.setContentText("Delete Successfully!");
        alert.showAndWait();
        display();
    }

}
