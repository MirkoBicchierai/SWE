package agentManager;

import java.util.Scanner;

public final class AgentMainMenu implements Menu {

    @Override
    public void showMenu() {

        Agent activeAgent = (Agent) Program.getInstance().getActiveUser();
        Scanner in = new Scanner(System.in);
        System.out.println("Hello "+activeAgent.getName()+"!");

        boolean quit = false;
        int menuItem;
        do {
            System.out.println("Menu option:");
            System.out.println("1. View catalog");
            System.out.println("2. Historical Order");
            System.out.println("3. Create Order");
            System.out.println("9. Logout");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");

            try {
                menuItem = Integer.parseInt(in.next());
            }catch (Exception e){
                menuItem = -1;
            }

            switch (menuItem) {
                case 1:
                    activeAgent.viewCatalog();
                    break;
                case 2:
                    Program.getInstance().setMenu(new AgentHistoricalOrderMenu());
                    quit = true;
                    break;
                case 3:
                    Program.getInstance().setMenu(new AgentCreateOrderMenu());
                    quit = true;
                    break;
                case 9:
                    Program.getInstance().logout();
                    quit = true;
                    break;
                case 0:
                    quit = true;
                    Program.getInstance().close();
                    break;
                default:
                    System.err.println("Invalid choice.");
            }
        } while (!quit);
    }

}
