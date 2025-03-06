// Main.java (Swing UI)
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Main {
    private static ProductService productService = new ProductService();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Ecom thingy");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JPanel inputPanel = new JPanel();
        JTextField searchField = new JTextField(15);
        JButton searchButton = new JButton("Search");
        JButton listProductsButton = new JButton("List Products");

        listProductsButton.addActionListener(e -> {
            List<String> products = productService.getAllProducts();
            textArea.setText("");
            for (String product : products) {
                textArea.append(product + "\n");
            }
        });

        searchButton.addActionListener(e -> {
            String keyword = searchField.getText().trim();
            List<String> products = productService.searchProducts(keyword);
            textArea.setText("");
            for (String product : products) {
                textArea.append(product + "\n");
            }
        });

        inputPanel.add(new JLabel("Search:"));
        inputPanel.add(searchField);
        inputPanel.add(searchButton);
        inputPanel.add(listProductsButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(scrollPane, BorderLayout.CENTER);

        frame.add(panel);
        frame.setVisible(true);
    }
}
