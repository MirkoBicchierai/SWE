import java.sql.*;

public class Main {

    public static void main(String[] args) throws Exception{

        //Connection c = DBConnection.getInstance();
        Statement stmt = null;
        Agenti A1 = new Agenti("Mirko", "123456",100);
        Programma.getInstance().users.add(A1);
        Programma.getInstance().upload();

    }

}
