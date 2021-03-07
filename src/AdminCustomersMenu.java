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
            try {
                menuItem = Integer.parseInt(in.next());
            }catch (Exception e){
                menuItem = -1;
            }
            switch (menuItem) {

                case 1:
                    createCustomers(admin);
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

                    System.err.println("Invalid choice.");

            }

        } while (!quit);
    }


    private int createCustomers(Utenti activeUser){

        Scanner in = new Scanner(System.in);

        System.out.println("Insert Email:");
        String email = in.nextLine();
        System.out.println("Insert country :");
        String country = in.nextLine();
        System.out.println("Insert Business-Name :");
        String name = in.nextLine();

        return  activeUser.createCustomer(name,country,email);
    }
}
