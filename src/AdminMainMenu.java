import java.util.Scanner;

public class AdminMainMenu implements Menu{

    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {

        Amministratori admin = (Amministratori)activeUser;
        Scanner in = new Scanner(System.in);

        System.out.println("Hello "+activeUser.getName()+"!");

        System.out.println("");

        for (String i: CentroNotifiche.getInstance().getNofications()){
            System.out.println(i);
            //CentroNotifiche.getInstance().getNofications().remove(i);
        }

        System.out.println("");

        boolean quit = false;

        int menuItem;

        do {
            System.out.println("Menu Option:");

            System.out.println("1. View Agents");
            System.out.println("2. View Catalogs");
            System.out.println("3. View Customers");
            System.out.println("4. View Storic Orders");

            System.out.println("9. Logout");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {

                case 1:
                    admin.viewAgent();
                    Programma.getInstance().setMenu(new AdminAgentMenu());
                    quit = true;
                    break;

                case 2:
                    activeUser.viewCatalog();
                    break;

                case 3:
                    admin.viewClient();
                    Programma.getInstance().setMenu(new AdminCustomersMenu());
                    quit = true;
                    break;

                case 4:
                    admin.viewOrders();
                    break;

                case 9:
                    Programma.getInstance().logout();
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
