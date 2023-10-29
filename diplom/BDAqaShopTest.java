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