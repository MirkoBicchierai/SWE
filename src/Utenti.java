import java.math.BigInteger;
import java.security.*;

public abstract class Utenti {

    protected int id;
    protected String name;
    protected String passwordHash;
    protected static int lastID;

    public Utenti(String name, String password) {
        this.passwordHash=getHash(password);
        lastID++;
        this.id = lastID;
        this.name = name;
    }

    public Utenti(String name, String passwordHash, int id) {
        this.passwordHash=passwordHash;
        this.id=id;
        lastID = Math.max(lastID, id);
        this.name = name;
    }

    public static String getHash(String password){
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public static int getLastID() {
        return lastID;
    }

    public void createCustomer(String businessName, String country, String email){
        Programma.getInstance().getCustomers().add(new Clienti(businessName,country,email));
        System.out.println("Created!");
    }

    public void viewCustomers(){
        System.out.println("");
        System.out.println("----------------------------------");
        for(Clienti c : Programma.getInstance().getCustomers()){
            System.out.println("CUSTOMERS -> ID: " + c.getId() + " Name: " + c.getBusinessName() + "€ Email: " + c.getEmail() + "€ Country: " + c.getCountry());
        }
        System.out.println("----------------------------------");
        System.out.println("");

    }

    public abstract void viewOrders();
    public abstract void viewCatalog();

}