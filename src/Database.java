import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.sql.*;

public class Database {
    private String       userName;
    private String       password;
    private String       connectionUrl;
    private Connection   connection;
    private Statement    statement;
    private CachedRowSet cachedRowSet;
    private ResultSet    resultSet;

    public Database(String userName, String password, String connectionUrl) throws SQLException, ClassNotFoundException {
        System.out.println("Initialization...");
        this.userName      = userName;
        this.password      = password;
        this.connectionUrl = connectionUrl;
        cachedRowSet       = new CachedRowSetImpl();
    }

    public void connect() throws ClassNotFoundException, SQLException {
        System.out.println("Try to connect...");
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionUrl, userName, password);
        statement = connection.createStatement();
        System.out.println("Connected...");
    }

    public void closeConnection() throws SQLException {
        System.out.println("Try to close connection...");
        connection.close();
        System.out.println("Connection closed...");
    }

    public boolean verifier(String userName) throws SQLException {
        System.out.println("test");
        boolean result = false;
        resultSet = statement.executeQuery("SELECT userName FROM users");
        cachedRowSet.populate(resultSet);
        while(cachedRowSet.next()) {
            if (cachedRowSet.getString("userName").equals(userName)) {
                result = false;
            } else {
                result = true;
            }
        }
        System.out.println(result);
        return result;
    }

    public void executeUpdateQuery(String userNameText, String passwordText) throws SQLException {
        if (verifier(userNameText) == false) {
            statement.executeUpdate("INSERT INTO users (userName, password) VALUES(mihai123, turetki)");
        }
    }
}
