import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductDAO {

        public void addProduct() {
            String sql = "INSERT INTO products (name, price, quantity) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseConnection.getConnection()) {
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, product.getName());
                pstmt.setDouble(2, product.getPrice());
                pstmt.setInt(3, product.getQuantity());
                pstmt.executeUpdate();
            }
        }
}
