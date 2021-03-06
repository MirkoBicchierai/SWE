import java.util.*;

public class CentroNotifiche implements Observer {

    private CentroNotifiche() {
        nofications = new ArrayList<>();
    }

    private ArrayList<String> nofications;

    private static CentroNotifiche Instance;

    public void viewNotification() {
    }

    public void addNotification(String newNotification){// non è in uml
            nofications.add(newNotification);
    }

    public void update(String notification) {
    }

    public static void getInstance() {
    }

    public void loadNotification() {
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}