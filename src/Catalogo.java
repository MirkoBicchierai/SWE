import java.util.*;

public class Catalogo {

    private int id;
    private String description;
    private String marketZone;
    private static int lastID = 0;
    private ArrayList<Articolo> articles;

    public Catalogo(ArrayList<Articolo> articles,String description , String marketZone) {
        this.articles = articles;
        this.description = description;
        this.marketZone = marketZone;
        lastID++;
        this.id=lastID;
    }
    public Catalogo(ArrayList<Articolo> articles,String description , String marketZone, int id) {
        this.articles = articles;
        this.description = description;
        this.marketZone = marketZone;
        this.id=id;
        lastID = Math.max(lastID, id);
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getMarketZone() {
        return marketZone;
    }

    public static int getLastID() {
        return lastID;
    }

    public ArrayList<Articolo> getArticles() {
        return articles;
    }

    public void printCatalog() {
        System.out.println("Catalog: " + description +" MarketZone: " + marketZone);
        for(Articolo i : articles){
            System.out.println("Article: " + i.getName() + "price: " + i.getPrice());
        }
    }

}