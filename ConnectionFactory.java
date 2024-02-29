import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection = null;

    private ConnectionFactory() {

    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                ResourceBundle bundle = ResourceBundle.getBundle("config");
                String url = bundle.getString("url");
                String userName = bundle.getString("username");
                String password = bundle.getString("password");
                connection = DriverManager.getConnection(url, userName, password);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}