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
    private Controller   controller;

    public Database(String userName, String password, String connectionUrl) throws SQLException, ClassNotFoundException {
        System.out.println("DATABASE: Initialization...");
        this.userName      = userName;
        this.password      = password;
        this.connectionUrl = connectionUrl;
    }

    public void connect() throws ClassNotFoundException, SQLException {
        System.out.println("DATABASE: Try to connect...");
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(connectionUrl, userName, password);
        statement = connection.createStatement();
        System.out.println("DATABASE: Connected...");
    }

    public void closeConnection() throws SQLException {
        System.out.println("DATABASE: Try to close connection...");
        connection.close();
        System.out.println("DATABASE: Connection closed...");
    }


    public void executeUpdateQuery(String userNameText, String passwordText) throws SQLException {
        System.out.println("incerc suca");
        statement.executeUpdate("INSERT INTO users (userName, password) VALUES('" + userNameText + "', '" + passwordText +"')");
    }

    public boolean executeSelect(String userNameText, String passwordText) throws SQLException {
        resultSet = statement.executeQuery("SELECT userName, password FROM users WHERE userName = '" + userNameText + "' AND password = '" + passwordText + "'");
        if (resultSet.next()) {
            return true;
        }
        return false;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

}
