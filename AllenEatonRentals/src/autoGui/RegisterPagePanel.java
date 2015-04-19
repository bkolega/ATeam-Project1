package autoGui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import java.time.Month;

public class RegisterPagePanel {
	private JTextField email;
	private JTextField firstName;
	private JTextField middleName;
	private JTextField lastName;
	private JTextField phone;
	private JPasswordField password;
	private JTextField address;
	private JTextField address2;
	private JTextField city;
	private JTextField zip;
	private JTextField state;
	private JTextField license;
	private JPanel RegisterPage = new JPanel();
	private JComboBox birthYear;
	private JComboBox birthDay;
	private JComboBox birthMonth;
	private static final String REGISTER_URL = "http://people.eecs.ku.edu/~dyoung/CustomerPHPScripts/register.php";
	private JPasswordField repassword;
	private JTextField cardNumber;
	private JComboBox cardExpMonth;
	private JComboBox cardExpDay;
	private JTextField licenseState;
	private int lastPage =0;
	
	private boolean isRegistered = false;
	
	RegisterPagePanel(JFrame parent, MouseAdapter backHandler) {
		parent.getContentPane().add(RegisterPage, "name_22846752421143");
		RegisterPage.setLayout(null);
		System.out.println(parent.getContentPane());
		RegisterPage.setBounds(100, 100, 1036, 608);
		
		email = new JTextField();
		email.setBounds(642, 87, 116, 22);
		RegisterPage.add(email);
		email.setColumns(10);
		
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
		
		firstName = new JTextField();
		firstName.setBounds(241, 85, 142, 22);
		RegisterPage.add(firstName);
		firstName.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Middle Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(141, 132, 88, 16);
		RegisterPage.add(lblNewLabel_1);
		
		middleName = new JTextField();
		middleName.setBounds(241, 129, 142, 22);
		RegisterPage.add(middleName);
		middleName.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Last Name*");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(141, 174, 88, 16);
		RegisterPage.add(lblNewLabel_2);
		
		lastName = new JTextField();
		lastName.setBounds(241, 172, 142, 22);
		RegisterPage.add(lastName);
		lastName.setColumns(10);
		
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
		lblPhoneNumber.setBounds(506, 204, 71, 16);
		RegisterPage.add(lblPhoneNumber);
		
		phone = new JTextField();
		phone.setBounds(642, 202, 116, 22);
		RegisterPage.add(phone);
		phone.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(642, 130, 116, 22);
		RegisterPage.add(password);
		
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
				backHandler.mouseClicked(e);
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
		
		address = new JTextField();
		address.setBounds(241, 215, 142, 22);
		RegisterPage.add(address);
		address.setColumns(10);
		
		address2 = new JTextField();
		address2.setBounds(241, 260, 142, 22);
		RegisterPage.add(address2);
		address2.setColumns(10);
		
		JLabel lblCity = new JLabel("City*");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCity.setBounds(141, 308, 56, 16);
		RegisterPage.add(lblCity);
		
		city = new JTextField();
		city.setBounds(241, 306, 142, 22);
		RegisterPage.add(city);
		city.setColumns(10);
		
		JLabel lblAddress_1 = new JLabel("Address 2");
		lblAddress_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAddress_1.setBounds(141, 263, 77, 16);
		RegisterPage.add(lblAddress_1);
		
		JLabel lblZip = new JLabel("Zip*");
		lblZip.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblZip.setBounds(141, 402, 56, 16);
		RegisterPage.add(lblZip);
		
		zip = new JTextField();
		zip.setBounds(241, 400, 142, 22);
		RegisterPage.add(zip);
		zip.setColumns(10);
		
		JLabel lblState = new JLabel("State*");
		lblState.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblState.setBounds(141, 355, 56, 16);
		RegisterPage.add(lblState);
		
		state = new JTextField();
		state.setBounds(241, 353, 142, 22);
		RegisterPage.add(state);
		state.setColumns(10);
		
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
		
		license = new JTextField();
		license.setBounds(642, 306, 116, 22);
		RegisterPage.add(license);
		license.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth*");
		lblDateOfBirth.setBounds(506, 372, 88, 16);
		RegisterPage.add(lblDateOfBirth);
		
		birthMonth = new JComboBox();
		birthMonth.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		birthMonth.setBounds(642, 370, 71, 22);
		RegisterPage.add(birthMonth);
		
		birthDay = new JComboBox();
		birthDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		birthDay.setBounds(708, 370, 62, 22);
		RegisterPage.add(birthDay);
		
		List<Integer> years = new ArrayList<Integer>();
		for (int i = 1915; i <= 2015; ++i) {
		    years.add(i);
		}
		
		birthYear = new JComboBox(years.toArray());
		birthYear.setBounds(769, 370, 97, 22);
		RegisterPage.add(birthYear);
		
		JButton btnContinue = new JButton("Continue ->");
		btnContinue.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					register(parent);

			}
		});
		btnContinue.setBounds(674, 428, 133, 34);
		RegisterPage.add(btnContinue);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password*");
		lblConfirmPassword.setBounds(506, 160, 121, 16);
		RegisterPage.add(lblConfirmPassword);
		
		repassword = new JPasswordField();
		repassword.setBounds(642, 157, 116, 22);
		RegisterPage.add(repassword);
		
		JLabel lblNewLabel_3 = new JLabel("Credit card #*");
		lblNewLabel_3.setBounds(141, 436, 93, 16);
		RegisterPage.add(lblNewLabel_3);
		
		cardNumber = new JTextField();
		cardNumber.setBounds(241, 434, 142, 28);
		RegisterPage.add(cardNumber);
		cardNumber.setColumns(10);
		
		JLabel lblCardExp = new JLabel("Card exp*");
		lblCardExp.setBounds(141, 464, 82, 16);
		RegisterPage.add(lblCardExp);
		
		cardExpMonth = new JComboBox();
		cardExpMonth.setModel(new DefaultComboBoxModel(new String[] {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"}));
		cardExpMonth.setBounds(238, 460, 77, 27);
		RegisterPage.add(cardExpMonth);
		
		cardExpDay = new JComboBox();
		cardExpDay.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		cardExpDay.setBounds(312, 460, 71, 27);
		RegisterPage.add(cardExpDay);
		
		JLabel lblLicenseState = new JLabel("License state*");
		lblLicenseState.setBounds(506, 338, 88, 16);
		RegisterPage.add(lblLicenseState);
		
		licenseState = new JTextField();
		licenseState.setBounds(642, 330, 116, 28);
		RegisterPage.add(licenseState);
		licenseState.setColumns(10);
		
		RegisterPage.setVisible(false);
	}
	
	private void register(JFrame parent) {
		List<NameValuePair> params = new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("firstname", firstName.getText()));
		params.add(new BasicNameValuePair("middlename", middleName.getText()));
		params.add(new BasicNameValuePair("lastname", lastName.getText()));
		
		params.add(new BasicNameValuePair("address", address.getText()));
		params.add(new BasicNameValuePair("address2", address2.getText()));
		
		params.add(new BasicNameValuePair("city", city.getText()));
		params.add(new BasicNameValuePair("state", state.getText()));
		params.add(new BasicNameValuePair("zip", zip.getText()));

		params.add(new BasicNameValuePair("username", email.getText()));
		params.add(new BasicNameValuePair("password", password.getText()));
		params.add(new BasicNameValuePair("repassword", repassword.getText()));
		
		params.add(new BasicNameValuePair("phone", phone.getText()));
		
		params.add(new BasicNameValuePair("license", license.getText()));
		
		params.add(new BasicNameValuePair("licensestate", licenseState.getText()));
		
		try {
			Date date = new SimpleDateFormat("MMM", Locale.ENGLISH).parse(birthMonth.getSelectedItem().toString());
			Calendar cal = Calendar.getInstance();
			
			cal.setTime(date);
			Integer monthNum = new Integer(cal.get(Calendar.MONTH)) + 1;
			
			String birthDate = birthYear.getSelectedItem()
			                 + "-" + addLeadingZero(monthNum.toString())
			                 + "-" + addLeadingZero(birthDay.getSelectedItem().toString());
			
			params.add(new BasicNameValuePair("birthdate", birthDate));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		params.add(new BasicNameValuePair("cardnumber", cardNumber.getText()));
		
		String cardExp = addLeadingZero(cardExpMonth.getSelectedItem().toString()) + "/" + addLeadingZero(cardExpDay.getSelectedItem().toString());
		params.add(new BasicNameValuePair("cardexp", cardExp));

		JsonHandler handler = new JsonHandler(REGISTER_URL, params);
		JSONObject result = handler.getJsonObject();
		System.out.println(result.toString());

		// TODO: **** Add proper error display ****
		if (result.getInt("success") == 1) {
			System.out.println("Registration successful!");
			RegisterPage.setVisible(false);
			parent.getContentPane().getComponent(getLastPage()).setVisible(true);
			isRegistered=true;
			
		} else {
			System.out.println("Registration failed");
		}
	}
	
	private String addLeadingZero(String s) {
		if (s.length() == 1) {
			return "0" + s;
		} else {
			return s;
		}
	}
	
	public boolean isRegistered() {
		return isRegistered;
	}
	
	public void setLastPage(int i) {
		lastPage=i;
	}
	
	public int getLastPage() {
		return lastPage;
	}
	
	JPanel getPanel() {
		return RegisterPage;
	}
}
