package agentManager;

import org.javatuples.Pair;
import java.util.ArrayList;

public final class Agent extends User implements Subject {

    private final Catalog catalog;
    private final float commissionPercentage;
    private final ArrayList<Observer> observers;

    public Agent(String name, String password, float commissionPercentage, Catalog catalog,String email) {
        super(name,password,email);
        this.commissionPercentage = commissionPercentage;
        this.catalog = catalog;
        this.observers = new ArrayList<>();
    }

    public Agent(String name, String passwordHash, float commissionPercentage, Catalog catalog, String email,int id) {
        super(name,passwordHash, email,id);
        this.commissionPercentage = commissionPercentage;
        this.catalog = catalog;
        this.observers = new ArrayList<>();
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public float getCommissionPercentage() {
        return commissionPercentage;
    }

    public void createOrder(Customer c, ArrayList<Pair<Article,Integer>> articles) {
        Order order = new Order(this,articles,c);
        Program.getInstance().getOrders().add(order);
        System.out.println("Created!");
        notify(order);
    }

    public boolean deleteOrder(int id) {

        Order orderToDelete = null;

        for(Order i : Program.getInstance().getOrders()) {
            if(i.getId() == id && i.getAgent().getId() == this.getId()){
                orderToDelete = i;
            }
        }

        if(orderToDelete==null) {
            System.err.println("Wrong ID! Re-insert it");
            return false;
        }

        Program.getInstance().getOrders().remove(orderToDelete);

        return true;

    }

    @Override
    public void notify(Order order) {
        for(Observer o: observers)
            o.update(order);
    }

    @Override
    public void viewCatalog() {
        System.out.println("----------------------------------");
        catalog.printCatalog();
        System.out.println("----------------------------------");
    }

    @Override
    public void viewOrders() {
        System.out.println("----------------------------------");
        boolean check = false;
        for(Order i : Program.getInstance().getOrders()){
            if(i.getAgent().getId() == this.getId()) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
                i.printArticle();
                System.out.println();
                check=true;
            }
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");
    }

    @Override
    public void attach(Observer o) {

        observers.add(o);

    }

    @Override
    public void detach(Observer o) {

        observers.remove(o);

    }
}