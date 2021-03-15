package agentManager;

import org.javatuples.Pair;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Arrays;

public final class Program {

    private User activeUser;
    private final ArrayList<User> users;
    private final ArrayList<Article> articles;
    private final ArrayList<Catalog> catalogs;
    private final ArrayList<Customer> customers;
    private final ArrayList<Order> orders;
    private final NotificationCenter notCenter;
    private static Program instance;
    private Menu menu;
    private Boolean wantClose = false;

    private Program() {
        notCenter = NotificationCenter.getInstance();
        users = new ArrayList<>();
        articles = new ArrayList<>();
        catalogs = new ArrayList<>();
        orders = new ArrayList<>();
        customers = new ArrayList<>();
        activeUser= null;
    }

    public User getActiveUser() { return activeUser; }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public ArrayList<Catalog> getCatalogs() {
        return catalogs;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    public ArrayList<Order> getOrders() {
        return orders;
    }

    public static Program getInstance() {

        if (instance == null)
            instance = new Program();

        return instance;
    }

    void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void run() {

        try {
            this.load(DBConnection.getInstance());
        } catch (SQLException e) {
            System.err.println("Unable to load!");
            return ;
        }

        this.setMenu(new LoginMenu());

        while (!wantClose) {
            menu.showMenu(activeUser);
        }

        System.out.println("Bye Bye!");
        this.upload(DBConnection.getInstance());

    }

    public boolean login(String name, String psw) {
        for (User i : users) {
            if (name.equals(i.getName()) && User.getHash(psw).equals(i.getPasswordHash())) {
                activeUser = i;
                break;
            }
        }

        if (activeUser == null) {
            System.err.println("Wrong password and/or UserName !");
            return false;
        }

        if (activeUser instanceof Administrator) {
            this.setMenu(new AdminMainMenu());
        } else {
            this.setMenu(new AgentMainMenu());
        }

        return true;
    }

    public void logout(){
        activeUser = null;
        this.setMenu(new LoginMenu());
    }

    boolean checkCustomersExist(int id){
        for(Customer c :customers){             //todo da levare
            if(c.getId()==id)
                return true;
        }
        System.err.println("Wrong ID re-insert it!.");
        return false;
    }

    public void load(Connection c) throws SQLException {

        Statement stmt = c.createStatement();
        Statement stmt1 = c.createStatement();
        ResultSet rs, rs1;

        rs = stmt.executeQuery("SELECT * FROM Customer;");
        while (rs.next()) {
            int id = rs.getInt("id");
            String businessName = rs.getString("BusinessName");
            String country = rs.getString("Country");
            String email = rs.getString("Email");
            customers.add(new Customer(id, businessName, country, email));
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
            articles.add(new Product(name, price, id));
        }
        rs = stmt.executeQuery("SELECT * FROM Article WHERE id in (SELECT IdCompound FROM ArticleCompound );");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            ArrayList<Article> components = new ArrayList<>();
            rs1 = stmt1.executeQuery("SELECT * FROM ArticleCompound WHERE IdCompound = " + id + " ;");
            while (rs1.next()) {
                int idComponent = rs1.getInt("idComponent");

                for (Article a : articles) {
                    if (a.getId() == idComponent) {
                        components.add(a);
                        break;
                    }
                }
            }
            articles.add(new Compound(name, components, id));
        }

        rs = stmt.executeQuery("SELECT * FROM CatalogHead;");
        while (rs.next()) {
            int id = rs.getInt("idHead");
            String description = rs.getString("Description");
            String marketZone = rs.getString("MarketZone");
            ArrayList<Article> tmp = new ArrayList<>();
            rs1 = stmt1.executeQuery("SELECT * FROM CatalogRow WHERE IdHead = " + id + " ;");
            while (rs1.next()) {
                int idArticle = rs1.getInt("idArticle");
                for (Article a : articles) {
                    if (a.getId() == idArticle) {
                        tmp.add(a);
                        break;
                    }
                }
            }
            catalogs.add(new Catalog(tmp, description, marketZone, id));
        }

        rs = stmt.executeQuery("SELECT * FROM User;");
        while (rs.next()) {
            int id = rs.getInt("id");                             //1 agent - 0 administrator
            String name = rs.getString("Name");
            String passHash = rs.getString("Passwordhash");
            int type = rs.getInt("Type");
            int idCatalog = rs.getInt("IdCatalog");
            float commissionPercentage = rs.getFloat("CommissionPerc");

            if (type == 1) {
                Catalog tmp = null;
                for(Catalog i: catalogs) {
                    if (i.getId()==idCatalog){
                        tmp = i;
                    }
                }

                if(tmp == null){
                    System.err.println("Catalog don't exist!");
                    break;
                }

                users.add(new Agent(name, passHash, commissionPercentage, tmp,id));
            } else {
                users.add(new Administrator(name, passHash, id));
            }
        }

        rs = stmt.executeQuery("SELECT * FROM OrderHead;");
        while (rs.next()) {
            int id = rs.getInt("idHead");
            int idAgent = rs.getInt("idAgent");
            int idCustomers = rs.getInt("IdCustomer");
            float total = rs.getFloat("Total");
            float commission = rs.getFloat("Commission");

            Agent tmpAgent = null;
            for (User i : users) {
                if (i.getId() == idAgent) {
                    tmpAgent = (Agent) i;
                    break;
                }
            }

            Customer tmpCustomer = null;
            for (Customer i : customers) {
                if (i.getId() == idCustomers) {
                    tmpCustomer = i;
                    break;
                }
            }

            if(tmpCustomer == null){
                System.err.println("Customer don't exist!");
                break;
            }

            ArrayList<Pair<Article, Integer>> tmp = new ArrayList<>();
            rs1 = stmt1.executeQuery("SELECT * FROM OrderRow WHERE IdHead = " + id + " ;");
            while (rs1.next()) {
                int idArticle = rs1.getInt("idArticle");
                int qta = rs1.getInt("qta");
                for (Article a : articles) {
                    if (a.getId() == idArticle) {
                        tmp.add(new Pair<>(a,qta));
                        break;
                    }
                }
            }
            orders.add(new Order(total, commission, tmpAgent, tmp, tmpCustomer, id));
        }

    }

    public void upload(Connection c) {
        String sql;
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            for (String s : Arrays.asList("DELETE FROM User;", "DELETE FROM OrderHead;", "DELETE FROM OrderRow;", "DELETE FROM Notification;", "DELETE FROM Customer;", "DELETE FROM CatalogRow;", "DELETE FROM CatalogHead;", "DELETE FROM Article;", "DELETE FROM ArticleCompound;")) {
                sql = s;
                stmt.executeUpdate(sql);
                c.commit();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        int type;
        float perch;
        for (User user : users) {
            try {
                if (!(user instanceof Agent)) {
                    type = 0;
                    perch = 0;
                    sql = "INSERT INTO User (Id,Name,PasswordHash,Type,CommissionPerc) " + "VALUES (" + user.getId() + ", '" + user.getName() + "', '" + user.getPasswordHash() + "', " + type + ", " + perch + " );";
                } else {
                    type = 1;
                    Agent tmp = (Agent) user;
                    perch = tmp.getCommissionPercentage();
                    sql = "INSERT INTO User (Id,Name,PasswordHash,Type,CommissionPerc,IdCatalog) " + "VALUES (" + user.getId() + ", '" + user.getName() + "', '" + user.getPasswordHash() + "', " + type + ", " + perch + " ,"+tmp.getCatalog().getId()+");";
                }

                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (Customer customer : customers) {
            try {
                sql = "INSERT INTO Customer (id,BusinessName,Country,Email) " + "VALUES (" + customer.getId() + ", '" + customer.getBusinessName() + "', '" + customer.getCountry() + "', '" + customer.getEmail() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (Order order : orders) {
            try {
                if(order.getAgent()!=null)
                    sql = "INSERT INTO OrderHead (idHead,idAgent,IdCustomer,Total,Commission) " + "VALUES (" + order.getId() + ", '" + order.getAgent().getId() + "', "+order.getClient().getId()+" ,'" + order.getTotal() + "', '" + order.getCommissionTot() + "');";
                else
                    sql = "INSERT INTO OrderHead (idHead,idAgent,IdCustomer,Total,Commission) " + "VALUES (" + order.getId() + ", '" + -1 + "', "+order.getClient().getId()+" ,'" + order.getTotal() + "', '" + order.getCommissionTot() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            try {
                for (Pair<Article,Integer>  i: order.getRows()) {
                    sql = "INSERT INTO OrderRow (idHead,idArticle,qta) " + "VALUES (" + order.getId() + ", " + i.getValue0().getId() + ","+i.getValue1()+");";
                    stmt = c.createStatement();
                    stmt.executeUpdate(sql);
                    c.commit();
                }
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
        }

        for (Catalog catalog : catalogs) {
            try {
                sql = "INSERT INTO CatalogHead (idHead,Description,MarketZone) " + "VALUES (" + catalog.getId() + ", '" + catalog.getDescription() + "', '" + catalog.getMarketZone() + "');";
                stmt = c.createStatement();
                stmt.executeUpdate(sql);
                c.commit();
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }
            try {
                for (Article article : catalog.getArticles()) {
                    sql = "INSERT INTO CatalogRow (idHead,idArticle) " + "VALUES (" + catalog.getId() + ", " + article.getId() + ");";
                    stmt = c.createStatement();
                    stmt.executeUpdate(sql);
                    c.commit();
                }
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

        }

        for (Article article : articles) {
            if (article instanceof Compound) {
                Compound tmp = (Compound) article;
                for (Article a : tmp.getComponents()) {
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

        for (String notify : notCenter.getNotification()) {
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

        instance = null;
    }

    void close(){
        wantClose = true;
    }

}