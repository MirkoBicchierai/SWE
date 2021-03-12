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

        Program.getInstance().login("Admin","111");
        admin = (Administrator) Program.getInstance().getActiveUser();
        assertNotNull(admin);

    }

    @org.junit.jupiter.api.Test
    void testCreateAgent() {

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
