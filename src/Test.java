import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Controller controller = Controller.getController();
        controller.setController();
    }
}
