import java.util.Scanner;

public class AdminCatalogMenu implements Menu{

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
            System.out.println("1. Add Catalog");
            System.out.println("2. Delete Catalog");
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
                    admin.addCatalog();//todo la signature mai cosi
                    break;
                case 2:
                    System.out.println("Enter the code of the Catalog to Delete");
                    int idCatalog;
                    try {
                        idCatalog = Integer.parseInt(in.next());
                    }catch (Exception e){
                        idCatalog = -1;
                    }
                    admin.deleteCatalog(idCatalog);
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
}
