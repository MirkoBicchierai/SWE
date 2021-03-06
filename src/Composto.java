import java.util.*;

public class Composto extends Articolo {

    public Composto(String name,ArrayList<Articolo> components) {
        super( name, 0);
        for(Articolo i:components) {
            this.price += i.price;
        }
        this.components = components;
    }

    public Composto(String name,ArrayList<Articolo> components, int id) {
        super( name, 0, id);
        for(Articolo i:components) {
            this.price += i.price;
        }
        this.components = components;
    }

    public ArrayList<Articolo> getComponents() {
        return components;
    }

    private ArrayList<Articolo> components;

    public void display() {
    }

}