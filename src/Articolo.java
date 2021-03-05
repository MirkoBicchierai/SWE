import java.util.*;

public abstract class Articolo {


    public Articolo(String name, float price) {
        this.name = name;
        lastID++;
        this.id = lastID;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public float getPrice() {
        return price;
    }

    public static int getLastID() {
        return lastID;
    }

    private String name;
    private int id;
    private float price;
    private static int lastID;
    public abstract void display();

}