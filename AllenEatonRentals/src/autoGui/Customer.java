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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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
import javax.swing.JPopupMenu;

import java.awt.Component;

import net.sourceforge.jcalendarbutton.JCalendarButton;
import net.sourceforge.jcalendarbutton.JTimeButton;

import org.sourceforge.jcalendarbutton.JTimePopup;

import sun.util.calendar.BaseCalendar;
import sun.util.calendar.BaseCalendar.Date;
import net.sourceforge.jcalendarbutton.JCalendarPopup;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.text.DateFormat;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.JDatePanelImpl;

import javax.swing.JFormattedTextField.AbstractFormatter;

import org.jdatepicker.util.JDatePickerUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.JToolBar;

public class Customer extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private SqlDatabaseProvider databaseProvider;
	private DefaultListModel<String> listModel = new DefaultListModel();
	private JTextField txtDate;
	private JTextField txtDate_1;
	private JTextField txtTime;
	private JTextField txtTime_1;
	private String userEmail;
	private JLabel lblInvalid;
	private JPanel LoginWithSearchResults;
	private JPanel CustomerHomePage;
	private JButton btnReserveAsMember;
	private JButton btnReserveAsGuest;
	private JButton btnLogout;
	private boolean loggedIn = false;
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
	private JLabel lblInvalidRange = new JLabel("Please select a valid range of dates");
	private long dayReserved =0;
	private long dayReturned =0;
	private long totalReserved=0;
	private JLabel lblCartInfo = new JLabel(totalReserved + " days reserved");
	
	private JPanel RegisterPage = new JPanel();

	JSONObject jsonObject;

	private final static String RES_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_reservations.php";
	private final static String USER_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_customers.php";
	private final static String LOGIN_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/login.php";
	
	public void login(String userEmail, String pass) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", userEmail));
		params.add(new BasicNameValuePair("password", pass));
		JsonHandler handler = new JsonHandler(LOGIN_URL, params);
		System.out.println(handler.getJsonObject().toString());
		
		if (handler.getJsonObject().getInt("success") == 1) {
			loggedIn = true;
			this.userEmail = userEmail;
			lblInvalid.setVisible(false);
			LoginWithSearchResults.setVisible(false);
			CustomerHomePage.setVisible(true);
			btnLogout.setVisible(true);
			btnReserveAsGuest.setVisible(false);
			btnReserveAsMember.setVisible(false);
		} else {
			this.userEmail = null;
			loggedIn = false;
			lblInvalid.setVisible(true);
		}
//		jsonObject = new JsonReader(USER_URL).getJsonObject();
//		
//		JSONArray arr = jsonObject.getJSONArray("users");
//		
//		for (int i = 0; i < arr.length(); ++i) {
//			JSONObject obj = arr.getJSONObject(i);
//
////			listModel.addElement(obj.getString("start_date"));
//			
//			if (userEmail == obj.getString("email")) {
//				//if ()
//			}
//		}
		
		/*
		Connection conn = databaseProvider.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT user_password FROM `ALLEN_EATON_AUTO.USER` WHERE user_email=? LIMIT 1");
			stmt.setString(1, userEmail);
			stmt.executeQuery();
			ResultSet results = stmt.getResultSet();
			results.next();
			
			if (results.getString(1).equals(pass)) {
				// Successful log in
				this.userEmail = userEmail;
				loggedIn = true;
				lblInvalid.setVisible(false);
				LoginWithSearchResults.setVisible(false);
				CustomerHomePage.setVisible(true);
				btnLogout.setVisible(true);
				btnReserveAsGuest.setVisible(false);
				btnReserveAsMember.setVisible(false);
			} else {
				// Invalid log in
				lblInvalid.setVisible(true);
				loggedIn = false;
				userEmail = null;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}

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
		setBounds(100, 100, 1036, 608);
		getContentPane().setLayout(new CardLayout(0, 0));

		
		
		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////   PANELS   ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		CustomerHomePage = new JPanel();
		CustomerHomePage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		CustomerHomePage.setForeground(Color.BLACK);
		getContentPane().add(CustomerHomePage, "name_52494973958178");
		CustomerHomePage.setLayout(null);
		
		JPanel SearchResultsPage = new JPanel();
		getContentPane().add(SearchResultsPage, "name_52494991163566");
		SearchResultsPage.setLayout(null);
		
		LoginWithSearchResults = new JPanel();
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
		
		/////////////////////////////////////////////////////////////////////////////
		//////////////////////////// Register Page //////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////
		
		createRegisterPage();
		
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
		
		lblInvalid = new JLabel("Invalid Login/Password Combination");
		lblInvalid.setForeground(Color.RED);
		lblInvalid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInvalid.setBounds(357, 316, 270, 25);
		LoginWithSearchResults.add(lblInvalid);		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login(textField.getText(), passwordField.getText());
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
		btnBack_3.setBounds(12, 525, 97, 25);
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
		btnExit_4.setBounds(909, 525, 97, 25);
		LoginWithSearchResults.add(btnExit_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(473, 273, 142, 22);
		LoginWithSearchResults.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("New User? ");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoginWithSearchResults.setVisible(false);
				RegisterPage.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(357, 409, 258, 42);
		LoginWithSearchResults.add(btnNewButton_1);
		
		JLabel lblToContinueLog = new JLabel("To Continue, Log in or Create an account");
		lblToContinueLog.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblToContinueLog.setBounds(316, 70, 363, 42);
		LoginWithSearchResults.add(lblToContinueLog);
		
		
		
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
		btnExit_1.setBounds(909, 525, 97, 25);
		SearchResultsPage.add(btnExit_1);
		
		JLabel lblTheseAreYour_1 = DefaultComponentFactory.getInstance().createLabel("These are your search results: ");
		lblTheseAreYour_1.setBounds(12, 13, 189, 16);
		SearchResultsPage.add(lblTheseAreYour_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchResultsPage.setVisible(false);
				CustomerHomePage.setVisible(true);
				//make array of image labels that is removed here
			}
		});
		btnBack.setBounds(12, 525, 97, 25);
		SearchResultsPage.add(btnBack);
		
		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////  Customer Home Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		
		JLabel lblEnterAPick = DefaultComponentFactory.getInstance().createLabel("Enter a pick up date:");
		lblEnterAPick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterAPick.setBounds(77, 13, 179, 36);
		CustomerHomePage.add(lblEnterAPick);
		lblEnterAPick.setVisible(true);
		
		JLabel lblChooseVehicleType = DefaultComponentFactory.getInstance().createLabel("Choose vehicle class:");
		lblChooseVehicleType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseVehicleType.setBounds(77, 301, 144, 16);
		CustomerHomePage.add(lblChooseVehicleType);
		
		JComboBox comboBoxVehicleType = new JComboBox();
		comboBoxVehicleType.setModel(new DefaultComboBoxModel(new String[] {"Any class", "Economy", "Compact", "Standard", "Premium", "Small SUV", "Standard SUV", "Minivan"}));
		comboBoxVehicleType.setBounds(233, 297, 121, 27);
		CustomerHomePage.add(comboBoxVehicleType);
		
		JButton btnViewPastOrders = new JButton("View past orders");
		btnViewPastOrders.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				jsonObject = new JsonHandler(RES_URL).getJsonObject();
				listModel.clear();
				JSONArray arr = jsonObject.getJSONArray("reservations");
				
				for (int i = 0; i < arr.length(); ++i) {
					JSONObject obj = arr.getJSONObject(i);
					
					System.out.println(obj.toString());
					listModel.addElement(obj.getString("start_date"));
				}
				/*
				DatabaseRowSet rowSet = databaseProvider.getRows("`ALLEN_EATON_AUTO.RESERVATION`");
				while (rowSet.next()) {
					System.out.println(rowSet.getField("reservation_start_date").toString());
					listModel.addElement(rowSet.getField("reservation_start_date").toString());
				}
				*/
				CustomerHomePage.setVisible(false);
				PastOrdersPage.setVisible(true);
			}
		});
		btnViewPastOrders.setBounds(407, 487, 245, 27);
		CustomerHomePage.add(btnViewPastOrders);
		
		JButton btnModifyExistingReservation = new JButton("Modify existing reservation");
		btnModifyExistingReservation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				ModifyExistingOrdersPage.setVisible(true);
			}
		});
		btnModifyExistingReservation.setBounds(109, 487, 245, 27);
		CustomerHomePage.add(btnModifyExistingReservation);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				setVisible(false);
				dispose();
			}
		});
		btnExit.setBounds(890, 525, 97, 25);
		CustomerHomePage.add(btnExit);
		
		btnReserveAsMember = new JButton("Reserve as member");
		btnReserveAsMember.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(totalReserved > 0)
				{
					CustomerHomePage.setVisible(false);
					LoginWithSearchResults.setVisible(true);	
					lblInvalid.setVisible(false);
					lblInvalidRange.setVisible(false);
				}
				else
				{
					lblInvalidRange.setVisible(true);
				}
			}
		});
		btnReserveAsMember.setBounds(27, 389, 245, 36);
		CustomerHomePage.add(btnReserveAsMember);
		
		JLabel lblChooseRentalLocation = new JLabel("Choose rental location:");
		lblChooseRentalLocation.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseRentalLocation.setBounds(77, 340, 144, 16);
		CustomerHomePage.add(lblChooseRentalLocation);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Atchison, KS", "Belton, MO", "Emporia, KS", "Hiawatha, KS", "Kansas City, Mo", "Lawrence, KS", "Leavenworth, KS", "Manhattan, KS", "Platte City, Mo", "St Joseph, MO", "Topeka, KS", "Warrensburg, MO"}));
		comboBox.setBounds(233, 337, 121, 24);
		CustomerHomePage.add(comboBox);
		
		btnReserveAsGuest = new JButton("Reserve as guest");
		JLabel picLabel = new JLabel();
		btnReserveAsGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(totalReserved > 0)
				{
					lblInvalidRange.setVisible(false);
					CustomerHomePage.setVisible(false);
					SearchResultsPage.setVisible(true);
	
					if(comboBoxVehicleType.getSelectedItem().equals("Economy"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/economyCar.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
					if(comboBoxVehicleType.getSelectedItem().equals("Compact"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/compactCar.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
					if(comboBoxVehicleType.getSelectedItem().equals("Standard"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/standardCar.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
					if(comboBoxVehicleType.getSelectedItem().equals("Small SUV"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/smallSUV.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
					if(comboBoxVehicleType.getSelectedItem().equals("Minivan"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/minivan.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
					if(comboBoxVehicleType.getSelectedItem().equals("Standard SUV"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/standardSUV.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
					if(comboBoxVehicleType.getSelectedItem().equals("Premium"))
					{
							picLabel.setIcon(null);
							ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/premiumCar.jpg")));
							picLabel.setIcon(testImg);
							picLabel.setBounds(10, 25, 300, 400);
							SearchResultsPage.add(picLabel);
							picLabel.setVisible(true);
					}
				}
				else
				{
					lblInvalidRange.setVisible(true);
				}
			}
		});
		btnReserveAsGuest.setBounds(284, 389, 245, 36);
		CustomerHomePage.add(btnReserveAsGuest);
		
		JLabel lblSelectAnAge = new JLabel("Select an age range:");
		lblSelectAnAge.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSelectAnAge.setBounds(407, 296, 144, 27);
		CustomerHomePage.add(lblSelectAnAge);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"25+", "23-24", "20-22", "18-19", ""}));
		comboBox_1.setBounds(563, 300, 121, 21);
		CustomerHomePage.add(comboBox_1);
		
		JCheckBox chckbxReturnCarTo = new JCheckBox("Return car to same location");
		chckbxReturnCarTo.setSelected(true);
		chckbxReturnCarTo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxReturnCarTo.setBounds(407, 336, 208, 25);
		CustomerHomePage.add(chckbxReturnCarTo);
		
		JLabel lblCart_2 = new JLabel("Cart:");
		lblCart_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCart_2.setBounds(730, 23, 56, 16);
		CustomerHomePage.add(lblCart_2);
		
		JCalendarButton calendarButton = new JCalendarButton();
		calendarButton.setBounds(514, 13, 32, 30);
		CustomerHomePage.add(calendarButton);
		
		txtDate = new JTextField();
		txtDate.setBounds(236, 21, 234, 22);
		CustomerHomePage.add(txtDate);
		txtDate.setColumns(10);
		
		calendarButton.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
					if(evt.getPropertyName().equals("date"))
					{
						java.util.Date date = null;
						date = (java.util.Date) evt.getNewValue();
						calendarButton.setTargetDate(date);
						
						dayReserved = (date.getTime()/(1000*60*60*24));
						lblCartInfo.setText(totalReserved + " days reserved");
						
						if(calendarButton.getTargetDate() != null)
						{
							String s = DateFormat.getDateInstance(DateFormat.FULL, getLocale()).format(date);
							txtDate.setText(s);
						}
					}     			        
				}
			}
		);
		
		JLabel lblEnterAReturn = new JLabel("Enter a return date:");
		lblEnterAReturn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterAReturn.setBounds(77, 131, 131, 16);
		CustomerHomePage.add(lblEnterAReturn);
		
		
		
		txtDate_1 = new JTextField();
		txtDate_1.setBounds(236, 129, 234, 22);
		CustomerHomePage.add(txtDate_1);
		txtDate_1.setColumns(10);
		
		JCalendarButton calendarButton_1 = new JCalendarButton(null, null, null);

		calendarButton_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
					if(evt.getPropertyName().equals("date"))
					{
						java.util.Date date = null;
						date = (java.util.Date) evt.getNewValue();
						calendarButton_1.setTargetDate(date);
						
						dayReturned = (date.getTime()/(1000*60*60*24));
						totalReserved = (dayReturned - dayReserved);
						lblCartInfo.setText(totalReserved + " days reserved");
						
						if(calendarButton_1.getTargetDate() != null)
						{
							String s = DateFormat.getDateInstance(DateFormat.FULL, getLocale()).format(date);
							txtDate_1.setText(s);
						}
					}     			        
				}
			}
		
	);
		
		calendarButton_1.setBounds(514, 121, 32, 30);
		CustomerHomePage.add(calendarButton_1);
		
		JTimeButton timeButton_1 = new JTimeButton();
		timeButton_1.setBounds(514, 164, 32, 30);
		CustomerHomePage.add(timeButton_1);
		
		timeButton_1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//System.out.println(evt.getPropertyName());
					if(evt.getPropertyName().equals("date"))
					{
						java.util.Date date = null;
						date = (java.util.Date) evt.getNewValue();
						timeButton_1.setTargetDate(date);
						
						if(timeButton_1.getTargetDate() != null)
						{
							String s = DateFormat.getTimeInstance(DateFormat.SHORT, getLocale()).format(date);
							txtTime_1.setText(s);
						}
					}     			        
				}
			}
		);
		
		JTimeButton timeButton = new JTimeButton();
		timeButton.setBounds(514, 54, 32, 30);
		CustomerHomePage.add(timeButton);
		
		timeButton.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				//System.out.println(evt.getPropertyName());
					if(evt.getPropertyName().equals("date"))
					{
						java.util.Date date = null;
						date = (java.util.Date) evt.getNewValue();
						timeButton.setTargetDate(date);
						
						if(timeButton.getTargetDate() != null)
						{
							String s = DateFormat.getTimeInstance(DateFormat.SHORT, getLocale()).format(date);
							txtTime.setText(s);
						}
					}     			        
				}
			}
		);
		
		JLabel lblEnterATime = new JLabel("Enter a pick up time:");
		lblEnterATime.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterATime.setBounds(77, 62, 144, 16);
		CustomerHomePage.add(lblEnterATime);
		
		txtTime = new JTextField();
		txtTime.setBounds(236, 62, 234, 22);
		CustomerHomePage.add(txtTime);
		txtTime.setColumns(10);
		
		JLabel lblEnterAReturn_1 = new JLabel("Enter a return time:");
		lblEnterAReturn_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterAReturn_1.setBounds(77, 172, 144, 16);
		CustomerHomePage.add(lblEnterAReturn_1);
		
		txtTime_1 = new JTextField();
		txtTime_1.setColumns(10);
		txtTime_1.setBounds(236, 172, 234, 22);
		CustomerHomePage.add(txtTime_1);
		
		btnLogout = new JButton("Logout");
		btnLogout.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				userEmail = null;
				loggedIn = false;
				btnLogout.setVisible(false);
				btnReserveAsMember.setVisible(true);
				passwordField.setText("");
				btnReserveAsGuest.setVisible(true);
			}
		});
		btnLogout.setBounds(318, 437, 117, 29);
		btnLogout.setVisible(false);
		CustomerHomePage.add(btnLogout);
		
		
		lblInvalidRange.setForeground(Color.RED);
		lblInvalidRange.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInvalidRange.setBounds(212, 232, 299, 27);
		lblInvalidRange.setVisible(false);
		CustomerHomePage.add(lblInvalidRange);
		
		
		lblCartInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCartInfo.setBounds(782, 24, 121, 16);
		CustomerHomePage.add(lblCartInfo);
		
		JButton registerButton = new JButton("Register");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				RegisterPage.setVisible(true);
			}
		});
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		registerButton.setBounds(541, 389, 245, 36);
		CustomerHomePage.add(registerButton);
		
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
		btnBack_4.setBounds(12, 525, 97, 25);
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
		btnExit_5.setBounds(909, 525, 97, 25);
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
		btnExit_3.setBounds(909, 525, 97, 25);
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
		btnBack_2.setBounds(12, 525, 97, 25);
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
		btnExit_2.setBounds(909, 525, 97, 25);
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
		btnBack_1.setBounds(12, 525, 97, 25);
		PastOrdersPage.add(btnBack_1);
		
		JList list = new JList();
		list.setBounds(22, 50, 460, 431);
		list.setModel(listModel);
		PastOrdersPage.add(list);
		


	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	
	private void createRegisterPage() {
		getContentPane().add(RegisterPage, "name_22846752421143");
		RegisterPage.setLayout(null);
		
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
				LoginWithSearchResults.setVisible(true);
			}
		});
		btnBack_5.setBounds(12, 525, 97, 25);
		RegisterPage.add(btnBack_5);
		
		JButton btnExit_6 = new JButton("Exit");
		btnExit_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
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
	}
}
