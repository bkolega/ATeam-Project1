package autoGui;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTable;
import javax.swing.JEditorPane;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JCalendar;
import javax.swing.JTree;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Panel;
import java.awt.Canvas;
import java.awt.CardLayout;
import javax.swing.JList;


public class Customer extends JFrame {

	//private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField txtResults;

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
	//	contentPane = new JPanel();
	//	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		//setContentPane(contentPane); not sure what this does.
	//	contentPane.setLayout(null);
		
		JPanel CustomerHomePage = new JPanel();
		getContentPane().add(CustomerHomePage, "name_39837333787986");
		CustomerHomePage.setLayout(null);
		
		JPanel SearchResults = new JPanel();
		getContentPane().add(SearchResults, "name_39839104720486");
		SearchResults.setLayout(null);
		
		txtResults = new JTextField();
		txtResults.setText("These are your search results:");
		txtResults.setBounds(12, 13, 225, 22);
		SearchResults.add(txtResults);
		txtResults.setColumns(10);
		
		
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
		btnViewPastOrders.setBounds(12, 271, 245, 27);
		CustomerHomePage.add(btnViewPastOrders);
		
		JButton btnModifyExistingReservation = new JButton("Modify existing reservation");
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
		btnNewButton.setBounds(520, 77, 97, 25);
		CustomerHomePage.add(btnNewButton);
		
		JLabel lblNewUser = DefaultComponentFactory.getInstance().createLabel("New user?");
		lblNewUser.setBounds(440, 81, 68, 16);
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
				SearchResults.setVisible(true);
				
			}
		});
		btnSubmit.setBounds(305, 208, 97, 25);
		CustomerHomePage.add(btnSubmit);
		
		
		
		
		JPanel panel_2 = new JPanel();
		getContentPane().add(panel_2, "name_39842606014142");
		panel_2.setLayout(null);
		
		
		
	}
}
