package agentManager;

import java.util.ArrayList;
import java.util.Scanner;

public final class AdminCatalogMenu implements Menu{

    @Override
    public void showMenu() {

        Administrator admin = (Administrator) Program.getInstance().getActiveUser();
        Scanner in = new Scanner(System.in);

        boolean quit = false;

        int menuItem;

        do {
            admin.viewCatalog();
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
                    Program.getInstance().setMenu(new AdminMainMenu());
                    break;

                case 0:
                    quit = true;
                    Program.getInstance().close();
                    break;

                default:

                    System.err.println("Invalid choice.");

            }

        } while (!quit);
    }

    private void createCatalog(Administrator activeUser){

        Scanner in = new Scanner(System.in);

        System.out.println("Insert Description:");
        String description = in.nextLine();
        System.out.println("Insert Market Zone :");
        String marketZone = in.nextLine();

        ArrayList<Article> articles = new ArrayList<>();
        boolean agg;
        while (true){
            agg = false;
            activeUser.viewProduct();
            System.out.println("Insert Id Articles or 0 to terminate Catalog");
            try {
                int idArticle = Integer.parseInt(in.next());

                if (idArticle == 0) {
                    if (articles.size()>0) {
                        break;
                    }else{
                        System.err.println("Select at least an Article!");
                        continue;
                    }
                }

                for (Article i : Program.getInstance().getArticles()) {
                    if (i.getId() == idArticle) {
                        articles.add(i);
                        agg = true;
                    }
                }
                if (!agg) System.err.println("Id Article Not Found!");
            }catch (Exception e){
                System.err.println("Id Not Valid!");
            }
        }
        activeUser.createCatalog(description,marketZone,articles);
    }

}
