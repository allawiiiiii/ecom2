import javax.swing.*;
import java.awt.*;
import java.util.List;

public class Main {
    private static ProductService productService = new ProductService();
    private static CustomerService customerService = new CustomerService();
    private static OrderService orderService = new OrderService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("E-commerce Management");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Use a tabbed pane to separate sections
        JTabbedPane tabbedPane = new JTabbedPane();

        // ========== Product Management Tab ==========
        tabbedPane.addTab("Products", createProductPanel());

        // ========== Customer Management Tab ==========
        tabbedPane.addTab("Customers", createCustomerPanel());

        // ========== Order Management Tab ==========
        tabbedPane.addTab("Orders", createOrderPanel());

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    // ------------------ PRODUCT PANEL ------------------
    private static JPanel createProductPanel() {
        JPanel productPanel = new JPanel(new BorderLayout());

        // Top inputs
        JPanel inputPanel = new JPanel();

        JTextField prodNameField = new JTextField(8);
        JTextField prodPriceField = new JTextField(5);
        JTextField prodStockField = new JTextField(5);

        JButton addProductButton = new JButton("Add");
        JButton listProductsButton = new JButton("List All");
        JTextField searchField = new JTextField(6);
        JButton searchButton = new JButton("Search");
        JButton updateStockButton = new JButton("Update Stock");

        // Center text area
        JTextArea productArea = new JTextArea();
        productArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(productArea);

        // Add product
        addProductButton.addActionListener(e -> {
            String name = prodNameField.getText().trim();
            String priceText = prodPriceField.getText().trim();
            String stockText = prodStockField.getText().trim();

            try {
                double price = Double.parseDouble(priceText);
                int stock = Integer.parseInt(stockText);

                productService.addProduct(name, price, stock);
                JOptionPane.showMessageDialog(productPanel, "Product added!");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(productPanel, "Invalid price or stock.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // List all products
        listProductsButton.addActionListener(e -> {
            List<String> products = productService.getAllProducts();
            productArea.setText("");
            if (products.isEmpty()) {
                productArea.append("No products found.");
            } else {
                for (String p : products) {
                    productArea.append(p + "\n");
                }
            }
        });

        // Search products
        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            productArea.setText("");
            if (keyword.isEmpty()) {
                JOptionPane.showMessageDialog(productPanel, "Enter a search term.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            List<String> results = productService.searchProducts(keyword);
            if (results.isEmpty()) {
                productArea.append("No matching products found for: " + keyword);
            } else {
                for (String p : results) {
                    productArea.append(p + "\n");
                }
            }
        });

        // Update stock
        updateStockButton.addActionListener(e -> {
            String productName = JOptionPane.showInputDialog(productPanel, "Enter product name:");
            if (productName != null && !productName.trim().isEmpty()) {
                String newStockStr = JOptionPane.showInputDialog(productPanel, "Enter new stock quantity:");
                try {
                    int newStock = Integer.parseInt(newStockStr);
                    productService.updateStock(productName.trim(), newStock);
                    JOptionPane.showMessageDialog(productPanel, "Stock updated!");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(productPanel, "Invalid stock number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(productPanel, "Enter a valid product name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Assemble input panel
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(prodNameField);
        inputPanel.add(new JLabel("Price:"));
        inputPanel.add(prodPriceField);
        inputPanel.add(new JLabel("Stock:"));
        inputPanel.add(prodStockField);
        inputPanel.add(addProductButton);
        inputPanel.add(listProductsButton);

        inputPanel.add(new JLabel("Search:"));
        inputPanel.add(searchField);
        inputPanel.add(searchButton);

        inputPanel.add(updateStockButton);

        productPanel.add(inputPanel, BorderLayout.NORTH);
        productPanel.add(scrollPane, BorderLayout.CENTER);

        return productPanel;
    }

    // ------------------ CUSTOMER PANEL ------------------
    private static JPanel createCustomerPanel() {
        JPanel customerPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();

        JTextField custNameField = new JTextField(10);
        JTextField custEmailField = new JTextField(12);

        JButton registerButton = new JButton("Register");
        JButton updateEmailButton = new JButton("Update Email");

        JTextArea customerArea = new JTextArea();
        customerArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerArea);

        // Register customer
        registerButton.addActionListener(e -> {
            String name = custNameField.getText().trim();
            String email = custEmailField.getText().trim();

            if (name.isEmpty() || email.isEmpty()) {
                JOptionPane.showMessageDialog(customerPanel, "Enter name and email.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            customerService.registerCustomer(name, email);
            JOptionPane.showMessageDialog(customerPanel, "Customer registered!");
        });

        // Update customer email
        updateEmailButton.addActionListener(e -> {
            String name = custNameField.getText().trim();
            String newEmail = custEmailField.getText().trim();

            if (name.isEmpty() || newEmail.isEmpty()) {
                JOptionPane.showMessageDialog(customerPanel, "Enter a valid name and email.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (customerService.customerExists(name)) {
                customerService.updateCustomerEmail(name, newEmail);
                JOptionPane.showMessageDialog(customerPanel, "Email updated!");
            } else {
                JOptionPane.showMessageDialog(customerPanel, "Customer not found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(custNameField);
        inputPanel.add(new JLabel("Email:"));
        inputPanel.add(custEmailField);
        inputPanel.add(registerButton);
        inputPanel.add(updateEmailButton);

        customerPanel.add(inputPanel, BorderLayout.NORTH);
        customerPanel.add(scrollPane, BorderLayout.CENTER);

        return customerPanel;
    }

    // ------------------ ORDER PANEL ------------------
    private static JPanel createOrderPanel() {
        JPanel orderPanel = new JPanel(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JTextField orderCustField = new JTextField(8);
        JTextField orderProdField = new JTextField(8);
        JTextField orderQtyField  = new JTextField(5);

        JButton createOrderButton = new JButton("Create Order");
        JButton historyButton     = new JButton("Order History");

        JTextArea orderArea = new JTextArea();
        orderArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(orderArea);

        // Create Order
        createOrderButton.addActionListener(e -> {
            String custName = orderCustField.getText().trim();
            String prodName = orderProdField.getText().trim();
            int quantity;
            try {
                quantity = Integer.parseInt(orderQtyField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(orderPanel, "Invalid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (custName.isEmpty() || prodName.isEmpty()) {
                JOptionPane.showMessageDialog(orderPanel, "Enter a customer name and product name.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if customer exists
            if (!customerService.customerExists(custName)) {
                JOptionPane.showMessageDialog(orderPanel, "Customer not found! Register first.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Attempt to create the order
            try {
                orderService.createOrder(custName, prodName, quantity);
                JOptionPane.showMessageDialog(orderPanel, "Order created successfully!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(orderPanel, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Order History
        historyButton.addActionListener(e -> {
            String custName = JOptionPane.showInputDialog(orderPanel, "Enter customer name:");
            if (custName != null && !custName.trim().isEmpty()) {
                List<String> history = orderService.getOrderHistory(custName);
                orderArea.setText("");
                if (history.isEmpty()) {
                    orderArea.append("No orders found for: " + custName);
                } else {
                    for (String record : history) {
                        orderArea.append(record + "\n");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(orderPanel, "Please enter a valid customer name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        inputPanel.add(new JLabel("Customer:"));
        inputPanel.add(orderCustField);
        inputPanel.add(new JLabel("Product:"));
        inputPanel.add(orderProdField);
        inputPanel.add(new JLabel("Qty:"));
        inputPanel.add(orderQtyField);
        inputPanel.add(createOrderButton);
        inputPanel.add(historyButton);

        orderPanel.add(inputPanel, BorderLayout.NORTH);
        orderPanel.add(scrollPane, BorderLayout.CENTER);

        return orderPanel;
    }
}
