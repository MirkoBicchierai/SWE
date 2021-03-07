import java.util.Scanner;

public class AgentHistoricalOrderMenu implements Menu{
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {

        activeUser.viewOrders();

        Scanner in = new Scanner(System.in);

        System.out.println("Menu option:");
        System.out.println("1. Delete an order");
        System.out.println("9. Back");
        System.out.println("0. Back");

        boolean quit = false;
        int menuItem;
        do {
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {

                case 1:
                    System.out.println("richiesta id");
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
