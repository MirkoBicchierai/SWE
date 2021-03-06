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
        Articolo AA3 = new Prodotto("Nexal3",50);
        ArrayList<Articolo> articles = new  ArrayList<>();
        ArrayList<Articolo> articles2 = new  ArrayList<>();
        articles.add(AA1);
        articles.add(AA2);
        articles2.add(AA3);
        articles2.add(AA2);
        Ordini O1 = new Ordini(5,10,A1,articles);

        Catalogo Ca1 = new Catalogo(articles,"descrizione1","Italy");
        Catalogo Ca2 = new Catalogo(articles2,"descrizione2","Germany");

        Programma.getInstance().articles.add(AA1);
        Programma.getInstance().articles.add(AA2);
        Programma.getInstance().articles.add(AA3);

        Programma.getInstance().catalogs.add(Ca1);
        Programma.getInstance().catalogs.add(Ca2);

        Programma.getInstance().orders.add(O1);
        Programma.getInstance().customers.add(C1);
        Programma.getInstance().upload();

    }

}
