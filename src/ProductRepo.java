import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepo {

    // Get all products: name, price, stock
    public List<String> getAllProducts() {
        List<String> products = new ArrayList<>();
        String sql = "SELECT name, price, stock_quantity FROM products";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock_quantity");
                products.add("Name: " + name + " | Price: " + price + " | Stock: " + stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Search products by name (partial match)
    public List<String> searchProducts(String keyword) {
        List<String> products = new ArrayList<>();
        String sql = "SELECT name, price, stock_quantity FROM products WHERE name LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int stock = rs.getInt("stock_quantity");
                products.add("Name: " + name + " | Price: " + price + " | Stock: " + stock);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    // Add a new product
    public void addProduct(String name, double price, int stockQuantity) {
        String sql = "INSERT INTO products (name, price, stock_quantity) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setDouble(2, price);
            stmt.setInt(3, stockQuantity);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Check if a product is in stock
    public boolean isInStock(String productName) {
        String sql = "SELECT stock_quantity FROM products WHERE name = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, productName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int stock = rs.getInt("stock_quantity");
                return stock > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Update product stock
    public void updateStock(String productName, int newStock) {
        String sql = "UPDATE products SET stock_quantity = ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newStock);
            stmt.setString(2, productName);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
