package laf;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class SignupFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static final int BASE_WIDTH = 1456;
    private static final int BASE_HEIGHT = 916;
    
    private JPanel signupFrame;
    private JTextField txfUsername, txfEmail;
    private JPasswordField txfPassword;
    private JLabel lblSignup, lblUsername, lblEmail, lblPassword;
    private JButton btnSignup, btnBack;
    private JCheckBox checkSignup;
    private JPanel inputsContainer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                SignupFrame frame = new SignupFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public SignupFrame() {
        setTitle("Lost And Found");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, BASE_WIDTH, BASE_HEIGHT);
        setMinimumSize(new Dimension(600, 500));

        signupFrame = new JPanel(new GridBagLayout());
        signupFrame.setBackground(new Color(64, 0, 0));
        signupFrame.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(signupFrame);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        lblSignup = new JLabel("Sign up");
        lblSignup.setForeground(Color.WHITE);
        lblSignup.setFont(new Font("Tahoma", Font.BOLD, 76));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        signupFrame.add(lblSignup, gbc);

        // Inputs container
        inputsContainer = new JPanel(new GridBagLayout());
        inputsContainer.setOpaque(false);
        inputsContainer.setPreferredSize(new Dimension(948, 350));

        // Username label
        lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 36));
        GridBagConstraints gbc_lblUsername = new GridBagConstraints();
        gbc_lblUsername.anchor = GridBagConstraints.WEST;
        gbc_lblUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblUsername.gridx = 0;
        gbc_lblUsername.gridy = 0;
        gbc_lblUsername.weightx = 1.0;
        gbc_lblUsername.insets = new Insets(0, 0, 5, 0);
        inputsContainer.add(lblUsername, gbc_lblUsername);

        // Username input
        txfUsername = new JTextField();
        txfUsername.setFont(new Font("Tahoma", Font.PLAIN, 26));
        txfUsername.setForeground(Color.BLACK);
        txfUsername.setBackground(Color.WHITE);
        txfUsername.setPreferredSize(new Dimension(948, 72));
        GridBagConstraints gbc_txfUsername = new GridBagConstraints();
        gbc_txfUsername.anchor = GridBagConstraints.WEST;
        gbc_txfUsername.fill = GridBagConstraints.HORIZONTAL;
        gbc_txfUsername.gridx = 0;
        gbc_txfUsername.gridy = 1;
        gbc_txfUsername.weightx = 1.0;
        gbc_txfUsername.insets = new Insets(0, 0, 20, 0);
        inputsContainer.add(txfUsername, gbc_txfUsername);

        // Email label
        lblEmail = new JLabel("Email");
        lblEmail.setForeground(Color.WHITE);
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, 36));
        GridBagConstraints gbc_lblEmail = new GridBagConstraints();
        gbc_lblEmail.anchor = GridBagConstraints.WEST;
        gbc_lblEmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblEmail.gridx = 0;
        gbc_lblEmail.gridy = 2;
        gbc_lblEmail.weightx = 1.0;
        gbc_lblEmail.insets = new Insets(0, 0, 5, 0);
        inputsContainer.add(lblEmail, gbc_lblEmail);

        // Email input
        txfEmail = new JTextField();
        txfEmail.setFont(new Font("Tahoma", Font.PLAIN, 26));
        txfEmail.setForeground(Color.BLACK);
        txfEmail.setBackground(Color.WHITE);
        txfEmail.setPreferredSize(new Dimension(948, 72));
        GridBagConstraints gbc_txfEmail = new GridBagConstraints();
        gbc_txfEmail.anchor = GridBagConstraints.WEST;
        gbc_txfEmail.fill = GridBagConstraints.HORIZONTAL;
        gbc_txfEmail.gridx = 0;
        gbc_txfEmail.gridy = 3;
        gbc_txfEmail.weightx = 1.0;
        gbc_txfEmail.insets = new Insets(0, 0, 20, 0);
        inputsContainer.add(txfEmail, gbc_txfEmail);

        // Password label
        lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 36));
        GridBagConstraints gbc_lblPassword = new GridBagConstraints();
        gbc_lblPassword.anchor = GridBagConstraints.WEST;
        gbc_lblPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_lblPassword.gridx = 0;
        gbc_lblPassword.gridy = 4;
        gbc_lblPassword.weightx = 1.0;
        gbc_lblPassword.insets = new Insets(0, 0, 5, 0);
        inputsContainer.add(lblPassword, gbc_lblPassword);

        // Password input
        txfPassword = new JPasswordField();
        txfPassword.setFont(new Font("Tahoma", Font.PLAIN, 26));
        txfPassword.setForeground(Color.BLACK);
        txfPassword.setBackground(Color.WHITE);
        txfPassword.setPreferredSize(new Dimension(948, 72));
        GridBagConstraints gbc_txfPassword = new GridBagConstraints();
        gbc_txfPassword.anchor = GridBagConstraints.WEST;
        gbc_txfPassword.fill = GridBagConstraints.HORIZONTAL;
        gbc_txfPassword.gridx = 0;
        gbc_txfPassword.gridy = 5;
        gbc_txfPassword.weightx = 1.0;
        gbc_txfPassword.insets = new Insets(0, 0, 0, 0);
        inputsContainer.add(txfPassword, gbc_txfPassword);

        // Checkbox (separate column)
        checkSignup = new JCheckBox();
        checkSignup.setBackground(new Color(64, 0, 0));
        checkSignup.setToolTipText("Show password");
        checkSignup.addActionListener(e -> {
            if (checkSignup.isSelected()) {
                txfPassword.setEchoChar((char) 0);
            } else {
                txfPassword.setEchoChar('•');
            }
        });
        GridBagConstraints gbc_checkSignup = new GridBagConstraints();
        gbc_checkSignup.anchor = GridBagConstraints.WEST;
        gbc_checkSignup.fill = GridBagConstraints.NONE;
        gbc_checkSignup.gridx = 1;
        gbc_checkSignup.gridy = 5;
        gbc_checkSignup.weightx = 0;
        gbc_checkSignup.insets = new Insets(0, 10, 0, 0);
        inputsContainer.add(checkSignup, gbc_checkSignup);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 40, 0);
        signupFrame.add(inputsContainer, gbc);

        // Buttons panel with both Back and Sign Up
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        buttonsPanel.setOpaque(false);

        // Back button
        btnBack = new JButton("Back");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnBack.setPreferredSize(new Dimension(205, 72));
        btnBack.addActionListener(e -> {
            SignupFrame.this.dispose();
            new LoginWindow().setVisible(true);
        });
        buttonsPanel.add(btnBack);

        // Sign up button
        btnSignup = new JButton("Sign Up");
        btnSignup.setFont(new Font("Tahoma", Font.BOLD, 40));
        btnSignup.setPreferredSize(new Dimension(205, 72));
        btnSignup.addActionListener(e -> handleSignup());
        buttonsPanel.add(btnSignup);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        signupFrame.add(buttonsPanel, gbc);

        // Resize listener
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateUIScale();
            }
        });

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        updateUIScale();
    }

    private void updateUIScale() {
        double scaleX = (double) getWidth() / BASE_WIDTH;
        double scaleY = (double) getHeight() / BASE_HEIGHT;
        double scale = Math.min(scaleX, scaleY);

        // Scale fonts with minimums
        int titleSize = Math.max((int) (76 * scale), 28);
        int labelSize = Math.max((int) (36 * scale), 16);
        int inputSize = Math.max((int) (26 * scale), 14);
        int buttonSize = Math.max((int) (40 * scale), 18);

        lblSignup.setFont(new Font("Tahoma", Font.BOLD, titleSize));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, labelSize));
        lblEmail.setFont(new Font("Tahoma", Font.BOLD, labelSize));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, labelSize));
        txfUsername.setFont(new Font("Tahoma", Font.PLAIN, inputSize));
        txfEmail.setFont(new Font("Tahoma", Font.PLAIN, inputSize));
        txfPassword.setFont(new Font("Tahoma", Font.PLAIN, inputSize));
        btnSignup.setFont(new Font("Tahoma", Font.BOLD, buttonSize));
        btnBack.setFont(new Font("Tahoma", Font.BOLD, buttonSize));

        // Scale component sizes
        int inputWidth = Math.max((int) (948 * scale), 300);
        int inputHeight = Math.max((int) (72 * scale), 50);
        int btnWidth = Math.max((int) (205 * scale), 100);
        int btnHeight = Math.max((int) (72 * scale), 45);

        // Update container size
        inputsContainer.setPreferredSize(new Dimension(inputWidth + 40, (int) (350 * scale)));

        // Update input sizes
        txfUsername.setPreferredSize(new Dimension(inputWidth, inputHeight));
        txfEmail.setPreferredSize(new Dimension(inputWidth, inputHeight));
        txfPassword.setPreferredSize(new Dimension(inputWidth, inputHeight));

        btnSignup.setPreferredSize(new Dimension(btnWidth, btnHeight));
        btnBack.setPreferredSize(new Dimension(btnWidth, btnHeight));

        revalidate();
        repaint();
    }

    private void handleSignup() {
        String username = txfUsername.getText().trim();
        String email = txfEmail.getText().trim();
        String password = new String(txfPassword.getPassword()).trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Please fill in all fields.",
                    "Signup Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate email format
        if (!isValidEmail(email)) {
            JOptionPane.showMessageDialog(null,
                    "Please enter a valid email address.\nExample: user@gmail.com, user@yahoo.com",
                    "Invalid Email",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // INSERT INTO DATABASE
        try (Connection conn = SQLiteConnection.connect()) {
            String sql = "INSERT INTO users(username, email, password) VALUES(?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, email);
            pst.setString(3, password);
            pst.executeUpdate();

            System.out.println("USER ADDED: " + username + " | " + password);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,
                    "Error creating account: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // SUCCESS POPUP
        String[] options = {"Go to Login", "Create Another Account"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Account created successfully!",
                "Signup Success",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == 0) {
            SignupFrame.this.dispose();
            new LoginWindow().setVisible(true);
        } else if (choice == 1) {
            txfUsername.setText("");
            txfEmail.setText("");
            txfPassword.setText("");
        }
    }

    // Email validation method
    private boolean isValidEmail(String email) {
        if (email == null || !email.contains("@")) {
            return false;
        }
        
        String[] validDomains = {
            "@gmail.com",
            "@yahoo.com",
            "@outlook.com",
            "@hotmail.com",
            "@icloud.com",
            "@mail.com"
        };
        
        String emailLower = email.toLowerCase();
        for (String domain : validDomains) {
            if (emailLower.endsWith(domain)) {
                String beforeAt = email.substring(0, email.indexOf("@"));
                return !beforeAt.isEmpty();
            }
        }
        
        return false;
    }
}