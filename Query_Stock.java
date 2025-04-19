package BackEnd;

import PojoClass.Pojo;
import PojoClass.Pojo_Stock;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Query_Stock extends MyConnection {

    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Juice";
    String user = "root";
    String password = "724800";

    public ArrayList<Pojo_Stock> selectall_stock() {
        ArrayList<Pojo_Stock> list = new ArrayList<>();
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from stock");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Stock obj_pojo = new Pojo_Stock();
                obj_pojo.setId(Integer.parseInt(rs.getString("id")));
                obj_pojo.setJuice_name(rs.getString("juice_name"));
                obj_pojo.setQty(Integer.parseInt(rs.getString("qty")));
                obj_pojo.setRate(Double.parseDouble(rs.getString("price")));
                list.add(obj_pojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Pojo_Stock> selectone_stock(Pojo_Stock obj_pojo1) {
        ArrayList<Pojo_Stock> list = new ArrayList<>();
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from stock where id = ?");
            ps.setInt(1, obj_pojo1.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Stock obj_pojo = new Pojo_Stock();
                obj_pojo.setId(Integer.parseInt(rs.getString("id")));
                obj_pojo.setJuice_name(rs.getString("juice_name"));
                obj_pojo.setQty(Integer.parseInt(rs.getString("qty")));
                obj_pojo.setRate(Double.parseDouble(rs.getString("price")));
                list.add(obj_pojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pojo_Stock add_stock(Pojo_Stock obj_pojo) {
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("insert into stock(id,juice_name,qty,price)values(?,?,?,?);");
            ps.setInt(1, obj_pojo.getId());
            ps.setString(2, obj_pojo.getJuice_name());
            ps.setInt(3, obj_pojo.getQty());
            ps.setDouble(4, obj_pojo.getRate());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    public Pojo_Stock update_stock(Pojo_Stock obj_pojo) {
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("update stock set juice_name = ?,qty = ? ,price = ? where id = ?;");
            ps.setString(1, obj_pojo.getJuice_name());
            ps.setInt(2, obj_pojo.getQty());
            ps.setDouble(3, obj_pojo.getRate());
            ps.setInt(4, obj_pojo.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    public Pojo_Stock delete_stock(Pojo_Stock obj_pojo) {
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("delete from stock where id = ?;");
            ps.setInt(1, obj_pojo.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }
    
    public ArrayList<Pojo_Stock> selectall_stock_Billcode(Pojo_Stock obj_pojo) {
        ArrayList<Pojo_Stock> list = new ArrayList<>();
        try {
            Query_Stock obj = new Query_Stock();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select juice_name, id, qty, price from stock where juice_name like ?;");
            ps.setString(1, obj_pojo.getJuice_name() + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obj_pojo.setId(Integer.parseInt(rs.getString("id")));
                obj_pojo.setJuice_name(rs.getString("juice_name"));
                obj_pojo.setQty(Integer.parseInt(rs.getString("qty")));
                obj_pojo.setRate(Double.parseDouble(rs.getString("price")));
                list.add(obj_pojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

   
}

