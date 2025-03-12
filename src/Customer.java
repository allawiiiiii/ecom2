import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Customer extends JPanel {
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    // Customer attributes
=======
    //kund attribut
>>>>>>> parent of 68d379a (main)
=======
    // Customer attributes
>>>>>>> parent of 5892da0 (main)
=======
    // Customer attributes
>>>>>>> parent of 5892da0 (main)
    private String name;
    private String email;
    private String password;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    // Static list to store all registered customers
    private static List<Customer> registeredCustomers = new ArrayList<>();

    // GUI components for registration
=======
    //statisk lista för att spara alla registrerade kunder
    private static List<Customer> registeredCustomers = new ArrayList<>();

    //GUI komponenter för registrering
>>>>>>> parent of 68d379a (main)
=======
    // Static list to store all registered customers
    private static List<Customer> registeredCustomers = new ArrayList<>();

    // GUI components for registration
>>>>>>> parent of 5892da0 (main)
=======
    // Static list to store all registered customers
    private static List<Customer> registeredCustomers = new ArrayList<>();

    // GUI components for registration
>>>>>>> parent of 5892da0 (main)
    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton registerButton;

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    // Default constructor: builds the registration panel UI
=======
    //constructor
>>>>>>> parent of 68d379a (main)
=======
    // Default constructor: builds the registration panel UI
>>>>>>> parent of 5892da0 (main)
=======
    // Default constructor: builds the registration panel UI
>>>>>>> parent of 5892da0 (main)
    public Customer() {
        buildUI();
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 5892da0 (main)
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
<<<<<<< HEAD
=======
=======
    // Overloaded constructor to initialize customer data (if needed)
>>>>>>> parent of 5892da0 (main)
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

<<<<<<< HEAD
    //spara kund efter registrering
>>>>>>> parent of 68d379a (main)
=======
    // Registration logic: validate and store the customer
>>>>>>> parent of 5892da0 (main)
=======
>>>>>>> parent of 5892da0 (main)
    private void registerCustomer() {
        name = nameField.getText().trim();
        email = emailField.getText().trim();
        password = new String(passwordField.getPassword());

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        // Basic validation
=======
        //basic validering
>>>>>>> parent of 68d379a (main)
=======
        // Basic validation
>>>>>>> parent of 5892da0 (main)
=======
        // Basic validation
>>>>>>> parent of 5892da0 (main)
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
        // Check if a customer with this email is already registered
=======
        //kontrollera ifall kund med samma email finns
>>>>>>> parent of 68d379a (main)
=======
        // Check if a customer with this email is already registered
>>>>>>> parent of 5892da0 (main)
=======
        // Check if a customer with this email is already registered
>>>>>>> parent of 5892da0 (main)
        if (findCustomerByEmail(email) != null) {
            JOptionPane.showMessageDialog(this, "User with this email already exists.",
                    "Registration Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> parent of 5892da0 (main)
        // Add this customer to the static list
        registeredCustomers.add(this);
        JOptionPane.showMessageDialog(this, "Registration successful!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

    // Static method to look up a customer by email
<<<<<<< HEAD
=======
        //lägg till kund i static list
=======
        // Add this customer to the static list
>>>>>>> parent of 5892da0 (main)
        registeredCustomers.add(this);
        JOptionPane.showMessageDialog(this, "Registration successful!",
                "Success", JOptionPane.INFORMATION_MESSAGE);
    }

<<<<<<< HEAD
    // Static method att söka efter kund via email
>>>>>>> parent of 68d379a (main)
=======
    // Static method to look up a customer by email
>>>>>>> parent of 5892da0 (main)
=======
>>>>>>> parent of 5892da0 (main)
    public static Customer findCustomerByEmail(String email) {
        for (Customer customer : registeredCustomers) {
            if (customer.email.equals(email)) {
                return customer;
            }
        }
        return null;
    }

<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
    // Getters for customer data
=======
    //Getter för kund data
>>>>>>> parent of 68d379a (main)
=======
    // Getters for customer data
>>>>>>> parent of 5892da0 (main)
=======
    // Getters for customer data
>>>>>>> parent of 5892da0 (main)
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
