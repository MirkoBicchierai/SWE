package agentManager;

import java.util.ArrayList;
import java.util.Scanner;

public final class AdminArticleMenu implements Menu{

    @Override
    public void showMenu() {

        Administrator admin = (Administrator) Program.getInstance().getActiveUser();
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
                    createProductQuery(admin);
                    break;

                case 2:
                    System.out.println("Enter the code of the Product to Delete");
                    try {
                        int idP = in.nextInt();
                        admin.deleteProduct(idP);
                    }catch (Exception e){
                        System.err.println("Invalid Id!");
                    }
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

    private void createProductQuery(Administrator activeUser){

        Scanner in = new Scanner(System.in);
        boolean done = false;
        float price = 0;
        ArrayList<Article> articles = new ArrayList<>();

        System.out.println("Insert Article Name :");
        String name = in.nextLine();

        int reply;
        do {
            System.out.println("Do you want to insert a Composite Article?");
            System.out.println("1. Yes");
            System.out.println("0. No");

            try {
                reply = Integer.parseInt(in.next());
            }catch (Exception e){
                reply = -1;
            }
            switch (reply) {

                case 1:
                    boolean agg;
                    while (true) {
                        agg = false;
                        activeUser.viewProduct();
                        System.out.println("Insert Id Articles Components or 0 to terminate Composition");
                        try {
                            int idArticle = Integer.parseInt(in.next());

                            if (idArticle == 0) {
                                if (articles.size() > 0) {
                                    break;
                                } else {
                                    System.err.println("Select at least an Article!");
                                    continue;
                                }
                            }

                            for (Article i : Program.getInstance().getArticles()) {
                                if (i.getId() == idArticle) {
                                    articles.add(i);
                                    price+=i.price;
                                    agg = true;
                                }
                            }

                            if (!agg) System.err.println("Id Article Not Found!");
                        } catch (Exception e) {
                            System.err.println("Id Not Valid!");
                        }
                    }
                    done = true;
                    activeUser.createProduct(name,articles);
                    break;

                case 0:
                    do {
                        System.out.println("Insert Price :");
                        try {
                            price = Float.parseFloat(in.next());
                            break;
                        } catch (Exception e) {
                            System.err.println("Invalid Choice!");
                        }
                    } while (true);
                    done = true;
                    activeUser.createProduct(name,price);
                    break;

                default:
                    System.err.println("Invalid choice.");
            }
        } while (!done);
    }

}
