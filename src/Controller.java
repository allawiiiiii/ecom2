import javax.swing.*;
import java.awt.*;

public class Controller extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;

    public Controller() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create instances of the panels
        Customer registrationPanel = new Customer();
        LoginPanel loginPanel = new LoginPanel(this);
        DashboardPanel dashboardPanel = new DashboardPanel();

        // Add panels to the main panel with unique identifiers
        mainPanel.add(registrationPanel, "Registration");
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(dashboardPanel, "Dashboard");

        add(mainPanel);
        setTitle("E Handel Project");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
    }

    // Method to switch panels
    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Controller frame = new Controller();
            frame.setVisible(true);
        });
    }
}
