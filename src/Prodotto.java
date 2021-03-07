import java.util.*;

public class Prodotto extends Articolo {

    public Prodotto(String name, float price) {
        super(name, price);
    }

    public Prodotto(String name, float price, int id) {
        super(name, price, id);
    }

    public void display() {
        System.out.println("--Article: " + name + "price: " + price);
    }

}