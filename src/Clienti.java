import java.util.*;

public class Clienti {

    public Clienti(String businessName, String country, String email) {
        this.businessName = businessName;
        this.country = country;
        this.email = email;
        lastID++;
        this.id=lastID;
    }

    public int id; // non è in uml
    public String businessName;
    public String country;
    public String email;
    private static int lastID;

}