package agentManager;

public interface Subject {

    void notify(Object obj);
    void attach(Observer o);
    void detach(Observer o);

}
