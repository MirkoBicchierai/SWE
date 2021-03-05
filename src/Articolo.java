import java.util.*;

public abstract class Articolo {

    public Articolo() {
    }

    public String name;
    public int id;
    public float price;
    private static int lastID;
    public abstract void display();

}