import java.util.*;

public class CentroNotifiche implements Observer {

    private CentroNotifiche() {
        nofications = new ArrayList<>();
    }

    private ArrayList<String> nofications;

    private static CentroNotifiche instance;

    public void viewNotification() {
    }

    public void addNotification(String newNotification){// non è in uml
            nofications.add(newNotification);
    }

    public void update(String notification) {
    }


    public static CentroNotifiche getInstance() {
            if (instance==null){
                instance = new CentroNotifiche();
            }
            return instance;
    }

    public void loadNotification() {
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}