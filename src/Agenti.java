public class Agenti extends Utenti {

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

    private Catalogo catalog;

    public float getCommissionPerc() {
        return commissionPerc;
    }

    private float commissionPerc;

    public void viewPersonalStoricOrder() {
    }

    public void viewCatalog() {

        System.out.println("Catalog: " + catalog.getDescription() +" MarketZone: " + catalog.getMarketZone());
        for(Articolo i : catalog.getArticles()){
            System.out.println("Article: " + i.getName() + "price: " + i.getPrice());
        }

    }

    public void createOrder() {
    }

    public boolean deleteOrder(int id) {

        boolean check = false;
        for(Ordini i : Programma.getInstance().getOrders()) {
            if(i.getId() == id){
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
        int k = 0;
        for(Ordini i : Programma.getInstance().getOrders()){
            if(i.getAgent().getId() == this.id) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
                k++;
            }
        }
        if(k==0)
            System.out.println("There are no orders.");

    }

}