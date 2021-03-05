
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;
        try {

            conn = DriverManager.getConnection("jdbc:sqlite:db/swe.db");
            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
