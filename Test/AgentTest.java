import agentManager.Agent;
import agentManager.Program;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {

    static Agent agent =null;

    @BeforeAll
    static void prepare() {
        Program.getInstance().login("Mirko","111");
        agent =(Agent) Program.getInstance().getActiveUser();
        assertNotNull(agent);
    }

    @org.junit.jupiter.api.Test
    void testOrderCreation() {


    }

    @org.junit.jupiter.api.Test
    void test1() {
        System.out.println("Wrong ID! Re-insert it");
    }

    @org.junit.jupiter.api.Test
    void test2() {
        System.out.println("Wrong ID! Re-insert it");
    }

    private boolean equals(int id, int i) {
        return id == i;
    }
}
