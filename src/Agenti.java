import org.javatuples.Pair;

import java.util.ArrayList;

public class Agenti extends Utenti implements Observable{

    private Catalogo catalog;
    private float commissionPerc;

    public Agenti(String name, String password, float commissionPerc, Catalogo catalog) {
            super(name,password);
            this.commissionPerc = commissionPerc;
            this.catalog = catalog;
    }

    public Agenti(String name, String passwordHash, float commissionPerc, Catalogo catalog, int id) {
        super(name,passwordHash, id);
        this.commissionPerc = commissionPerc;
        this.catalog = catalog;
    }

    public Catalogo getCatalog() {
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

    public void createOrder(Clienti c, ArrayList<Pair<Articolo,Integer>> articles) {
        Programma.getInstance().getOrders().add(new Ordini(this,articles,c));
        System.out.println("Created!");
        notify(CentroNotifiche.getInstance(),"Sio merda");
    }

    @Override
    public void notify(Observer o,String notification) {
        o.update(notification);
    }

    public boolean deleteOrder(int id) {

        boolean check = false;
        for(Ordini i : Programma.getInstance().getOrders()) {
            if(i.getId() == id && i.getAgent().getId() == this.id){
                Programma.getInstance().getOrders().remove(i);
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
        for(Ordini i : Programma.getInstance().getOrders()){
            if(i.getAgent().getId() == this.id) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
                check=true;
            }
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");
    }


}