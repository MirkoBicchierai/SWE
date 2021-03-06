import java.util.*;

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

    private Catalogo catalog;

    public float getCommissionPerc() {
        return commissionPerc;
    }

    private float commissionPerc;

    public void viewPersonalStoricOrder() {
    }

    public void viewCatalog() {
    }

    public void createOrder() {
    }

    public void deleteOrder() {
    }

}