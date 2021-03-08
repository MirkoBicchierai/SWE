import org.javatuples.Pair;

import java.util.*;

public class Ordini extends Observable  {

    //private ArrayList<Articolo> articles;

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

    public float getCommissionTot() {
        return commissionTot;
    }

    public Agenti getAgent() {
        return agent;
    }

}