package agentManager;

import java.util.*;

public class Compound extends Article {

    private ArrayList<Article> components;

    public Compound(String name, ArrayList<Article> components) {
        super( name, 0);
        for(Article i:components) {
            this.price += i.price;
        }
        this.components = components;
    }

    public Compound(String name, ArrayList<Article> components, int id) {
        super( name, 0, id);
        for(Article i:components) {
            this.price += i.price;
        }
        this.components = components;
    }

    public ArrayList<Article> getComponents() {
        return components;
    }

    public void display() {
        System.out.println("--Id: "+this.id+" Article compound: " + name + " Price: " + price);
        for(Article i : components)
            System.out.println("  ||Compound: " + i.getName() + " Price: " + i.getPrice());
    }

}