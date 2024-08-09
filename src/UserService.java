import java.sql.SQLException;

public class UserService {
public class UserService {

  private UserDAO userDAO; // Dependency injection for UserDAO

  public UserService(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  // Register a new user
  public User registerUser(String username, String password, String email) throws Exception {
    if (userDAO.getUserByUsername(username) != null) {
      throw new Exception("Username already exists!");
    }
    User user = new User(username, password, email, "buyer"); // Default role to buyer
    userDAO.createUser(user);
    return user;
  }

  // Login a user
  public User loginUser(String username, String password) throws Exception {
    User user = userDAO.getUserByUsername(username);
    if (user == null || !PasswordHasher.checkPassword(password, user.getPassword())) {
      throw new Exception("Invalid username or password!");
    }
    return user;
  }

  // Get a user by ID (for admin functionalities)
  public User getUserById(int userId) throws SQLException {
    return userDAO.getUserById(userId);
  }

  // Update user information (consider security implications)
  public void updateUserProfile(User user) throws SQLException {
    userDAO.updateUser(user);
  }

  // Delete a user (consider security implications and authorization)
  public void deleteUser(int userId) throws SQLException {
    userDAO.deleteUser(userId);
  }
}
    
}
