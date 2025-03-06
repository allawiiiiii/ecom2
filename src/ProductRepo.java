// ProductRepo.java
import java.sql.*;
import java.util.*;

public class ProductRepo {
    public List<String> getAllProducts() {
        List<String> products = new ArrayList<>();
        String sql = "SELECT name FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                products.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public List<String> searchProducts(String keyword) {
        List<String> products = new ArrayList<>();
        String sql = "SELECT name FROM products WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                products.add(rs.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}