public class Admin extends User{
    public Admin(String username, String password, String email) {
        super(username, password, email, "admin");
    }
    public void viewUserList() {
        System.out.println("Viewing user list...");
    }
    
}
