import java.util.*;

public class Composto extends Articolo {

    private ArrayList<Articolo> components;

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

    public void display() {
        System.out.println("Article compound: " + name + "price: " + price);
        for(Articolo i : components)
            System.out.println("--Compound: " + i.getName() + "price: " + i.getPrice());
    }

}