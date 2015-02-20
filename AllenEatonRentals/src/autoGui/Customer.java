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


public class Customer extends JFrame {

	private JPanel contentPane;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 742, 489);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	
		contentPane.setLayout(null);
		
		JLabel lblEnterAPick = DefaultComponentFactory.getInstance().createLabel("Enter a pick up date");
		lblEnterAPick.setBounds(12, 13, 121, 36);
		contentPane.add(lblEnterAPick);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(145, 13, 257, 184);
		contentPane.add(calendar);
		
		JLabel lblChooseVehicleType = DefaultComponentFactory.getInstance().createLabel("Choose vehicle type");
		lblChooseVehicleType.setBounds(12, 204, 121, 16);
		contentPane.add(lblChooseVehicleType);
		
		JComboBox comboBoxVehicleType = new JComboBox();
		comboBoxVehicleType.setModel(new DefaultComboBoxModel(new String[] {"Small", "Medium", "Large"}));
		comboBoxVehicleType.setBounds(155, 201, 102, 27);
		contentPane.add(comboBoxVehicleType);
		
		JButton btnViewPastOrders = new JButton("View past orders");
		btnViewPastOrders.setBounds(12, 272, 245, 27);
		contentPane.add(btnViewPastOrders);
		
		JButton btnModifyExistingReservation = new JButton("Modify existing reservation");
		btnModifyExistingReservation.setBounds(12, 312, 245, 27);
		contentPane.add(btnModifyExistingReservation);
		
		JLabel lblLogIn = DefaultComponentFactory.getInstance().createLabel("Log in: ");
		lblLogIn.setBounds(440, 23, 62, 16);
		contentPane.add(lblLogIn);
		
		textField = new JTextField();
		textField.setBounds(520, 20, 116, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel("Password:");
		lblPassword.setBounds(440, 52, 68, 16);
		contentPane.add(lblPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(520, 49, 104, 22);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBounds(520, 77, 97, 25);
		contentPane.add(btnNewButton);
		
		JLabel lblNewUser = DefaultComponentFactory.getInstance().createLabel("New user?");
		lblNewUser.setBounds(440, 81, 68, 16);
		contentPane.add(lblNewUser);
	}
}
