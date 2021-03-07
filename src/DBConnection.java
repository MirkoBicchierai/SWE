import java.sql.*;
public class DBConnection {

    private static Connection c = null;

    public static Connection getInstance() {
        if(c==null) {
            try {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:db/swe.db");
                c.setAutoCommit(false);
            } catch (Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(-1);
            }
        }
        return c;
    }

    private DBConnection(){}

}
