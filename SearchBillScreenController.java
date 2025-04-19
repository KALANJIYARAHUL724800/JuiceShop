/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import BackEnd.Query_Report;
import PojoClass.Pojo_Report;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author KL RAHUL
 */
public class SearchBillScreenController implements Initializable {
    @FXML
    private TextField billEntry;
    @FXML
    private TableView<PojoClass.Pojo_Report> table_data;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Integer> billno_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, String> cusname_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Long> cusmobileno_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Integer> salseman_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, String> itemname_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Integer> qty_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Double> price_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Double> netvalue_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Integer> itemcode_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, String> date_Col;
    @FXML
    private Label total_amount_label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OK_Button(ActionEvent event) {
        PojoClass.Pojo_Report obj_pojo = new Pojo_Report();
        obj_pojo.setBillno(Integer.parseInt(billEntry.getText()));
        Query_Report obj = new Query_Report();
        
         ArrayList<PojoClass.Pojo_Report> list = obj.SearchBill_Details(obj_pojo);

        ObservableList table_list = FXCollections.observableArrayList();
        table_data.setItems(table_list);
        table_list.addAll(list);

        billno_Col.setCellValueFactory(new PropertyValueFactory<>("billno"));
        cusname_Col.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        cusmobileno_Col.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        salseman_Col.setCellValueFactory(new PropertyValueFactory<>("salseman_name"));
        itemname_Col.setCellValueFactory(new PropertyValueFactory<>("juice_name"));
        qty_Col.setCellValueFactory(new PropertyValueFactory<>("qty"));
        price_Col.setCellValueFactory(new PropertyValueFactory<>("price"));
        netvalue_Col.setCellValueFactory(new PropertyValueFactory<>("netvalue"));
        date_Col.setCellValueFactory(new PropertyValueFactory<>("date"));
        itemcode_Col.setCellValueFactory(new PropertyValueFactory<>("itemcode_id"));
    }

    @FXML
    private void Print_Button(ActionEvent event) {
    }
    
}
