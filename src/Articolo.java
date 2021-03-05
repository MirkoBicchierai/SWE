import java.util.*;

public abstract class Articolo {


    public Articolo(String name, float price) {
        this.name = name;
        lastID++;
        this.id = lastID;
        this.price = price;
    }

    public String name;
    public int id;
    public float price;
    private static int lastID;
    public abstract void display();

}