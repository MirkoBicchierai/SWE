import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{

        //Connection c = DBConnection.getInstance();
        Statement stmt = null;
        Agenti A1 = new Agenti("Mirko", "123456",100);
        Clienti C1 = new Clienti("Nexal","Italy","mirko@nexal.it");


        Articolo AA1 = new Prodotto("Nexal1",3);
        Articolo AA2 = new Prodotto("Nexal2",5);
        ArrayList<Articolo> articles = new  ArrayList<>();
        articles.add(AA1);
        articles.add(AA2);
        Ordini O1 = new Ordini(5,10,A1,articles);
        Programma.getInstance().orders.add(O1);
        Programma.getInstance().customers.add(C1);
        Programma.getInstance().upload();

    }

}
