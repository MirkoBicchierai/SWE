import org.javatuples.Pair;

import java.util.*;
import java.sql.*;

public class Programma {

    private Utenti activeUser;
    private ArrayList<Utenti> users;
    private ArrayList<Articolo> articles;
    private ArrayList<Catalogo> catalogs;
    private ArrayList<Clienti> customers;
    private ArrayList<Ordini> orders;
    private CentroNotifiche notCenter;
    private static Programma instance;
    private Connection c;
    private Menu menu;
    private Boolean wantClose = false;

    private Programma() {
        notCenter = CentroNotifiche.getInstance();
        users = new ArrayList<>();
        articles = new ArrayList<>();
        catalogs = new ArrayList<>();
        orders = new ArrayList<>();
        customers = new ArrayList<>();
        activeUser= null;
        c = DBConnection.getInstance();
    }

    public Utenti getActiveUser() {
        return activeUser;
    }

    public ArrayList<Utenti> getUsers() {
        return users;
    }

    public ArrayList<Articolo> getArticles() {
        return articles;
    }

    public ArrayList<Catalogo> getCatalogs() {
        return catalogs;
    }

    public ArrayList<Clienti> getCustomers() {
        return customers;
    }

    public ArrayList<Ordini> getOrders() {
        return orders;
    }

    public void close(){
        wantClose = true;
    }

    public void setMenu(Menu menu) { //forse inutile
        this.menu = menu;
    }

    private Menu getState() { //forse inutile
        return this.menu.getCurrentState();
    }

    public void run() {

        this.setMenu(new LoginMenu());

        while (!wantClose) {
            this.getState().showMenu(activeUser);
        }

        System.out.println("Bye Bye!");
        this.upload();

    }

    public void logout(){
        activeUser = null;
        this.setMenu(new LoginMenu());
    }

    public boolean checkCustomersExist(int id){
        for(Clienti c :customers){
            if(c.getId()==id)
                return true;
        }
        System.err.println("Wrong ID re-insert it!.");
        return false;
    }

    public void debug(){
        System.out.println("----------------------------------");
        System.out.println("Articoli: " + getArticles().size());
        System.out.println("Utenti: " + getUsers().size());
        System.out.println("Cataloghi: " + getCatalogs().size());
        System.out.println("Clienti: " + getCustomers().size());
        System.out.println("Ordini: " + getOrders().size());
        System.out.println("Notifiche: " + notCenter.getNofications().size());
        System.out.println("----------------------------------");
    }

    public Utenti login(String name, String psw) {
        for (Utenti i : users) {
            if (name.equals(i.name) && Utenti.getHash(psw).equals(i.passwordHash)) {
                activeUser = i;
                break;
            }
        }

        if (activeUser == null) {
            System.err.println("Password e/o Nome utente Errati!");
            return activeUser;
        }

        if (activeUser instanceof Amministratori) {
            this.setMenu(new AdminMainMenu());
        } else {
            this.setMenu(new AgentMainMenu());
        }

        return activeUser;

    }

    public static Programma getInstance() {
        if (instance == null) {
            instance = new Programma();
            try {
                instance.load();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public void load() throws SQLException {

        Statement stmt = c.createStatement();
        Statement stmt1 = c.createStatement();
        ResultSet rs, rs1 = null;

        rs = stmt.executeQuery("SELECT * FROM Customer;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String businessName = rs.getString("BusinessName");
            String country = rs.getString("Country");
            String email = rs.getString("Email");
            customers.add(new Clienti(id, businessName, country, email));
        }

        rs = stmt.executeQuery("SELECT * FROM Notification;");
        while (rs.next()) {
            String message = rs.getString("message");
            notCenter.update(message);
        }

        rs = stmt.executeQuery("SELECT * FROM Article WHERE id not in (SELECT IdCompound FROM ArticleCompound );");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            float price = rs.getFloat("Price");
            articles.add(new Prodotto(name, price, id));
        }
        rs = stmt.executeQuery("SELECT * FROM Article WHERE id in (SELECT IdCompound FROM ArticleCompound );");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            float price = rs.getFloat("Price");
            ArrayList<Articolo> tmp = new ArrayList<>();
            rs1 = stmt1.executeQuery("SELECT * FROM ArticleCompound WHERE IdCompound = " + id + " ;");
            while (rs1.next()) {
                int idComponent = rs1.getInt("idComponent");

                for (Articolo a : articles) {
                    if (a.getId() == idComponent) {
                        tmp.add(a);
                        break;
                    }
                }
            }
            articles.add(new Composto(name, tmp, id));
        }

        rs = stmt.executeQuery("SELECT * FROM CatalogHead;");
        while (rs.next()) {
            int id = rs.getInt("idHead");
            String description = rs.getString("Description");
            String marketZone = rs.getString("MarketZone");
            ArrayList<Articolo> tmp = new ArrayList<>();
            rs1 = stmt1.executeQuery("SELECT * FROM CatalogRow WHERE IdHead = " + id + " ;");
            while (rs1.next()) {
                int idArticle = rs1.getInt("idArticle");
                for (Articolo a : articles) {
                    if (a.getId() == idArticle) {
                        tmp.add(a);
                        break;
                    }
                }
            }
            catalogs.add(new Catalogo(tmp, description, marketZone, id));
        }

        rs = stmt.executeQuery("SELECT * FROM User;");
        while (rs.next()) {
            int id = rs.getInt("id");                             //1 agente - 0 amministratore
            String name = rs.getString("Name");
            String passHash = rs.getString("Passwordhash");
            int type = rs.getInt("Type");
            int idCatalog = rs.getInt("IdCatalog");
            float commissionPerc = rs.getFloat("CommissionPerc");

            if (type == 1) {
                Catalogo tmp = null;
                for(Catalogo i: catalogs) {
                    if (i.getId()==idCatalog){
                        tmp = i;
                    }
                }

                if(tmp == null){
                    System.err.println("Catalogo Non Presente!");
                    break;
                }

                users.add(new Agenti(name, passHash, commissionPerc, tmp,id));
            } else {
                users.add(new Amministratori(name, passHash, id));
            }
        }

        rs = stmt.executeQuery("SELECT * FROM OrderHead;");
        while (rs.next()) {
            int id = rs.getInt("idHead");
            int idAgent = rs.getInt("idAgent");
            int idCustomers = rs.getInt("IdCustomer");
            float total = rs.getFloat("Total");
            float commission = rs.getFloat("Commission");

            Agenti tmpAgent = null;
            for (Utenti i : users) {
                if (i.getId() == idAgent) {
                    tmpAgent = (Agenti) i;
                    break;
                }
            }
            /*
            if(tmpAgent == null){
                System.err.println("Agente Non Presente!");
                break;
            }*/

            Clienti tmpCustomer = null;
            for (Clienti i : customers) {
                if (i.getId() == idCustomers) {
                    tmpCustomer = i;
                    break;
                }
            }

            if(tmpCustomer == null){
                System.err.println("Cliente Non Presente!");
                break;
            }

            ArrayList<Pair<Articolo, Integer>> tmp = new ArrayList<>();
            rs1 = stmt1.executeQuery("SELECT * FROM OrderRow WHERE IdHead = " + id + " ;");
            while (rs1.next()) {
                int idArticle = rs1.getInt("idArticle");
                int qta = rs1.getInt("qta");
                for (Articolo a : articles) {
                    if (a.getId() == idArticle) {
                        tmp.add(new Pair<>(a,qta));
                        break;
                    }
                }
            }
            orders.add(new Ordini(total, commission, tmpAgent, tmp, tmpCustomer, id));
        }

    }

    public void upload() {
        String sql = "";
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
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        int type;
        float perch;
        for (Utenti user : users) {
            try {
                if (!(user instanceof Agenti)) {
                    type = 0;
                    perch = 0;
                    sql = "INSERT INTO User (Id,Name,PasswordHash,Type,CommissionPerc) " + "VALUES (" + user.getId() + ", '" + user.getName() + "', '" + user.getPasswordHash() + "', " + type + ", " + perch + " );";
                } else {
                    type = 1;
                    Agenti tmp = (Agenti) user;
                    perch = tmp.getCommissionPerc();
                    sql = "INSERT INTO User (Id,Name,PasswordHash,Type,CommissionPerc,IdCatalog) " + "VALUES (" + user.getId() + ", '" + user.getName() + "', '" + user.getPasswordHash() + "', " + type + ", " + perch + " ,"+tmp.getCatalog().getId()+");";
                }

                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (Clienti customer : customers) {
            try {
                sql = "INSERT INTO Customer (id,BusinessName,Country,Email) " + "VALUES (" + customer.getId() + ", '" + customer.getBusinessName() + "', '" + customer.getCountry() + "', '" + customer.getEmail() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (Ordini order : orders) {
            try {
                sql = "INSERT INTO OrderHead (idHead,idAgent,IdCustomer,Total,Commission) " + "VALUES (" + order.getId() + ", '" + order.getAgent().getId() + "', "+order.getClient().getId()+" ,'" + order.getTotal() + "', '" + order.getCommissionTot() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            try {
                for (Pair<Articolo,Integer>  i: order.getRows()) {
                    sql = "INSERT INTO OrderRow (idHead,idArticle,qta) " + "VALUES (" + order.getId() + ", " + i.getValue0().getId() + ","+i.getValue1()+");";
                    stmt = c.createStatement();
                    stmt.executeUpdate(sql);
                    c.commit();
                }
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (Catalogo catalog : catalogs) {
            try {
                sql = "INSERT INTO CatalogHead (idHead,Description,MarketZone) " + "VALUES (" + catalog.getId() + ", '" + catalog.getDescription() + "', '" + catalog.getMarketZone() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            try {
                for (Articolo article : catalog.getArticles()) {
                    sql = "INSERT INTO CatalogRow (idHead,idArticle) " + "VALUES (" + catalog.getId() + ", " + article.getId() + ");";
                    stmt = c.createStatement();
                    stmt.executeUpdate(sql);
                    c.commit();
                }
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }

        for (Articolo article : articles) {
            if (article instanceof Composto) {
                Composto tmp = (Composto) article;
                for (Articolo a : tmp.getComponents()) {
                    try {
                        sql = "INSERT INTO ArticleCompound (IdCompound,IdComponent) " + "VALUES (" + article.getId() + ", " + a.getId() + ");";
                        stmt = c.createStatement();
                        stmt.executeUpdate(sql);
                        c.commit();
                    } catch (Exception e) {
                        System.err.println(e.getClass().getName() + ": " + e.getMessage());
                    }
                }
            }
            try {
                sql = "INSERT INTO Article (Id,Name,Price) " + "VALUES (" + article.getId() + ", '" + article.getName() + "', '" + article.getPrice() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (String notify : notCenter.getNofications()) {
            try {
                sql = "INSERT INTO Notification (Message) " + "VALUES ('" + notify + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        try {
            stmt.close();
            c.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }

    }

}