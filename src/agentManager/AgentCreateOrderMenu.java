package agentManager;

import org.javatuples.Pair;
import java.util.ArrayList;
import java.util.Scanner;

public class AgentCreateOrderMenu implements Menu{
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(User activeUser) {
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        int menuItem;
        int idS;
        do {

            activeUser.viewCustomers();

            System.out.println("agentManager.Menu option:");
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
                    do{
                        System.out.println("Insert an id:");
                        try {
                            idS = Integer.parseInt(in.next());
                        }catch (Exception e){
                            idS = -1;
                        }
                    }while( !Program.getInstance().checkCustomersExist( idS ) );

                    subMenuSelectArticles( (Agent) activeUser , idS );
                    break;

                case 9:
                    Program.getInstance().setMenu(new AgentMainMenu());
                    quit = true;
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

    private void createCustomers(User activeUser){
        Scanner in = new Scanner(System.in);
        System.out.println("Insert Email:");
        String email = in.nextLine();
        System.out.println("Insert country :");
        String country = in.nextLine();
        System.out.println("Insert Business-Name :");
        String name = in.nextLine();
        activeUser.createCustomer(name,country,email);
    }

    private void subMenuSelectArticles(Agent agent, int idSelectedCustomers){

        Scanner in = new Scanner(System.in);
        ArrayList<Pair<Article,Integer>> articlespair = new ArrayList<>();
        Catalog c = agent.getCatalog();

        boolean agg;
        int qtaArticle = 0;
        while (true){
            agg = false;
            agent.viewCatalog();
            System.out.println("Insert an Id Articles or 0 to terminate Order");
            try {
                int idArticle = Integer.parseInt(in.next());
                if (idArticle == 0)
                    if(articlespair.size()>0)
                        break;
                    else {
                        System.err.println("Select at least an Article!");
                        continue;
                    }
                for (Article i : c.getArticles()) {
                    if (i.getId() == idArticle) {

                        do {
                            System.out.println("Insert quantity of article (>0)");
                            try {
                                qtaArticle = Math.abs(Integer.parseInt(in.next()));
                            } catch (Exception e) {
                                System.err.println("Value not valid");
                                qtaArticle = -1;
                            }
                            if(qtaArticle==0){
                                System.err.println("Value not valid");
                                qtaArticle = -1;
                            }
                        }while(qtaArticle==-1);

                        articlespair.add(new Pair<>(i,qtaArticle));
                        agg = true;
                    }
                }
                if (!agg) System.err.println("Id Article Not Found!");
            }catch (Exception e){
                System.err.println("Id not valid!");
            }
        }

        for(Customer i : Program.getInstance().getCustomers()){
            if(i.getId() == idSelectedCustomers) {
                agent.createOrder(i, articlespair);
                return;
            }
        }

    }

}
