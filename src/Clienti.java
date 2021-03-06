import java.util.*;

public class Clienti {

    public Clienti(String businessName, String country, String email) {
        this.businessName = businessName;
        this.country = country;
        this.email = email;
        lastID++;
        this.id=lastID;
    }

    public Clienti(int id,String businessName, String country, String email) {
        this.businessName = businessName;
        this.country = country;
        this.email = email;
        this.id=id;
        lastID = Math.max(lastID, id);
    }

    private int id; // non è in uml

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCountry() {
        return country;
    }

    public static int getLastID() {
        return lastID;
    }

    private String businessName;
    private String country;
    private String email;
    private static int lastID = 0;

}