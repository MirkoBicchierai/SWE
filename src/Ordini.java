import java.util.*;

public class Ordini extends Observable  {

    public Ordini(float total, float commissionTot, Agenti agent, ArrayList<Articolo> articles , Clienti client ) {
        this.total = total;
        this.commissionTot = commissionTot;
        lastID++;
        this.id = lastID;
        this.agent = agent;
        this.articles = articles;
        this.client = client;
    }

    public Ordini(float total, float commissionTot, Agenti agent, ArrayList<Articolo> articles, Clienti client ,int id ) {
        this.total = total;
        this.commissionTot = commissionTot;
        this.agent = agent;
        this.articles = articles;
        this.id=id;
        lastID = Math.max(lastID, id);
        this.client = client;
    }

    public ArrayList<Articolo> getArticles() {
        return articles;
    }

    private ArrayList<Articolo> articles;

    public Clienti getClient() {
        return client;
    }

    private Clienti client;

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