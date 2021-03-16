package agentManager;

public interface Subject {

    void notify(Order order);
    void attach(Observer o);
    void detach(Observer o);

}
