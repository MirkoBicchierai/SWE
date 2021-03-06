import java.util.*;

public class Catalogo {

    public Catalogo(ArrayList<Articolo> articles,String description , String marketZone) {
        this.articles = articles;
        this.description = description;
        this.marketZone = marketZone;
        lastID++;
        this.id=lastID;
    }

    public int id;
    public String description;
    public String marketZone;
    private static int lastID;
    private ArrayList<Articolo> articles;

    public void viewCatalog() {
    }

    public void getArticle(int id) {
    }

}