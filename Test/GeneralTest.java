import agentManager.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class GeneralTest {

    private static Program p = Program.getInstance();

    @BeforeAll
    static void prepare() {
        try {
            p.load(DBConnectionTest.getInstance());
        } catch (SQLException e) {
            System.err.println("Unable to load data!");
            fail();
        }
    }

    @Test
    @DisplayName("Upload/Load data Test")
    void testUploadLoadData() throws SQLException {
        String sql;
        Statement stmt;
        ResultSet rs;

        Connection c = DBConnectionTest.getInstance();
        p.upload(c);
        p = Program.getInstance();

        c = DBConnectionTest.getInstance();

        String customerName = "testCustomer";
        String customerCountry = "testCountry";
        String customerEmail = "testemail";

        sql = "INSERT INTO Customer (BusinessName,Country,Email) " + "VALUES ('"+customerName + "', '" + customerCountry+ "', '" + customerEmail+ "');";

        stmt = c.createStatement();
        stmt.executeUpdate(sql);
        c.commit();

        String articleName = "testCustomer";
        float articlePrice = 10.2F;

        sql = "INSERT INTO Article (Name,Price) " + "VALUES ('" +articleName+ "', '" + articlePrice+ "');";
        stmt = c.createStatement();
        stmt.executeUpdate(sql);
        c.commit();

        p.load(c);

        Customer newCustomer = null;
        Article newArticle = null;

        for (Customer i:p.getCustomers()){
            if (i.getBusinessName().equals(customerName) && i.getCountry().equals(customerCountry) && i.getEmail().equals(customerEmail)){
                newCustomer=i;
            }
        }

        for (Article i:p.getArticles()){
            if (i.getName().equals(articleName) &&i.getPrice()==articlePrice){
                newArticle=i;
            }
        }

        Customer finalNewCustomer = newCustomer;
        Article finalNewArticle = newArticle;
        assertAll("Load From DB",
                () -> assertNotNull(finalNewCustomer),
                () -> assertNotNull(finalNewArticle)
        );

        p.login("Admin","111");
        Administrator admin = (Administrator) p.getActiveUser();

        admin.createProduct("TestProductUpload",12.2F);
        admin.createCustomer("TestCustomerUpload", "TestCustomerUpload", "TestCustomerUpload" );

        p.upload(c);

        c = DBConnectionTest.getInstance();
        stmt = c.createStatement();

        int risArticle = 0;
        rs = stmt.executeQuery("SELECT COUNT(*) as ris FROM Article WHERE name='TestProductUpload';");
        while (rs.next()) {
            risArticle = rs.getInt("ris");
        }

        int risCustomer = 0;
        rs = stmt.executeQuery("SELECT COUNT(*) as ris FROM Customer WHERE businessname='TestCustomerUpload' AND country='TestCustomerUpload' AND email='TestCustomerUpload';");
        while (rs.next()) {
            risCustomer = rs.getInt("ris");
        }

        int finalRisArticle = risArticle;
        int finalRisCustomer = risCustomer;
        assertAll("Upload To DB",
                () -> assertTrue(finalRisArticle>=1),
                () -> assertTrue(finalRisCustomer>=1)
        );

        sql = "DELETE FROM Customer WHERE LOWER(businessname) LIKE '%test%';";
        stmt.executeUpdate(sql);
        c.commit();

        sql = "DELETE FROM Article WHERE LOWER(name) LIKE '%test%';";
        stmt.executeUpdate(sql);
        c.commit();
    }

    @Test
    @DisplayName("Login user Test")
    void testLoginUser() {

        p.login("Agent1","111");

        User user = null;

        for (User i:p.getUsers()){
            if (i.getName().equals("Agent1")){
                user = i;
            }
        }

        User expectedUser1 = user;

        assertAll("Agent Login",
                () -> assertEquals(expectedUser1, p.getActiveUser()),
                () -> assertTrue(p.getActiveUser() instanceof Agent)
        );

        p.logout();

        p.login("Admin","111");

        user = null;

        for (User i:p.getUsers()){
            if (i.getName().equals("Admin")){
                user = i;
            }
        }

        User expectedUser2 = user;

        assertAll("Admin Login",
                () -> assertEquals(expectedUser2, p.getActiveUser()),
                () -> assertTrue(p.getActiveUser() instanceof Administrator)
        );
    }

}
