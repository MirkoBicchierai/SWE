import agentManager.*;

import org.junit.jupiter.api.BeforeAll;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class AdministratorTest {

    static Administrator admin = null;

    @BeforeAll
    static void prepare(){

        try {
            Program.getInstance().load(DBConnectionTest.getInstance());
        } catch (SQLException e) {
            System.err.println("Unable to load data!");
            fail();
        }

        Program.getInstance().login("Ganjiro","111");
        admin = (Administrator) Program.getInstance().getActiveUser();
        assertNotNull(admin);

    }

    @org.junit.jupiter.api.Test
    void testCreateAgent() {
        ArrayList<Article> articles = new ArrayList<>();
        Catalog c = new Catalog(articles,"catalogo1","italy");
        admin.createAgent("Mirko","111", 3.5F,c);
    }

    @org.junit.jupiter.api.Test
    void testCreateCatalog() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testCreateProduct() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testDeleteCatalog() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testDeleteAgent() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testDeleteCustomer() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testDeleteArticle() {
        System.out.println("");
    }

}
