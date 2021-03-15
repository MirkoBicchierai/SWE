package agentManager;

import java.util.ArrayList;

public final class NotificationCenter implements Observer {

    private ArrayList<String> notification;
    private static NotificationCenter instance;

    private NotificationCenter() {
        notification = new ArrayList<>();
    }

    public static NotificationCenter getInstance() {
        if (instance==null){
            instance = new NotificationCenter();
        }
        return instance;
    }

    public ArrayList<String> getNotification() {
        return notification;
    }

    public void viewNotification() {
        System.out.println("----------------------------------");
        for (String i: notification){
            System.out.println(i);
        }
        if(notification.size()==0) System.out.println("There aren't Notification!");

        System.out.println("----------------------------------");
        resetNotification();
    }

    private void resetNotification(){
        notification = new ArrayList<>();
    }

    @Override
    public void update(String notification) {
        this.notification.add(notification);
    }

}