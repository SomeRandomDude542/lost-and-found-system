package laf;

import java.awt.EventQueue;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.io.File;
import javax.swing.JFileChooser;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLayeredPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.ScrollPaneConstants;
import javax.swing.BoxLayout;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.io.File;




public class AdminDashboardWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTabbedPane Dashboardtab;
	private JPanel LostItemPan;
	private JPanel ProgressPan;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private String currentUser;
	private File selectedImageFile = null;
	private String savedImagePath = null;
	private JLabel lblImagePreview;
    private String currentUsername;
    private JLabel lblImagePreview_1; 
    private JButton btnImageIns_1; 
    private JPanel pendingLostList;
    private JPanel pendingFoundList;
    private JLabel lblFoundItemsUnclaimed;  
    private JScrollPane scrollPaneforLost;
    private JScrollPane scrollPaneforFound;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminDashboardWindow frame = new AdminDashboardWindow();
					 frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public AdminDashboardWindow() {
	    this("Admin");
	}

	private String loggedUser;

	public AdminDashboardWindow(String username) {
	    this.loggedUser = username;
	    this.currentUser = username;
	    this.currentUsername = username;  
	    setTitle("Lost And Found ");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 548, 358);
	    
	  

	    contentPane = new JPanel();
	    contentPane.setBackground(new Color(244, 244, 244));
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(null);

	    JPanel panel = new JPanel();
	    panel.setBounds(0, 0, 142, 319);
	    contentPane.add(panel);
	    panel.setLayout(null);

	    JLabel lblNewLabel = new JLabel("Dashboard");
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
	    lblNewLabel.setBounds(0, 11, 142, 30);
	    panel.add(lblNewLabel);

	    JButton btnLostItemHub = new JButton("Lost Item Hub");
	    btnLostItemHub.setFont(new Font("Tahoma", Font.BOLD, 15));
	    btnLostItemHub.setBounds(0, 174, 142, 30);
	    panel.add(btnLostItemHub);

	    JButton btnLogout = new JButton("Logout");
	    btnLogout.setFont(new Font("Tahoma", Font.BOLD, 15));
	    btnLogout.setBounds(0, 229, 142, 30);
	    panel.add(btnLogout);

	    JButton btnLostItem = new JButton("Lost Item");
	    btnLostItem.setFont(new Font("Tahoma", Font.BOLD, 15));
	    btnLostItem.setBounds(0, 64, 142, 30);
	    panel.add(btnLostItem);

	    JButton btnProgress = new JButton("Progress");
	    btnProgress.setFont(new Font("Tahoma", Font.BOLD, 15));
	    btnProgress.setBounds(0, 119, 142, 30);
	    panel.add(btnProgress);
	    
	    
	    Dashboardtab = new JTabbedPane(JTabbedPane.TOP);
	    Dashboardtab.setBounds(141, 0, 391, 319);

	    // Hide the tab buttons
	    Dashboardtab.setUI(new javax.swing.plaf.basic.BasicTabbedPaneUI() {
	        @Override
	        protected int calculateTabAreaHeight(int tabPlacement, int runCount, int maxTabHeight) {
	            return 0; 
	        }

	        @Override
	        protected int calculateTabAreaWidth(int tabPlacement, int vertRunCount, int maxTabWidth) {
	            return 0;
	        }
	    });

	    
	    JButton btnAdminPanel = new JButton("Admin Panel");
	    btnAdminPanel.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {

	            AdminPanel admin = new AdminPanel();
	            admin.setVisible(true);

	            dispose();
	        }
	    });
	    btnAdminPanel.setForeground(new Color(255, 255, 255));
	    btnAdminPanel.setBackground(new Color(64, 0, 0));
	    btnAdminPanel.setFont(new Font("Tahoma", Font.BOLD, 15));
	    btnAdminPanel.setBounds(0, 292, 142, 27);
	    panel.add(btnAdminPanel);
	    
	    Dashboardtab = new JTabbedPane(JTabbedPane.TOP);
	    Dashboardtab.setBounds(141, -41, 391, 360);
	    contentPane.add(Dashboardtab);
	    	    
	    	    JPanel ItemLostStats = new JPanel();
	    	    Dashboardtab.addTab("New tab", null, ItemLostStats, null);
	    	    ItemLostStats.setLayout(null);
	    	    
	    	    JLabel lblWellcomeforLostStats = new JLabel("Welcome, " + currentUser);
	    	    lblWellcomeforLostStats.setBounds(10, 11, 179, 32);
	    	    lblWellcomeforLostStats.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    ItemLostStats.add(lblWellcomeforLostStats);
	    	    
	    	    JPanel panel_1 = new JPanel();
	    	    panel_1.setBounds(10, 54, 187, 95);
	    	    panel_1.setBackground(new Color(64, 0, 0));
	    	    ItemLostStats.add(panel_1);
	    	    panel_1.setLayout(null);
	    	    
	    	    JLabel lblMyLost = new JLabel("My Lost Items");
	    	    lblMyLost.setForeground(new Color(255, 255, 255));
	    	    lblMyLost.setBounds(10, 5, 111, 20);
	    	    lblMyLost.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    panel_1.add(lblMyLost);
	    	    
	    	    JLabel lblLostItemPending = new JLabel("New label");
	    	    lblLostItemPending.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    lblLostItemPending.setForeground(Color.WHITE);
	    	    lblLostItemPending.setBounds(10, 29, 46, 37);
	    	    panel_1.add(lblLostItemPending);
	    	    
	    	    int lostItemsCount = countUserLostItems(currentUsername);
	    	    lblLostItemPending.setText(String.valueOf(lostItemsCount));  
	    	    
	    	    JLabel lblPendings = new JLabel("Pendings");
	    	    lblPendings.setForeground(new Color(255, 255, 255));
	    	    lblPendings.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    lblPendings.setBounds(10, 64, 111, 20);
	    	    panel_1.add(lblPendings);
	    	    
	    	    JPanel panel_1_1 = new JPanel();
	    	    panel_1_1.setBounds(207, 11, 169, 180);
	    	    panel_1_1.setBackground(new Color(64, 0, 0));
	    	    ItemLostStats.add(panel_1_1);
	    	    panel_1_1.setLayout(null);
	    	    
	    	    JLabel lblStatsLost = new JLabel("Statistics");
	    	    lblStatsLost.setForeground(new Color(255, 255, 255));
	    	    lblStatsLost.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblStatsLost.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    lblStatsLost.setBounds(34, 11, 111, 20);
	    	    panel_1_1.add(lblStatsLost);
	    	    
	    	    JSeparator separator = new JSeparator();
	    	    separator.setBounds(10, 35, 149, 1);
	    	    panel_1_1.add(separator);
	    	    
	    	    JLabel lblTotalReport = new JLabel("<html><br> Total Report <br> Submitted </b></htrm>");
	    	    lblTotalReport.setForeground(new Color(255, 255, 255));
	    	    lblTotalReport.setVerticalAlignment(SwingConstants.BOTTOM);
	    	    lblTotalReport.setHorizontalAlignment(SwingConstants.LEFT);
	    	    lblTotalReport.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    lblTotalReport.setBounds(10, 42, 89, 37);
	    	    panel_1_1.add(lblTotalReport);
	    	    
	    	    JLabel lblTotalReport_1 = new JLabel("<html><br> Resolved <br> Tickets </b></htrm>");
	    	    lblTotalReport_1.setForeground(new Color(255, 255, 255));
	    	    lblTotalReport_1.setVerticalAlignment(SwingConstants.BOTTOM);
	    	    lblTotalReport_1.setHorizontalAlignment(SwingConstants.LEFT);
	    	    lblTotalReport_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    lblTotalReport_1.setBounds(10, 87, 89, 37);
	    	    panel_1_1.add(lblTotalReport_1);
	    	    
	    	    JLabel lblTotalReport_1_1 = new JLabel("<html><br> Pending <br> Tickets </b></htrm>");
	    	    lblTotalReport_1_1.setForeground(new Color(255, 255, 255));
	    	    lblTotalReport_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
	    	    lblTotalReport_1_1.setHorizontalAlignment(SwingConstants.LEFT);
	    	    lblTotalReport_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    lblTotalReport_1_1.setBounds(10, 132, 89, 37);
	    	    panel_1_1.add(lblTotalReport_1_1);
	    	    
	    	    JSeparator separator_1 = new JSeparator();
	    	    separator_1.setBounds(10, 85, 149, 1);
	    	    panel_1_1.add(separator_1);
	    	    
	    	    JSeparator separator_1_1 = new JSeparator();
	    	    separator_1_1.setBounds(10, 130, 149, 1);
	    	    panel_1_1.add(separator_1_1);
	    	    
	    	    JLabel lblTotRepStatUpdforLost = new JLabel("New label");
	    	    lblTotRepStatUpdforLost.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblTotRepStatUpdforLost.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    lblTotRepStatUpdforLost.setForeground(new Color(255, 255, 255));
	    	    lblTotRepStatUpdforLost.setBounds(113, 42, 46, 37);
	    	    panel_1_1.add(lblTotRepStatUpdforLost);
	    	    
	    	    JLabel lblResTickStatUpdforLost = new JLabel("New label");
	    	    lblResTickStatUpdforLost.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblResTickStatUpdforLost.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    lblResTickStatUpdforLost.setForeground(new Color(255, 255, 255));
	    	    lblResTickStatUpdforLost.setBounds(113, 87, 46, 37);
	    	    panel_1_1.add(lblResTickStatUpdforLost);
	    	    
	    	    JLabel lblStatsPenTickForLost = new JLabel("New label");
	    	    lblStatsPenTickForLost.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblStatsPenTickForLost.setForeground(Color.WHITE);
	    	    lblStatsPenTickForLost.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    lblStatsPenTickForLost.setBounds(113, 132, 46, 37);
	    	    panel_1_1.add(lblStatsPenTickForLost);
	    	    
	    	 // For Lost Items Stats
	    	    scrollPaneforLost = new JScrollPane();
	    	    scrollPaneforLost.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    	    scrollPaneforLost.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	    	    scrollPaneforLost.setBounds(10, 229, 366, 75);
	    	    ItemLostStats.add(scrollPaneforLost);

	    	    pendingLostList = new JPanel();
	    	    pendingLostList.setLayout(new BoxLayout(pendingLostList, BoxLayout.Y_AXIS)); // Y_AXIS for vertical
	    	    scrollPaneforLost.setViewportView(pendingLostList);

	    	    // Load pending tickets
	    	    loadPendingLostItems(pendingLostList, currentUsername);
	    	    
	    	    JPanel panel_2 = new JPanel();
	    	    panel_2.setBounds(10, 202, 366, 28);
	    	    panel_2.setBackground(new Color(64, 0, 0));
	    	    ItemLostStats.add(panel_2);
	    	    panel_2.setLayout(null);
	    	    
	    	    JLabel lblPendingTicketsLost = new JLabel("Pending Tickets");
	    	    lblPendingTicketsLost.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblPendingTicketsLost.setForeground(new Color(255, 255, 255));
	    	    lblPendingTicketsLost.setBounds(99, 0, 169, 28);
	    	    panel_2.add(lblPendingTicketsLost);
	    	    lblPendingTicketsLost.setFont(new Font("Tahoma", Font.BOLD, 16));
	    	    lblWellcomeforLostStats.setText("Welcome, " + currentUser);
	    
	    	    LostItemPan = new JPanel();
	    	    LostItemPan.setBackground(new Color(255, 255, 255));
	    	    Dashboardtab.addTab("Lost Item", LostItemPan);
	    	    LostItemPan.setLayout(null);
	    	    
	    	    JLabel LostItemSelectLabel = new JLabel("Select an Action");
	    	    LostItemSelectLabel.setBounds(98, 32, 177, 51);
	    	    LostItemSelectLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    	    LostItemSelectLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    LostItemPan.add(LostItemSelectLabel);
	    	    
	    	    JButton btnReportLost = new JButton("<html><b>Submit<br>a  Found <br> Item</b></html>");
	    	    btnReportLost.setBounds(30, 99, 147, 178);
	    	    btnReportLost.setForeground(Color.WHITE);
	    	    btnReportLost.setFont(new Font("Tahoma", Font.BOLD, 28));
	    	    btnReportLost.setBackground(new Color(64, 0, 0));
	    	    LostItemPan.add(btnReportLost);
	    	    
	    	    JButton btnFoundLost = new JButton("<html><b>Report<br>a  Lost <br> Item</b></html>");
	    	    btnFoundLost.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    	}
	    	    });
	    	    btnFoundLost.setBounds(213, 99, 147, 178);
	    	    btnFoundLost.setForeground(Color.WHITE);
	    	    btnFoundLost.setFont(new Font("Tahoma", Font.PLAIN, 28));
	    	    btnFoundLost.setBackground(new Color(64, 0, 0));
	    	    LostItemPan.add(btnFoundLost);
	    	    
	    	    JPanel HubPan = new JPanel();
	    	    HubPan.setBackground(new Color(255, 0, 0));
	    	    Dashboardtab.addTab("New tab", null, HubPan, null);
	    	    
	    	    JPanel LogoutPan = new JPanel();
	    	    LogoutPan.setBackground(new Color(255, 255, 255));
	    	    Dashboardtab.addTab("New tab", null, LogoutPan, null);
	    	    LogoutPan.setLayout(null);
	    	    
	    	    JLabel lblNewLabel_3 = new JLabel("<html><div style='text-align:center;'>Are you sure you want<br>to <span style='color:#800000;'> logout?</span></div></html>\"");
	    	    lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
	    	    lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    lblNewLabel_3.setBounds(73, 79, 239, 55);
	    	    LogoutPan.add(lblNewLabel_3);
	    	    
	    	    JLabel lblNewLabel_4 = new JLabel("You’ll need to sign in again to access your dashboard.");
	    	    lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 11));
	    	    lblNewLabel_4.setBounds(64, 134, 257, 14);
	    	    LogoutPan.add(lblNewLabel_4);
	    	    
	    	    JButton btnLogoutCancel = new JButton("Cancel");
	    	    btnLogoutCancel.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    	}
	    	    });
	    	    btnLogoutCancel.setFont(new Font("Tahoma", Font.BOLD, 16));
	    	    btnLogoutCancel.setForeground(new Color(64, 0, 0));
	    	    btnLogoutCancel.setBackground(new Color(192, 192, 192));
	    	    btnLogoutCancel.setBounds(64, 189, 115, 30);
	    	    LogoutPan.add(btnLogoutCancel);
	    	    
	    	    JButton btnLogoutConfirm = new JButton("Confirm");
	    	    btnLogoutConfirm.addActionListener(new ActionListener() {
	    	    	public void actionPerformed(ActionEvent e) {
	    	    		
	    	    		AdminDashboardWindow.this.dispose();
	    	            
	    	            LoginWindow loginWindow = new LoginWindow();
	    	            loginWindow.setVisible(true);
	    	    		
	    	    	}
	    	    });
	    	    btnLogoutConfirm.setFont(new Font("Tahoma", Font.BOLD, 16));
	    	    btnLogoutConfirm.setForeground(new Color(0, 0, 0));
	    	    btnLogoutConfirm.setBackground(new Color(192, 192, 192));
	    	    btnLogoutConfirm.setBounds(206, 189, 115, 30);
	    	    LogoutPan.add(btnLogoutConfirm);
	    	    
	    	    
	    	    	    ProgressPan = new JPanel();
	    	    	    ProgressPan.setBackground(new Color(255, 255, 255));
	    	    	    Dashboardtab.addTab("Progress", ProgressPan);
	    	    	    ProgressPan.setLayout(null);
	    	    	    
	    	    	    JLabel lblNewLabel_1 = new JLabel("Check Your Progress");
	    	    	    lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblNewLabel_1.setBounds(86, 32, 214, 51);
	    	    	    ProgressPan.add(lblNewLabel_1);
	    	    	    
	    	    	    JButton btnLostItemProg = new JButton("<html><b>Lost Item<br>Report <br> Progress</b></html>");
	    	    	    btnLostItemProg.addActionListener(new ActionListener() {
	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    	}
	    	    	    });
	    	    	    btnLostItemProg.setForeground(new Color(255, 255, 255));
	    	    	    btnLostItemProg.setFont(new Font("Tahoma", Font.PLAIN, 19));
	    	    	    btnLostItemProg.setBackground(new Color(64, 0, 0));
	    	    	    btnLostItemProg.setBounds(36, 99, 147, 178);
	    	    	    ProgressPan.add(btnLostItemProg);
	    	    	    
	    	    	    JButton btnFoundItemProfg = new JButton("<html><b>Found Item<br>Report <br> Progress</b></html>");
	    	    	    btnFoundItemProfg.setBackground(new Color(64, 0, 0));
	    	    	    btnFoundItemProfg.setForeground(new Color(255, 255, 255));
	    	    	    btnFoundItemProfg.setFont(new Font("Tahoma", Font.BOLD, 19));
	    	    	    btnFoundItemProfg.setBounds(213, 99, 147, 178);
	    	    	    ProgressPan.add(btnFoundItemProfg);
	    	    	    
	    	    	    JPanel ItemLostForm = new JPanel();
	    	    	    Dashboardtab.addTab("New tab", null, ItemLostForm, null);
	    	    	    ItemLostForm.setLayout(null);
	    	    	    
	    	    	    JLabel lblItemLostForm = new JLabel("Item Lost");
	    	    	    lblItemLostForm.setBounds(126, 0, 134, 29);
	    	    	    lblItemLostForm.setFont(new Font("Tahoma", Font.BOLD, 22));
	    	    	    lblItemLostForm.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    ItemLostForm.add(lblItemLostForm);
	    	    	    
	    	    	    JLabel lblNewLabel_2 = new JLabel("Item Name");
	    	    	    lblNewLabel_2.setBounds(30, 32, 84, 17);
	    	    	    lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    ItemLostForm.add(lblNewLabel_2);
	    	    	    
	    	    	    textField = new JTextField();
	    	    	    textField.setBounds(30, 49, 332, 17);
	    	    	    ItemLostForm.add(textField);
	    	    	    textField.setColumns(10);
	    	    	    
	    	    	    JLabel lblNewLabel_2_1 = new JLabel("Location Lost");
	    	    	    lblNewLabel_2_1.setBounds(30, 77, 93, 17);
	    	    	    lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    ItemLostForm.add(lblNewLabel_2_1);
	    	    	    
	    	    	    textField_1 = new JTextField();
	    	    	    textField_1.setBounds(30, 94, 332, 17);
	    	    	    textField_1.setColumns(10);
	    	    	    ItemLostForm.add(textField_1);
	    	    	    
	    	    	    JLabel lblNewLabel_2_2 = new JLabel("Date Lost");
	    	    	    lblNewLabel_2_2.setBounds(30, 122, 66, 17);
	    	    	    lblNewLabel_2_2.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    ItemLostForm.add(lblNewLabel_2_2);
	    	    	    
	    	    	    textField_2 = new JTextField();
	    	    	    textField_2.setBounds(30, 139, 332, 17);
	    	    	    textField_2.setColumns(10);
	    	    	    ItemLostForm.add(textField_2);
	    	    	    
	    	    	    JLabel lblNewLabel_2_3 = new JLabel("Description");
	    	    	    lblNewLabel_2_3.setBounds(30, 167, 77, 17);
	    	    	    lblNewLabel_2_3.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    ItemLostForm.add(lblNewLabel_2_3);
	    	    	    
	    	    	    textField_3 = new JTextField();
	    	    	    textField_3.setBounds(30, 184, 332, 29);
	    	    	    textField_3.setColumns(10);
	    	    	    ItemLostForm.add(textField_3);
	    	    	    
	    	    	    JButton btnNewButton_1 = new JButton("Submit");
	    	    	    btnNewButton_1.setBounds(134, 287, 118, 29);
	    	    	    btnNewButton_1.addActionListener(new ActionListener() {
	    	    	        public void actionPerformed(ActionEvent e) {
	    	    	            // Get form values
	    	    	            String itemName = textField.getText().trim();
	    	    	            String locationLost = textField_1.getText().trim();
	    	    	            String dateLost = textField_2.getText().trim();
	    	    	            String description = textField_3.getText().trim();

	    	    	            // Validate required fields
	    	    	            if (itemName.isEmpty() || locationLost.isEmpty() || dateLost.isEmpty() || description.isEmpty()) {
	    	    	                JOptionPane.showMessageDialog(null,
	    	    	                    "Please fill in all required fields.",
	    	    	                    "Validation Error",
	    	    	                    JOptionPane.ERROR_MESSAGE);
	    	    	                return;
	    	    	            }

	    	    	            // Save image if selected
	    	    	            String imagePath = null;
	    	    	            if (selectedImageFile != null) {
	    	    	                try {
	    	    	                    File imagesDir = new File("images/items");
	    	    	                    if (!imagesDir.exists()) {
	    	    	                        imagesDir.mkdirs();
	    	    	                    }

	    	    	                    String extension = selectedImageFile.getName().substring(
	    	    	                        selectedImageFile.getName().lastIndexOf('.') + 1);
	    	    	                    String newFileName = System.currentTimeMillis() + "_" + 
	    	    	                        itemName.replaceAll("[^a-zA-Z0-9]", "_") + "." + extension;
	    	    	                    File destFile = new File(imagesDir, newFileName);

	    	    	                    java.nio.file.Files.copy(selectedImageFile.toPath(), 
	    	    	                        destFile.toPath(), 
	    	    	                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
	    	    	                    
	    	    	                    imagePath = destFile.getPath();
	    	    	                    System.out.println("Image saved to: " + imagePath);

	    	    	                } catch (Exception ex) {
	    	    	                    JOptionPane.showMessageDialog(null,
	    	    	                        "Error saving image: " + ex.getMessage(),
	    	    	                        "File Error",
	    	    	                        JOptionPane.ERROR_MESSAGE);
	    	    	                    return;
	    	    	                }
	    	    	            }

	    	    	            // Save to database
	    	    	            try (Connection conn = SQLiteConnection.connect()) {
	    	    	                String sql = "INSERT INTO lost_items (item_name, location_lost, date_lost, description, image_path, reported_by) " +
	    	    	                             "VALUES (?, ?, ?, ?, ?, ?)";
	    	    	                
	    	    	                java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
	    	    	                pstmt.setString(1, itemName);
	    	    	                pstmt.setString(2, locationLost);
	    	    	                pstmt.setString(3, dateLost);
	    	    	                pstmt.setString(4, description);
	    	    	                pstmt.setString(5, imagePath); // Can be null if no image
	    	    	                pstmt.setString(6, currentUsername); // The logged-in user
	    	    	                
	    	    	                pstmt.executeUpdate();
	    	    	                
	    	    	                System.out.println("Lost item report saved!");
	    	    	                
	    	    	                JOptionPane.showMessageDialog(null,
	    	    	                    "Lost item report submitted successfully!",
	    	    	                    "Success",
	    	    	                    JOptionPane.INFORMATION_MESSAGE);
	    	    	                	    	    	 
	    	    	                // Clear form
	    	    	                textField.setText("");
	    	    	                textField_1.setText("");
	    	    	                textField_2.setText("");
	    	    	                textField_3.setText("");
	    	    	                lblImagePreview_1.setIcon(null);
	    	    	                lblImagePreview_1.setText("No Image");
	    	    	                btnImageIns_1.setText("Choose Image");
	    	    	                btnImageIns_1.setForeground(Color.BLACK);
	    	    	                selectedImageFile = null;
	    	    	                
	    	    	                int count = countUserLostItems(currentUsername);
	    	    	                lblLostItemPending.setText(String.valueOf(count));

	    	    	                // Refresh pending tickets list
	    	    	                loadPendingLostItems(pendingLostList, currentUsername);


	    	    	            } catch (Exception ex) {
	    	    	                ex.printStackTrace();
	    	    	                JOptionPane.showMessageDialog(null,
	    	    	                    "Database Error: " + ex.getMessage(),
	    	    	                    "Error",
	    	    	                    JOptionPane.ERROR_MESSAGE);
	    	    	            }
	    	    	        }
	    	    	    });
	    	    	    btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 18));
	    	    	    btnNewButton_1.setBackground(new Color(64, 0, 0));
	    	    	    btnNewButton_1.setForeground(new Color(255, 255, 255));
	    	    	    ItemLostForm.add(btnNewButton_1);
	    	    	    
	    	    	    lblImagePreview_1 = new JLabel("No Image");
	    	    	    lblImagePreview_1.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblImagePreview_1.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	    	    	    lblImagePreview_1.setBounds(190, 215, 78, 68); // Made taller for image
	    	    	    ItemLostForm.add(lblImagePreview_1);

	    	    	    // Then create button
	    	    	    btnImageIns_1 = new JButton("Choose Image");
	    	    	    btnImageIns_1.addActionListener(new ActionListener() {
	    	    	        public void actionPerformed(ActionEvent e) {
	    	    	            JFileChooser fileChooser = new JFileChooser();
	    	    	            fileChooser.setDialogTitle("Select Item Image");

	    	    	            javax.swing.filechooser.FileNameExtensionFilter filter =
	    	    	                new javax.swing.filechooser.FileNameExtensionFilter(
	    	    	                    "Image Files", "jpg", "jpeg", "png", "gif", "bmp", "jfif");
	    	    	            fileChooser.setFileFilter(filter);

	    	    	            int result = fileChooser.showOpenDialog(null);

	    	    	            if (result == JFileChooser.APPROVE_OPTION) {
	    	    	                File tempFile = fileChooser.getSelectedFile();
	    	    	                
	    	    	                // Validate file type FIRST
	    	    	                String fileName = tempFile.getName().toLowerCase();
	    	    	                String[] supportedTypes = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
	    	    	                boolean isSupported = false;
	    	    	                
	    	    	                for (String type : supportedTypes) {
	    	    	                    if (fileName.endsWith(type)) {
	    	    	                        isSupported = true;
	    	    	                        break;
	    	    	                    }
	    	    	                }
	    	    	                
	    	    	                if (!isSupported) {
	    	    	                    JOptionPane.showMessageDialog(null,
	    	    	                        "Unsupported file type!\n\nSupported formats:\n" +
	    	    	                        "JPG, JPEG, PNG, GIF, BMP\n\n" +
	    	    	                        "Your file: " + fileName,
	    	    	                        "Invalid Image Format",
	    	    	                        JOptionPane.ERROR_MESSAGE);
	    	    	                    // DON'T set selectedImageFile - just return
	    	    	                    return;
	    	    	                }
	    	    	                
	    	    	                // Only set the file if it's supported
	    	    	                selectedImageFile = tempFile;

	    	    	                try {
	    	    	                    ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());
	    	    	                    Image image = imageIcon.getImage();
	    	    	                    Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    	    	                    lblImagePreview_1.setIcon(new ImageIcon(scaledImage));
	    	    	                    lblImagePreview_1.setText("");
	    	    	                    
	    	    	                    btnImageIns_1.setText("✓ Selected");
	    	    	                    btnImageIns_1.setForeground(new Color(0, 128, 0));
	    	    	                    
	    	    	                } catch (Exception ex) {
	    	    	                    lblImagePreview_1.setText("Error");
	    	    	                    selectedImageFile = null; // Reset on error
	    	    	                    JOptionPane.showMessageDialog(null,
	    	    	                        "Error loading image preview",
	    	    	                        "Image Error",
	    	    	                        JOptionPane.ERROR_MESSAGE);
	    	    	                }
	    	    	            }
	    	    	        }
	    	    	    });
	    	    	    btnImageIns_1.setBounds(30, 235, 150, 29);
	    	    	    ItemLostForm.add(btnImageIns_1);
	    	    	
	    	    	    
	    	    	    JPanel FoundItemForm = new JPanel();
	    	    	    Dashboardtab.addTab("New tab", null, FoundItemForm, null);
	    	    	    FoundItemForm.setLayout(null);
	    	    	    
	    	    	    JLabel lblFoundItem = new JLabel("Found Item");
	    	    	    lblFoundItem.setBounds(126, 0, 134, 29);
	    	    	    lblFoundItem.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblFoundItem.setFont(new Font("Tahoma", Font.BOLD, 22));
	    	    	    FoundItemForm.add(lblFoundItem);
	    	    	    
	    	    	    JLabel lblNewLabel_2_4 = new JLabel("Item Name");
	    	    	    lblNewLabel_2_4.setBounds(30, 32, 84, 17);
	    	    	    lblNewLabel_2_4.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    FoundItemForm.add(lblNewLabel_2_4);
	    	    	    
	    	    	    textField_4 = new JTextField();
	    	    	    textField_4.setBounds(30, 49, 332, 17);
	    	    	    textField_4.setColumns(10);
	    	    	    FoundItemForm.add(textField_4);
	    	    	    
	    	    	    JLabel lblNewLabel_2_1_1 = new JLabel("Location Lost");
	    	    	    lblNewLabel_2_1_1.setBounds(30, 77, 93, 17);
	    	    	    lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    FoundItemForm.add(lblNewLabel_2_1_1);
	    	    	    
	    	    	    textField_5 = new JTextField();
	    	    	    textField_5.setBounds(30, 94, 332, 17);
	    	    	    textField_5.setColumns(10);
	    	    	    FoundItemForm.add(textField_5);
	    	    	    
	    	    	    JLabel lblNewLabel_2_2_1 = new JLabel("Date Lost");
	    	    	    lblNewLabel_2_2_1.setBounds(30, 122, 66, 17);
	    	    	    lblNewLabel_2_2_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    FoundItemForm.add(lblNewLabel_2_2_1);
	    	    	    
	    	    	    textField_6 = new JTextField();
	    	    	    textField_6.setBounds(30, 139, 332, 17);
	    	    	    textField_6.setColumns(10);
	    	    	    FoundItemForm.add(textField_6);
	    	    	    
	    	    	    JLabel lblNewLabel_2_3_1 = new JLabel("Description");
	    	    	    lblNewLabel_2_3_1.setBounds(30, 167, 77, 17);
	    	    	    lblNewLabel_2_3_1.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    FoundItemForm.add(lblNewLabel_2_3_1);
	    	    	    
	    	    	    textField_7 = new JTextField();
	    	    	    textField_7.setBounds(30, 184, 332, 29);
	    	    	    textField_7.setColumns(10);
	    	    	    FoundItemForm.add(textField_7);
	    	    	    
	    	    	    JButton btnNewButton_1_1 = new JButton("Submit");
	    	    	    btnNewButton_1_1.addActionListener(new ActionListener() {
	    	    	        public void actionPerformed(ActionEvent e) {
	    	    	            // Get form values
	    	    	            String itemName = textField_4.getText().trim();
	    	    	            String locationFound = textField_5.getText().trim();
	    	    	            String dateFound = textField_6.getText().trim();
	    	    	            String description = textField_7.getText().trim();

	    	    	            // Validate required fields
	    	    	            if (itemName.isEmpty() || locationFound.isEmpty() || dateFound.isEmpty() || description.isEmpty()) {
	    	    	                JOptionPane.showMessageDialog(null,
	    	    	                    "Please fill in all required fields.",
	    	    	                    "Validation Error",
	    	    	                    JOptionPane.ERROR_MESSAGE);
	    	    	                return;
	    	    	            }

	    	    	            // Save image if selected
	    	    	            String imagePath = null;
	    	    	            if (selectedImageFile != null) {
	    	    	                try {
	    	    	                    File imagesDir = new File("images/items");
	    	    	                    if (!imagesDir.exists()) {
	    	    	                        imagesDir.mkdirs();
	    	    	                    }

	    	    	                    String extension = selectedImageFile.getName().substring(
	    	    	                        selectedImageFile.getName().lastIndexOf('.') + 1);
	    	    	                    String newFileName = System.currentTimeMillis() + "_" + 
	    	    	                        itemName.replaceAll("[^a-zA-Z0-9]", "_") + "." + extension;
	    	    	                    File destFile = new File(imagesDir, newFileName);

	    	    	                    java.nio.file.Files.copy(selectedImageFile.toPath(), 
	    	    	                        destFile.toPath(), 
	    	    	                        java.nio.file.StandardCopyOption.REPLACE_EXISTING);
	    	    	                    
	    	    	                    imagePath = destFile.getPath();
	    	    	                    System.out.println("Image saved to: " + imagePath);

	    	    	                } catch (Exception ex) {
	    	    	                    JOptionPane.showMessageDialog(null,
	    	    	                        "Error saving image: " + ex.getMessage(),
	    	    	                        "File Error",
	    	    	                        JOptionPane.ERROR_MESSAGE);
	    	    	                    return;
	    	    	                }
	    	    	            }

	    	    	            // Save to database
	    	    	            try (Connection conn = SQLiteConnection.connect()) {
	    	    	                String sql = "INSERT INTO found_items (item_name, location_found, date_found, description, image_path, reported_by) " +
	    	    	                             "VALUES (?, ?, ?, ?, ?, ?)";
	    	    	                
	    	    	                PreparedStatement pstmt = conn.prepareStatement(sql);
	    	    	                pstmt.setString(1, itemName);
	    	    	                pstmt.setString(2, locationFound);
	    	    	                pstmt.setString(3, dateFound);
	    	    	                pstmt.setString(4, description);
	    	    	                pstmt.setString(5, imagePath); // Can be null if no image
	    	    	                pstmt.setString(6, currentUsername); // The logged-in user
	    	    	                
	    	    	                pstmt.executeUpdate();
	    	    	                
	    	    	                System.out.println("Found item report saved!");
	    	    	                
	    	    	                JOptionPane.showMessageDialog(null,
	    	    	                    "Found item report submitted successfully!",
	    	    	                    "Success",
	    	    	                    JOptionPane.INFORMATION_MESSAGE);

	    	    	                // Clear form
	    	    	                textField_4.setText("");
	    	    	                textField_5.setText("");
	    	    	                textField_6.setText("");
	    	    	                textField_7.setText("");
	    	    	                lblImagePreview.setIcon(null);
	    	    	                lblImagePreview.setText("No Image");
	    	    	                selectedImageFile = null;

	    	    	                // Refresh count
	    	    	                int count = countUserFoundItems(currentUsername);
	    	    	                lblFoundItemsUnclaimed.setText(String.valueOf(count));
	    	    	                
	    	    	                // Refresh pending tickets list
	    	    	                loadPendingFoundItems(pendingFoundList, currentUsername);

	    	    	            } catch (Exception ex) {
	    	    	                ex.printStackTrace();
	    	    	                JOptionPane.showMessageDialog(null,
	    	    	                    "Database Error: " + ex.getMessage(),
	    	    	                    "Error",
	    	    	                    JOptionPane.ERROR_MESSAGE);
	    	    	            }
	    	    	        }
	    	    	    });
	    	    	    btnNewButton_1_1.setBounds(134, 287, 118, 29);
	    	    	    btnNewButton_1_1.setForeground(Color.WHITE);
	    	    	    btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 18));
	    	    	    btnNewButton_1_1.setBackground(new Color(64, 0, 0));
	    	    	    FoundItemForm.add(btnNewButton_1_1);
	    	    	    
	    	    	 // Create label FIRST
	    	    	    lblImagePreview = new JLabel("No Image");
	    	    	    lblImagePreview.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblImagePreview.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
	    	    	    lblImagePreview.setBounds(190, 215, 78, 68); // Made taller for image
	    	    	    FoundItemForm.add(lblImagePreview);

	    	    	    // Then create button
	    	    	    JButton btnImageIns = new JButton("Choose Image");
	    	    	    btnImageIns.addActionListener(new ActionListener() {
	    	    	        public void actionPerformed(ActionEvent e) {
	    	    	            JFileChooser fileChooser = new JFileChooser();
	    	    	            fileChooser.setDialogTitle("Select Item Image");

	    	    	            javax.swing.filechooser.FileNameExtensionFilter filter =
	    	    	                new javax.swing.filechooser.FileNameExtensionFilter(
	    	    	                    "Image Files", "jpg", "jpeg", "png", "gif", "bmp", "jfif");
	    	    	            fileChooser.setFileFilter(filter);

	    	    	            int result = fileChooser.showOpenDialog(null);

	    	    	            if (result == JFileChooser.APPROVE_OPTION) {
	    	    	                selectedImageFile = fileChooser.getSelectedFile();
	    	    	                
	    	    	                // Validate file type
	    	    	                String fileName = selectedImageFile.getName().toLowerCase();
	    	    	                String[] supportedTypes = {".jpg", ".jpeg", ".png", ".gif", ".bmp"};
	    	    	                boolean isSupported = false;
	    	    	                
	    	    	                for (String type : supportedTypes) {
	    	    	                    if (fileName.endsWith(type)) {
	    	    	                        isSupported = true;
	    	    	                        break;
	    	    	                    }
	    	    	                }
	    	    	                
	    	    	                if (!isSupported) {
	    	    	                    JOptionPane.showMessageDialog(null,
	    	    	                        "Unsupported file type!\n\nSupported formats:\n" +
	    	    	                        "JPG, JPEG, PNG, GIF, BMP\n\n" +
	    	    	                        "Your file: " + fileName,
	    	    	                        "Invalid Image Format",
	    	    	                        JOptionPane.ERROR_MESSAGE);
	    	    	                    selectedImageFile = null;
	    	    	                    return;
	    	    	                }

	    	    	                try {
	    	    	                    ImageIcon imageIcon = new ImageIcon(selectedImageFile.getAbsolutePath());
	    	    	                    Image image = imageIcon.getImage();
	    	    	                    Image scaledImage = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
	    	    	                    lblImagePreview.setIcon(new ImageIcon(scaledImage)); // or lblImagePreview for Found form
	    	    	                    lblImagePreview.setText("");
	    	    	                } catch (Exception ex) {
	    	    	                    lblImagePreview.setText("Error");
	    	    	                    JOptionPane.showMessageDialog(null,
	    	    	                        "Error loading image preview",
	    	    	                        "Image Error",
	    	    	                        JOptionPane.ERROR_MESSAGE);
	    	    	                    selectedImageFile = null;
	    	    	                }

	    	    	                btnImageIns.setText("✓ Selected"); // or btnImageIns for Found form
	    	    	                btnImageIns.setForeground(new Color(0, 128, 0));
	    	    	            }
	    	    	        }
	    	    	    });
	    	    	    btnImageIns.setBounds(30, 235, 150, 29);
	    	    	    FoundItemForm.add(btnImageIns);
	    	    	    
	    	    	    JPanel ItemFoundStats = new JPanel();
	    	    	    Dashboardtab.addTab("New tab", null, ItemFoundStats, null);
	    	    	    ItemFoundStats.setLayout(null);
	    	    	    
	    	    	    JLabel lblWellcomeforFoundStats = new JLabel("Welcome, " + currentUser);
	    	    	    lblWellcomeforFoundStats.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    	    lblWellcomeforFoundStats.setBounds(10, 11, 179, 32);
	    	    	    ItemFoundStats.add(lblWellcomeforFoundStats);
	    	    	    
	    	    	    JPanel panel_1_2 = new JPanel();
	    	    	    panel_1_2.setLayout(null);
	    	    	    panel_1_2.setBackground(new Color(64, 0, 0));
	    	    	    panel_1_2.setBounds(10, 54, 187, 95);
	    	    	    ItemFoundStats.add(panel_1_2);
	    	    	    
	    	    	    JLabel lblFoundItems = new JLabel("Found Items");
	    	    	    lblFoundItems.setForeground(Color.WHITE);
	    	    	    lblFoundItems.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    	    lblFoundItems.setBounds(10, 5, 111, 20);
	    	    	    panel_1_2.add(lblFoundItems);
	    	    	    
	    	    	    lblFoundItemsUnclaimed = new JLabel("New label");
	    	    	    lblFoundItemsUnclaimed.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    	    lblFoundItemsUnclaimed.setForeground(Color.WHITE);
	    	    	    lblFoundItemsUnclaimed.setBounds(10, 29, 46, 37);
	    	    	    panel_1_2.add(lblFoundItemsUnclaimed);
	    	    	    
	    	    	    int foundItemsCount = countUserFoundItems(currentUsername);
	    	    	    lblFoundItemsUnclaimed.setText(String.valueOf(foundItemsCount));
	    	    	    
	    	    	    JLabel lblUnclaimed = new JLabel("Unclaimed");
	    	    	    lblUnclaimed.setForeground(Color.WHITE);
	    	    	    lblUnclaimed.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    	    lblUnclaimed.setBounds(10, 64, 111, 20);
	    	    	    panel_1_2.add(lblUnclaimed);
	    	    	    
	    	    	    JPanel panel_1_1_1 = new JPanel();
	    	    	    panel_1_1_1.setLayout(null);
	    	    	    panel_1_1_1.setBackground(new Color(64, 0, 0));
	    	    	    panel_1_1_1.setBounds(207, 11, 169, 180);
	    	    	    ItemFoundStats.add(panel_1_1_1);
	    	    	    
	    	    	    JLabel lblStatsFound = new JLabel("Statistics");
	    	    	    lblStatsFound.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblStatsFound.setForeground(Color.WHITE);
	    	    	    lblStatsFound.setFont(new Font("Tahoma", Font.BOLD, 15));
	    	    	    lblStatsFound.setBounds(34, 11, 111, 20);
	    	    	    panel_1_1_1.add(lblStatsFound);
	    	    	    
	    	    	    JSeparator separator_2 = new JSeparator();
	    	    	    separator_2.setBounds(10, 35, 149, 1);
	    	    	    panel_1_1_1.add(separator_2);
	    	    	    
	    	    	    JLabel lblTotalReportforFound = new JLabel("<html><br> Total Report <br> Submitted </b></htrm>");
	    	    	    lblTotalReportforFound.setVerticalAlignment(SwingConstants.BOTTOM);
	    	    	    lblTotalReportforFound.setHorizontalAlignment(SwingConstants.LEFT);
	    	    	    lblTotalReportforFound.setForeground(Color.WHITE);
	    	    	    lblTotalReportforFound.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    lblTotalReportforFound.setBounds(10, 42, 89, 37);
	    	    	    panel_1_1_1.add(lblTotalReportforFound);
	    	    	    
	    	    	    JLabel lblResoveldTickforFound = new JLabel("<html><br> Resolved <br> Tickets </b></htrm>");
	    	    	    lblResoveldTickforFound.setVerticalAlignment(SwingConstants.BOTTOM);
	    	    	    lblResoveldTickforFound.setHorizontalAlignment(SwingConstants.LEFT);
	    	    	    lblResoveldTickforFound.setForeground(Color.WHITE);
	    	    	    lblResoveldTickforFound.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    lblResoveldTickforFound.setBounds(10, 87, 89, 37);
	    	    	    panel_1_1_1.add(lblResoveldTickforFound);
	    	    	    
	    	    	    JLabel lblPendingTicketsforFound = new JLabel("<html><br> Pending <br> Tickets </b></htrm>");
	    	    	    lblPendingTicketsforFound.setVerticalAlignment(SwingConstants.BOTTOM);
	    	    	    lblPendingTicketsforFound.setHorizontalAlignment(SwingConstants.LEFT);
	    	    	    lblPendingTicketsforFound.setForeground(Color.WHITE);
	    	    	    lblPendingTicketsforFound.setFont(new Font("Tahoma", Font.BOLD, 14));
	    	    	    lblPendingTicketsforFound.setBounds(10, 132, 89, 37);
	    	    	    panel_1_1_1.add(lblPendingTicketsforFound);
	    	    	    
	    	    	    JSeparator separator_1_2 = new JSeparator();
	    	    	    separator_1_2.setBounds(10, 85, 149, 1);
	    	    	    panel_1_1_1.add(separator_1_2);
	    	    	    
	    	    	    JSeparator separator_1_1_1 = new JSeparator();
	    	    	    separator_1_1_1.setBounds(10, 130, 149, 1);
	    	    	    panel_1_1_1.add(separator_1_1_1);
	    	    	    
	    	    	    JLabel lblTotRepStatUpdforFound = new JLabel("New label");
	    	    	    lblTotRepStatUpdforFound.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblTotRepStatUpdforFound.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    	    lblTotRepStatUpdforFound.setForeground(Color.WHITE);
	    	    	    lblTotRepStatUpdforFound.setBounds(113, 42, 46, 37);
	    	    	    panel_1_1_1.add(lblTotRepStatUpdforFound);
	    	    	    
	    	    	    JLabel lblResTickStatUpdforFound = new JLabel("New label");
	    	    	    lblResTickStatUpdforFound.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblResTickStatUpdforFound.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    	    lblResTickStatUpdforFound.setForeground(Color.WHITE);
	    	    	    lblResTickStatUpdforFound.setBounds(113, 87, 46, 37);
	    	    	    panel_1_1_1.add(lblResTickStatUpdforFound);
	    	    	    
	    	    	    JLabel lblPenTickUpdforFound = new JLabel("New label");
	    	    	    lblPenTickUpdforFound.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblPenTickUpdforFound.setFont(new Font("Tahoma", Font.BOLD, 20));
	    	    	    lblPenTickUpdforFound.setForeground(Color.WHITE);
	    	    	    lblPenTickUpdforFound.setBounds(113, 132, 46, 37);
	    	    	    panel_1_1_1.add(lblPenTickUpdforFound);
	    	    	    
	    	    	    scrollPaneforFound = new JScrollPane();
	    	    	    scrollPaneforFound.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    	    	    scrollPaneforFound.setBounds(10, 229, 366, 75);
	    	    	    ItemFoundStats.add(scrollPaneforFound);
	    	    	    
	    	    	    pendingFoundList = new JPanel();  // Remove 'JPanel' since it's now a field
	    	    	    scrollPaneforFound.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	    	    	    scrollPaneforFound.setViewportView(pendingFoundList);

	    	    	    // Load pending tickets
	    	    	    loadPendingFoundItems(pendingFoundList, currentUsername);
	    	    	    
	    	    	    JPanel panel_2_1 = new JPanel();
	    	    	    panel_2_1.setLayout(null);
	    	    	    panel_2_1.setBackground(new Color(64, 0, 0));
	    	    	    panel_2_1.setBounds(10, 202, 366, 28);
	    	    	    ItemFoundStats.add(panel_2_1);
	    	    	    
	    	    	    JLabel lblPendingTicketsLost_1 = new JLabel("Pending Tickets");
	    	    	    lblPendingTicketsLost_1.setForeground(new Color(255, 255, 255));
	    	    	    lblPendingTicketsLost_1.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    lblPendingTicketsLost_1.setBounds(99, 0, 169, 28);
	    	    	    panel_2_1.add(lblPendingTicketsLost_1);
	    	    	    lblPendingTicketsLost_1.setFont(new Font("Tahoma", Font.BOLD, 16));
	    	    	    
	    	    	    JPanel FIrstPanel = new JPanel();
	    	    	    FIrstPanel.setBackground(new Color(255, 255, 255));
	    	    	    Dashboardtab.addTab("New tab", null, FIrstPanel, null);
	    	    	    FIrstPanel.setLayout(null);
	    	    	    
	    	    	    JLabel FirstPanellbl = new JLabel("Welcome, " + currentUser);
	    	    	    FirstPanellbl.setHorizontalAlignment(SwingConstants.CENTER);
	    	    	    FirstPanellbl.setFont(new Font("Tahoma", Font.BOLD, 22));
	    	    	    FirstPanellbl.setBounds(53, 38, 280, 45);
	    	    	    FIrstPanel.add(FirstPanellbl);
	    	    	    
	    	    	    JButton btnSettings = new JButton("Settings");
	    	    	    btnSettings.addActionListener(new ActionListener() {
	    	    	    	public void actionPerformed(ActionEvent e) {
	    	    	    		
	    	    	    		
	    	    	    	}
	    	    	    	
	    	    	    
	    	    	    });

	    btnLostItem.addActionListener(e -> Dashboardtab.setSelectedComponent(LostItemPan));
	    btnProgress.addActionListener(e -> Dashboardtab.setSelectedComponent(ProgressPan));
	    btnLostItemHub.addActionListener(e -> Dashboardtab.setSelectedComponent(HubPan));
	    btnLogout.addActionListener(e -> Dashboardtab.setSelectedComponent(LogoutPan));
	    btnFoundLost.addActionListener(e -> Dashboardtab.setSelectedComponent(ItemLostForm));
	    btnReportLost.addActionListener(e -> Dashboardtab.setSelectedComponent(FoundItemForm));
	    btnLostItemProg.addActionListener(e -> Dashboardtab.setSelectedComponent(ItemLostStats));
	    btnFoundItemProfg.addActionListener(e -> Dashboardtab.setSelectedComponent(ItemFoundStats));
	    
	    btnLogoutCancel.addActionListener(e -> Dashboardtab.setSelectedIndex(0)); 
	    Dashboardtab.setSelectedIndex(8);


	   
	       
	    }
	private int countUserLostItems(String username) {
	    int count = 0;
	    try (Connection conn = SQLiteConnection.connect()) {
	        String sql = "SELECT COUNT(*) FROM lost_items WHERE reported_by = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        
	        java.sql.ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception ex) {
	        System.err.println("Error counting lost items: " + ex.getMessage());
	    }
	    return count;
	}

	private int countUserFoundItems(String username) {
	    int count = 0;
	    try (Connection conn = SQLiteConnection.connect()) {
	        // We'll create this table next
	        String sql = "SELECT COUNT(*) FROM found_items WHERE reported_by = ?";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        
	        java.sql.ResultSet rs = pstmt.executeQuery();
	        if (rs.next()) {
	            count = rs.getInt(1);
	        }
	    } catch (Exception ex) {
	        System.err.println("Error counting found items: " + ex.getMessage());
	    }
	    return count;
	}
	private void loadPendingLostItems(JPanel containerPanel, String username) {
	    // Clear existing items
	    containerPanel.removeAll();
	    
	    try (Connection conn = SQLiteConnection.connect()) {
	        String sql = "SELECT id, item_name, description, date_lost, status FROM lost_items " +
	                     "WHERE reported_by = ? AND status = 'Pending' ORDER BY date_reported DESC";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        
	        java.sql.ResultSet rs = pstmt.executeQuery();
	        
	        boolean hasItems = false;
	        while (rs.next()) {
	            hasItems = true;
	            int ticketId = rs.getInt("id");
	            String itemName = rs.getString("item_name");
	            String description = rs.getString("description");
	            String dateLost = rs.getString("date_lost");
	            String status = rs.getString("status");
	            
	            // Create a panel for each ticket
	            JPanel ticketPanel = new JPanel();
	            ticketPanel.setLayout(null);
	            ticketPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
	            ticketPanel.setPreferredSize(new Dimension(scrollPaneforLost.getWidth(), 30));
	            ticketPanel.setBackground(Color.WHITE);
	            ticketPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
	            
	            // Ticket ID
	            JLabel lblTicketId = new JLabel("#" + ticketId);
	            lblTicketId.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblTicketId.setBounds(10, 0, 50, 28);
	            ticketPanel.add(lblTicketId);
	            
	            // Type
	            JLabel lblType = new JLabel("Lost");
	            lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblType.setBounds(70, 0, 40, 28);
	            ticketPanel.add(lblType);
	            
	            // Description (truncated)
	            String shortDesc = description.length() > 15 ? description.substring(0, 15) + "..." : description;
	            JLabel lblDesc = new JLabel(shortDesc);
	            lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblDesc.setBounds(120, 0, 250, 28);
	            lblDesc.setToolTipText(description); // Full description on hover
	            ticketPanel.add(lblDesc);
	            
	            // Date
	            JLabel lblDate = new JLabel(dateLost);
	            lblDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblDate.setBounds(220, 0, 60, 28);
	            ticketPanel.add(lblDate);
	            
	            // Status
	            JLabel lblStatus = new JLabel(status);
	            lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
	            lblStatus.setForeground(new Color(255, 165, 0)); // Orange for pending
	            lblStatus.setBounds(285, 0, 60, 28);
	            ticketPanel.add(lblStatus);
	            
	            containerPanel.add(ticketPanel);
	        }
	        
	        if (!hasItems) {
	            JLabel lblNoItems = new JLabel("No pending tickets");
	            lblNoItems.setFont(new Font("Tahoma", Font.ITALIC, 12));
	            lblNoItems.setForeground(Color.GRAY);
	            containerPanel.add(lblNoItems);
	        }
	        
	    } catch (Exception ex) {
	        System.err.println("Error loading pending items: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    
	    // Refresh the panel
	    containerPanel.revalidate();
	    containerPanel.repaint();
	}

	private void loadPendingFoundItems(JPanel containerPanel, String username) {
	    // Clear existing items
	    containerPanel.removeAll();
	    
	    try (Connection conn = SQLiteConnection.connect()) {
	        String sql = "SELECT id, item_name, description, date_found, status FROM found_items " +
	                     "WHERE reported_by = ? AND status = 'Pending' ORDER BY date_reported DESC";
	        PreparedStatement pstmt = conn.prepareStatement(sql);
	        pstmt.setString(1, username);
	        
	        java.sql.ResultSet rs = pstmt.executeQuery();
	        
	        boolean hasItems = false;
	        while (rs.next()) {
	            hasItems = true;
	            int ticketId = rs.getInt("id");
	            String itemName = rs.getString("item_name");
	            String description = rs.getString("description");
	            String dateFound = rs.getString("date_found");
	            String status = rs.getString("status");
	            
	            // Create a panel for each ticket
	            JPanel ticketPanel = new JPanel();
	            ticketPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
	            ticketPanel.setPreferredSize(new Dimension(scrollPaneforFound.getWidth(), 30));
	            ticketPanel.setBackground(Color.WHITE);
	            ticketPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));
	            
	            // Ticket ID
	            JLabel lblTicketId = new JLabel("#" + ticketId);
	            lblTicketId.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblTicketId.setBounds(10, 0, 50, 28);
	            ticketPanel.add(lblTicketId);
	            
	            // Type
	            JLabel lblType = new JLabel("Found");
	            lblType.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblType.setBounds(70, 0, 40, 28);
	            ticketPanel.add(lblType);
	            
	            // Description (truncated)
	            String shortDesc = description.length() > 15 ? description.substring(0, 15) + "..." : description;
	            JLabel lblDesc = new JLabel(shortDesc);
	            lblDesc.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblDesc.setBounds(120, 0, 250, 28);
	            lblDesc.setToolTipText(description);
	            ticketPanel.add(lblDesc);
	            
	            // Date
	            JLabel lblDate = new JLabel(dateFound);
	            lblDate.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            lblDate.setBounds(130, 0, 60, 28);
	            ticketPanel.add(lblDate);
	            
	            // Status
	            JLabel lblStatus = new JLabel(status);
	            lblStatus.setFont(new Font("Tahoma", Font.BOLD, 11));
	            lblStatus.setForeground(new Color(255, 165, 0)); // Orange
	            lblStatus.setBounds(70, 0, 60, 28);
	            ticketPanel.add(lblStatus);
	            
	            containerPanel.add(ticketPanel);
	        }
	        
	        if (!hasItems) {
	            JLabel lblNoItems = new JLabel("No pending tickets");
	            lblNoItems.setFont(new Font("Tahoma", Font.ITALIC, 12));
	            lblNoItems.setForeground(Color.GRAY);
	            containerPanel.add(lblNoItems);
	        }
	        
	    } catch (Exception ex) {
	        System.err.println("Error loading pending found items: " + ex.getMessage());
	        ex.printStackTrace();
	    }
	    
	    containerPanel.revalidate();
	    containerPanel.repaint();
	}    
	    }