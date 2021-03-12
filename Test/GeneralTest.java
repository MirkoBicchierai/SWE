import org.junit.jupiter.api.BeforeAll;

public class GeneralTest {

    @BeforeAll
    static void prepare() {
        DBConnectionTest.getInstance();
    }

    @org.junit.jupiter.api.Test
    void testUploadData() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testLoadData() {
        System.out.println("");
    }

    @org.junit.jupiter.api.Test
    void testLoginUser() {
        System.out.println("");
    }

}
