package agentManager;

import java.util.ArrayList;

public final class Administrator extends User {

    public Administrator(String name, String password) {
        super(name, password);
    }
    public Administrator(String name, String passwordHash, int id) {
        super(name, passwordHash, id);
    }

    @Override
    public void viewOrders() {
        System.out.println("----------------------------------");
        boolean check = false;
        for(Order i : Program.getInstance().getOrders()){
            if (i.getAgent()!=null) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName()+" Agent: "+i.getAgent().getName());
            }else{
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName()+" Agent: DELETED");
            }
            i.printArticle();
            System.out.println();
            check=true;
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");
    }

    @Override
    public void viewCatalog() {
        System.out.println("----------------------------------");
        if (Program.getInstance().getCatalogs().size()>0){
            System.out.println();
            for (Catalog i : Program.getInstance().getCatalogs()) {
                i.printCatalog();
                System.out.println();
            }
        }else
            System.out.println("There are no catalogs!.");
        System.out.println("----------------------------------");
    }

    public void viewProduct() {
        System.out.println("----------------------------------");
        for(Article i : Program.getInstance().getArticles()){
            i.display();
        }
        System.out.println("----------------------------------");
    }

    public void viewAgent() {
        System.out.println("----------------------------------");
        Agent a;
        boolean check = false;
        for (User u : Program.getInstance().getUsers()){
            if(u instanceof Agent){
                check = true;
                a = (Agent)u;
                System.out.println("Agent -> ID: "+a.getId()+" Name: "+a.getName()+" Commission: "+a.getCommissionPerc()+"%");
            }
        }
        if(!check)
            System.out.println("There are no agent.");
        System.out.println("----------------------------------");
    }

    public void viewCatalogAgent(int idAgent){
        System.out.println("----------------------------------");
        Agent a;
        boolean check = false;
        for (User u : Program.getInstance().getUsers()){
            if((u instanceof Agent) && u.getId() == idAgent){
                check = true;
                a = (Agent)u;
                System.out.println("Agent -> ID: "+a.getId()+" Name: "+a.getName()+" Commission: "+a.getCommissionPerc()+"%");
                a.getCatalog().printCatalog();
            }
        }
        if(!check)
            System.err.println("Agent ID wrong!.");
        System.out.println("----------------------------------");
    }

    public void viewCustomerOrders(int idCustomer){

        System.out.println("----------------------------------");
        boolean check = false;
        for(Order i : Program.getInstance().getOrders()){
            if (i.getAgent()!=null&&i.getAgent().getId()==idCustomer) {
                System.out.println("Order -> ID: " + i.getId() + " TOTAL: " + i.getTotal() + "€ COMMISSION: " + i.getCommissionTot() + "€ CLIENT: " + i.getClient().getBusinessName());
                i.printArticle();
                check = true;
            }
        }
        if(!check)
            System.out.println("There are no orders.");
        System.out.println("----------------------------------");

    }

    public void createAgent(String name, String password, float percCommission, Catalog catalog) {
        Program.getInstance().getUsers().add(new Agent(name,password,percCommission, catalog));
        System.out.println("Created!");
    }

    public void createCatalog(String description, String marketZone, ArrayList<Article> articles) {

        Program.getInstance().getCatalogs().add(new Catalog(articles,description,marketZone));
        System.out.println("Created!");
    }

    public void createProduct(String name, float price, ArrayList<Article> a) {
        if (a==null||a.size()==0){
            Program.getInstance().getArticles().add(new Product(name, price));
        }else {
            Program.getInstance().getArticles().add(new Compound(name, a));
        }
    }

    public void deleteCatalog(int IdCatalog){//controllo se utente collegato
        Catalog tmp = null;

        for(User i: Program.getInstance().getUsers()){
            if(i instanceof Agent && ((Agent) i).getCatalog().getId()==IdCatalog){
                System.err.println("Catalog Can't be Deleted! It's Linked to an User!");
                return;
            }
        }

        for(Catalog i: Program.getInstance().getCatalogs()){
            if(i.getId()==IdCatalog){
                tmp = i;
            }
        }

        if (tmp == null){
            System.err.println("Wrong ID! Re-insert it");
            return;
        }

        Program.getInstance().getCatalogs().remove(tmp);
        System.out.println("Deleted!");
    }

    public void deleteClient(int idClient) {
        Customer tmp = null;

        for(Order i: Program.getInstance().getOrders()){
            if(i.getClient().getId()==idClient){
                System.err.println("Client Can't be Deleted! It's Linked to an Order!");
                return;
            }
        }

        for(Customer i: Program.getInstance().getCustomers()){
            if(i.getId()==idClient){
                tmp = i;
            }
        }

        if (tmp == null){
            System.err.println("Wrong ID! Re-insert it");
            return;
        }

        Program.getInstance().getCustomers().remove(tmp);
        System.out.println("Deleted!");
    }

    public void deleteProduct(int idArticle) {
        Article tmp = null;

        for(Order i: Program.getInstance().getOrders()){
            for(Article j:i.getArticles()){
                if (j.getId()==idArticle){
                    System.err.println("This Article is Already Ordered! It can't be Deleted!");
                    return;
                }
            }
        }

        for(Article i: Program.getInstance().getArticles()){
            if(i instanceof Compound){
                for(Article j : ((Compound) i).getComponents()){
                    if (j.getId()==idArticle){
                        System.err.println("This Article is a Component Of Another Article! It can't be Deleted!");
                        return;
                    }
                }
            }
        }

        for(Article i: Program.getInstance().getArticles()){
            if(i.getId()==idArticle){
                tmp = i;
            }
        }

        if (tmp == null){
            System.err.println("Wrong ID! Re-insert it");
            return;
        }

        for(Catalog i: Program.getInstance().getCatalogs())
            i.getArticles().removeIf(j -> j.getId() == idArticle); //rimuove j se j.getid == article


        Program.getInstance().getArticles().remove(tmp);
        System.out.println("Deleted!");
    }

    public void deleteAgent(int idAgent){
        Agent agent=null;
        for(User i : Program.getInstance().getUsers()){
            if (i instanceof Agent && i.getId()==idAgent){
                agent = (Agent) i;
                break;
            }
        }

        if (agent==null){
            System.err.println("Id Agent Not Exist!");
            return;
        }

        for(Order i : Program.getInstance().getOrders()){
            if (i.getAgent()==agent){
                i.agentDeleted();
            }
        }

        Program.getInstance().getUsers().remove(agent);
        System.out.println("Deleted!");
    }


}