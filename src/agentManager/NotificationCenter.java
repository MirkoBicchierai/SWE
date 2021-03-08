package agentManager;

import java.util.ArrayList;

public class NotificationCenter implements Observer {

    private NotificationCenter() {
        nofications = new ArrayList<>();
    }

    public ArrayList<String> getNofications() {
        return nofications;
    }

    private ArrayList<String> nofications;

    private static NotificationCenter instance;

    public void viewNotification() {
        System.out.println("----------------------------------");
        for (String i: nofications){
            System.out.println(i);
        }
        if(nofications.size()==0) System.out.println("There aren't Notification!");

        System.out.println("----------------------------------");
        resetNotification();
    }

    @Override
    public void update(String notification) {
        nofications.add(notification);
    }

    public static NotificationCenter getInstance() {
            if (instance==null){
                instance = new NotificationCenter();
            }
            return instance;
    }

    private void resetNotification(){
        nofications = new ArrayList<>();
    }

}