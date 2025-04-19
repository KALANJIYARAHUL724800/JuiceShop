package BackEnd;

import PojoClass.Pojo_Bill_orginal;
import PojoClass.Pojo_Billing;
import PojoClass.Pojo_Salseman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Query_Billing {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Juice";
    String user = "root";
    String password = "724800";

    public ArrayList<Pojo_Billing> Billing_All() {
        ArrayList<Pojo_Billing> list = new ArrayList<>();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from tempbill;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Billing obj_pojo = new Pojo_Billing();
                obj_pojo.setSalseman_name(rs.getString("salseman_name"));
                obj_pojo.setJuice_name(rs.getString("juice_name"));
                obj_pojo.setQty(rs.getInt("qty"));
                obj_pojo.setPrice(rs.getDouble("price"));
                obj_pojo.setNetvalue(rs.getDouble("netvalue"));
                obj_pojo.setDate(rs.getString("date"));
                obj_pojo.setItemcode_id(rs.getInt("id"));
                list.add(obj_pojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pojo_Billing Billing_NetAmount() {
        PojoClass.Pojo_Billing obj_pojo = new Pojo_Billing();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select juice_name,price,id from stock;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj_pojo.setJuice_name(rs.getString("juice_name"));
                obj_pojo.setNetvalue(rs.getDouble("price"));
                obj_pojo.setItemcode_id(rs.getInt("id"));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    
    public Pojo_Billing selectone_Salseman(Pojo_Billing obj_pojo) {
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from salseman where id = ?");
            ps.setInt(1, obj_pojo.getSalseman_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj_pojo.setSalseman_name(rs.getString("salseman_name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }
    
    public Pojo_Billing Total_Amount() {
        Pojo_Billing obj_pojo = new Pojo_Billing();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select sum(netvalue) from tempbill;");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj_pojo.setAmount(rs.getDouble("netvalue"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }
    
    public Pojo_Billing Bill_One_RetriveData(Pojo_Billing obj_pojo) {
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("insert into tempbill(salseman_name,juice_name,qty,price,netvalue,date,id) values(?,?,?,?,?,?,?);");
            ps.setInt(1, obj_pojo.getSalseman_id());
            ps.setString(2, obj_pojo.getJuice_name());
            ps.setInt(3, obj_pojo.getQty());
            ps.setDouble(4, obj_pojo.getPrice());
            ps.setDouble(5, obj_pojo.getNetvalue());
            ps.setString(6, obj_pojo.getDate());
            ps.setInt(7, obj_pojo.getItemcode_id());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }


    public Pojo_Billing Bill_Insert(Pojo_Billing obj_pojo) {
        
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("insert into tempbill(salseman_name,juice_name,qty,price,netvalue,date,id,customer_name,mobileno,billno) values(?,?,?,?,?,?,?,?,?,?);");
            ps.setString(1, obj_pojo.getSalseman_name());
            ps.setString(2, obj_pojo.getJuice_name());
            ps.setInt(3, obj_pojo.getQty());
            ps.setDouble(4, obj_pojo.getPrice());
            ps.setDouble(5, obj_pojo.getNetvalue());
            ps.setString(6, obj_pojo.getDate());
            ps.setInt(7, obj_pojo.getItemcode_id());
            ps.setString(8, obj_pojo.getCustomer_name());
            ps.setLong(9, obj_pojo.getMobile());
            ps.setInt(10, obj_pojo.getBillno());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    
    public void Truncate_Billtemp() {
       
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("truncate tempbill;");
            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public PojoClass.Pojo_Bill_orginal Bill_orginal_billno() {
       PojoClass.Pojo_Bill_orginal obj_pojo = new Pojo_Bill_orginal();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select billno from bill_orginal;");
            ResultSet rs = ps.executeQuery();
           while (rs.next()) {
                obj_pojo.setBillno(rs.getInt("billno"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }
     public PojoClass.Pojo_Bill_orginal Bill_orginal_Insert(PojoClass.Pojo_Bill_orginal obj_pojo) {
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("insert into bill_orginal(billno,customer_name,mobileno) values(?,?,?);");
            ps.setInt(1, obj_pojo.getBillno());
            ps.setString(2, obj_pojo.getCustomer_name());
            ps.setDouble(3, obj_pojo.getMobile_number());
            ps.executeUpdate();
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }
 public Pojo_Billing juicename(Pojo_Billing obj_pojo) {
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select juice_name,price from stock where id=?;");
            ps.setInt(1, obj_pojo.getItemcode_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj_pojo.setJuice_name(rs.getString("juice_name"));
                obj_pojo.setPrice(rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }
}
