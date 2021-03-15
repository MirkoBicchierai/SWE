package agentManager;

public abstract class Article {

    protected final String name;
    protected final int id;
    protected float price;
    private static int lastID;

    public Article(String name, float price) {
        this.name = name;
        lastID++;
        this.id = lastID;
        this.price = price;
    }

    public Article(String name, float price, int id) {
        this.name = name;
        this.price = price;
        this.id=id;
        lastID = Math.max(lastID, id);
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

    public abstract void display();

}