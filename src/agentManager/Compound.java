package agentManager;

import java.util.ArrayList;

public final class Compound extends Article {

    private final ArrayList<Article> components;

    public Compound(String name, ArrayList<Article> components) {
        super( name, 0);
        for(Article i:components)
            this.price += i.price;
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
        System.out.println("--Id: "+this.id+" Compound: " + name + " Price: " + price);
        for(Article i : components) {
            System.out.println("  ||Component: " + i.getName() + " Price: " + i.getPrice() + (i instanceof Compound ? " (Compound)":""));
        }
    }

}