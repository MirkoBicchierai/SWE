package agentManager;

public interface Subject {

    void notify(String notification);
    void attach(Observer o);
    void detach(Observer o);

}
