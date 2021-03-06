import java.util.*;

public class Agenti extends Utenti {

    public Agenti(String name, String password, float commissionPerc) {
            super(name,password);
            this.commissionPerc = commissionPerc;
    }

    public Agenti(String name, String password, float commissionPerc, int id) {
        super(name,password, id);
        this.commissionPerc = commissionPerc;
    }

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