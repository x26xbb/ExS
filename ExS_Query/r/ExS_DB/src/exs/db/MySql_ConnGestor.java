package exs.db;

import exs.logs.err.Log;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Kevin Villalobos A
 */
public class MySql_ConnGestor extends ConnBase {

    public MySql_ConnGestor(String path) {
        super(path);
    }

    @Override
    public void connect() {
        try {            
            String url = props.getUrl() + props.getIP() + ":" + props.getPort() + "/" + props.getDb();
            Class.forName(props.getDriver());
            conn = DriverManager.getConnection(url, props.getUser(), props.getPassword());
        } catch (ClassNotFoundException | SQLException e) {
            Log.SendLog(e.getMessage());
            Log.SendLog(props.toString());
        }
    }
}
