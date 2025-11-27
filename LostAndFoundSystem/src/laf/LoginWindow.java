package laf;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class LoginWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static final int BASE_WIDTH = 1456;
    private static final int BASE_HEIGHT = 916;
    
    private JPanel loginFrame;
    private JTextField usernameInput;
    private JPasswordField passwordInput;
    private JLabel lblLogin, lblUsername, lblPassword;
    private JButton btnLogin, btnCreateAcc;
    private JCheckBox checkLogin;
    private JPanel inputsContainer;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                LoginWindow frame = new LoginWindow();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public LoginWindow() {
        setForeground(Color.BLACK);
        setTitle("Lost And Found");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 200, BASE_WIDTH, BASE_HEIGHT);
        setMinimumSize(new Dimension(600, 400));

        loginFrame = new JPanel(new GridBagLayout());
        loginFrame.setBackground(new Color(64, 0, 0));
        loginFrame.setBorder(new EmptyBorder(20, 20, 20, 20));
        setContentPane(loginFrame);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title
        lblLogin = new JLabel("Log In");
        lblLogin.setForeground(Color.WHITE);
        lblLogin.setFont(new Font("Tahoma", Font.BOLD, 76));
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 40, 0);
        loginFrame.add(lblLogin, gbc);

        // Inputs container (centered, fixed width)
        inputsContainer = new JPanel(new GridBagLayout());
        inputsContainer.setOpaque(false);
        inputsContainer.setPreferredSize(new Dimension(948, 250));

        GridBagConstraints igbc = new GridBagConstraints();
        igbc.anchor = GridBagConstraints.WEST;
        igbc.fill = GridBagConstraints.HORIZONTAL;

        
        
        // Username label
        lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, 36));
        igbc.gridx = 0;
        igbc.gridy = 0;
        igbc.gridwidth = 1;
        igbc.insets = new Insets(0, 0, 5, 0);
        igbc.weightx = 1.0;
        inputsContainer.add(lblUsername, igbc);

        // Username input
        usernameInput = new JTextField();
        usernameInput.setFont(new Font("Tahoma", Font.PLAIN, 26));
        usernameInput.setForeground(Color.BLACK);
        usernameInput.setBackground(Color.WHITE);
        usernameInput.setPreferredSize(new Dimension(948, 72));
        igbc.gridy = 1;
        igbc.insets = new Insets(0, 0, 30, 0);
        inputsContainer.add(usernameInput, igbc);

        // Password label
        lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, 36));
        igbc.gridy = 2;
        igbc.insets = new Insets(0, 0, 5, 0);
        inputsContainer.add(lblPassword, igbc);

        // Password input (same width as username)
        passwordInput = new JPasswordField();
        passwordInput.setFont(new Font("Tahoma", Font.PLAIN, 26));
        passwordInput.setForeground(Color.BLACK);
        passwordInput.setBackground(Color.WHITE);
        passwordInput.setPreferredSize(new Dimension(948, 72));
        igbc.gridy = 3;
        igbc.insets = new Insets(0, 0, 0, 0);
        inputsContainer.add(passwordInput, igbc);

        // Checkbox (separate column, doesn't affect input width)
        checkLogin = new JCheckBox();
        checkLogin.setBackground(new Color(64, 0, 0));
        checkLogin.setToolTipText("Show password");
        checkLogin.addActionListener(e -> {
            if (checkLogin.isSelected()) {
                passwordInput.setEchoChar((char) 0);
            } else {
                passwordInput.setEchoChar('•');
            }
        });
        igbc.gridx = 1;
        igbc.gridy = 3;
        igbc.weightx = 0;
        igbc.fill = GridBagConstraints.NONE;
        igbc.insets = new Insets(0, 10, 0, 0);
        inputsContainer.add(checkLogin, igbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 50, 0);
        loginFrame.add(inputsContainer, gbc);

        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonsPanel.setOpaque(false);

        btnLogin = new JButton("Log In");
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setPreferredSize(new Dimension(335, 61));
        btnLogin.addActionListener(e -> handleLogin());
        buttonsPanel.add(btnLogin);

        btnCreateAcc = new JButton("Create Account");
        btnCreateAcc.setFont(new Font("Tahoma", Font.BOLD, 30));
        btnCreateAcc.setPreferredSize(new Dimension(335, 61));
        btnCreateAcc.addActionListener(e -> {
            SignupFrame signup = new SignupFrame();
            signup.setVisible(true);
            LoginWindow.this.dispose();
        });
        buttonsPanel.add(btnCreateAcc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        loginFrame.add(buttonsPanel, gbc);

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
        int buttonSize = Math.max((int) (30 * scale), 14);

        lblLogin.setFont(new Font("Tahoma", Font.BOLD, titleSize));
        lblUsername.setFont(new Font("Tahoma", Font.BOLD, labelSize));
        lblPassword.setFont(new Font("Tahoma", Font.BOLD, labelSize));
        usernameInput.setFont(new Font("Tahoma", Font.PLAIN, inputSize));
        passwordInput.setFont(new Font("Tahoma", Font.PLAIN, inputSize));
        btnLogin.setFont(new Font("Tahoma", Font.BOLD, buttonSize));
        btnCreateAcc.setFont(new Font("Tahoma", Font.BOLD, buttonSize));

        // Scale component sizes
        int inputWidth = Math.max((int) (948 * scale), 300);
        int inputHeight = Math.max((int) (72 * scale), 50);
        int btnWidth = Math.max((int) (335 * scale), 120);
        int btnHeight = Math.max((int) (61 * scale), 35);

        // Update container size
        inputsContainer.setPreferredSize(new Dimension(inputWidth + 40, (int)(250 * scale)));

        // Update input sizes
        usernameInput.setPreferredSize(new Dimension(inputWidth, inputHeight));
        passwordInput.setPreferredSize(new Dimension(inputWidth, inputHeight));

        btnLogin.setPreferredSize(new Dimension(btnWidth, btnHeight));
        btnCreateAcc.setPreferredSize(new Dimension(btnWidth, btnHeight));

        revalidate();
        repaint();
    }

    private void handleLogin() {
        String username = usernameInput.getText().trim();
        String password = new String(passwordInput.getPassword()).trim();

        String url = "jdbc:sqlite:db/lostandfound.db";

        try (Connection conn = DriverManager.getConnection(url)) {
            String sql = "SELECT role FROM users WHERE username = ? AND password = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                if (role.equalsIgnoreCase("admin")) {
                    AdminDashboardWindow adminDashboard = new AdminDashboardWindow(username);
                    adminDashboard.setVisible(true);
                } else {
                    DashboardWindow dashboard = new DashboardWindow(username);
                    dashboard.setVisible(true);
                }

                LoginWindow.this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login Details",
                        "Login Error", JOptionPane.ERROR_MESSAGE);
                usernameInput.setText(null);
                passwordInput.setText(null);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database Error: " + ex.getMessage());
        }
    }
}