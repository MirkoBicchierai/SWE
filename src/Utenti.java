import java.math.BigInteger;
import java.util.*;
import java.security.*;

public class Utenti {

    public Utenti(String name, String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashtext = bigInt.toString(16);
            while(hashtext.length() < 32 ){
                hashtext = "0" + hashtext;
            }
            this.passwordHash = hashtext;
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Errore Generazione Hash");
        }
        lastID++;
        this.id = lastID;
        this.name = name;

    }


    private int id;
    private String name;
    private String passwordHash;
    private static int lastID;

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

}