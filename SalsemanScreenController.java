package FrontEnd;

import BackEnd.Query_Salseman;
import PojoClass.Pojo_Salseman;
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

public class SalsemanScreenController implements Initializable {

    @FXML
    private TableView<PojoClass.Pojo_Salseman> tabledata;
    @FXML
    private TableColumn<PojoClass.Pojo_Salseman, Integer> table_id;
    @FXML
    private TableColumn<PojoClass.Pojo_Salseman, String> table_salsemanname;
    @FXML
    private Button Add_Button;
    @FXML
    private Button Update_Button;
    @FXML
    private Button Delete_Button;
    @FXML
    private TextField id_entry;
    @FXML
    private TextField salseman_name_entry;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();
    }

    public void display() {
        Query_Salseman obj = new Query_Salseman();
        ArrayList<PojoClass.Pojo_Salseman> list = obj.selectall_Salseman();

        ObservableList tablelist = FXCollections.observableArrayList();
        tabledata.setItems(tablelist);
        tablelist.addAll(list);

        table_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        table_salsemanname.setCellValueFactory(new PropertyValueFactory<>("salseman"));
    }

    @FXML
    private void Update(ActionEvent event) {
        PojoClass.Pojo_Salseman obj_pojo = new Pojo_Salseman();
        obj_pojo.setId(Integer.parseInt(id_entry.getText()));
        obj_pojo.setSalseman(salseman_name_entry.getText());
        Query_Salseman obj = new Query_Salseman();
        obj.update_Salseman(obj_pojo);
        // Example: Show an alert when the Update button is clicked
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update Action");
        alert.setHeaderText("Update SalesMan");
        alert.setContentText("Update Successfully!");
        alert.showAndWait();
        display();
    }

    @FXML
    private void Add(ActionEvent event) {

        PojoClass.Pojo_Salseman obj_pojo = new Pojo_Salseman();
        obj_pojo.setId(Integer.parseInt(id_entry.getText()));
        obj_pojo.setSalseman(salseman_name_entry.getText());
        Query_Salseman obj = new Query_Salseman();
        obj.add_Salseman(obj_pojo);
        // Example: Show an alert when the Add button is clicked
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Add Action");
        alert.setHeaderText("Add SalesMan");
        alert.setContentText("Add Successfully!");
        alert.showAndWait();
        display();
    }

    @FXML
    private void Delete(ActionEvent event) {
        PojoClass.Pojo_Salseman obj_pojo = new Pojo_Salseman();
        obj_pojo.setId(Integer.parseInt(id_entry.getText()));
        obj_pojo.setSalseman(salseman_name_entry.getText());

        Query_Salseman obj = new Query_Salseman();
        obj.delete_Salseman(obj_pojo);
        // Example: Show an alert when the Delete button is clicked
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Delete Action");
        alert.setHeaderText("Delete SalesMan");
        alert.setContentText("Delete Successfully!");
        alert.showAndWait();
        display();
    }

}
