package agentManager;

import java.util.Scanner;

public final class AdminMainMenu implements Menu{

    @Override
    public void showMenu() {

        Administrator admin = (Administrator) Program.getInstance().getActiveUser();
        Scanner in = new Scanner(System.in);

        System.out.println("Hello "+admin.getName()+"!");

        admin.viewNotification();

        boolean quit = false;
        int menuItem;

        do {
            System.out.println("Menu Option:");

            System.out.println("1. View Agents");
            System.out.println("2. View Catalogs");
            System.out.println("3. View Customers");
            System.out.println("4. View Historical Orders");
            System.out.println("5. View Articles");
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
                    Program.getInstance().setMenu(new AdminAgentMenu());
                    quit = true;
                    break;

                case 2:
                    Program.getInstance().setMenu(new AdminCatalogMenu());
                    quit = true;
                    break;

                case 3:
                    Program.getInstance().setMenu(new AdminCustomersMenu());
                    quit = true;
                    break;

                case 4:
                    admin.viewOrders();
                    break;

                case 5:
                    Program.getInstance().setMenu(new AdminArticleMenu());
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
