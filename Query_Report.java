package BackEnd;

import PojoClass.Pojo_Billing;
import PojoClass.Pojo_Report;
import com.mysql.cj.protocol.Resultset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Query_Report {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Juice";
    String user = "root";
    String password = "724800";

    public ArrayList<Pojo_Report> billreport_all() {
        ArrayList<PojoClass.Pojo_Report> list = new ArrayList();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from orginal_datas;");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Report obj_pojo = new Pojo_Report();
                obj_pojo.setBillno(rs.getInt(1));
                obj_pojo.setCustomer_name(rs.getString(2));
                obj_pojo.setMobile(rs.getLong(3));
                obj_pojo.setSalseman_name(rs.getString(4));
                obj_pojo.setJuice_name(rs.getString(5));
                obj_pojo.setQty(rs.getInt(6));
                obj_pojo.setPrice(rs.getDouble(7));
                obj_pojo.setNetvalue(rs.getDouble(8));
                obj_pojo.setDate(rs.getString(9));
                obj_pojo.setItemcode_id(rs.getInt(10));
                list.add(obj_pojo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<PojoClass.Pojo_Report> billreport_today(String date) {
        ArrayList<PojoClass.Pojo_Report> list = new ArrayList();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from orginal_datas where date = ?;");
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Report tempPojo = new Pojo_Report();  // Create new object inside the loop
                tempPojo.setBillno(rs.getInt(1));
                tempPojo.setCustomer_name(rs.getString(2));
                tempPojo.setMobile(rs.getLong(3));
                tempPojo.setSalseman_name(rs.getString(4));
                tempPojo.setJuice_name(rs.getString(5));
                tempPojo.setQty(rs.getInt(6));
                tempPojo.setPrice(rs.getDouble(7));
                tempPojo.setNetvalue(rs.getDouble(8));
                tempPojo.setDate(rs.getString(9));
                tempPojo.setItemcode_id(rs.getInt(10));
                list.add(tempPojo);  // Add new object to the list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public ArrayList<Pojo_Report> from_to_old_date(PojoClass.Pojo_Report obj_pojo) {
        ArrayList<PojoClass.Pojo_Report> list = new ArrayList();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("SELECT * FROM orginal_datas WHERE date BETWEEN ? AND ? order by date;");
            ps.setString(1, obj_pojo.getFrom_date());
            ps.setString(2, obj_pojo.getTodate());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Report tempPojo = new Pojo_Report();  // Create new object inside the loop
                tempPojo.setBillno(rs.getInt(1));
                tempPojo.setCustomer_name(rs.getString(2));
                tempPojo.setMobile(rs.getLong(3));
                tempPojo.setSalseman_name(rs.getString(4));
                tempPojo.setJuice_name(rs.getString(5));
                tempPojo.setQty(rs.getInt(6));
                tempPojo.setPrice(rs.getDouble(7));
                tempPojo.setNetvalue(rs.getDouble(8));
                tempPojo.setDate(rs.getString(9));
                tempPojo.setItemcode_id(rs.getInt(10));
                list.add(tempPojo);  // Add new object to the list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pojo_Report billreport_totalamount() {
        Pojo_Report obj_pojo = new Pojo_Report();

        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("SELECT SUM(netvalue) AS total FROM orginal_datas;");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                obj_pojo.setNetvalue(rs.getDouble("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    public PojoClass.Pojo_Report TotalAmount_Perday(String date) {
        PojoClass.Pojo_Report tempPojo = new Pojo_Report();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("SELECT SUM(netvalue) AS total FROM orginal_datas WHERE STR_TO_DATE(date, '%Y-%m-%d') = ?;");
            ps.setString(1, date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                tempPojo.setNetvalue(rs.getDouble("total"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tempPojo;
    }
public ArrayList<Pojo_Report> SearchBill_Details(PojoClass.Pojo_Report obj_pojo) {
        ArrayList<PojoClass.Pojo_Report> list = new ArrayList();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from orginal_datas where billno = ?;");
            ps.setInt(1, obj_pojo.getBillno());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Report tempPojo = new Pojo_Report();  // Create new object inside the loop
                tempPojo.setBillno(rs.getInt(1));
                tempPojo.setCustomer_name(rs.getString(2));
                tempPojo.setMobile(rs.getLong(3));
                tempPojo.setSalseman_name(rs.getString(4));
                tempPojo.setJuice_name(rs.getString(5));
                tempPojo.setQty(rs.getInt(6));
                tempPojo.setPrice(rs.getDouble(7));
                tempPojo.setNetvalue(rs.getDouble(8));
                tempPojo.setDate(rs.getString(9));
                tempPojo.setItemcode_id(rs.getInt(10));
                list.add(tempPojo);  // Add new object to the list
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
