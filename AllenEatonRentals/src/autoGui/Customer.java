package autoGui;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javafx.scene.layout.Border;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import aerentals.database.SqlDatabaseProvider;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import java.awt.Font;
import java.awt.Color;

import javax.swing.JCheckBox;
import javax.swing.JList;
import javax.swing.JPopupMenu;

import java.awt.Component;

import net.sourceforge.jcalendarbutton.JCalendarButton;
import net.sourceforge.jcalendarbutton.JTimeButton;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.SwingConstants;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JTextArea;
import javax.swing.Icon;

import java.lang.Thread;

public class Customer extends JFrame {
	private JTextField textField;
	private JPasswordField passwordField;
	private SqlDatabaseProvider databaseProvider;
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private JTextField txtDate;
	private JTextField txtDate_1;
	private JTextField txtTime;
	private JTextField txtTime_1;
	private String userEmail;
	private JLabel lblInvalid;
	private JPanel LoginPage;
	private JPanel CustomerHomePage;
	private JButton btnReserveAsMember;
	private JButton btnReserveAsGuest;
	private JButton btnLogout;
	boolean loggedIn = false;
	private JLabel lblInvalidRange = new JLabel("Please select a valid range of dates");
	private int dayReserved =0;
	private int dayReturned =0;
	private int minuteReserved =0;
	private int minuteReturned =0;
	private int totalDaysReserved =0;
	private int totalMinutesReserved =0;
	private JTextArea lblCartInfo = new JTextArea("Cart :    " + totalDaysReserved + " Days Reserved");
	private RegisterPagePanel RegisterPage;
	private String carType = "";
	private JPanel SearchResultsPage; 
	private JComboBox comboBoxVehicleType;
	private JLabel lblEstimatedCost;
	private int dailyCarCost = 0;
	private int weeklyCarCost = 0;
	private int totalWeeksReserved =0;
	private int additionalOptionsCost=0;
	private int estimatedCostRange1=0;
	private int estimatedCostRange2=0;
	private int estimatedCost=0;
	private int totalCost=0;
	private JLabel lblTotalCost = new JLabel();
	private int loginReturnPage = 0;
	private JLabel lblProcessing = new JLabel("Processing ");
	AnimatedIcon iconProcessing = new AnimatedIcon( lblProcessing );
	Date date = null;
	Date date2 = null;
	
	JSONObject jsonObject;

	private final static String RES_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_reservations.php";
	private final static String USER_URL = "http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_customers.php";
	private final static String LOGIN_URL = "http://people.eecs.ku.edu/~dyoung/CustomerPHPScripts/login.php";
	private final static String RESERVE_URL = "http://people.eecs.ku.edu/~dyoung/CustomerPHPScripts/reserve_car.php";
	private final static String CAR_ID_URL = "http://people.eecs.ku.edu/~dyoung/CustomerPHPScripts/car_id.php";
	private JLabel picLabel;
	
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
			LoginPage.setVisible(false);
			if(loginReturnPage==1) 
			{
				reserveCar();
			}
			getContentPane().getComponent(loginReturnPage).setVisible(true);
			//SearchResultsPage.setVisible(true);
			btnLogout.setVisible(true);
			btnReserveAsGuest.setVisible(false);
			lblProcessing.setVisible(false);
			iconProcessing.stop();
			
		} else {
			this.userEmail = null;
			loggedIn = false;
			lblInvalid.setVisible(true);
			lblProcessing.setVisible(false);
			iconProcessing.stop();
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
	@SuppressWarnings({ "deprecation", "rawtypes", "unchecked" })
	public Customer() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1036, 608);
		getContentPane().setLayout(new CardLayout(0, 0));
		
		javax.swing.border.Border padding = BorderFactory.createLineBorder(Color.GRAY, 1, true);
		((JComponent) getContentPane()).setBorder(padding);

		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////////   PANELS   ///////////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		CustomerHomePage = new JPanel();
		CustomerHomePage.setForeground(Color.BLACK);
		getContentPane().add(CustomerHomePage, "name_52494973958178");
		CustomerHomePage.setLayout(null);
		
		SearchResultsPage = new JPanel();
		getContentPane().add(SearchResultsPage, "name_52494991163566");
		SearchResultsPage.setLayout(null);
		
		LoginPage = new JPanel();
		getContentPane().add(LoginPage, "name_52495009040667");
		LoginPage.setLayout(null);
		
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
		
		RegisterPage = new RegisterPagePanel(this, new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CustomerHomePage.add(lblCartInfo);
			}
		});
		
		////////////////////////////////////////////////////////////////////////////////
		////////////////////////////////   Login Page  /////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		lblProcessing.setBounds(430, 475, 258, 42);
		lblProcessing.setHorizontalTextPosition( JLabel.LEADING );
		iconProcessing.setAlignmentX( AnimatedIcon.LEFT );
		iconProcessing.addIcon( new TextIcon(lblProcessing, ".") );
		iconProcessing.addIcon( new TextIcon(lblProcessing, "..") );
		iconProcessing.addIcon( new TextIcon(lblProcessing, "...") );
		lblProcessing.setIcon( iconProcessing );
		lblProcessing.setVisible(false);
		LoginPage.add(lblProcessing);
		
		JLabel lblLogin = new JLabel("Login:");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLogin.setBounds(357, 195, 68, 42);
		LoginPage.add(lblLogin);
		
		textField = new JTextField();
		textField.setBounds(473, 206, 142, 22);
		LoginPage.add(textField);
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(357, 261, 100, 42);
		LoginPage.add(lblPassword);
		
		lblInvalid = new JLabel("Invalid Login/Password Combination");
		lblInvalid.setForeground(Color.RED);
		lblInvalid.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblInvalid.setBounds(357, 316, 270, 25);
		LoginPage.add(lblInvalid);		
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			  lblProcessing.setVisible(true);
			  iconProcessing.restart();
			  iconProcessing.start();
			  SwingWorker worker = new SwingWorker() {
			     @Override
			     public Object doInBackground() {
			       login(textField.getText(), passwordField.getText());
			       return null;
			     }
			   };
			    worker.execute();
			}
		});
		btnNewButton.setBounds(357, 354, 258, 42);
		LoginPage.add(btnNewButton);
		
		JButton btnBack_3 = new JButton("Back");
		btnBack_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage.setVisible(false);
				CustomerHomePage.setVisible(true);
				CustomerHomePage.add(lblCartInfo);
			}
		});
		btnBack_3.setBounds(12, 525, 97, 25);
		LoginPage.add(btnBack_3);
		
		JButton btnExit_4 = new JButton("Exit");
		btnExit_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
			}
		});
		btnExit_4.setBounds(909, 525, 97, 25);
		LoginPage.add(btnExit_4);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(473, 273, 142, 22);
		LoginPage.add(passwordField);
		
		JButton btnNewButton_1 = new JButton("New User? ");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				LoginPage.setVisible(false);
				RegisterPage.getPanel().setVisible(true);
				RegisterPage.setLastPage(2);
			}
		});
		btnNewButton_1.setBounds(357, 409, 258, 42);
		LoginPage.add(btnNewButton_1);
		
		JLabel lblToContinueLog = new JLabel("To Continue, Log in or Create an account");
		lblToContinueLog.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblToContinueLog.setBounds(316, 70, 363, 42);
		LoginPage.add(lblToContinueLog);

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
		
		lblEstimatedCost = new JLabel();
		lblEstimatedCost.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEstimatedCost.setBounds(12, 330, 450, 33);
		SearchResultsPage.add(lblEstimatedCost);
		
		JButton btnContinue = new JButton("Continue ->");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				SearchResultsPage.setVisible(false);
				AdditionalOptionsPage.setVisible(true);
				AdditionalOptionsPage.add(lblCartInfo);
			}
		});
		btnContinue.setBounds(674, 428, 133, 34);
		SearchResultsPage.add(btnContinue);

		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////  Customer Home Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		lblCartInfo.setEditable(false);
		lblCartInfo.setBorder(padding);
		lblCartInfo.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblCartInfo.setBounds(752, 21, 215, 83);
		CustomerHomePage.add(lblCartInfo);
		
		JLabel lblEnterAPick = DefaultComponentFactory.getInstance().createLabel("Enter a pick up date:");
		lblEnterAPick.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEnterAPick.setBounds(77, 13, 179, 36);
		CustomerHomePage.add(lblEnterAPick);
		lblEnterAPick.setVisible(true);
		
		JLabel lblChooseVehicleType = DefaultComponentFactory.getInstance().createLabel("Choose vehicle class:");
		lblChooseVehicleType.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseVehicleType.setBounds(77, 301, 144, 16);
		CustomerHomePage.add(lblChooseVehicleType);
		
		comboBoxVehicleType = new JComboBox();
		comboBoxVehicleType.setModel(new DefaultComboBoxModel(new String[] {"Economy", "Compact", "Standard", "Premium", "Small SUV", "Standard SUV", "Minivan"}));
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
					if (loggedIn) {
						lblInvalid.setVisible(false);
						LoginPage.setVisible(false);
						reserveCar();
						SearchResultsPage.setVisible(true);
						btnLogout.setVisible(true);
						return;
					}
					CustomerHomePage.setVisible(false);
					LoginPage.setVisible(true);
					loginReturnPage = 1;
					LoginPage.add(lblCartInfo);
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
		picLabel = new JLabel();
		btnReserveAsGuest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				reserveCar();
				RegisterPage.setLastPage(4);
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
		comboBox_1.setBounds(563, 301, 121, 20);
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
						
						dayReserved = (int) (date.getTime()/(1000*60*60*24));
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
						
						dayReturned = (int) (date.getTime()/(1000*60*60*24));
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
						minuteReturned = (int) (date.getTime()/(1000*60));
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
						minuteReserved = (int) (date.getTime()/(1000*60));
						
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
		btnLogout.setBounds(412, 438, 117, 29);
		btnLogout.setVisible(false);
		CustomerHomePage.add(btnLogout);
		
		
		lblInvalidRange.setForeground(Color.RED);
		lblInvalidRange.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblInvalidRange.setBounds(212, 232, 299, 27);
		lblInvalidRange.setVisible(false);
		CustomerHomePage.add(lblInvalidRange);
		

		
		JButton registerButton = new JButton("Register");
		registerButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				CustomerHomePage.setVisible(false);
				RegisterPage.getPanel().setVisible(true);
				RegisterPage.setLastPage(0);
			}
		});

		registerButton.setBounds(541, 389, 245, 36);
		CustomerHomePage.add(registerButton);
		
		JButton btnNewButton_2 = new JButton("Log in");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				CustomerHomePage.setVisible(false);
				LoginPage.setVisible(true);
				lblInvalid.setVisible(false);
				loginReturnPage=0;
			}
		});
		btnNewButton_2.setBounds(284, 438, 116, 27);
		CustomerHomePage.add(btnNewButton_2);
		
		////////////////////////////////////////////////////////////////////////////////
		//////////////////////  Additional Options Page   //////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JLabel lblOptions = new JLabel("Options: ");
		lblOptions.setFont(new Font("Tahoma", Font.PLAIN, 16)); //title
		lblOptions.setBounds(12, 13, 84, 26);
		AdditionalOptionsPage.add(lblOptions);
		
		JCheckBox chckbxGpsReceiver = new JCheckBox("GPS Receiver - $15/day"); // GPS check box
		chckbxGpsReceiver.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxGpsReceiver.setBounds(40, 72, 188, 25);
		AdditionalOptionsPage.add(chckbxGpsReceiver);
		
		JCheckBox chckbxChildSeat = new JCheckBox("Child Seat - $10/day"); // child seat check box
		chckbxChildSeat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChildSeat.setBounds(40, 342, 171, 25);
		AdditionalOptionsPage.add(chckbxChildSeat);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4"})); // Number of child seats
		comboBox_2.setBounds(332, 343, 108, 22);
		AdditionalOptionsPage.add(comboBox_2);
		
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
		
		JButton btnContinue_1 = new JButton("Continue->");
		btnContinue_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				additionalOptionsCost =0;
				if(chckbxGpsReceiver.isSelected()) { additionalOptionsCost += 15; }
				if(chckbxChildSeat.isSelected()) { additionalOptionsCost += (10 * (comboBox_2.getSelectedIndex()+1)); }
				if(chckbxKtagRental.isSelected()) { additionalOptionsCost += 2; }
				if(chckbxRoadsideAssistance.isSelected()) { additionalOptionsCost += 7; }
				if(chckbxLossDamageWaiver.isSelected()) { additionalOptionsCost += 25; }
				if(chckbxPersonalAccidentInsurance.isSelected()) { additionalOptionsCost += 5; }
				additionalOptionsCost *= totalDaysReserved;
				totalCost = additionalOptionsCost + estimatedCost;
				lblTotalCost.setText("Total Cost: $" + totalCost + " plus tax");
				System.out.println("total cost: $" + totalCost + " plus tax");
				
				if (loggedIn == true || RegisterPage.isRegistered())
				{
					AdditionalOptionsPage.setVisible(false);
					ReviewAndSubmit.setVisible(true);
				}
				else
				{
					AdditionalOptionsPage.setVisible(false);
					RegisterPage.getPanel().setVisible(true);
					RegisterPage.setLastPage(6);
				}
			
			}
		});
		btnContinue_1.setBounds(674, 428, 133, 34);
		AdditionalOptionsPage.add(btnContinue_1);
		
		////////////////////////////////////////////////////////////////////////////////
		/////////////////////////  Modify Orders Page   ////////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JButton btnExit_3 = new JButton("Exit");
		btnExit_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				dispose();
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
		
		JList<String> list = new JList<String>();
		list.setBounds(22, 50, 460, 431);
		list.setModel(listModel);
		PastOrdersPage.add(list);
		
		////////////////////////////////////////////////////////////////////////////////
		///////////////////////////  Review and Submit   ///////////////////////////////
		////////////////////////////////////////////////////////////////////////////////
		
		JButton btnBack_5 = new JButton("Back");
		btnBack_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ReviewAndSubmit.setVisible(false);
				AdditionalOptionsPage.setVisible(true);
				AdditionalOptionsPage.add(lblCartInfo);
				additionalOptionsCost = 0;
			}
		});
		btnBack_5.setBounds(12, 525, 97, 25);
		ReviewAndSubmit.add(btnBack_5);
		
		JLabel lblNewLabel = new JLabel("Please Review Your Order: ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(350, 61, 202, 36);
		ReviewAndSubmit.add(lblNewLabel);
		
		lblTotalCost.setBounds(350, 454, 260, 25); // set in additional options page
		lblTotalCost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		ReviewAndSubmit.add(lblTotalCost);
		
		JTextArea receipt = new JTextArea();
		receipt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		receipt.setBorder(padding);
		receipt.setBackground(Color.WHITE);
		receipt.setBounds(350, 134, 375, 280);
		receipt.setEditable(false);
		ReviewAndSubmit.add(receipt);
		
		JButton btnSubmitOrder = new JButton("Submit Order");
		btnSubmitOrder.setBounds(600, 447, 125, 36);
		ReviewAndSubmit.add(btnSubmitOrder);
		ReviewAndSubmit.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent arg0) {
				
				receipt.setText("");
				date = calendarButton.getTargetDate();
				String start = DateFormat.getDateInstance(DateFormat.FULL, getLocale()).format(date);
				
				date2 = calendarButton_1.getTargetDate();
				String end = DateFormat.getDateInstance(DateFormat.FULL, getLocale()).format(date2);
	
				receipt.append("Car Type: " + carType + "\n\n");
				receipt.append("Reserved From: " + start + "\n                 To: " + end + "\n\n" + "Additional Options: \n\n");
				if(chckbxGpsReceiver.isSelected()) { receipt.append("        GPS Receiver\n"); }
				if(chckbxChildSeat.isSelected()) { receipt.append(("        " + (comboBox_2.getSelectedIndex()+1) + " Child Seats"+"\n")); }
				if(chckbxKtagRental.isSelected()) { receipt.append("        Ktag Rental\n"); }
				if(chckbxRoadsideAssistance.isSelected()) { receipt.append("        RoadSide Assistance\n"); }
				if(chckbxLossDamageWaiver.isSelected()) { receipt.append("        Loss Damage Waiver Insurance\n"); }
				if(chckbxPersonalAccidentInsurance.isSelected()) { receipt.append("        Personal Accident Insurance"); }
			}
		});
		
		btnSubmitOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				List<NameValuePair> carIdParams = new ArrayList<NameValuePair>();
				carIdParams.add(new BasicNameValuePair("cartype", processCarType(carType)));
				
				JsonHandler carIdHandler = new JsonHandler(CAR_ID_URL, carIdParams);
				
				String carId = new Integer(carIdHandler.getJsonObject().getJSONObject("message").getInt("car_id")).toString();
				
				String childSeats;
				if (chckbxChildSeat.isSelected()) {
					childSeats = new Integer(comboBox_2.getSelectedIndex()+1).toString();
				} else {
					childSeats = "0";
				}
				
				String startDate = formatDate(date);
				String endDate   = formatDate(date2);
				
				System.out.println(startDate);
				
				params.add(new BasicNameValuePair("username", userEmail));
				params.add(new BasicNameValuePair("carid", carId));
				params.add(new BasicNameValuePair("gps", chckbxGpsReceiver.isSelected() ? "1" : "0"));
				params.add(new BasicNameValuePair("childseat", childSeats));
				params.add(new BasicNameValuePair("ktag", chckbxKtagRental.isSelected() ? "1" : "0"));
				params.add(new BasicNameValuePair("assistance", chckbxRoadsideAssistance.isSelected() ? "1" : "0"));
				params.add(new BasicNameValuePair("dinsurance", chckbxLossDamageWaiver.isSelected() ? "1" : "0"));
				params.add(new BasicNameValuePair("ainsurance", chckbxPersonalAccidentInsurance.isSelected() ? "1" : "0"));
				
				params.add(new BasicNameValuePair("cartype", carType));
				params.add(new BasicNameValuePair("start", startDate));
				params.add(new BasicNameValuePair("end", endDate));
				params.add(new BasicNameValuePair("perweek", "1"));
				String[] splitLocation = comboBox.getSelectedItem().toString().split(", ");
				String city = splitLocation[0];
				String state = splitLocation[1];
				if (state.toUpperCase().equals("KS")) {
					state = "Kansas";
				} else if (state.toUpperCase().equals("MO")) {
					state = "Missouri";
				} else {
					System.err.println("Invalid state");
				}
				params.add(new BasicNameValuePair("city", city));
				params.add(new BasicNameValuePair("state", state));
				
				JsonHandler handler = new JsonHandler(RESERVE_URL, params);
				JSONObject result = handler.getJsonObject();
				// TODO: Show success message and go back to main page
			}
		});
	}
	private void updateCart()
	{
		if(totalMinutesReserved > 0 || totalDaysReserved > 0)
		{
			lblCartInfo.setText("Cart :    " + totalDaysReserved + " Days Reserved\n"+ " + " 
								+ totalMinutesReserved + " Minutes\n" +  "Car: " +  carType );
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
	
	private void reserveCar() {
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
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/economyCar.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 45;
					weeklyCarCost = 300;
			}
			if(comboBoxVehicleType.getSelectedItem().equals("Compact"))
			{
					picLabel.setIcon(null);
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/compactCar.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 50;
					weeklyCarCost = 325;
			}
			if(comboBoxVehicleType.getSelectedItem().equals("Standard"))
			{
					picLabel.setIcon(null);
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/standardCar.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 60;
					weeklyCarCost = 400;
			}
			if(comboBoxVehicleType.getSelectedItem().equals("Small SUV"))
			{
					picLabel.setIcon(null);
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/smallSUV.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 70;
					weeklyCarCost = 475;
			}
			if(comboBoxVehicleType.getSelectedItem().equals("Minivan"))
			{
					picLabel.setIcon(null);
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/minivan.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 85;
					weeklyCarCost = 575;
			}
			if(comboBoxVehicleType.getSelectedItem().equals("Standard SUV"))
			{
					picLabel.setIcon(null);
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/standardSUV.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 75;
					weeklyCarCost = 500;
			}
			if(comboBoxVehicleType.getSelectedItem().equals("Premium"))
			{
					picLabel.setIcon(null);
					ImageIcon testImg = new ImageIcon(this.getClass().getResource(("images/premiumCar.png")));
					picLabel.setIcon(testImg);
					picLabel.setBounds(10, 25, 300, 400);
					SearchResultsPage.add(picLabel);
					picLabel.setVisible(true);
					dailyCarCost = 65;
					weeklyCarCost = 435;
			}
		}
		else
		{
			lblInvalidRange.setVisible(true);
		}
		

		if(totalDaysReserved < 7)
		{
			lblEstimatedCost.setText("Estimated cost: $" + totalDaysReserved * dailyCarCost + " plus tax");
			estimatedCost = totalDaysReserved * dailyCarCost;
			System.out.println(estimatedCost);
		}
		else
		{
			totalWeeksReserved = Math.floorDiv(totalDaysReserved, 7);
			lblEstimatedCost.setText("Estimated cost: $" + ((totalWeeksReserved * weeklyCarCost) 
									 + ((totalDaysReserved % 7) * dailyCarCost)) + " plus tax");
			estimatedCost = totalWeeksReserved * weeklyCarCost + ((totalDaysReserved % 7) * dailyCarCost);
		}
		
	}
	
	private String processCarType(String carType) {
		if (carType.toLowerCase().equals("small suv")) {
			return "Sm SUV";
		} else if (carType.toLowerCase().equals("stanard suv")) {
			return "Std SUV";
		} else {
			return carType;
		}
	}
	
	private String formatDate(Date d) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(d);
	}
}
