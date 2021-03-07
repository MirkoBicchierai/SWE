public class Agenti extends Utenti {

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

    public void viewCatalog() {
        catalog.printCatalog();
    }

    public void createOrder() {
    }

    public boolean deleteOrder(int id) {

        boolean check = false;
        for(Ordini i : Programma.getInstance().getOrders()) {
            if(i.getId() == id && i.getAgent().getId() == this.id){
                Programma.getInstance().getOrders().remove(i);
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