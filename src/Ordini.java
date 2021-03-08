import org.javatuples.Pair;

import java.util.*;

public class Ordini {

    private ArrayList<Pair<Articolo, Integer>> pairArticles;

    private int id;
    private static int lastID;
    private Agenti agent;
    private float total;
    private float commissionTot;
    private Clienti client;

    public Ordini(Agenti agent, ArrayList<Pair<Articolo, Integer>> pairArticles , Clienti client ) {

        this.total = 0;

        for (Pair<Articolo, Integer> a : pairArticles){
            this.total = this.total + a.getValue0().getPrice();
        }

        this.commissionTot = (agent.getCommissionPerc()*this.total)/100;

        lastID++;
        this.id = lastID;
        this.agent = agent;
        this.pairArticles = pairArticles;
        this.client = client;
    }

    public Ordini(float total, float commissionTot, Agenti agent, ArrayList<Pair<Articolo, Integer>> pairArticles , Clienti client ) {
        this.total = total;
        this.commissionTot = commissionTot;
        lastID++;
        this.id = lastID;
        this.agent = agent;
        this.pairArticles = pairArticles;
        this.client = client;
    }

    public Ordini(float total, float commissionTot, Agenti agent, ArrayList<Pair<Articolo, Integer>> pairArticles, Clienti client ,int id ) {
        this.total = total;
        this.commissionTot = commissionTot;
        this.agent = agent;
        this.pairArticles = pairArticles;
        this.id=id;
        lastID = Math.max(lastID, id);
        this.client = client;
    }

    public ArrayList<Articolo> getArticles() {

        ArrayList<Articolo> tmp = new ArrayList<>();
        for (Pair<Articolo, Integer> a : pairArticles){
            tmp.add(a.getValue0());
        }

        return tmp;
    }

    public ArrayList<Pair<Articolo, Integer>> getRows() {
        return pairArticles;
    }

    public Clienti getClient() {
        return client;
    }

    public int getId() {
        return id;
    }

    public static int getLastID() {
        return lastID;
    }

    public float getTotal() {
        return total;
    }

    public void printArticle() {
        for(Pair<Articolo,Integer> i:pairArticles){
            System.out.println("    • Id: "+i.getValue0().getId()+" Article: "+i.getValue0().getName()+" Price: "+i.getValue0().getPrice()+" Qta: "+i.getValue1());
        }
    }

    public float getCommissionTot() {
        return commissionTot;
    }

    public Agenti getAgent() {
        return agent;
    }

}