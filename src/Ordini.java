import java.util.*;

public class Ordini extends Observable  {

    public Ordini(float total, float commissionTot) {
        this.total = total;
        this.commissionTot = commissionTot;
        lastID++;
        this.id = lastID;
    }

    public int id;
    private static int lastID;
    public float total;
    public float commissionTot;

}