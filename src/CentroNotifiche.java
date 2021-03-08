import java.util.*;

public class CentroNotifiche implements Observer {

    private CentroNotifiche() {
        nofications = new ArrayList<>();
    }

    public ArrayList<String> getNofications() {
        return nofications;
    }

    private ArrayList<String> nofications;

    private static CentroNotifiche instance;

    public void viewNotification() {
        //TODO STAMPA
        resetNotification();
    }

    @Override
    public void update(String notification) {
        nofications.add(notification);
    }

    public static CentroNotifiche getInstance() {
            if (instance==null){
                instance = new CentroNotifiche();
            }
            return instance;
    }

    private void resetNotification(){
        nofications = new ArrayList<>();
    }

}