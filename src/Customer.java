import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Customer extends JPanel {
    // Customer attributes
    private String name;
    private String email;
    private String password;

    // Static list to store all registered customers
    private static List<Customer> registeredCustomers = new ArrayList<>();

    // GUI components for registration
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

    // Default constructor: builds the registration panel UI
    public Customer() {
        buildUI();
    }

    // Overloaded constructor to initialize customer data (if needed)
    public Customer(String name, String email, String password) {
        this(); // Build UI first
        this.name = name;
        this.email = email;
        this.password = password;

        // Update the text fields with provided data
        nameField.setText(name);
        emailField.setText(email);
        passwordField.setText(password);
    }

    // Method to set up the UI components
    private void buildUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // Name label and text field
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameField = new JTextField(20);
        add(nameField, gbc);

        // Email label and text field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        emailField = new JTextField(20);
        add(emailField, gbc);

        // Password label and text field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);

        // Register button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        registerButton = new JButton("Register");
        add(registerButton, gbc);

        // Handle registration when button is pressed
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCustomer();
            }
        });
    }

    // Registration logic: validate and store the customer
    private void registerCustomer() {
        name = nameField.getText().trim();
        email = emailField.getText().trim();
        password = new String(passwordField.getPassword());

        // Basic validation
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if a customer with this email is already registered
        if (findCustomerByEmail(email) != null) {
            JOptionPane.showMessageDialog(this, "User with this email already exists.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Add this customer to the static list
        registeredCustomers.add(this);
        JOptionPane.showMessageDialog(this, "Registration successful!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Static method to look up a customer by email
    public static Customer findCustomerByEmail(String email) {
        for (Customer customer : registeredCustomers) {
            if (customer.email.equals(email)) {
                return customer;
            }
        }
        return null;
    }

    // Getters for customer data
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
