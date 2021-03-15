package agentManager;

public interface Observable {

    void notify(Observer o, String notification);

}
