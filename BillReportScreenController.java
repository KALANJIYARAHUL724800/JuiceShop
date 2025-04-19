/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import BackEnd.Query_Report;
import PojoClass.Pojo_Report;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author KL RAHUL
 */
public class BillReportScreenController implements Initializable {

    @FXML
    private DatePicker from_date;
    @FXML
    private DatePicker end_date;
    @FXML
    private Button Filter_Button;
    @FXML
    private Label total_amount_label;
    @FXML
    private TableView<PojoClass.Pojo_Report> table_data;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Integer> billno_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, String> cusname_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, Long> cusmobileno_Col;
    @FXML
    private TableColumn<PojoClass.Pojo_Report, String> salseman_Col;
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

    public void show() {
        Query_Report obj = new Query_Report();
        Pojo_Report obj_pojo = new Pojo_Report();
        
        ArrayList<PojoClass.Pojo_Report> list = obj.billreport_today(LoginScreenController.date);

        System.out.println(LoginScreenController.date);
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
        
        Pojo_Report objdate = obj.TotalAmount_Perday(LoginScreenController.date);
        total_amount_label.setText(Double.toString(objdate.getNetvalue()));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        show();
        Query_Report obj = new Query_Report();
        Pojo_Report obj_pojo = obj.billreport_totalamount();
        //total_amount_label.setText(Double.toString(obj_pojo.getNetvalue()));
        System.out.println(obj_pojo.getNetvalue());

    }

    @FXML
    private void FilterButton(ActionEvent event) {

        Query_Report obj = new Query_Report();
        PojoClass.Pojo_Report obj_pojo = new Pojo_Report();
        obj_pojo.setFrom_date(from_date.getValue().toString());
        obj_pojo.setTodate(end_date.getValue().toString());

        System.out.println(from_date.getValue().toString());
        System.out.println(end_date.getValue().toString());
        ArrayList<Pojo_Report> list = obj.from_to_old_date(obj_pojo);

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

}
