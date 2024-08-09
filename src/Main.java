import java.sql.SQLException;
import java.util.Scanner;

// Import all necessary classes from your project package structure

public class Main {

  public static void main(String[] args) throws Exception {
    Scanner scanner = new Scanner(System.in);
    UserService userService = new UserService(new UserDAOImpl()); // Replace with your implementation
    ProductService productService = new ProductService(new ProductDAOImpl()); // Replace with your implementation

    int choice;

    do {
      System.out.println("\nE-Commerce Platform");
      System.out.println("1. Register");
      System.out.println("2. Login");
      System.out.println("3. Exit");
      System.out.print("Enter your choice: ");
      //System.out.println("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine(); // Consume newline character

      switch (choice) {
        case 1:
          registerUser(userService, scanner);
          break;
        case 2:
          User user = loginUser(userService, scanner);
          if (user != null) {
            handleLoggedInUser(user, productService, scanner);
          }
          break;
        case 3:
          System.out.println("Exiting program...");
          break;
        default:
          System.out.println("Invalid choice!");
      }
    } while (choice != 3);

    scanner.close();
  }

  private static void registerUser(UserService userService, Scanner scanner) throws Exception {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();
    System.out.print("Enter email: ");
    String email = scanner.nextLine();

    userService.registerUser(username, password, email);
    System.out.println("Registration successful!");
  }

  private static User loginUser(UserService userService, Scanner scanner) throws Exception {
    System.out.print("Enter username: ");
    String username = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    try {
      User user = userService.loginUser(username, password);
      System.out.println("Login successful!");
      return user;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      return null;
    }
  }

  private static void handleLoggedInUser(User user, ProductService productService, Scanner scanner) throws Exception {
    int choice;

    do {
      System.out.println("\nWelcome " + user.getUsername() + "!");
      if (user.getRole().equals("buyer")) {
        System.out.println("1. Browse products");
        System.out.println("2. Logout");
      } else if (user.getRole().equals("seller")) {
        System.out.println("1. Add product");
        System.out.println("2. View your products");
        System.out.println("3. Logout");
      } else if (user.getRole().equals("admin")) {
        // Add functionalities for admin users (e.g., view all users, manage products)
        System.out.println("1. View all users (Admin)");
        System.out.println("2. Logout");
      }

      System.out.print("Enter your choice: ");
      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          if (user.getRole().equals("buyer")) {
            browseProducts(productService, scanner);
          } else if (user.getRole().equals("seller")) {
            addProduct(productService, user, scanner);
          } else if (user.getRole().equals("admin")) {
            // Implement functionalities for admin users (e.g., view all users)
            System.out.println("View all users functionality not implemented yet!");
          }
          break;
        case 2:
          if (user.getRole().equals("seller")) {
            viewProductsBySeller(productService, user, scanner);
          }
          break;
        case 3:
          System.out.println("Logout successful!");
          break;
        default:
          System.out.println("Invalid choice!");
      }
    } while (choice != 3);
  }

  private static void browseProducts(ProductService productService, Scanner scanner) throws SQLException {
    

