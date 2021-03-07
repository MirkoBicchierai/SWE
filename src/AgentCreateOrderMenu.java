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
        int idC;
        do {

            System.out.println("Menu option:");
            System.out.println("1. Create new customers");
            System.out.println("2. Select a existing customers");

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
                    idC = createCustomers(activeUser);
                    System.out.println(idC);
                    break;
                case 2:
                    // TODO prendere idc
                    quit = subMenuSelectCustomer();
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

    private boolean subMenuSelectCustomer(){
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        boolean retQuit = false;
        int menuItem;
        do {

            System.out.println("Menu option:");
            System.out.println("1. ");
            System.out.println("2. ");

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

                    break;
                case 2:
                    System.out.println("Do something");
                    break;
                case 9:
                    Programma.getInstance().setMenu(new AgentCreateOrderMenu());
                    retQuit = true;
                    quit = true;
                    break;
                case 0:
                    retQuit = true;
                    quit = true;
                    Programma.getInstance().close();
                    break;
                default:
                    System.err.println("Invalid choice.");
            }
        } while (!quit);
        return retQuit;
    }

}
