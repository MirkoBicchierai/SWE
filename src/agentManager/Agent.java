package agentManager;

import org.javatuples.Pair;
import java.util.ArrayList;

public class Agent extends User implements Observable{

    private Catalog catalog;
    private float commissionPerc;

    public Agent(String name, String password, float commissionPerc, Catalog catalog) {
            super(name,password);
            this.commissionPerc = commissionPerc;
            this.catalog = catalog;
    }

    public Agent(String name, String passwordHash, float commissionPerc, Catalog catalog, int id) {
        super(name,passwordHash, id);
        this.commissionPerc = commissionPerc;
        this.catalog = catalog;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public float getCommissionPerc() {
        return commissionPerc;
    }

    @Override
    public void viewCatalog() {
        System.out.println("----------------------------------");
        catalog.printCatalog();
        System.out.println("----------------------------------");
    }

    public void createOrder(Customer c, ArrayList<Pair<Article,Integer>> articles) {
        Program.getInstance().getOrders().add(new Order(this,articles,c));
        System.out.println("Created!");
        notify(NotificationCenter.getInstance(),"A new order has been issued by for customer " + c.getBusinessName() + " from " + this.getName());
    }

    @Override
    public void notify(Observer o,String notification) {
        o.update(notification);
    }

    public boolean deleteOrder(int id) {

        boolean check = false;
        for(Order i : Program.getInstance().getOrders()) {
            if(i.getId() == id && i.getAgent().getId() == this.getId()){
                Program.getInstance().getOrders().remove(i);
                i.printArticle();
                check=true;
            }
        }
        if(!check)
            System.err.println("Wrong ID! Re-insert it");
        return check;
    }

    @Override
    public void viewOrders() {
        System.out.println("----------------------------------");
        boolean check = false;
        for(Order i : Program.getInstance().getOrders()){
            if(i.getAgent().getId() == this.getId()) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
                i.printArticle();
                System.out.println("");
                check=true;
            }
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");
    }


}