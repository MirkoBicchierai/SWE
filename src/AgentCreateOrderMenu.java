import java.util.Scanner;

public class AgentCreateOrderMenu implements Menu{
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {
        Programma p = Programma.getInstance();
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        int menuItem;
        do {

            System.out.println("Menu option:");
            System.out.println("1. Create");
            System.out.println("2. Do something");

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
                    quit = subMenuCreateOrder(p);
                    break;
                case 2:
                    System.out.println("Do something");
                    break;
                case 9:
                    p.setMenu(new AgentMainMenu());
                    quit = true;
                    break;
                case 0:
                    quit = true;
                    p.close();
                    break;
                default:
                    System.err.println("Invalid choice.");
            }
        } while (!quit);
    }

    private boolean subMenuCreateOrder(Programma p){
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
                    Programma.getInstance().setMenu(new AgentMainMenu());
                    retQuit = true;
                    quit = true;
                    break;
                case 0:
                    retQuit = true;
                    quit = true;
                    p.close();
                    break;
                default:
                    System.err.println("Invalid choice.");
            }
        } while (!quit);
        return retQuit;
    }

}
