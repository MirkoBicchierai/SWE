import agentManager.Administrator;
import agentManager.Program;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;

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
    void test() {

    }

    @org.junit.jupiter.api.Test
    void test1() {
        System.out.println("Wrong ID! Re-insert it");
    }

    @org.junit.jupiter.api.Test
    void test2() {
        System.out.println("Wrong ID! Re-insert it");
    }

}
