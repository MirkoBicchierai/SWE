import java.math.BigInteger;
import java.security.*;

public class Utenti {

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

    protected int id;
    protected String name;
    protected String passwordHash;
    protected int type;
    protected static int lastID;

    public static String getHash(String password){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
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

}