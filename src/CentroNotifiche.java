import java.util.*;

public class CentroNotifiche implements Observer {

    private CentroNotifiche() {
        nofications = new ArrayList<>();
    }

    private ArrayList<String> nofications;

    private static CentroNotifiche Instance;

    public void viewNotification() {
        // TODO implement here
    }

    public void addNotification(String newNotification){// non è in uml
            nofications.add(newNotification);
    }

    public void update(String notification) {
        // TODO implement here
    }

    public static void getInstance() {
        // TODO implement here
    }

    public void loadNotification() {
        // TODO implement here
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}