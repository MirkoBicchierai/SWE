import agentManager.*;
import agentManager.Order;
import org.javatuples.Pair;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class AgentTest {

    private static Agent agent =null;
    private static Program p = Program.getInstance();

    @BeforeAll
    static void prepare() {
        try {
            p.load(DBConnectionTest.getInstance());
        } catch (SQLException e) {
            System.err.println("Unable to load data!");
            fail();
        }

        p.login("Agent1","111");
        agent =(Agent) p.getActiveUser();

        assertNotNull(agent);
    }

    @Test
    void testOrderCreation() {

        ArrayList<Pair<Article,Integer>> articles = new ArrayList<>();

        articles.add(new Pair<>(agent.getCatalog().getArticles().get(1),20));
        articles.add(new Pair<>(agent.getCatalog().getArticles().get(2),50));

        Customer customer = p.getCustomers().get(2);

        agent.createOrder(customer,articles);

        Order createdOrder = p.getOrders().get(p.getOrders().size()-1);

        String messageNotifica = NotificationCenter.getInstance().getNofications().get(NotificationCenter.getInstance().getNofications().size()-1);

        assertAll("Order's Data",
                () -> assertEquals(createdOrder.getAgent(), agent),
                () -> assertEquals(createdOrder.getClient(), customer),
                () -> assertEquals(createdOrder.getArticles().get(0), articles.get(0).getValue0()),
                () -> assertEquals(createdOrder.getArticles().get(1), articles.get(1).getValue0()),
                () -> assertTrue(messageNotifica.contains(agent.getName()))
        );

    }

    @Test
    void testDeleteOrder() {

        int orderCountBefore = p.getOrders().size();

        Order order = p.getOrders().get(0);

        agent.deleteOrder(order.getId());

        assertAll("Order deleted",
                () -> assertTrue(p.getOrders().size()<orderCountBefore),
                () -> assertFalse(p.getOrders().contains(order))
        );
        
    }

}
