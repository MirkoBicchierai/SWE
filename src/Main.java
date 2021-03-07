import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws Exception{
        boolean debug = false;
        if(debug)
            startProgram();
        else
            realMain();
    }

    private static void realMain(){

        Programma p = Programma.getInstance();
        p.run();

    }

    private static void startProgram(){
        long startTime = System.nanoTime();

        Clienti C1 = new Clienti("Nexal","Italy","mirko@nexal.it");
        Clienti C2 = new Clienti("Aperion","Italy","andrea@aperion.it");

        Programma.getInstance().getCustomers().add(C1);
        Programma.getInstance().getCustomers().add(C2);

        Articolo AA1 = new Prodotto("Nexal1",3);
        Articolo AA2 = new Prodotto("Nexal2",5);
        Articolo AA3 = new Prodotto("Nexal3",50);
        Programma.getInstance().getArticles().add(AA1);
        Programma.getInstance().getArticles().add(AA2);
        Programma.getInstance().getArticles().add(AA3);

        ArrayList<Articolo> articles = new  ArrayList<>();
        ArrayList<Articolo> articles2 = new  ArrayList<>();
        articles.add(AA1);
        articles.add(AA2);
        articles2.add(AA3);
        articles2.add(AA2);
        Catalogo Ca1 = new Catalogo(articles,"descrizione1","Italy");
        Catalogo Ca2 = new Catalogo(articles2,"descrizione2","Germany");
        Programma.getInstance().getCatalogs().add(Ca1);
        Programma.getInstance().getCatalogs().add(Ca2);

        Agenti A1 = new Agenti("Mirko", "111",100, Ca1);
        Agenti A11 = new Agenti("Mirko2", "111",100, Ca2);
        Amministratori Admin = new Amministratori("Ganjiro", "111");
        Programma.getInstance().getUsers().add(A1);
        Programma.getInstance().getUsers().add(A11);
        Programma.getInstance().getUsers().add(Admin);

        Ordini O1 = new Ordini(5,10,A1,articles,C1);
        Ordini O2 = new Ordini(5,10,A11,articles2,C2);
        Programma.getInstance().getOrders().add(O1);
        Programma.getInstance().getOrders().add(O2);

        ArrayList<Articolo> articlesComp1 = new  ArrayList<>();
        ArrayList<Articolo> articlesComp2 = new  ArrayList<>();
        articlesComp1.add(AA1);
        articlesComp1.add(AA2);
        articlesComp2.add(AA2);
        articlesComp2.add(AA3);

        Articolo AC1 = new Composto("NexalComp1",articlesComp1);
        Articolo AC2 = new Composto("NexalComp2",articlesComp2);
        Programma.getInstance().getArticles().add(AC1);
        Programma.getInstance().getArticles().add(AC2);

        CentroNotifiche.getInstance().update("Notify 1");
        CentroNotifiche.getInstance().update("Notify 2");
        CentroNotifiche.getInstance().update("Notify 3");
        CentroNotifiche.getInstance().update("Notify 4");
        CentroNotifiche.getInstance().update("Notify 5");

        Programma.getInstance().upload();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("Execution time: "+duration+"ms");
    }

}