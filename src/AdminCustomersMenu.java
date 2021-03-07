import java.util.Scanner;

public class AdminCustomersMenu implements Menu{
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
            System.out.println("1. Add Customers");
            System.out.println("2. Delete Customers");
            System.out.println("3. View Order Client");
            System.out.println("9. Back");
            System.out.println("0. Quit");
            System.out.print("Choose menu item: ");
            menuItem = in.nextInt();
            switch (menuItem) {

                case 1:
                    admin.addClient();//todo mai cosi
                    break;
                case 2:
                    System.out.println("Enter the code of the Customer to Delete");
                    int idC = in.nextInt();
                    admin.deleteClient(idC);
                    break;
                case 3:
                    System.out.println("Enter the code of the Customer for which to view the Orders");
                    int idCustomer = in.nextInt();
                    admin.viewCustomerOrders(idCustomer);
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
