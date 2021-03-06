import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception{

        realMain();

    }

    private static void realMain(){

        Programma p = Programma.getInstance();
        //Utenti A1 = new Amministratori("Ganjiro", "111");
        //p.users.add(A1);
        System.out.println(p.articles.size());
        System.out.println(p.users.size());
        System.out.println(p.catalogs.size());
        System.out.println(p.customers.size());
        System.out.println(p.orders.size());
        System.out.println(p.notCenter.getNofications().size());
        Scanner in = new Scanner(System.in);
        System.out.println("Inserire Nome Utente:");
        String name = in.nextLine();
        System.out.println("Inserire Password:");
        String psw = in.nextLine();
        p.run();

    }

    private static void startProgram(){
        long startTime = System.nanoTime();

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


        Catalogo Ca1 = new Catalogo(articles,"descrizione1","Italy");
        Catalogo Ca2 = new Catalogo(articles2,"descrizione2","Germany");
        Agenti A1 = new Agenti("Mirko", "123456",100, Ca1);

        Ordini O1 = new Ordini(5,10,A1,articles);
        Programma.getInstance().articles.add(AA1);
        Programma.getInstance().articles.add(AA2);
        Programma.getInstance().articles.add(AA3);

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

        Programma.getInstance().catalogs.add(Ca1);
        Programma.getInstance().catalogs.add(Ca2);

        Programma.getInstance().orders.add(O1);
        Programma.getInstance().customers.add(C1);
        Programma.getInstance().upload();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("Execution time: "+duration+"ms");
    }

}