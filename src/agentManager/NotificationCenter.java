package agentManager;

import java.util.ArrayList;

public final class NotificationCenter implements Observer {

    private ArrayList<String> notification;
    private static NotificationCenter instance;

    public NotificationCenter() {
        notification = new ArrayList<>();
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

    public void addNotification(String string){
        notification.add(string);
    }

    @Override
    public void update(Object obj) {
        Order order = (Order)obj;
        this.notification.add("A new order has been issued by for customer " + order.getClient().getBusinessName() + " from " + order.getAgent().getName());
    }

}