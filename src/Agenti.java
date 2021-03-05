import java.util.*;

public class Agenti extends Utenti {

    public Agenti(String name, String password, float commissionPerc) {
            super(name,password);
            this.commissionPerc = commissionPerc;
    }

    public float getCommissionPerc() {
        return commissionPerc;
    }

    private float commissionPerc;

    public void viewPersonalStoricOrder() {
        // TODO implement here
    }

    public void viewCatalog() {
        // TODO implement here
    }

    public void createOrder() {
        // TODO implement here
    }

    public void deleteOrder() {
        // TODO implement here
    }

}