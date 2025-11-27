package laf;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdminPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                AdminPanel frame = new AdminPanel();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    


    public AdminPanel() {
        setFont(new Font("Dialog", Font.BOLD, 12));
        setTitle("Admin Panel");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 548, 358);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 142, 319);
        contentPane.add(panel);

        JLabel lblAdminLabel = new JLabel("Admin Panel");
        lblAdminLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblAdminLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblAdminLabel.setBounds(0, 11, 142, 30);
        panel.add(lblAdminLabel);

        // TAB PANE
        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(143, -25, 389, 344);
        contentPane.add(tabbedPane);

        // TAB 1 - USERS
        JPanel usersPanel = new JPanel();
        usersPanel.setLayout(null);
        JLabel lblUsers = new JLabel("Users List + Ban Options Here");
        lblUsers.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsers.setBounds(20, 30, 300, 30);
        usersPanel.add(lblUsers);
        tabbedPane.addTab("Users", usersPanel);

        // TAB 2 - REPORTS
        JPanel reportsPanel = new JPanel();
        reportsPanel.setLayout(null);
        JLabel lblReports = new JLabel("Reports + Delete Option Here");
        lblReports.setHorizontalAlignment(SwingConstants.CENTER);
        lblReports.setBounds(20, 30, 300, 30);
        reportsPanel.add(lblReports);
        tabbedPane.addTab("Reports", reportsPanel);

        // TAB 3 - ITEMS
        JPanel itemsPanel = new JPanel();
        itemsPanel.setLayout(null);
        JLabel lblItems = new JLabel("Manage Lost/Found Items Here");
        lblItems.setHorizontalAlignment(SwingConstants.CENTER);
        lblItems.setBounds(20, 30, 300, 30);
        itemsPanel.add(lblItems);
        tabbedPane.addTab("Items", itemsPanel);

        // TAB 4 - LOGS
        JPanel logsPanel = new JPanel();
        logsPanel.setLayout(null);
        JLabel lblLogs = new JLabel("All Activity Logs Here");
        lblLogs.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogs.setBounds(20, 30, 300, 30);
        logsPanel.add(lblLogs);
        tabbedPane.addTab("Logs", logsPanel);

        // SIDE NAVIGATION BUTTONS
        JButton btnUsers = new JButton("View Users");
        btnUsers.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnUsers.setBounds(0, 63, 142, 30);
        btnUsers.addActionListener(e -> tabbedPane.setSelectedIndex(0));
        panel.add(btnUsers);

        JButton btnReports = new JButton("View Reports");
        btnReports.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnReports.setBounds(0, 115, 142, 30);
        btnReports.addActionListener(e -> tabbedPane.setSelectedIndex(1));
        panel.add(btnReports);

        JButton btnManage = new JButton("Manage Items");
        btnManage.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnManage.setBounds(0, 167, 142, 30);
        btnManage.addActionListener(e -> tabbedPane.setSelectedIndex(2));
        panel.add(btnManage);

        JButton btnViewLogs = new JButton("View Logs");
        btnViewLogs.setFont(new Font("Tahoma", Font.BOLD, 15));
        btnViewLogs.setBounds(0, 217, 142, 30);
        btnViewLogs.addActionListener(e -> tabbedPane.setSelectedIndex(3));
        panel.add(btnViewLogs);

        JButton btnReturn = new JButton("Return");
        btnReturn.setForeground(Color.WHITE);
        btnReturn.setBackground(new Color(64, 0, 0));
        btnReturn.setFont(new Font("Tahoma", Font.BOLD, 14));
        btnReturn.setBounds(0, 288, 142, 31);
        btnReturn.addActionListener(e -> {
            new AdminDashboardWindow("admin").setVisible(true);
            dispose();
        });
        panel.add(btnReturn);
    }
}