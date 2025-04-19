package BackEnd;

import java.sql.Connection;

public interface DBConnection {
    public Connection SQLConnection(String driver,String url,String user,String password);
}
