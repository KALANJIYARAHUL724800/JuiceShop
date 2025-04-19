/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FrontEnd;

import BackEnd.Query_Billing;
import BackEnd.Query_Salseman;
import BackEnd.Query_Stock;
import static FrontEnd.Main.stage;
import PojoClass.Pojo_Bill_orginal;
import PojoClass.Pojo_Billing;
import PojoClass.Pojo_Salseman;
import PojoClass.Pojo_Stock;
import com.mysql.cj.QueryBindings;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author KL RAHUL
 */
public class BillScreenController implements Initializable {

    public static double amount = 0;

    @FXML
    private TextField salseman_entry;
    @FXML
    private TextField qty_entry;
    @FXML
    private TextField itemcode_entry;
    @FXML
    private TextField search_itemcode_entry;
    @FXML
    private Label total_amount_label;
    @FXML
    private Label code_label;
    @FXML
    private DatePicker date;
    @FXML
    private TableView<Pojo_Billing> tabledata;
    @FXML
    private TableColumn<Pojo_Billing, String> productname_table;
    @FXML
    private TableColumn<Pojo_Billing, Integer> qty_table;
    @FXML
    private TableColumn<Pojo_Billing, Double> price_table;
    @FXML
    private TableColumn<Pojo_Billing, Double> netvalue_table;
    @FXML
    private TableColumn<Pojo_Billing, Integer> itemcode_table;
    @FXML
    private Label salseman_label;
    @FXML
    private TextField customer_nameEntry;
    @FXML
    private TextField mobileno_Entry;
    @FXML
    private TextField billnumber_Generate;

    @FXML
    private VBox billContainer;
    @FXML
    private TextArea billarea;

    public static String jname;
    public static int billno;

    public static ArrayList<Pojo_Billing> bill = new ArrayList();
    ArrayList<Integer> bill_list = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        display();
        billsno();
    }

    @FXML
    public void billcodesearch() {
        try {
            Pojo_Stock stock = new Pojo_Stock();
            BillScreenController.jname = search_itemcode_entry.getText();
            //System.out.println(search_itemcode_entry.getText());
            Parent root = FXMLLoader.load(getClass().getResource("BillCodeSearch.fxml"));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            stage.setScene(scene);
            stage.show();

            search_itemcode_entry.clear();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void billsno() {
        Query_Billing obj = new Query_Billing();

        //bill number generates
        PojoClass.Pojo_Bill_orginal bill = obj.Bill_orginal_billno();
        int temp = bill.getBillno() + 1; // 0 + 1
        bill_list.add(temp); // 0-->index  = 1

        System.out.println(temp + " : temp data base get no values");
        BillScreenController.billno = bill_list.get(0);
        billnumber_Generate.setText(Integer.toString(bill_list.get(0)));
        System.out.println("Array list 0 index value is :" + bill_list.get(0));

    }

    public void display() {

        Query_Billing obj = new Query_Billing();

        ArrayList<Pojo_Billing> list = obj.Billing_All();

        ObservableList table_list = FXCollections.observableArrayList();
        tabledata.setItems(table_list);
        table_list.addAll(list);

        productname_table.setCellValueFactory(new PropertyValueFactory<>("juice_name"));
        itemcode_table.setCellValueFactory(new PropertyValueFactory<>("itemcode_id"));
        price_table.setCellValueFactory(new PropertyValueFactory<>("price"));
        netvalue_table.setCellValueFactory(new PropertyValueFactory<>("netvalue"));
        qty_table.setCellValueFactory(new PropertyValueFactory<>("qty"));

    }

    public void billno_cusname_mobile() {
        PojoClass.Pojo_Bill_orginal obj_pojo = new Pojo_Bill_orginal();
        obj_pojo.setBillno(Integer.parseInt(billnumber_Generate.getText()));
        obj_pojo.setCustomer_name(customer_nameEntry.getText());
        obj_pojo.setMobile_number(Double.parseDouble(mobileno_Entry.getText()));

        Query_Billing obj = new Query_Billing();
        obj.Bill_orginal_Insert(obj_pojo);
    }

    @FXML
    private void Salseman_ReportButton(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SalsemanReportScreen.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void BillReport_Button(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("BillReportScreen.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Juice_Report(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("JuiceReportScreen.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void Salseman_Button(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("SalsemanScreen.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Stock_Button(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("StockScreen.fxml"));

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Add_Button(ActionEvent event) {

        billno_cusname_mobile();
        PojoClass.Pojo_Billing obj_pojo = new Pojo_Billing();

        obj_pojo.setSalseman_id(Integer.parseInt(salseman_entry.getText()));
        obj_pojo.setItemcode_id(Integer.parseInt(itemcode_entry.getText()));
        obj_pojo.setQty(Integer.parseInt(qty_entry.getText()));

        obj_pojo.setSearch_itemcode(search_itemcode_entry.getText());
        //obj_pojo.setDate(date.getValue().toString());

        Query_Billing obj = new Query_Billing();
        Pojo_Billing salse_man = obj.selectone_Salseman(obj_pojo);
        Pojo_Billing juicename = obj.juicename(obj_pojo);

        obj_pojo.setSalseman_name(salse_man.getSalseman_name());
        obj_pojo.setJuice_name(juicename.getJuice_name());
        obj_pojo.setPrice(juicename.getPrice());
        obj_pojo.setNetvalue(Double.parseDouble(qty_entry.getText()) * juicename.getPrice());
        //obj_pojo.setItemcode_id(obj.Billing_NetAmount().getItemcode_id());

        obj_pojo.setBillno(Integer.parseInt(billnumber_Generate.getText()));
        obj_pojo.setCustomer_name(customer_nameEntry.getText());
        obj_pojo.setMobile(Long.parseLong(mobileno_Entry.getText()));
        bill.add(obj_pojo);
        //System.out.println(billnumber_Generate.getText());
        //System.out.println(customer_nameEntry.getText());
        //System.out.println(mobileno_Entry.getText());
//double amt = obj.Total_Amount().getAmount();
        salseman_label.setText(salse_man.getSalseman_name());

        total_amount_label.setText(Double.toString(BillScreenController.amount += obj_pojo.getNetvalue()));
        obj.Bill_Insert(obj_pojo);
        // System.out.println(amt);
        display();

    }

    @FXML
    private void Bill_Button(ActionEvent event) throws IOException {
        //billsno();
        billnumber_Generate.clear();

        //BillScreenController.billno = bill_list.get(0) + 1;
        System.out.println("Bill button billno :" + billno);
        // billnumber_Generate.setText();
        BillScreenController.billno+=1;
        billnumber_Generate.setText(Integer.toString(BillScreenController.billno));
        bill_list.clear();

        BillScreenController.amount = 0;
        total_amount_label.setText("0.00");
        Query_Billing obj = new Query_Billing();
        obj.Truncate_Billtemp();
        salseman_label.setText("");
        salseman_entry.clear();
        qty_entry.clear();
        itemcode_entry.clear();;
        search_itemcode_entry.clear();
        customer_nameEntry.clear();
        mobileno_Entry.clear();
        display();

        billarea();
    }

    public void billarea() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("MyBills.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }
}
