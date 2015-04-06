package autoGui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegisterPagePanel {
	private JTextField textField_2;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JPasswordField passwordField_1;
	private JTextField textField_5;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JPanel RegisterPage = new JPanel();
	
	RegisterPagePanel(JFrame parent) {
		parent.getContentPane().add(RegisterPage, "name_22846752421143");
		RegisterPage.setLayout(null);
		
		RegisterPage.setBounds(100, 100, 1036, 608);
		
		textField_2 = new JTextField();
		textField_2.setBounds(584, 87, 116, 22);
		RegisterPage.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email*");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(506, 89, 56, 16);
		RegisterPage.add(lblEmail);
		
		JLabel lblNewLabel = new JLabel("Password*");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(506, 132, 71, 16);
		RegisterPage.add(lblNewLabel);
		
		JLabel lblFirstName = new JLabel("First Name*");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblFirstName.setBounds(141, 87, 88, 16);
		RegisterPage.add(lblFirstName);
		
		textField_6 = new JTextField();
		textField_6.setBounds(241, 85, 116, 22);
		RegisterPage.add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Middle Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(141, 132, 88, 16);
		RegisterPage.add(lblNewLabel_1);
		
		textField_7 = new JTextField();
		textField_7.setBounds(241, 129, 116, 22);
		RegisterPage.add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(141, 174, 88, 16);
		RegisterPage.add(lblNewLabel_2);
		
		textField_8 = new JTextField();
		textField_8.setBounds(241, 172, 116, 22);
		RegisterPage.add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblPersonalInformation = new JLabel("Personal Information");
		lblPersonalInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPersonalInformation.setBounds(86, 33, 159, 27);
		RegisterPage.add(lblPersonalInformation);
		
		JLabel lblContactInformation = new JLabel("Contact Information");
		lblContactInformation.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblContactInformation.setBounds(459, 33, 175, 27);
		RegisterPage.add(lblContactInformation);
		
		JLabel lblPhoneNumber = new JLabel("Phone*");
		lblPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhoneNumber.setBounds(506, 175, 71, 16);
		RegisterPage.add(lblPhoneNumber);
		
		textField_9 = new JTextField();
		textField_9.setBounds(584, 173, 116, 22);
		RegisterPage.add(textField_9);
		textField_9.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(584, 130, 116, 22);
		RegisterPage.add(passwordField_1);
		
		JButton btnBack_5 = new JButton("Back");
		btnBack_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBack_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				RegisterPage.setVisible(false);
				parent.setVisible(true);
				
			}
		});
		btnBack_5.setBounds(12, 525, 97, 25);
		RegisterPage.add(btnBack_5);
		
		JButton btnExit_6 = new JButton("Exit");
		btnExit_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				parent.setVisible(false);
				parent.dispose();
			}
		});
		btnExit_6.setBounds(909, 525, 97, 25);
		RegisterPage.add(btnExit_6);
		
		JLabel lblAddress = new JLabel("Address*");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress.setBounds(141, 217, 56, 16);
		RegisterPage.add(lblAddress);
		
		textField_5 = new JTextField();
		textField_5.setBounds(241, 215, 116, 22);
		RegisterPage.add(textField_5);
		textField_5.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(241, 260, 116, 22);
		RegisterPage.add(textField_10);
		textField_10.setColumns(10);
		
		JLabel lblCity = new JLabel("City*");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity.setBounds(141, 308, 56, 16);
		RegisterPage.add(lblCity);
		
		textField_11 = new JTextField();
		textField_11.setBounds(241, 306, 116, 22);
		RegisterPage.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblAddress_1 = new JLabel("Address 2");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress_1.setBounds(141, 263, 77, 16);
		RegisterPage.add(lblAddress_1);
		
		JLabel lblZip = new JLabel("Zip*");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZip.setBounds(141, 402, 56, 16);
		RegisterPage.add(lblZip);
		
		textField_12 = new JTextField();
		textField_12.setBounds(241, 400, 116, 22);
		RegisterPage.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblState = new JLabel("State*");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState.setBounds(141, 355, 56, 16);
		RegisterPage.add(lblState);
		
		textField_13 = new JTextField();
		textField_13.setBounds(241, 353, 116, 22);
		RegisterPage.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblRequired = new JLabel("* = required");
		lblRequired.setBounds(301, 39, 88, 16);
		RegisterPage.add(lblRequired);
		
		JLabel lblLicenseInfo = new JLabel("License Information");
		lblLicenseInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLicenseInfo.setBounds(459, 257, 148, 27);
		RegisterPage.add(lblLicenseInfo);
		
		JLabel lblNumber = new JLabel("Number*");
		lblNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNumber.setBounds(506, 310, 71, 16);
		RegisterPage.add(lblNumber);
		
		textField_14 = new JTextField();
		textField_14.setBounds(599, 308, 116, 22);
		RegisterPage.add(textField_14);
		textField_14.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth*");
		lblDateOfBirth.setBounds(506, 358, 88, 16);
		RegisterPage.add(lblDateOfBirth);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		comboBox_3.setBounds(599, 355, 59, 22);
		RegisterPage.add(comboBox_3);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBox_4.setBounds(673, 355, 45, 22);
		RegisterPage.add(comboBox_4);
		
		List<Integer> years = new ArrayList<Integer>();
		for (int i = 1915; i <= 2015; ++i) {
		    years.add(i);
		}
		
		JComboBox comboBox_5 = new JComboBox(years.toArray());
		comboBox_5.setBounds(736, 355, 71, 22);
		RegisterPage.add(comboBox_5);
		
		JButton btnContinue = new JButton("Continue ->");
		btnContinue.setBounds(674, 428, 133, 34);
		RegisterPage.add(btnContinue);
		
		RegisterPage.setVisible(false);
	}
	
	JPanel getPanel() {
		return RegisterPage;
	}
}
