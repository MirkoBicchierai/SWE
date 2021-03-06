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
        System.out.println("3. Operation 1");
        System.out.println("4. Operation 1");
        System.out.println("5. Operation 1");

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

                    System.out.println("You've chosen item #2");

                    // do something...

                    break;

                case 3:

                    System.out.println("You've chosen item #3");

                    // do something...

                    break;

                case 4:

                    System.out.println("You've chosen item #4");

                    // do something...

                    break;

                case 5:

                    System.out.println("You've chosen item #5");

                    // do something...

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
