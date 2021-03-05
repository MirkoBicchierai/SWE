import java.util.*;
import java.sql.*;

public class Programma {

    private Programma() {}

    private int activeID;
    private ArrayList<Utenti> users = new ArrayList<>();
    private ArrayList<Articolo> articles = new ArrayList<>();
    private ArrayList<Catalogo> catalogs = new ArrayList<>();
    private ArrayList<Clienti> customers = new ArrayList<>();
    private ArrayList<Ordini> orders = new ArrayList<>();
    private CentroNotifiche notCenter;
    private static Programma instance;
    private Connection c = DBConnection.getInstance();

    public void login(int id, String psw) {

    }

    public static Programma getInstance() {
        if (instance==null) {
            instance = new Programma();
            instance.load();
        }
        return instance;
    }

    public void load() {


    }

    public void upload() {
        String sql ="";
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            sql = "DELETE FROM User WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM OrderHead WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM Notification WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM Customer WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM CatalogRow WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM CatalogHead WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM Article WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
            sql = "DELETE FROM ArticleCompound WHERE 1=1;";
            stmt.executeUpdate(sql);
            c.commit();
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
            try {
                stmt.close();
                c.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        sql = "";
        for (Utenti user : users) {
            try {
                sql = "INSERT INTO User (Id,Name,PasswordHash,Type,CommissionPerc) " + "VALUES ("+user+", "+user+", "+user+", "+user+", "+user+" );";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                System.out.println(user);
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                System.exit(0);
                try {
                    stmt.close();
                    c.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        sql = "";
        for (Articolo article : articles) {
            sql = "";
            System.out.println(article);
        }

        sql = "";
        for (Catalogo catalog : catalogs) {
            sql = "";
            System.out.println(catalog);
        }

        sql = "";
        for (Clienti customer : customers) {
            sql = "";
            System.out.println(customer);
        }

        sql = "";
        for (Ordini order : orders) {
            sql = "";
            System.out.println(order);
        }

    }

}