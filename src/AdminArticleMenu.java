import java.util.ArrayList;
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
            admin.viewProduct();
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
                    //
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

    private void createProduct(Amministratori activeUser){

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

                if (idArticle == 0) {
                    if (articles.size()>0) {
                        break;
                    }else{
                        System.err.println("Select at least an Article!");
                        continue;
                    }
                }

                for (Articolo i : Programma.getInstance().getArticles()) {
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

        activeUser.createCatlog(description,marketZone,articles);
    }
}
