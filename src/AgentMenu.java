import java.util.Scanner;

import java.util.Scanner;

public class AgentMenu implements Menu {
    //potrebbe essere static?? insieme ad admin
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {

        Scanner in = new Scanner(System.in);

        System.out.println("Menu option:");


        System.out.println("1. Operation 1");
        System.out.println("2. Operation 2");
        System.out.println("3. Operation 3");

        System.out.println("0. Quit");

        // handle user commands
        boolean quit = false;
        int menuItem;
        do {
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {
                case 1:
                    System.out.println("You've chosen item #1");
                    // do something...
                    break;
                case 2:
                    System.out.println("You've chosen item #2");
                    // do something...
                    break;
                case 3:
                    System.out.println("You've chosen item #3");
                    // do something...
                    break;
                case 0:
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);

        System.out.println("Bye-bye!");

    }

}
