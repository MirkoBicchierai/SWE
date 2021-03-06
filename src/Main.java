import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        realMain();

    }

    private static void realMain(){

        Programma p = Programma.getInstance();

        System.out.println(p.articles.size());
        System.out.println(p.users.size());
        System.out.println(p.catalogs.size());
        System.out.println(p.customers.size());
        System.out.println(p.orders.size());
        System.out.println(p.notCenter.getNofications().size());

        p.run();
    }

    private static void startProgram(){
        long startTime = System.nanoTime();

        Clienti C1 = new Clienti("Nexal","Italy","mirko@nexal.it");
        Clienti C2 = new Clienti("Aperion","Italy","andrea@aperion.it");
        Programma.getInstance().customers.add(C1);
        Programma.getInstance().customers.add(C2);

        Articolo AA1 = new Prodotto("Nexal1",3);
        Articolo AA2 = new Prodotto("Nexal2",5);
        Articolo AA3 = new Prodotto("Nexal3",50);
        Programma.getInstance().articles.add(AA1);
        Programma.getInstance().articles.add(AA2);
        Programma.getInstance().articles.add(AA3);

        ArrayList<Articolo> articles = new  ArrayList<>();
        ArrayList<Articolo> articles2 = new  ArrayList<>();
        articles.add(AA1);
        articles.add(AA2);
        articles2.add(AA3);
        articles2.add(AA2);
        Catalogo Ca1 = new Catalogo(articles,"descrizione1","Italy");
        Catalogo Ca2 = new Catalogo(articles2,"descrizione2","Germany");
        Programma.getInstance().catalogs.add(Ca1);
        Programma.getInstance().catalogs.add(Ca2);

        Agenti A1 = new Agenti("Mirko", "111",100, Ca1);
        Agenti A11 = new Agenti("Mirko2", "111",100, Ca2);
        Amministratori Admin = new Amministratori("Ganjiro", "111");
        Programma.getInstance().users.add(A1);
        Programma.getInstance().users.add(A11);
        Programma.getInstance().users.add(Admin);

        Ordini O1 = new Ordini(5,10,A1,articles);
        Ordini O2 = new Ordini(5,10,A11,articles2);
        Programma.getInstance().orders.add(O1);
        Programma.getInstance().orders.add(O2);

        ArrayList<Articolo> articlesComp1 = new  ArrayList<>();
        ArrayList<Articolo> articlesComp2 = new  ArrayList<>();
        articlesComp1.add(AA1);
        articlesComp1.add(AA2);
        articlesComp2.add(AA2);
        articlesComp2.add(AA3);

        Articolo AC1 = new Composto("NexalComp1",articlesComp1);
        Articolo AC2 = new Composto("NexalComp2",articlesComp2);
        Programma.getInstance().articles.add(AC1);
        Programma.getInstance().articles.add(AC2);

        Programma.getInstance().notCenter.update("Notify 1");
        Programma.getInstance().notCenter.update("Notify 2");
        Programma.getInstance().notCenter.update("Notify 3");
        Programma.getInstance().notCenter.update("Notify 4");
        Programma.getInstance().notCenter.update("Notify 5");

        Programma.getInstance().upload();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("Execution time: "+duration+"ms");
    }

}