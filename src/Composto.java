import java.util.*;

public class Composto extends Articolo {

    public Composto(String name,ArrayList<Articolo> components) {
        super( name, 0);
        for(Articolo i:components) {
            this.price += i.price;
        }
        this.components = components;
    }

    ArrayList<Articolo> components;

    public void display() {
    }

}