import java.util.*;
import java.sql.*;

public class Programma {

    private Programma() {}

    private int activeID;
    public ArrayList<Utenti> users = new ArrayList<>();
    private ArrayList<Articolo> articles = new ArrayList<>();
    private ArrayList<Catalogo> catalogs = new ArrayList<>();
    public ArrayList<Clienti> customers = new ArrayList<>();
    public ArrayList<Ordini> orders = new ArrayList<>();
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
        int type;
        float perch;
        for (Utenti user : users) {
            try {
                if(!(user instanceof Agenti)) {
                    type = 0;
                    perch = 0;
                }else{
                    type = 1;
                    Agenti  tmp = (Agenti) user;
                    perch = tmp.getCommissionPerc();
                }
                sql = "INSERT INTO User (Id,Name,PasswordHash,Type,CommissionPerc) " + "VALUES ("+user.getId()+", '"+user.getName()+"', '"+user.getPasswordHash()+"', "+type+", "+perch+" );";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                try {
                    stmt.close();
                    c.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        sql = "";
        for (Clienti customer : customers) {
            try {
                sql = "INSERT INTO Customer (id,BusinessName,Country,Email) " + "VALUES ("+customer.getId()+", '"+customer.getBusinessName()+"', '"+customer.getCountry()+"', '"+customer.getEmail()+"');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                try {
                    stmt.close();
                    c.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }

        sql = "";
        for (Ordini order : orders) {
            try {
                sql = "INSERT INTO OrderHead (idHead,idAgent,Total,Commission) " + "VALUES ("+order.getId()+", '"+order.getAgent().getId()+"', '"+order.getTotal()+"', '"+order.getCommissionTot()+"');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                try {
                    stmt.close();
                    c.close();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }

            try {

                for (Articolo article : order.getArticles()) {
                    sql = "INSERT INTO OrderRow (idHead,idArticle) " + "VALUES ("+order.getId()+", "+article.getId()+");";
                    stmt = c.createStatement();
                    stmt.executeUpdate(sql);
                    c.commit();
                }

            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
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


    }

}