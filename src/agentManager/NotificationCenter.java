package agentManager;

import java.util.ArrayList;

public final class NotificationCenter implements Observer {

    private NotificationCenter() {
        notification = new ArrayList<>();
    }

    public ArrayList<String> getNotification() {
        return notification;
    }

    private ArrayList<String> notification;

    private static NotificationCenter instance;

    public void viewNotification() {
        System.out.println("----------------------------------");
        for (String i: notification){
            System.out.println(i);
        }
        if(notification.size()==0) System.out.println("There aren't Notification!");

        System.out.println("----------------------------------");
        resetNotification();
    }

    @Override
    public void update(String notification) {
        this.notification.add(notification);
    }

    public static NotificationCenter getInstance() {
            if (instance==null){
                instance = new NotificationCenter();
            }
            return instance;
    }

    private void resetNotification(){
        notification = new ArrayList<>();
    }

}