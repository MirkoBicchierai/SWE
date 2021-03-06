import java.util.*;

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

    public void viewGlobalStoricOrder() {
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

    public void viewAgent() {
    }

    public void deleteClient() {
    }

    public void deleteProduct() {
    }

    public void createProduct() {
    }

}