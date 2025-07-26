
// Import required Swing and AWT packages for GUI components
// For AWT components and layouts (BorderLayout, Font)
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent; // For button click events
import java.awt.event.ActionListener; // For handling button actions

// For Swing components (JFrame, JButton, etc.)
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

// Main class extending JFrame to create a window application
public class ATM extends JFrame {

    // Store the current balance (in-memory, no database)
    private double balance = 1000.00; // Initial account balance set to $1000

    // GUI components declaration
    private JLabel balanceLabel; // Displays current balance
    private JTextField amountField; // Field for user to enter amount
    private JButton withdrawButton; // Button to withdraw money
    private JButton depositButton; // Button to deposit money
    private JButton checkBalanceButton; // Button to check current balance

    // Constructor to initialize the ATM interface
    public ATM() {
        // Configure main window properties
        setTitle("Advanced ATM Interface"); // Window title
        setSize(500, 350); // Window dimensions (width x height)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application when window is closed
        setLayout(new BorderLayout()); // Use BorderLayout as main layout manager
        getContentPane().setBackground(new Color(240, 240, 240)); // Set light gray background

        // Create main panel with vertical box layout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); // Arrange components vertically
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30)); // Add 30px padding

        // Create and configure balance display label
        balanceLabel = new JLabel("Current Balance: $" + String.format("%.2f", balance));
        balanceLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align the label
        balanceLabel.setFont(new Font("Segoe UI", Font.BOLD, 22)); // Set font style and size
        balanceLabel.setForeground(new Color(0, 100, 0)); // Dark green text color

        // Create amount input components
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        amountField = new JTextField();
        amountField.setMaximumSize(new Dimension(250, 35)); // Set maximum size
        amountField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align
        amountField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        amountField.setHorizontalAlignment(JTextField.CENTER); // Center text in field

        // Create buttons panel with FlowLayout
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 15)); // Center buttons with 20px horizontal gap
        buttonsPanel.setBackground(new Color(240, 240, 240)); // Match main panel background

        // Create and configure Withdraw button
        withdrawButton = new JButton("Withdraw");
        withdrawButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        withdrawButton.setBackground(new Color(220, 80, 80)); // Red background
        withdrawButton.setForeground(Color.WHITE); // White text
        withdrawButton.setFocusPainted(false); // Remove focus border
        withdrawButton.setPreferredSize(new Dimension(120, 40)); // Set button size

        // Create and configure Deposit button
        depositButton = new JButton("Deposit");
        depositButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        depositButton.setBackground(new Color(70, 150, 70)); // Green background
        depositButton.setForeground(Color.WHITE);
        depositButton.setFocusPainted(false);
        depositButton.setPreferredSize(new Dimension(120, 40));

        // Create and configure Check Balance button
        checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        checkBalanceButton.setBackground(new Color(80, 100, 220)); // Blue background
        checkBalanceButton.setForeground(Color.WHITE);
        checkBalanceButton.setFocusPainted(false);
        checkBalanceButton.setPreferredSize(new Dimension(140, 40));

        // Add buttons to the buttons panel
        buttonsPanel.add(withdrawButton);
        buttonsPanel.add(depositButton);
        buttonsPanel.add(checkBalanceButton);

        // Add all components to the main panel in order
        mainPanel.add(balanceLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Add vertical spacing
        mainPanel.add(amountLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Small vertical spacing
        mainPanel.add(amountField);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 25))); // Larger vertical spacing
        mainPanel.add(buttonsPanel);

        // Add main panel to the center of the frame
        add(mainPanel, BorderLayout.CENTER);

        // Add action listeners to buttons
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(); // Call withdraw method when clicked
            }
        });

        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit(); // Call deposit method when clicked
            }
        });

        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBalance(); // Call checkBalance method when clicked
            }
        });

        // Center the window on screen when launched
        setLocationRelativeTo(null);
    }

    // Method to handle withdrawal operations
    private void withdraw() {
        try {
            // Get amount from text field and parse to double
            double amount = Double.parseDouble(amountField.getText());

            // Validate the amount is positive
            if (amount <= 0) {
                showErrorMessage("Please enter a positive amount");
                return;
            }

            // Check sufficient funds
            if (amount > balance) {
                showErrorMessage("Insufficient funds!");
                return;
            }

            // Process withdrawal
            balance -= amount;
            updateBalanceDisplay();
            showSuccessMessage("Withdrawal successful!");

        } catch (NumberFormatException e) {
            showErrorMessage("Please enter a valid number");
        }
    }

    // Method to handle deposit operations
    private void deposit() {
        try {
            // Get amount from text field and parse to double
            double amount = Double.parseDouble(amountField.getText());

            // Validate the amount is positive
            if (amount <= 0) {
                showErrorMessage("Please enter a positive amount");
                return;
            }

            // Process deposit
            balance += amount;
            updateBalanceDisplay();
            showSuccessMessage("Deposit successful!");

        } catch (NumberFormatException e) {
            showErrorMessage("Please enter a valid number");
        }
    }

    // Method to check and display current balance
    private void checkBalance() {
        JOptionPane.showMessageDialog(this,
                "Current Balance: $" + String.format("%.2f", balance),
                "Account Balance",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Helper method to update the balance display label
    private void updateBalanceDisplay() {
        balanceLabel.setText("Current Balance: $" + String.format("%.2f", balance));
    }

    // Helper method to show error messages
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Transaction Error",
                JOptionPane.ERROR_MESSAGE);
    }

    // Helper method to show success messages
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Transaction Successful",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Main method to launch the application
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create and display the ATM interface
                ATM atm = new ATM();
                atm.setVisible(true); // Show the window
            }
        });
    }
}
