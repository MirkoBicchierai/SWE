import agentManager.Administrator;
import agentManager.Program;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AdministratorTest {

    static Administrator admin = null;

    @BeforeAll
    static void prepare() {
        Program.getInstance().login("Ganjiro","111");
        admin = (Administrator) Program.getInstance().getActiveUser();
        assertNotNull(admin);
    }

    @org.junit.jupiter.api.Test
    void deleteCatalog() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void deleteAgent() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void deleteCustomer() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void deleteArticle() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void createAgent() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void createCatalog() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void createProduct() {
        System.out.println("");
    }

}
