import java.util.ArrayList;
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
        int idS;
        do {

            activeUser.viewCustomers();

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
                    createCustomers(activeUser);
                    break;
                case 2:
                    // TODO prendere idc
                    do{
                        System.out.println("Insert an id:");
                        try {
                            idS = Integer.parseInt(in.next());
                        }catch (Exception e){
                            idS = -1;
                        }
                    }while( !Programma.getInstance().checkCustomersExist( idS ) );

                    subMenuSelectArticles( (Agenti) activeUser , idS );

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

    private void createCustomers(Utenti activeUser){

        Scanner in = new Scanner(System.in);

        System.out.println("Insert Email:");
        String email = in.nextLine();
        System.out.println("Insert country :");
        String country = in.nextLine();
        System.out.println("Insert Business-Name :");
        String name = in.nextLine();

        activeUser.createCustomer(name,country,email);
    }

    private void subMenuSelectArticles(Agenti agent, int idSelectedCustomers){

        Scanner in = new Scanner(System.in);
        ArrayList<Articolo> articles = new ArrayList<>();

        //TODO controllare id articoli selezionati (devono essere nel catalogo) && observer

        boolean agg;
        while (true){
            agg = false;
            agent.viewCatalog();
            System.out.println("Insert an Id Articles or 0 to terminate Order");
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
            }catch (Exception e){  }
            if (!agg) System.err.println("Id Article Not Found!");
        }

        for(Clienti i : Programma.getInstance().getCustomers()){
            if(i.getId() == idSelectedCustomers) {
                agent.createOrder(i, articles);
                return;
            }
        }

    }

}
