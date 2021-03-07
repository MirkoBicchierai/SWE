import java.util.Scanner;

public class AdminArticleMenu implements Menu{
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
            System.out.println("1. Add Article");
            System.out.println("2. Delete Article");
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
                    admin.createProduct();//todo mai cosi
                    break;

                case 2:
                    System.out.println("Enter the code of the Product to Delete");
                    int idP = in.nextInt();
                    admin.deleteProduct(idP);
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
