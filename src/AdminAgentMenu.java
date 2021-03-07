import java.util.ArrayList;
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
            try {
                menuItem = Integer.parseInt(in.next());
            }catch (Exception e){
                menuItem = -1;
            }
            switch (menuItem) {

                case 1:
                    admin.createAgent(); //todo mai cosi
                    break;
                case 2:
                    System.out.println("Enter the code of the Agent to Delete");
                    int idA = in.nextInt();
                    admin.deleteAgent(idA);
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

                    System.err.println("Invalid choice.");

            }

        } while (!quit);
    }

    private void createAgent(Amministratori activeUser){

        Scanner in = new Scanner(System.in);

        System.out.println("Insert Name:");
        String Name = in.nextLine();
        System.out.println("Insert Password :");
        String Password = in.nextLine();
        float percComm ;
        do{
            System.out.println("Insert Percentage of Commission :");
            try {
                percComm = Math.abs(Float.parseFloat(in.next()));
            }catch (Exception e){
                System.err.println("You must insert a number!");
                percComm = -1;
            }
        }while (percComm==-1);


        //activeUser.createAgent(description,marketZone); //todo da sistemare
    }
}
