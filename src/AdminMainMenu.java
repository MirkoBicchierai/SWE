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
        }

        System.out.println("");

        System.out.println("Menu Option:");

        System.out.println("1. View Agents");
        System.out.println("2. View Catalogs");
        System.out.println("3. View Customers");
        System.out.println("4. View Storic Orders");

        System.out.println("0. Quit");


        boolean quit = false;

        int menuItem;

        do {
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {

                case 1:
                    admin.viewAgent();
                    Programma.getInstance().setMenu(new AgentMainMenu());
                    quit = true;
                    break;
                case 2:
                    admin.viewCatalog();

                    quit = true;
                    break;
                case 3:
                    admin.viewClient();

                    break;
                case 4:
                    admin.viewOrders();
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
