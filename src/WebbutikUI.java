import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class WebbutikUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JButton refreshButton;
    private JButton addButton;

    public WebbutikUI() {
        setTitle("Webbutik - Product Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Quantity"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        refreshButton = new JButton("Refresh");
        addButton = new JButton("Add Product");
        buttonPanel.add(refreshButton);
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);

        refreshButton.addActionListener(e -> refreshProductList());
        addButton.addActionListener(e -> openAddProductDialog());

        refreshProductList();
    }

    private void refreshProductList() {
        tableModel.setRowCount(0);
        List<Product> products = ProductDAO.getAllProducts();
        for(Product p : products) {
            tableModel.addRow(new Object[]{p.getId(), p.getName(), p.getPrice(), p.getQuantity()});
        }
    }

    private void openAddProductDialog() {
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();

        Object[] message = {
                "Name:", nameField,
                "Price:", priceField,
                "Quantity:", quantityField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Add New Product", JOptionPane.OK_CANCEL_OPTION);
        if(option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int quantity = Integer.parseInt(quantityField.getText());
                Product newProduct = new Product(name, price, quantity);
                ProductDAO.addProduct(newProduct);
                refreshProductList();
            } catch(Exception ex) {
                JOptionPane.showMessageDialog(this, "Error adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new WebbutikUI().setVisible(true));
    }
}
