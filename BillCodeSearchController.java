/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author KL RAHUL
 */
public class BillCodeSearchController implements Initializable {

    @FXML
    private TableView<?> table_data;
    @FXML
    private TableColumn<Pojo_Stock, String> juice_name;
    @FXML
    private TableColumn<Pojo_Stock, Integer> juice_code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
    }

    public void show() {
        PojoClass.Pojo_Stock obj_pojo = new Pojo_Stock();
         obj_pojo.setJuice_name(BillScreenController.jname);
         
        Query_Stock obj = new Query_Stock();
        ArrayList<Pojo_Stock> list = obj.selectall_stock_Billcode(obj_pojo);

        ObservableList table_list = FXCollections.observableArrayList();
        table_data.setItems(table_list);
        table_list.addAll(list);
        
        juice_name.setCellValueFactory(new PropertyValueFactory<>("juice_name"));
        juice_code.setCellValueFactory(new PropertyValueFactory<>("id"));
    }

    @FXML
    private void OK_Button(ActionEvent event) {
    }

    @FXML
    private void Cancel_Button(ActionEvent event) {
    }

}
