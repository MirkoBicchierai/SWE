import org.javatuples.Pair;

import java.util.ArrayList;

public class Amministratori extends Utenti {



    public Amministratori(String name, String password) {
        super(name, password);
    }
    public Amministratori(String name, String passwordHash, int id) {
        super(name, passwordHash, id);
    }

    @Override
    public void viewOrders() {
        System.out.println("----------------------------------");
        boolean check = false;
        for(Ordini i : Programma.getInstance().getOrders()){
            System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
            i.printArticle();
            check=true;
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");
    }

    @Override
    public void viewCatalog() {
        System.out.println("----------------------------------");
        if (Programma.getInstance().getCatalogs().size()>0){
            System.out.println("");
            for (Catalogo i : Programma.getInstance().getCatalogs()) {
                i.printCatalog();
                System.out.println("");
            }
        }else
            System.out.println("There are no catalogs!.");
        System.out.println("----------------------------------");
    }

    public void viewProduct() {
        System.out.println("----------------------------------");
        for(Articolo i :Programma.getInstance().getArticles()){
            i.display();
        }
        System.out.println("----------------------------------");
    }

    public void createAgent(String name, String password, float percCommission, Catalogo catalogo) {
        Programma.getInstance().getUsers().add(new Agenti(name,password,percCommission,catalogo));
        System.out.println("Created!");
    }

    public void createCatlog(String descr, String marketZone, ArrayList<Articolo> articles) {

        Programma.getInstance().getCatalogs().add(new Catalogo(articles,descr,marketZone));
        System.out.println("Created!");
    }

    public void deleteAgent(int idAgent){
        Agenti agent=null;
        for(Utenti i : Programma.getInstance().getUsers()){
            if (i instanceof Agenti){
                agent = (Agenti) i;
                break;
            }
        }

        if (agent==null){
            System.err.println("Id Agente Inesistente!");
            return;
        }

        for(Ordini i : Programma.getInstance().getOrders()){
            if (i.getAgent()==agent){
                i.agentDeleted();
            }
        }
        System.out.println("Deleted!");
    }

    public void viewAgent() {
    }

    public void viewCatalogAgent(int idAgent){
    }

    public void viewCustomerOrders(int idCustomer){

        System.out.println("----------------------------------");
        boolean check = false;
        for(Ordini i : Programma.getInstance().getOrders()){
            if (i.getAgent().getId()==idCustomer) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
                i.printArticle();
                check = true;
            }
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");

    }


    public void deleteCatalog(int IdCatalog){//controllo se utente collegato
        Catalogo tmp = null;

        for(Utenti i:Programma.getInstance().getUsers()){
            if(i instanceof Agenti && ((Agenti) i).getCatalog().getId()==IdCatalog){
                System.err.println("Catalog Can't be Deleted! It's Linked to an User!");
                return;
            }
        }

        for(Catalogo i:Programma.getInstance().getCatalogs()){
            if(i.getId()==IdCatalog){
                tmp = i;
            }
        }

        if (tmp == null){
            System.err.println("Wrong ID! Re-insert it");
            return;
        }

        Programma.getInstance().getCatalogs().remove(tmp);
        System.out.println("Deleted!");
    }

    public void deleteClient(int idCLient) {
        Clienti tmp = null;
        for(Clienti i:Programma.getInstance().getCustomers()){
            if(i.getId()==idCLient){
                tmp = i;
            }
        }

        if (tmp == null){
            System.err.println("Wrong ID! Re-insert it");
            return;
        }

        Programma.getInstance().getCustomers().remove(tmp);
        System.out.println("Deleted!");
    }

    public void deleteProduct(int idArticle) {
        Articolo tmp = null;

        for(Ordini i:Programma.getInstance().getOrders()){
            for(Articolo j:i.getArticles()){
                if (j.getId()==idArticle){
                    System.err.println("This Article is Already Ordered! It can't be Deleted!");
                    return;
                }
            }
        }

        for(Articolo i:Programma.getInstance().getArticles()){
            if(i instanceof Composto ){
                for(Articolo j : ((Composto) i).getComponents()){
                    if (j.getId()==idArticle){
                        System.err.println("This Article is a Component Of Another Article! It can't be Deleted!");
                        return;
                    }
                }
            }
        }

        for(Articolo i:Programma.getInstance().getArticles()){
            if(i.getId()==idArticle){
                tmp = i;
            }
        }

        if (tmp == null){
            System.err.println("Wrong ID! Re-insert it");
            return;
        }

        for(Catalogo i:Programma.getInstance().getCatalogs())
            i.getArticles().removeIf(j -> j.getId() == idArticle); //rimuove j se j.getid == article


        Programma.getInstance().getArticles().remove(tmp);
        System.out.println("Deleted!");
    }

    public void createProduct(String name, float price, ArrayList<Articolo> a) {
        if (a.size()==0){
            Programma.getInstance().getArticles().add(new Prodotto(name, price));
        }else {
            Programma.getInstance().getArticles().add(new Composto(name, a));
        }


    }

}