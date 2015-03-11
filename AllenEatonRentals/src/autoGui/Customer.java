package autoGui;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;








import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import aerentals.database.DatabaseRowSet;
import aerentals.database.SqlDatabaseProvider;
import aerentals.database.SqlDatabaseRowSet;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import com.toedter.calendar.JCalendar;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

import java.awt.Scrollbar;

import javax.swing.JSeparator;


public class Customer extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private SqlDatabaseProvider databaseProvider;
	private DefaultListModel<String> listModel = new DefaultListModel();

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
		setBounds(100, 100, 1087, 654);
		getContentPane().setLayout(new CardLayout(0, 0));

		databaseProvider = new SqlDatabaseProvider("jdbc:mysql://localhost/car_rentals", "test", "test1234");
		
		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////   PANELS   ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JPanel CustomerHomePage = new JPanel();
		CustomerHomePage.setForeground(Color.BLACK);
		getContentPane().add(CustomerHomePage, "name_52494973958178");
		CustomerHomePage.setLayout(null);
		
		JPanel SearchResultsPage = new JPanel();
		getContentPane().add(SearchResultsPage, "name_52494991163566");
		SearchResultsPage.setLayout(null);
		
		JPanel LoginWithSearchResults = new JPanel();
		getContentPane().add(LoginWithSearchResults, "name_52495009040667");
		LoginWithSearchResults.setLayout(null);
		
		JPanel PastOrdersPage = new JPanel();
		getContentPane().add(PastOrdersPage, "name_52495023942527");
		PastOrdersPage.setLayout(null);
		
		JPanel AdditionalOptionsPage = new JPanel();
		getContentPane().add(AdditionalOptionsPage, "name_52495039405259");
		AdditionalOptionsPage.setLayout(null);
		
		JPanel ModifyExistingOrdersPage = new JPanel();
		getContentPane().add(ModifyExistingOrdersPage, "name_52495053616420");
		ModifyExistingOrdersPage.setLayout(null);
		
		JPanel ReviewAndSubmit = new JPanel();
		getContentPane().add(ReviewAndSubmit, "name_52495068223220");
		ReviewAndSubmit.setLayout(null);
		
		////////////////////////////////////////////////////////////////////////////////
		////////////////////////   Login With Search Results Page  /////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(357, 195, 68, 42);
		LoginWithSearchResults.add(lblLogin);
		
		textField = new JTextField();
		textField.setBounds(473, 206, 142, 22);
		LoginWithSearchResults.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(357, 261, 100, 42);
		LoginWithSearchResults.add(lblPassword);
		
		JLabel lblInvalid = new JLabel("Invalid Login/Password Combination");
		lblInvalid.setForeground(Color.RED);
		lblInvalid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInvalid.setBounds(357, 316, 270, 25);
		LoginWithSearchResults.add(lblInvalid);		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(textField.getText().equals("Jason"))
				{
					LoginWithSearchResults.setVisible(false);
					SearchResultsPage.setVisible(true);
				}
				else
				{
					lblInvalid.setVisible(true);
				}
			}
		});
		btnNewButton.setBounds(357, 354, 258, 42);
		LoginWithSearchResults.add(btnNewButton);
		
		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginWithSearchResults.setVisible(false);
				CustomerHomePage.setVisible(true);
			}
		});
		btnBack_3.setBounds(12, 571, 97, 25);
		LoginWithSearchResults.add(btnBack_3);
		
		JButton btnExit_4 = new JButton("Exit");
		btnExit_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				//TabbedPane Login = new TabbedPane();
				//Login.setVisible(true);
			}
		});
		btnExit_4.setBounds(960, 571, 97, 25);
		LoginWithSearchResults.add(btnExit_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(473, 273, 142, 22);
		LoginWithSearchResults.add(passwordField);
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		////////////////////////  Search Results Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JButton btnExit_1 = new JButton("Exit");
		btnExit_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
				//TabbedPane Login = new TabbedPane();
				//Login.setVisible(true);
			}
		});
		btnExit_1.setBounds(960, 571, 97, 25);
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
		btnBack.setBounds(12, 571, 97, 25);
		SearchResultsPage.add(btnBack);
		
		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////  Customer Home Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblEnterAPick = DefaultComponentFactory.getInstance().createLabel("Enter a pick up date:");
		lblEnterAPick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterAPick.setBounds(12, 13, 179, 36);
		CustomerHomePage.add(lblEnterAPick);
		lblEnterAPick.setVisible(true);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(203, 13, 461, 255);
		CustomerHomePage.add(calendar);
		
		JLabel lblChooseVehicleType = DefaultComponentFactory.getInstance().createLabel("Choose vehicle class:");
		lblChooseVehicleType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseVehicleType.setBounds(12, 302, 144, 16);
		CustomerHomePage.add(lblChooseVehicleType);
		
		JComboBox comboBoxVehicleType = new JComboBox();
		comboBoxVehicleType.setModel(new DefaultComboBoxModel(new String[] {"Any class", "Economy", "Compact", "Standard", "Premium", "Small SUV", "Standard SUV", "Minivan"}));
		comboBoxVehicleType.setBounds(200, 298, 121, 27);
		CustomerHomePage.add(comboBoxVehicleType);
		
		JButton btnViewPastOrders = new JButton("View past orders");
		btnViewPastOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				listModel.clear();
				DatabaseRowSet rowSet = databaseProvider.getRows("`ALLEN_EATON_AUTO.RESERVATION`");
				while (rowSet.next()) {
					System.out.println(rowSet.getField("reservation_start_date").toString());
					listModel.addElement(rowSet.getField("reservation_start_date").toString());
				}
				CustomerHomePage.setVisible(false);
				PastOrdersPage.setVisible(true);
			}
		});
		btnViewPastOrders.setBounds(12, 517, 245, 27);
		CustomerHomePage.add(btnViewPastOrders);
		
		JButton btnModifyExistingReservation = new JButton("Modify existing reservation");
		btnModifyExistingReservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				ModifyExistingOrdersPage.setVisible(true);
			}
		});
		btnModifyExistingReservation.setBounds(12, 557, 245, 27);
		CustomerHomePage.add(btnModifyExistingReservation);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
				//TabbedPane Login = new TabbedPane();
				//Login.setVisible(true);
			}
		});
		btnExit.setBounds(960, 558, 97, 25);
		CustomerHomePage.add(btnExit);
		
		JButton btnSubmit = new JButton("Reserve as member");
		btnSubmit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				LoginWithSearchResults.setVisible(true);	
				lblInvalid.setVisible(false);
			}
		});
		btnSubmit.setBounds(203, 430, 208, 36);
		CustomerHomePage.add(btnSubmit);
		
		JLabel lblChooseRentalLocation = new JLabel("Choose rental location:");
		lblChooseRentalLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseRentalLocation.setBounds(12, 340, 144, 16);
		CustomerHomePage.add(lblChooseRentalLocation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Atchison, KS", "Belton, MO", "Emporia, KS", "Hiawatha, KS", "Kansas City, Mo", "Lawrence, KS", "Leavenworth, KS", "Manhattan, KS", "Platte City, Mo", "St Joseph, MO", "Topeka, KS", "Warrensburg, MO"}));
		comboBox.setBounds(200, 338, 121, 24);
		CustomerHomePage.add(comboBox);
		
		JButton btnReserveAsGuest = new JButton("Reserve as guest");
		btnReserveAsGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CustomerHomePage.setVisible(false);
				SearchResultsPage.setVisible(true);
				if(comboBoxVehicleType.getSelectedItem().equals("Economy"))
				{
				//	try {
						
						JLabel picLabel = new JLabel(new ImageIcon (Toolkit.getDefaultToolkit().getImage("AllenEatonRentals/images/derpamine.jpg")));
						picLabel.setBounds(10, 25, 300, 400);
						SearchResultsPage.add(picLabel);
						picLabel.setVisible(true);
						
				//	} catch (IOException e) {
				//		e.printStackTrace();
				//	}
				}
			}
		});
		btnReserveAsGuest.setBounds(461, 430, 203, 36);
		CustomerHomePage.add(btnReserveAsGuest);
		
		JLabel lblSelectAnAge = new JLabel("Select an age range:");
		lblSelectAnAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectAnAge.setBounds(355, 298, 144, 27);
		CustomerHomePage.add(lblSelectAnAge);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"25+", "23-24", "20-22", "18-19", ""}));
		comboBox_1.setBounds(543, 304, 121, 21);
		CustomerHomePage.add(comboBox_1);
		
		JCheckBox chckbxReturnCarTo = new JCheckBox("Return car to same location");
		chckbxReturnCarTo.setSelected(true);
		chckbxReturnCarTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxReturnCarTo.setBounds(355, 336, 208, 25);
		CustomerHomePage.add(chckbxReturnCarTo);
		
		JLabel lblCart_2 = new JLabel("Cart:");
		lblCart_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCart_2.setBounds(794, 24, 56, 16);
		CustomerHomePage.add(lblCart_2);
		
		////////////////////////////////////////////////////////////////////////////////
		//////////////////////  Additional Options Page   //////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		

		
		JLabel lblOptions = new JLabel("Options:");
		lblOptions.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblOptions.setBounds(12, 13, 84, 26);
		AdditionalOptionsPage.add(lblOptions);
		
		JCheckBox chckbxGpsReciever = new JCheckBox("GPS Reciever - $15/day");
		chckbxGpsReciever.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxGpsReciever.setBounds(40, 72, 188, 25);
		AdditionalOptionsPage.add(chckbxGpsReciever);
		
		JCheckBox chckbxChildSeat = new JCheckBox("Child Seat - $10/day");
		chckbxChildSeat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChildSeat.setBounds(40, 342, 171, 25);
		AdditionalOptionsPage.add(chckbxChildSeat);
		
		JCheckBox chckbxKtagRental = new JCheckBox("K-TAG Rental - $2/day (plus accumulated tolls)");
		chckbxKtagRental.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxKtagRental.setBounds(40, 127, 320, 25);
		AdditionalOptionsPage.add(chckbxKtagRental);
		
		JCheckBox chckbxRoadsideAssistance = new JCheckBox("Roadside Assistance - $7/day");
		chckbxRoadsideAssistance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxRoadsideAssistance.setBounds(40, 180, 304, 25);
		AdditionalOptionsPage.add(chckbxRoadsideAssistance);
		
		JCheckBox chckbxLossDamageWaiver = new JCheckBox("Loss Damage Waiver Insurance - $25/day");
		chckbxLossDamageWaiver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxLossDamageWaiver.setBounds(40, 236, 304, 25);
		AdditionalOptionsPage.add(chckbxLossDamageWaiver);
		
		JCheckBox chckbxPersonalAccidentInsurance = new JCheckBox("Personal Accident Insurance - $5/day");
		chckbxPersonalAccidentInsurance.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPersonalAccidentInsurance.setBounds(40, 288, 304, 25);
		AdditionalOptionsPage.add(chckbxPersonalAccidentInsurance);
		
		JButton btnBack_4 = new JButton("Back");
		btnBack_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				AdditionalOptionsPage.setVisible(false);
				SearchResultsPage.setVisible(true);
			}
		});
		btnBack_4.setBounds(12, 571, 97, 25);
		AdditionalOptionsPage.add(btnBack_4);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"}));
		comboBox_2.setBounds(332, 343, 108, 22);
		AdditionalOptionsPage.add(comboBox_2);
		
		JLabel lblQuantity = new JLabel("Quantity:");
		lblQuantity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblQuantity.setBounds(236, 346, 84, 16);
		AdditionalOptionsPage.add(lblQuantity);
		
		JLabel lblCart = new JLabel("Cart:");
		lblCart.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCart.setBounds(666, 19, 171, 26);
		AdditionalOptionsPage.add(lblCart);
		
		JButton btnExit_5 = new JButton("Exit");
		btnExit_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnExit_5.setBounds(960, 571, 97, 25);
		AdditionalOptionsPage.add(btnExit_5);
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////  Modify Orders Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JButton btnExit_3 = new JButton("Exit");
		btnExit_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				//TabbedPane Login = new TabbedPane();
				//Login.setVisible(true);
			}
		});
		btnExit_3.setBounds(960, 571, 97, 25);
		ModifyExistingOrdersPage.add(btnExit_3);
		
		JLabel lblYouCanModify = DefaultComponentFactory.getInstance().createLabel("You can modify existing orders here:");
		lblYouCanModify.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblYouCanModify.setBounds(12, 13, 295, 25);
		ModifyExistingOrdersPage.add(lblYouCanModify);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyExistingOrdersPage.setVisible(false);
				CustomerHomePage.setVisible(true);
			}
		});
		btnBack_2.setBounds(12, 571, 97, 25);
		ModifyExistingOrdersPage.add(btnBack_2);
		
		JLabel lblCart_1 = new JLabel("Cart:");
		lblCart_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCart_1.setBounds(678, 18, 76, 20);
		ModifyExistingOrdersPage.add(lblCart_1);
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////  Past Orders Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
			
		JButton btnExit_2 = new JButton("Exit");
		btnExit_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
				//TabbedPane Login = new TabbedPane();
				//Login.setVisible(true);
			}
		});
		btnExit_2.setBounds(960, 571, 97, 25);
		PastOrdersPage.add(btnExit_2);
		
		JLabel lblTheseAreYour = DefaultComponentFactory.getInstance().createLabel("These are your past orders: ");
		lblTheseAreYour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTheseAreYour.setBounds(12, 13, 201, 25);
		PastOrdersPage.add(lblTheseAreYour);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PastOrdersPage.setVisible(false);
				CustomerHomePage.setVisible(true);
			}
		});
		btnBack_1.setBounds(12, 571, 97, 25);
		PastOrdersPage.add(btnBack_1);
		
		JList list = new JList();
		list.setBounds(22, 50, 460, 431);
		list.setModel(listModel);
		PastOrdersPage.add(list);
		
		
		
		

	}
}
