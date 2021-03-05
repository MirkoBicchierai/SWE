import java.util.*;

public class Programma {

    private Programma() {}

    private int activeID;
    private ArrayList<Utenti> users;
    private ArrayList<Articolo> articles;
    private CentroNotifiche notCenter;
    private ArrayList<Catalogo> catalogs;
    private ArrayList<Clienti> customers;
    private ArrayList<Ordini> orders;
    private static Programma instance;

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