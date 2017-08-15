import java.sql.SQLException;

public class Test {
    private Database database;
    private Window   window;
    private String   userName = "root";
    private String   password = "root";
    private String   connectionUrl = "jdbc:mysql://localhost:3306/userProject?autoReconnect=true&useSSL=false";

    public Test() throws SQLException, ClassNotFoundException {
        window   = new Window();
        database = new Database(userName, password, connectionUrl);
        window.setDatabase(database);
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Test test = new Test();
    }
}
