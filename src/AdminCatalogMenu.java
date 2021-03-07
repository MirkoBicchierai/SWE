import java.util.ArrayList;
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

        boolean quit = false;               //finito!!

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
                    createCatalog(admin);
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

    private void createCatalog(Amministratori activeUser){

        Scanner in = new Scanner(System.in);

        System.out.println("Insert Description:");
        String description = in.nextLine();
        System.out.println("Insert Market Zone :");
        String marketZone = in.nextLine();

        ArrayList<Articolo> articles = new ArrayList<>();
        boolean agg;
        while (true){
            agg = false;
            activeUser.viewProduct();
            System.out.println("Insert Id Articles or 0 to terminate Catalog");
            try {
                int idArticle = Integer.parseInt(in.next());

                if (idArticle == 0) break;

                for (Articolo i : Programma.getInstance().getArticles()) {
                    if (i.getId() == idArticle) {
                        articles.add(i);
                        agg = true;
                    }
                }
                if (!agg) System.err.println("Id Article Not Found!");
            }catch (Exception e){}
            if (!agg) System.err.println("Id Article Not Found!");
        }

        activeUser.createCatlog(description,marketZone,articles);
    }
}
