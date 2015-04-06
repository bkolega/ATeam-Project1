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

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.SwingConstants;

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
	private JLabel lblInvalidRange = new JLabel("Please select a valid range of dates");
	private long dayReserved =0;
	private long dayReturned =0;
	private long minuteReserved =0;
	private long minuteReturned =0;
	private long totalDaysReserved =0;
	private long totalMinutesReserved =0;
	private JLabel lblCartInfo = new JLabel("Cart :        " + totalDaysReserved + " Days Reserved");
	private RegisterPagePanel RegisterPage;
	private String carType = "";

	JSONObject jsonObject;

	private final static String RES_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_reservations.php";
	private final static String USER_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_customers.php";
	private final static String LOGIN_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/login.php";
	
	public void login(String userEmail, String pass) {
		// XXX: This works with employee users right now, so it will need to be changed.
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
		
		RegisterPage = new RegisterPagePanel(this);
		//RegisterPage.getPanel().add(lblCartInfo);
		
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
				CustomerHomePage.add(lblCartInfo);
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
				RegisterPage.getPanel().setVisible(true);
				RegisterPage.getPanel().add(lblCartInfo);
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
			}
		});
		btnExit_1.setBounds(909, 525, 97, 25);
		SearchResultsPage.add(btnExit_1);
		
		JLabel lblTheseAreYour_1 = DefaultComponentFactory.getInstance().createLabel("These are your search results: ");
		lblTheseAreYour_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTheseAreYour_1.setBounds(12, 13, 287, 33);
		SearchResultsPage.add(lblTheseAreYour_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SearchResultsPage.setVisible(false);
				CustomerHomePage.setVisible(true);
				CustomerHomePage.add(lblCartInfo);
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
		comboBoxVehicleType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				carType = (String) comboBoxVehicleType.getSelectedItem();
				updateCart();
			}
		});

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
				ModifyExistingOrdersPage.add(lblCartInfo);
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
				if(totalDaysReserved > 0)
				{
					CustomerHomePage.setVisible(false);
					LoginWithSearchResults.setVisible(true);
					LoginWithSearchResults.add(lblCartInfo);
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
				
				carType = (String) comboBoxVehicleType.getSelectedItem();
				
				if(totalDaysReserved > 0)
				{
					lblInvalidRange.setVisible(false);
					CustomerHomePage.setVisible(false);
					SearchResultsPage.setVisible(true);
					SearchResultsPage.add(lblCartInfo);
	
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
						totalDaysReserved = (dayReturned - dayReserved);
						//System.out.println(totalDaysReserved);
						if (totalDaysReserved > 0)
						{
							updateCart();
						}
						
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
						totalDaysReserved = (dayReturned - dayReserved);
						System.out.println(totalDaysReserved);
						if (totalDaysReserved > 0)
						{
							updateCart();
						}
						
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
						minuteReturned = (date.getTime()/(1000*60));
						totalMinutesReserved = minuteReturned - minuteReserved;
						
						if(minuteReturned - minuteReserved > 0)
						{
							totalMinutesReserved = minuteReturned - minuteReserved;
							updateCart();
						}
						else
						{
							totalMinutesReserved = 0;
							updateCart();
						}
						System.out.println(minuteReturned - minuteReserved);
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
						minuteReserved = (date.getTime()/(1000*60));
						
						if(minuteReturned - minuteReserved > 0)
						{
							totalMinutesReserved = minuteReturned - minuteReserved;
							updateCart();
						}
						else
						{
							totalMinutesReserved = 0;
							updateCart();
						}
						System.out.println(minuteReturned - minuteReserved);
						
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
		
		lblCartInfo.setVerticalAlignment(SwingConstants.TOP);
		lblCartInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCartInfo.setBounds(752, 21, 205, 60);
		CustomerHomePage.add(lblCartInfo);
		//SearchResultsPage.add(lblCartInfo);
		
		//LoginWithSearchResults.add(lblCartInfo);
		
		JButton registerButton = new JButton("Register");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				RegisterPage.getPanel().setVisible(true);
				RegisterPage.getPanel().add(lblCartInfo);
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
				SearchResultsPage.add(lblCartInfo);
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
		lblYouCanModify.setBounds(12, 13, 394, 25);
		ModifyExistingOrdersPage.add(lblYouCanModify);
		
		JButton btnBack_2 = new JButton("Back");
		btnBack_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModifyExistingOrdersPage.setVisible(false);
				CustomerHomePage.setVisible(true);
				CustomerHomePage.add(lblCartInfo);
			}
		});
		btnBack_2.setBounds(12, 525, 97, 25);
		ModifyExistingOrdersPage.add(btnBack_2);
		
		
		
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////  Past Orders Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
			
		JButton btnExit_2 = new JButton("Exit");
		btnExit_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnExit_2.setBounds(909, 525, 97, 25);
		PastOrdersPage.add(btnExit_2);
		
		JLabel lblTheseAreYour = DefaultComponentFactory.getInstance().createLabel("These are your past orders: ");
		lblTheseAreYour.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblTheseAreYour.setBounds(12, 13, 297, 25);
		PastOrdersPage.add(lblTheseAreYour);
		
		JButton btnBack_1 = new JButton("Back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PastOrdersPage.setVisible(false);
				CustomerHomePage.setVisible(true);
				CustomerHomePage.add(lblCartInfo);
			}
		});
		btnBack_1.setBounds(12, 525, 97, 25);
		PastOrdersPage.add(btnBack_1);
		
		JList list = new JList();
		list.setBounds(22, 50, 460, 431);
		list.setModel(listModel);
		PastOrdersPage.add(list);
		


	}
	private void updateCart()
	{
		if(totalMinutesReserved > 0 || totalDaysReserved > 0)
		{
			lblCartInfo.setText("<html>" +"Cart :        " + totalDaysReserved + " Days Reserved"+ "<br>" + " + " + totalMinutesReserved 
					+ " Minutes " + "<br>" + "Car: " +  carType + "</html>" );
		}
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
}
