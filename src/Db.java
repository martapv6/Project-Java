import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Db {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/proiectjava",
                    "root",
                    "MartasiVlad10aug."
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
