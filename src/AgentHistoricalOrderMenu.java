import java.util.Scanner;

public class AgentHistoricalOrderMenu implements Menu{
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {

        activeUser.viewOrders();
        Agenti agent = (Agenti)activeUser;
        Scanner in = new Scanner(System.in);

        boolean quit = false;
        int idOrder;
        int menuItem;
        do {
            activeUser.viewOrders();
            System.out.println("Menu option:");
            System.out.println("1. Delete an order");
            System.out.println("9. Back");
            System.out.println("0. Back");
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {

                case 1:
                    do {
                        System.out.println("Insert order ID");
                        idOrder = in.nextInt();
                    }while(!agent.deleteOrder(idOrder));
                    break;

                case 9:
                    Programma.getInstance().setMenu(new AgentMainMenu());
                    quit = true;
                    break;

                case 0:
                    quit = true;
                    Programma.getInstance().close();
                    break;

                default:
                    System.out.println("Invalid choice.");

            }
        } while (!quit);

    }
}
