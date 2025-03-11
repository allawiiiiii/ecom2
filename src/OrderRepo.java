import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    public void createOrder(String customerName, String productName, int quantity) {
        String findCustomerSql = "SELECT id FROM customers WHERE name = ?";
        String findProductSql  = "SELECT id, stock_quantity FROM products WHERE name = ?";
        String insertOrderSql  = "INSERT INTO orders (customer_id, product_id, quantity, order_date) VALUES (?, ?, ?, NOW())";
        String updateStockSql  = "UPDATE products SET stock_quantity = stock_quantity - ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection()) {
            conn.setAutoCommit(false);

            int customerId;
            int productId;
            int currentStock;

            // Fetch customer ID
            try (PreparedStatement stmt = conn.prepareStatement(findCustomerSql)) {
                stmt.setString(1, customerName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    customerId = rs.getInt("id");
                } else {
                    conn.rollback();
                    throw new SQLException("Customer not found: " + customerName);
                }
            }

            // Fetch product ID + stock
            try (PreparedStatement stmt = conn.prepareStatement(findProductSql)) {
                stmt.setString(1, productName);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    productId    = rs.getInt("id");
                    currentStock = rs.getInt("stock_quantity");
                } else {
                    conn.rollback();
                    throw new SQLException("Product not found: " + productName);
                }
            }

            // Check stock
            if (currentStock < quantity) {
                conn.rollback();
                throw new SQLException("Insufficient stock for product: " + productName);
            }

            // Insert order
            try (PreparedStatement stmt = conn.prepareStatement(insertOrderSql)) {
                stmt.setInt(1, customerId);
                stmt.setInt(2, productId);
                stmt.setInt(3, quantity);
                stmt.executeUpdate();
            }

            // Update stock
            try (PreparedStatement stmt = conn.prepareStatement(updateStockSql)) {
                stmt.setInt(1, quantity);
                stmt.setInt(2, productId);
                stmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Show all orders from the database
    public List<String> getAllOrders() {
        List<String> orders = new ArrayList<>();

        String sql = """
            SELECT o.id,
                   c.name AS customer_name,
                   p.name AS product_name,
                   o.quantity,
                   o.order_date
              FROM orders o
              JOIN customers c ON o.customer_id = c.id
              JOIN products p ON o.product_id = p.id
             ORDER BY o.order_date DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int orderId        = rs.getInt("id");
                String customer    = rs.getString("customer_name");
                String product     = rs.getString("product_name");
                int qty            = rs.getInt("quantity");
                Timestamp date     = rs.getTimestamp("order_date");

                String info = String.format(
                        "Order ID: %d | Customer: %s | Product: %s | Qty: %d | Date: %s",
                        orderId, customer, product, qty, date
                );
                orders.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    // Show a particular customer's order history
    public List<String> getOrderHistory(String customerName) {
        List<String> history = new ArrayList<>();
        String sql = """
            SELECT p.name AS product_name, o.quantity, o.order_date
            FROM orders o
            JOIN customers c ON o.customer_id = c.id
            JOIN products p ON o.product_id = p.id
            WHERE c.name = ?
            ORDER BY o.order_date DESC
            """;

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String product  = rs.getString("product_name");
                int quantity    = rs.getInt("quantity");
                Timestamp date  = rs.getTimestamp("order_date");

                history.add("Product: " + product + ", Qty: " + quantity + ", Date: " + date);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return history;
    }
}
