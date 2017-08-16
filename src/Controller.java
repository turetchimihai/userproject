import java.sql.SQLException;

public class Controller {
    private        Database   database;
    private        Window     window;
    private        String     userName = "root";
    private        String     password = "root";
    private        String     connectionUrl = "jdbc:mysql://localhost:3306/userProject?autoReconnect=true&useSSL=false";
    private static Controller controller;

    static {
        try {
            controller = new Controller();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Controller() throws SQLException, ClassNotFoundException {
        window   = new Window();
        database = new Database(userName, password, connectionUrl);
    }

    public static Controller getController() throws SQLException, ClassNotFoundException {
        return controller;
    }

    public void setController() {
        database.setController(controller);
        window.setController(controller);
    }

    public void connectToDatabase() throws SQLException, ClassNotFoundException {
        database.connect();
    }

    public void disconnectFromDatabase() throws SQLException {
        database.closeConnection();
    }

    public boolean verifyStringLength(String string) {
        return string.length() >= 6;
    }

    public boolean tryToLogin(String userName, String password) throws SQLException {
        System.out.println("DATABASE: Try to login...");
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
        if (database.executeSelect(userName, password) == true) {
            return true;
        }
        return false;
    }

    public boolean tryToRegister(String userName, String password) throws SQLException {
        System.out.println("DATABASE: Try to register...");
        System.out.println("Username: " + userName);
        System.out.println("Password: " + password);
        if (database.executeSelect(userName, password) == true) {
            System.out.println("DATABASE: FAILED! Duplicated");
            return false;
        }
        database.executeUpdateQuery(userName, password);
        return true;
    }
}
