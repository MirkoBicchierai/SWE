import java.util.*;

public class Ordini extends Observable  {

    public Ordini(float total, float commissionTot, Agenti agent, ArrayList<Articolo> articles ) {
        this.total = total;
        this.commissionTot = commissionTot;
        lastID++;
        this.id = lastID;
        this.agent = agent;
        this.articles = articles;
    }

    public ArrayList<Articolo> getArticles() {
        return articles;
    }

    private ArrayList<Articolo> articles;

    private int id;
    private static int lastID;

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

    private Agenti agent;

    private float total;
    private float commissionTot;

}