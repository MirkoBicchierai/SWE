public class Amministratori extends Utenti {

    public Amministratori(String name, String password) {
        super(name, password);
    }
    public Amministratori(String name, String passwordHash, int id) {
        super(name, passwordHash, id);
    }

    @Override
    public void viewOrders() {
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

    public void viewCatalog() {
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