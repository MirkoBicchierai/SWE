import java.util.*;

/**
 * 
 */
public class Ordini extends Observable  {

    /**
     * Default constructor
     */
    public Ordini() {
    }

    /**
     * 
     */
    public int id;

    /**
     * 
     */
    private static int lastID;

    /**
     * 
     */
    public float total;

    /**
     * 
     */
    public float commissionTot;

}