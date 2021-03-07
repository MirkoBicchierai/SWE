public class Amministratori extends Utenti {

    public Amministratori(String name, String password) {
        super(name, password);
    }
    public Amministratori(String name, String passwordHash, int id) {
        super(name, passwordHash, id);
    }

    @Override
    public void viewOrders() {
        System.out.println("----------------------------------");
        boolean check = false;
        for(Ordini i : Programma.getInstance().getOrders()){
            System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
            check=true;
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");
    }

    @Override
    public void viewCatalog() {
        System.out.println("----------------------------------");
        if (Programma.getInstance().getCatalogs().size()>0){
            System.out.println("");
            for (Catalogo i : Programma.getInstance().getCatalogs()) {
                i.printCatalog();
                System.out.println("");
            }
        }else
            System.out.println("There are no catalogs!.");
        System.out.println("----------------------------------");
    }

    public void viewClient() {
    }

    public void viewProduct() {
    }

    public void createAgent() {
    }

    public void createCatlog() {
    }

    public void selectCatalog(int id) {
    }

    public void selectProduct(int id) {
    }

    public void addClient() {
    }

    public void addAgent() {
    }

    public void deleteAgent(int idAgent){
    }

    public void viewAgent() {
    }

    public void viewCatalogAgent(int idAgent){
    }

    public void viewCustomerOrders(int idAgent){
    }

    public void addCatalog(){
    }

    public void deleteCatalog(int IdCatalog){
    }

    public void deleteClient(int idCLient) {
    }

    public void deleteProduct() {
    }

    public void createProduct() {
    }

}