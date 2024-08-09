import java.sql.SQLException;
import java.util.List;

public class ProductService {
public class ProductService {

  private ProductDAO productDAO; // Dependency injection for ProductDAO

  public ProductService(ProductDAO productDAO) {
    this.productDAO = productDAO;
  }

  // Create a new product
  public Product createProduct(Product product, User seller) throws Exception {
    if (seller.getRole().equals("buyer")) {
      throw new Exception("Only sellers can create products!");
    }
    product.setSellerId(seller.getId());
    return productDAO.createProduct(product);
  }

  // Get a product by ID
  public Product getProductById(int productId) throws SQLException {
    return productDAO.getProductById(productId);
  }

  // Get all products (consider pagination for large datasets)
  public List<Product> getAllProducts() throws SQLException {
    return productDAO.getAllProducts();
  }

  // Get products by seller ID (assuming a seller can view their own products)
  public List<Product> getProductsBySeller(User seller) throws SQLException {
    return productDAO.getProductsBySellerId(seller.getId());
  }

  // Update product information (consider security implications)
  public void updateProduct(Product product) throws SQLException {
    productDAO.updateProduct(product);
  }

  // Delete a product (consider security implications and authorization)
  public void deleteProduct(int productId, User seller) throws Exception {
    Product product = productDAO.getProductById(productId);
    if (product == null || product.getSellerId() != seller.getId()) {
      throw new Exception("Unauthorized access or product not found!");
    }
    productDAO.deleteProduct(productId);
  }
}
    
}
