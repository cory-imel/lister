import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class DatabaseTest {
    public static void main(String[] args) throws SQLException {

        Config config = new Config();
        DriverManager.registerDriver(new Driver());

        Connection connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
        );

        showItems(connection);

        addItem(connection, "Spinach");
        addItem(connection, "JavaScript");
        addItem(connection, "Red Hot Chili Peppers");

        showItems(connection);
    }

    public static void showItems(Connection connection) throws SQLException {
        String selectQuery = "SELECT * FROM items";

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery(selectQuery);

        while (rs.next()) {
            System.out.println("id: " + rs.getLong("id"));
            System.out.println("title: " + rs.getString("name"));
        }
    }

    public static void addItem(Connection connection, String item) throws SQLException {

        String query = "INSERT INTO items (name) VALUES ('" + item + "');";

        Statement stmt = connection.createStatement();

        stmt.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
    }

}