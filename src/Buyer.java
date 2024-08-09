public class Buyer extends User{
    public Buyer(String username, String password, String email) {
    super(username, password, email, "buyer");
    }
    
    public void browseProducts() {
        System.out.println("Browsing products...");
    }
}