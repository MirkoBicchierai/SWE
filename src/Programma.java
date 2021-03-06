import java.util.*;
import java.sql.*;

public class Programma {

    private Programma() {}

    private int activeID;
    public ArrayList<Utenti> users = new ArrayList<>();
    public ArrayList<Articolo> articles = new ArrayList<>();
    public ArrayList<Catalogo> catalogs = new ArrayList<>();
    public ArrayList<Clienti> customers = new ArrayList<>();
    public ArrayList<Ordini> orders = new ArrayList<>();
    public CentroNotifiche notCenter;
    private static Programma instance;
    private Connection c = DBConnection.getInstance();

    public void login(int id, String psw) {
        System.out.println("LOGIN");
    }

    public static Programma getInstance() {
        if (instance==null) {
            instance = new Programma();
            instance.load();
        }
        return instance;
    }

    public void load() throws SQLException {

        Statement stmt = c.createStatement();
        ResultSet rs,rs1 = null;

        rs = stmt.executeQuery( "SELECT * FROM Customer;" );
        while ( rs.next() ) {
            int id = rs.getInt("id");
            String  businessName = rs.getString("BusinessName");
            String  country = rs.getString("Country");
            String  email = rs.getString("Email");

            customers.add(new Clienti(id,businessName,country,email));
        }

        CentroNotifiche notCenter = CentroNotifiche.getInstance();
        rs = stmt.executeQuery( "SELECT * FROM Notification;" );
        while ( rs.next() ) {
            String  message = rs.getString("message");

            notCenter.addNotification(message);
        }

        rs = stmt.executeQuery( "SELECT * FROM User;" );
        while ( rs.next() ) {
            int id = rs.getInt("id");                             //1 agente - 0 amministratore
            String  name = rs.getString("Name");
            String  passHash = rs.getString("Passwordhash");
            int type = rs.getInt("Type");
            float commissionPerc = rs.getFloat("CommissionPerc");

            users.add(type==1?new Agenti(name, passHash, commissionPerc) : new Amministratori(name, passHash));
        }

        rs = stmt.executeQuery( "SELECT * FROM CatalogHead;" );
        while ( rs.next() ) {
            int id = rs.getInt("idHead");
            String name = rs.getString("Description");
            String passHash = rs.getString("MarketZone");

            rs1 = stmt.executeQuery( "SELECT * FROM CatalogRow WHERE IdHead = "+id+" ;" );
            while ( rs1.next() ) {
                int idArticle = rs.getInt("idArticle");
            }
        }

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
            sql = "DELETE FROM OrderRow WHERE 1=1;";
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
        }

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
            }
        }

        for (Clienti customer : customers) {
            try {
                sql = "INSERT INTO Customer (id,BusinessName,Country,Email) " + "VALUES ("+customer.getId()+", '"+customer.getBusinessName()+"', '"+customer.getCountry()+"', '"+customer.getEmail()+"');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }
        }

        for (Ordini order : orders) {
            try {
                sql = "INSERT INTO OrderHead (idHead,idAgent,Total,Commission) " + "VALUES ("+order.getId()+", '"+order.getAgent().getId()+"', '"+order.getTotal()+"', '"+order.getCommissionTot()+"');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
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
            }

        }

        for (Catalogo catalog : catalogs) {
            try {
                sql = "INSERT INTO CatalogHead (idHead,Description,MarketZone) " + "VALUES ("+catalog.getId()+", '"+catalog.getDescription()+"', '"+catalog.getMarketZone()+"');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

            try {

                for (Articolo article : catalog.getArticles()) {
                    sql = "INSERT INTO CatalogRow (idHead,idArticle) " + "VALUES ("+catalog.getId()+", "+article.getId()+");";
                    stmt = c.createStatement();
                    stmt.executeUpdate(sql);
                    c.commit();
                }

            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

        }

        for (Articolo article : articles) {
            if(article instanceof Composto) {
                Composto  tmp = (Composto) article;
                for (Articolo a : tmp.getComponents()) {
                    try {
                        sql = "INSERT INTO ArticleCompound (IdCompound,IdComponent) " + "VALUES ("+article.getId()+", "+a.getId()+");";
                        stmt = c.createStatement();
                        stmt.executeUpdate(sql);
                        c.commit();
                    } catch ( Exception e ) {
                        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                    }
                }
            }
            try {
                sql = "INSERT INTO Article (Id,Name,Price) " + "VALUES ("+article.getId()+", '"+article.getName()+"', '"+article.getPrice()+"');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch ( Exception e ) {
                System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            }

        }

        // GESTIRE NOTIFICHE

        try {
            stmt.close();
            c.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

}