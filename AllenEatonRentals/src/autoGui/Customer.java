package autoGui;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JCalendar;


public class Customer extends JFrame {

	//private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer frame = new Customer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Customer() {
		getContentPane().setLayout(new CardLayout(0, 0));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 734, 452);
		
		JPanel CustomerHomePage = new JPanel();
		getContentPane().add(CustomerHomePage, "name_39837333787986");
		CustomerHomePage.setLayout(null);
		
		JPanel SearchResultsPage = new JPanel();
		getContentPane().add(SearchResultsPage, "name_39839104720486");
		SearchResultsPage.setLayout(null);
		
		JPanel PastOrdersPage = new JPanel();
		getContentPane().add(PastOrdersPage, "name_39842606014142");
		PastOrdersPage.setLayout(null);
		
		JPanel ModifyExistingOrdersPage = new JPanel();
		getContentPane().add(ModifyExistingOrdersPage, "name_13517395309252");
		ModifyExistingOrdersPage.setLayout(null);
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
				TabbedPane Login = new TabbedPane();
				Login.setVisible(true);
			}
		});
		btnExit_1.setBounds(607, 369, 97, 25);
		SearchResultsPage.add(btnExit_1);
		
		JLabel lblTheseAreYour_1 = DefaultComponentFactory.getInstance().createLabel("These are your search results: ");
		lblTheseAreYour_1.setBounds(12, 13, 189, 16);
		SearchResultsPage.add(lblTheseAreYour_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchResultsPage.setVisible(false);
				CustomerHomePage.setVisible(true);
			}
		});
		btnBack.setBounds(12, 369, 97, 25);
		SearchResultsPage.add(btnBack);
		
		JLabel lblEnterAPick = DefaultComponentFactory.getInstance().createLabel("Enter a pick up date");
		lblEnterAPick.setBounds(12, 13, 121, 36);
		CustomerHomePage.add(lblEnterAPick);
		lblEnterAPick.setVisible(true);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(145, 13, 257, 184);
		CustomerHomePage.add(calendar);
		
		JLabel lblChooseVehicleType = DefaultComponentFactory.getInstance().createLabel("Choose vehicle type");
		lblChooseVehicleType.setBounds(12, 212, 121, 16);
		CustomerHomePage.add(lblChooseVehicleType);
		
		JComboBox comboBoxVehicleType = new JComboBox();
		comboBoxVehicleType.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium", "Large"}));
		comboBoxVehicleType.setBounds(155, 207, 102, 27);
		CustomerHomePage.add(comboBoxVehicleType);
		
		JButton btnViewPastOrders = new JButton("View past orders");
		btnViewPastOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CustomerHomePage.setVisible(false);
				PastOrdersPage.setVisible(true);
			}
		});
		btnViewPastOrders.setBounds(12, 271, 245, 27);
		CustomerHomePage.add(btnViewPastOrders);
		
		JButton btnModifyExistingReservation = new JButton("Modify existing reservation");
		btnModifyExistingReservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				ModifyExistingOrdersPage.setVisible(true);
			}
		});
		btnModifyExistingReservation.setBounds(12, 311, 245, 27);
		CustomerHomePage.add(btnModifyExistingReservation);
		
		JLabel lblLogIn = DefaultComponentFactory.getInstance().createLabel("Log in: ");
		lblLogIn.setBounds(440, 23, 62, 16);
		CustomerHomePage.add(lblLogIn);
		
		textField = new JTextField();
		textField.setBounds(520, 20, 116, 22);
		CustomerHomePage.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblPassword.setBounds(440, 52, 68, 16);
		CustomerHomePage.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(520, 49, 104, 22);
		CustomerHomePage.add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				TabbedPane Login = new TabbedPane();
				Login.setVisible(true);
			}
		});
		btnNewButton.setBounds(520, 111, 97, 25);
		CustomerHomePage.add(btnNewButton);
		
		JLabel lblNewUser = DefaultComponentFactory.getInstance().createLabel("New user?");
		lblNewUser.setBounds(440, 115, 68, 16);
		CustomerHomePage.add(lblNewUser);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
				TabbedPane Login = new TabbedPane();
				Login.setVisible(true);
			}
		});
		btnExit.setBounds(607, 312, 97, 25);
		CustomerHomePage.add(btnExit);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			
				CustomerHomePage.setVisible(false);
				SearchResultsPage.setVisible(true);
				
			}
		});
		btnSubmit.setBounds(305, 208, 97, 25);
		CustomerHomePage.add(btnSubmit);
		
		JButton btnNewButton_1 = new JButton("Log in");
		btnNewButton_1.setBounds(520, 78, 97, 25);
		CustomerHomePage.add(btnNewButton_1);
		
		JButton btnExit_2 = new JButton("Exit");
		btnExit_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				TabbedPane Login = new TabbedPane();
				Login.setVisible(true);
			}
		});
		btnExit_2.setBounds(607, 369, 97, 25);
		PastOrdersPage.add(btnExit_2);
		
		JLabel lblTheseAreYour = DefaultComponentFactory.getInstance().createLabel("These are your past orders: ");
		lblTheseAreYour.setBounds(12, 13, 178, 16);
		PastOrdersPage.add(lblTheseAreYour);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PastOrdersPage.setVisible(false);
				CustomerHomePage.setVisible(true);
			}
		});
		btnBack_1.setBounds(12, 369, 97, 25);
		PastOrdersPage.add(btnBack_1);
		
		
		
		JButton btnExit_3 = new JButton("Exit");
		btnExit_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				TabbedPane Login = new TabbedPane();
				Login.setVisible(true);
			}
		});
		btnExit_3.setBounds(607, 369, 97, 25);
		ModifyExistingOrdersPage.add(btnExit_3);
		
		JLabel lblYouCanModify = DefaultComponentFactory.getInstance().createLabel("You can modify existing orders here:");
		lblYouCanModify.setBounds(12, 13, 230, 16);
		ModifyExistingOrdersPage.add(lblYouCanModify);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyExistingOrdersPage.setVisible(false);
				CustomerHomePage.setVisible(true);
			}
		});
		btnBack_2.setBounds(12, 369, 97, 25);
		ModifyExistingOrdersPage.add(btnBack_2);

	}
}
