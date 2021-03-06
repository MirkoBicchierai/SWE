public class AdministatorMenu implements Menu{

    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu(Utenti activeUser) {
        System.out.println("SOno un Admin");
    }
}
