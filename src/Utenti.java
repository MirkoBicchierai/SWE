import java.math.BigInteger;
import java.security.*;

public class Utenti {

    public Utenti(String name, String password) {
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(password.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1,digest);
            String hashText = bigInt.toString(16);
            while(hashText.length() < 32 ){
                hashText = "0" + hashText;
            }
            this.passwordHash = hashText;
        }catch (NoSuchAlgorithmException e) {
            System.out.println("Hash generator error");
        }
        lastID++;
        this.id = lastID;
        this.name = name;

    }

    protected int id;
    protected String name;
    protected String passwordHash;
    protected int type;
    protected static int lastID;

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