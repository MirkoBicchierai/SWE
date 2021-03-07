import java.util.Scanner;

public class AdminAgentMenu implements Menu{
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {
        Amministratori admin = (Amministratori)activeUser;
        Scanner in = new Scanner(System.in);

        boolean quit = false;

        int menuItem;

        do {
            System.out.println("1. Add Agent");
            System.out.println("2. Delete Agent");
            System.out.println("3. View Catalog Agent");
            System.out.println("9. Back");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {

                case 1:
                    admin.addAgent();
                    break;
                case 2:
                    admin.deleteAgent();
                    break;
                case 3:
                    System.out.println("Enter the code of the agent for which to view the catalog");
                    int idAgent = in.nextInt();
                    admin.viewCatalogAgent(idAgent);
                    break;
                case 9:
                    quit = true;
                    Programma.getInstance().setMenu(new AdminMainMenu());
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
