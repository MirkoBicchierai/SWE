package agentManager;

import org.javatuples.Pair;
import java.util.ArrayList;

public final class Order {

    private final ArrayList<Pair<Article, Integer>> pairArticles;
    private final int id;
    private static int lastID;
    private Agent agent;
    private final float total;
    private final float commissionTot;
    private final Customer client;

    public Order(Order old){
        this(old.getTotal(),old.getCommissionTot(), old.getAgent(), old.getRows(), old.getClient(), old.getId());
    }

    public Order(Agent agent, ArrayList<Pair<Article, Integer>> pairArticles , Customer client ) {

        float tmp = 0;
        for (Pair<Article, Integer> a : pairArticles)
            tmp = tmp + a.getValue0().getPrice() * a.getValue1();

        this.total = tmp;
        this.commissionTot = (agent.getCommissionPercentage()*this.total)/100;
        lastID++;
        this.id = lastID;
        this.agent = agent;
        this.pairArticles = pairArticles;
        this.client = client;
    }

    public Order(float total, float commissionTot, Agent agent, ArrayList<Pair<Article, Integer>> pairArticles , Customer client ) {
        this.total = total;
        this.commissionTot = commissionTot;
        lastID++;
        this.id = lastID;
        this.agent = agent;
        this.pairArticles = pairArticles;
        this.client = client;
    }

    public Order(float total, float commissionTot, Agent agent, ArrayList<Pair<Article, Integer>> pairArticles, Customer client , int id ) {
        this.total = total;
        this.commissionTot = commissionTot;
        this.agent = agent;
        this.pairArticles = pairArticles;
        this.id=id;
        lastID = Math.max(lastID, id);
        this.client = client;
    }

    public ArrayList<Article> getArticles() {

        ArrayList<Article> tmp = new ArrayList<>();
        for (Pair<Article, Integer> a : pairArticles){
            tmp.add(a.getValue0());
        }

        return tmp;

    }

    public ArrayList<Pair<Article, Integer>> getRows() {
        return pairArticles;
    }

    public Customer getClient() {
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

    public Agent getAgent() {
        return agent;
    }

    public float getCommissionTot() {
        return commissionTot;
    }

    public void printArticle() {

        for(Pair<Article,Integer> i:pairArticles)
            System.out.println("    • Id: "+i.getValue0().getId()+" Article: "+i.getValue0().getName()+" Price: "+i.getValue0().getPrice()+" Qta: "+i.getValue1());

    }

    public void agentDeleted(){
        agent=null;
    }

}