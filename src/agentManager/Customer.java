package agentManager;

public final class Customer {

    private final String businessName;
    private final String country;
    private final String email;
    private static int lastID = 0;
    private final int id;

    public Customer(String businessName, String country, String email) {
        this.businessName = businessName;
        this.country = country;
        this.email = email;
        lastID++;
        this.id=lastID;
    }

    public Customer(int id, String businessName, String country, String email) {
        this.businessName = businessName;
        this.country = country;
        this.email = email;
        this.id=id;
        lastID = Math.max(lastID, id);
    }

    public String getEmail() {
        return email;
    }

    public int getId() {
        return id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public String getCountry() {
        return country;
    }

    public static int getLastID() {
        return lastID;
    }

}