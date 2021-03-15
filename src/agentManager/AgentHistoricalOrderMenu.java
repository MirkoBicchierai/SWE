package agentManager;

import java.util.Scanner;

public final class AgentHistoricalOrderMenu implements Menu{

    @Override
    public void showMenu() {

        Agent agent = (Agent) Program.getInstance().getActiveUser();
        Scanner in = new Scanner(System.in);

        boolean quit = false;
        int idOrder;
        int menuItem;
        do {
            agent.viewOrders();
            System.out.println("Menu option:");
            System.out.println("1. Delete an order");
            System.out.println("9. Back");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");

            try {
                menuItem = Integer.parseInt(in.next());
            }catch (Exception e){
                menuItem = -1;
            }

            switch (menuItem) {
                case 1:
                    do {
                        System.out.println("Insert order ID");
                        try {
                            idOrder = Integer.parseInt(in.next());
                        }catch (Exception e){
                            idOrder = -1;
                        }
                    }while(!agent.deleteOrder(idOrder));
                    break;

                case 9:
                    Program.getInstance().setMenu(new AgentMainMenu());
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
