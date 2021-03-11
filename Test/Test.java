import agentManager.Administrator;
import agentManager.Program;
import agentManager.User;
import org.junit.jupiter.api.AssertionsKt;
import org.junit.jupiter.api.BeforeAll;

public class Test {
    @BeforeAll
    static void diocane(){
        //Program.getInstance().login("Ganjiro","111");
    }

    void sasa(){

    }

    @org.junit.jupiter.api.Test
    void test(){
        //Program p = Program.getInstance();
        Administrator a = new Administrator("ciao","ciao");
        assert equals(a.getId(),1);

    }

    private boolean equals(int id, int i) {
        return id==i;
    }


}
