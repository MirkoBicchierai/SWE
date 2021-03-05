import java.sql.Connection;
import java.util.*;

public class Programma {

    private Programma() {}

    private int activeID;
    private ArrayList<Utenti> users = new ArrayList<>();
    private ArrayList<Articolo> articles = new ArrayList<>();
    private CentroNotifiche notCenter;
    private ArrayList<Catalogo> catalogs = new ArrayList<>();
    private ArrayList<Clienti> customers = new ArrayList<>();
    private ArrayList<Ordini> orders = new ArrayList<>();
    private static Programma instance;
    private Connection c=DBConnection.getInstance();

    public void login(int id, String psw) {

    }

    public static Programma getInstance() {
        if (instance==null) {
            instance = new Programma();
            instance.load();
        }
        return instance;
    }

    public void load() {



    }

    public void upload() {
        // TODO implement here
    }

}