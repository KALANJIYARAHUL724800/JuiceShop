package BackEnd;

import java.sql.Connection;
import java.sql.DriverManager;

public class MyConnection implements DBConnection {

    @Override
    public Connection SQLConnection(String driver, String url, String user, String password) {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
