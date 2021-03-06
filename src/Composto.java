import java.util.*;

public class Composto extends Articolo {

    public Composto(String name, float price, ArrayList<Articolo> components) {
        super( name, price);
        this.components = components;
    }

    ArrayList<Articolo> components;

    public void display() {
    }

}