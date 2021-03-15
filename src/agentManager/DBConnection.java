package agentManager;

import java.sql.Connection;
import java.sql.DriverManager;

public final class DBConnection {

    private static Connection c = null;

    static Connection getInstance(){
        try {
            if( c==null || c.isClosed() ) {
                Class.forName("org.sqlite.JDBC");
                c = DriverManager.getConnection("jdbc:sqlite:db/swe.db");
                c.setAutoCommit(false);
            }
        } catch (Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(-1);
        }
        return c;
    }

    private DBConnection(){}

}
