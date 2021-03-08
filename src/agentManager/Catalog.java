package agentManager;

import java.util.*;

public class Catalog {

    private int id;
    private String description;
    private String marketZone;
    private static int lastID = 0;
    private ArrayList<Article> articles;

    public Catalog(ArrayList<Article> articles, String description , String marketZone) {
        this.articles = articles;
        this.description = description;
        this.marketZone = marketZone;
        lastID++;
        this.id=lastID;
    }
    public Catalog(ArrayList<Article> articles, String description , String marketZone, int id) {
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

    public ArrayList<Article> getArticles() {
        return articles;
    }

    public void printCatalog() {
        System.out.println("Id: "+id+" Catalog: " + description +" MarketZone: " + marketZone);
        for(Article i : articles){
            i.display();
        }
    }

}