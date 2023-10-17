
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHelper {

    private static Connection connection;

    public static Connection connectToMySQL(String url, String username, String password) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public static Connection connectToPostgreSQL(String url, String username, String password) throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public static void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}
Теперь вы можете использовать этот класс в ваших тестах для взаимодействия с базой данных. Пример:

java
Copy code
// AqaShopTest.java
import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AqaShopTest extends BaseTest {

    @Test
    @Description("Test database interaction")
    public void testDatabaseInteraction() throws SQLException {
        // Пример работы с MySQL
        Connection mySQLConnection = DatabaseHelper.connectToMySQL("jdbc:mysql://localhost:3306/your_database", "username", "password");
        ResultSet resultSetMySQL = executeQuery(mySQLConnection, "SELECT * FROM your_table");
        // Обработка результата...

        // Пример работы с PostgreSQL
        Connection postgreSQLConnection = DatabaseHelper.connectToPostgreSQL("jdbc:postgresql://localhost:5432/your_database", "username", "password");
        ResultSet resultSetPostgreSQL = executeQuery(postgreSQLConnection, "SELECT * FROM your_table");
        // Обработка результата...

        DatabaseHelper.closeConnection();
    }

    private ResultSet executeQuery(Connection connection, String query) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(query);
    }
}