import java.util.Scanner;

public class AgentMainMenu implements Menu {
    //potrebbe essere static?? insieme ad admin
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {

        Agenti activeAgent = (Agenti)activeUser;

        Scanner in = new Scanner(System.in);

        System.out.println("Hello "+activeUser.getName()+"!");
        System.out.println("Menu option:");

        System.out.println("1. View catalog");
        System.out.println("2. Historical Order");
        System.out.println("3. Create Order");

        System.out.println("0. Quit");

        boolean quit = false;
        int menuItem;
        do {
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {
                case 1:
                    activeAgent.viewCatalog();
                    break;
                case 2:
                    System.out.println("You've chosen item #2");
                    break;
                case 3:
                    System.out.println("You've chosen item #3");
                    break;
                case 0:
                    quit = true;
                    Programma.getInstance().close();
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);

        System.out.println("Bye-bye!");

    }

}
