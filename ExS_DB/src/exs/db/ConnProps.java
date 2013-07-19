package exs.db;

import java.io.Serializable;

/**
 *
 * @author Kevin Villalobos A.
 */
public class ConnProps implements Serializable {

    public ConnProps() {
    }

    @Override
    public String toString() {
        return driver + " " + db + " " + user + " " + ip + " " + port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }
    private String url = "jdbc:mysql://";
    private String driver = "com.mysql.jdbc.Driver";
    private String user = "";
    private String password = "";
    private String db = "";
    private String ip = "localhost";
    private String port = "3306";
}
