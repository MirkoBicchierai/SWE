import java.util.*;

public class Catalogo {

    public Catalogo(ArrayList<Articolo> articles,String description , String marketZone) {
        this.articles = articles;
        this.description = description;
        this.marketZone = marketZone;
        lastID++;
        this.id=lastID;
    }

    private int id;
    private String description;

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMarketZone() {
        return marketZone;
    }

    private String marketZone;
    private static int lastID = 0;

    public static int getLastID() {
        return lastID;
    }

    public ArrayList<Articolo> getArticles() {
        return articles;
    }

    private ArrayList<Articolo> articles;

    public void viewCatalog() {
    }

}