public class AgentMenu implements Menu{
                                                //potrebbe essere static?? insieme ad admin
    @Override
    public Menu getCurrentState() {
        return this;
    }

    @Override
    public void showMenu() {

        System.out.println("SOno un agente");

    }
}
