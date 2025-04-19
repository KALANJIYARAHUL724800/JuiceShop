package BackEnd;

import PojoClass.Pojo_Salseman;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Query_Salseman extends MyConnection{
    String driver = "com.mysql.cj.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Juice";
    String user = "root";
    String password = "724800";

    public ArrayList<Pojo_Salseman> selectall_Salseman() {
        ArrayList<Pojo_Salseman> list = new ArrayList<>();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from salseman");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Salseman obj_pojo = new Pojo_Salseman();
                obj_pojo.setId(Integer.parseInt(rs.getString("id")));
                obj_pojo.setSalseman(rs.getString("salseman_name"));
                list.add(obj_pojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public ArrayList<Pojo_Salseman> selectone_Salseman(Pojo_Salseman obj_pojo1) {
        ArrayList<Pojo_Salseman> list = new ArrayList<>();
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("select * from salseman where id = ?");
            ps.setInt(1, obj_pojo1.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                PojoClass.Pojo_Salseman obj_pojo = new Pojo_Salseman();
                obj_pojo.setId(Integer.parseInt(rs.getString("id")));
                obj_pojo.setSalseman(rs.getString("salseman_name"));
                list.add(obj_pojo);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public Pojo_Salseman add_Salseman(Pojo_Salseman obj_pojo) {
        try {
           MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("insert into salseman(id,salseman_name)values(?,?);");
            ps.setInt(1, obj_pojo.getId());
            ps.setString(2, obj_pojo.getSalseman());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    public Pojo_Salseman update_Salseman(Pojo_Salseman obj_pojo) {
        try {
            MyConnection obj = new MyConnection();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("update salseman set salseman_name = ? where id = ?;");
            ps.setString(1, obj_pojo.getSalseman());
            ps.setInt(2, obj_pojo.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }

    public Pojo_Salseman delete_Salseman(Pojo_Salseman obj_pojo) {
        try {
            Query_Salseman obj = new Query_Salseman();
            Connection con = obj.SQLConnection(driver, url, user, password);
            PreparedStatement ps = con.prepareStatement("delete from salseman where id = ?;");
            ps.setInt(1, obj_pojo.getId());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj_pojo;
    }    
}
