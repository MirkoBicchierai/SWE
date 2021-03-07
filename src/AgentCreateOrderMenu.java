import java.util.Scanner;

public class AgentCreateOrderMenu implements Menu{
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        int menuItem;
        do {

            System.out.println("Menu option:");
            System.out.println("1. Create");
            System.out.println("2. Do something");

            System.out.println("9. Back");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");

            menuItem = in.nextInt();
            switch (menuItem) {
                case 1:
                    System.out.println("Create");
                    break;
                case 2:
                    System.out.println("Do something");
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
