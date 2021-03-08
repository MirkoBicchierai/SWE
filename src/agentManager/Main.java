package agentManager;

import org.javatuples.Pair;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args){
        boolean debug = false;
        if(debug)
            startProgram();
        else
            realMain();

    }

    private static void realMain(){

            Program p = Program.getInstance();
            p.run();
            //todo Essere sicuri di upload Alla fine del programma
    }

    private static void startProgram(){
        long startTime = System.nanoTime();

        Customer C1 = new Customer("Nexal","Italy","mirko@nexal.it");
        Customer C2 = new Customer("Aperion","Italy","andrea@aperion.it");

        Program.getInstance().getCustomers().add(C1);
        Program.getInstance().getCustomers().add(C2);

        Article AA1 = new Product("Nexal1",3);
        Article AA2 = new Product("Nexal2",5);
        Article AA3 = new Product("Nexal3",50);
        Program.getInstance().getArticles().add(AA1);
        Program.getInstance().getArticles().add(AA2);
        Program.getInstance().getArticles().add(AA3);

        ArrayList<Article> articles = new  ArrayList<>();
        ArrayList<Article> articles2 = new  ArrayList<>();
        articles.add(AA1);
        articles.add(AA2);

        ArrayList<Pair<Article,Integer>> pair1= new ArrayList<>();
        pair1.add(new Pair<>(AA1,20));
        pair1.add(new Pair<>(AA2,21));

        ArrayList<Pair<Article,Integer>> pair2= new ArrayList<>();
        pair1.add(new Pair<>(AA3,10));
        pair1.add(new Pair<>(AA2,11));

        articles2.add(AA3);
        articles2.add(AA2);
        Catalog Ca1 = new Catalog(articles,"descrizione1","Italy");
        Catalog Ca2 = new Catalog(articles2,"descrizione2","Germany");
        Program.getInstance().getCatalogs().add(Ca1);
        Program.getInstance().getCatalogs().add(Ca2);

        Agent A1 = new Agent("Mirko", "111",100, Ca1);
        Agent A11 = new Agent("Mirko2", "111",100, Ca2);
        Administrator Admin = new Administrator("Ganjiro", "111");
        Program.getInstance().getUsers().add(A1);
        Program.getInstance().getUsers().add(A11);
        Program.getInstance().getUsers().add(Admin);

        Order O1 = new Order(5,10,A1,pair1,C1);
        Order O2 = new Order(5,10,A11,pair2,C2);
        Program.getInstance().getOrders().add(O1);
        Program.getInstance().getOrders().add(O2);

        ArrayList<Article> articlesComp1 = new  ArrayList<>();
        ArrayList<Article> articlesComp2 = new  ArrayList<>();
        articlesComp1.add(AA1);
        articlesComp1.add(AA2);
        articlesComp2.add(AA2);
        articlesComp2.add(AA3);

        Article AC1 = new Compound("NexalComp1",articlesComp1);
        Article AC2 = new Compound("NexalComp2",articlesComp2);
        Program.getInstance().getArticles().add(AC1);
        Program.getInstance().getArticles().add(AC2);

        NotificationCenter.getInstance().update("Notify 1");
        NotificationCenter.getInstance().update("Notify 2");
        NotificationCenter.getInstance().update("Notify 3");
        NotificationCenter.getInstance().update("Notify 4");
        NotificationCenter.getInstance().update("Notify 5");

        Program.getInstance().upload();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime)/1000000;
        System.out.println("Execution time: "+duration+"ms");
    }

}