import agentManager.Administrator;
import agentManager.Program;
import org.junit.jupiter.api.BeforeAll;

public class Test {

    @BeforeAll
    static void prepare() {
        Program.getInstance().login("Ganjiro","111");
    }

    @org.junit.jupiter.api.Test
    void test() {
        Administrator a = (Administrator) Program.getInstance().getActiveUser();
        System.err.println(a.getName());
    }

    @org.junit.jupiter.api.Test
    void test1() {
        System.err.println("Wrong ID! Re-insert it");
    }

    @org.junit.jupiter.api.Test
    void test2() {
        System.err.println("Wrong ID! Re-insert it");
    }

    private boolean equals(int id, int i) {
        return id == i;
    }

}