package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import com.ateam.alleneatonautorentals.R;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends Activity {
	private EditText textUser, textPass, textRePass, textFName, textMName, textLName,
					 textBirth, textPhone, textStreet, textStreet2, textCity, textState, 
					 textZip, textRole, textSupEmail;
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String REGISTER_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/register.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		switch (id) {
			case R.id.button_register_cancel:
				Intent ii = new Intent(Register.this, MainActivity.class);
				startActivity(ii);		
				finish();	
				break;
			case R.id.button_register_confirm:
				// Get the text from all of the fields
				textUser = (EditText)findViewById(R.id.employee_email_edit);
				textPass =  (EditText)findViewById(R.id.employee_password_edit);
				textRePass = (EditText)findViewById(R.id.employee_repassword_edit);
				textFName = (EditText)findViewById(R.id.first_name_edit);
				textMName = (EditText)findViewById(R.id.middle_name_edit);
				textLName = (EditText)findViewById(R.id.last_name_edit);
				textBirth = (EditText)findViewById(R.id.birthdate_edit);
				textPhone = (EditText)findViewById(R.id.phone_edit);
				textStreet = (EditText)findViewById(R.id.address_edit);
				textStreet2 = (EditText)findViewById(R.id.address2_edit);
				textCity = (EditText)findViewById(R.id.city_edit);
				textState = (EditText)findViewById(R.id.state_edit);
				textZip = (EditText)findViewById(R.id.zip_edit);
				textRole = (EditText)findViewById(R.id.employee_role_edit);
				textSupEmail = (EditText)findViewById(R.id.supervisor_email_edit);
				
				new AttemptRegister().execute();
				break;
			default:
				break;
		}

	}
	
	class AttemptRegister extends AsyncTask <String, String, String> {

		boolean failure = false;
		
		@Override
		protected void onPreExecute() {
			// Initiate
			super.onPreExecute();
			progressDialog = new ProgressDialog(Register.this);
			progressDialog.setMessage("Attempting to register...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			int success;
			
			String username = textUser.getText().toString();
			String password = textPass.getText().toString();
			String repassword = textRePass.getText().toString();
			String fname = textFName.getText().toString();
			String mname = textMName.getText().toString();
			String lname = textLName.getText().toString();
			String birthdate = textBirth.getText().toString();
			String phone = textPhone.getText().toString();
			String street = textStreet.getText().toString();
			String street2 = textStreet2.getText().toString();
			String city = textCity.getText().toString();
			String state = textState.getText().toString();
			String zip = textZip.getText().toString();
			String role = textRole.getText().toString();
			String supemail = textSupEmail.getText().toString();
			
			try {
				List <NameValuePair> params = new ArrayList <NameValuePair>();
				
				// Make the JSON to send to PHP server
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", password));
				params.add(new BasicNameValuePair("repassword", repassword));
				params.add(new BasicNameValuePair("firstname", fname));
				params.add(new BasicNameValuePair("middlename", mname));
				params.add(new BasicNameValuePair("lastname", lname));
				params.add(new BasicNameValuePair("birthdate", birthdate));
				params.add(new BasicNameValuePair("phone", phone));
				params.add(new BasicNameValuePair("street", street));
				params.add(new BasicNameValuePair("street2", street2));
				params.add(new BasicNameValuePair("city", city));
				params.add(new BasicNameValuePair("state", state));
				params.add(new BasicNameValuePair("zip", zip));
				params.add(new BasicNameValuePair("role", role));
				params.add(new BasicNameValuePair("supemail", supemail));

				Log.d("request!", "starting");
				
				// Getting the JSON back from PHP server
				JSONObject json = jsonParser.makeHttpRequest (REGISTER_URL, "POST", params);
				Log.d("Login Attempt", json.toString());
				
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Successfully Registered!", json.toString());
					
					// Move onto next activity
					Intent  ii = new Intent(Register.this, MainActivity.class);
					startActivity(ii);					
					finish();	
					
	
					return json.getString(TAG_MESSAGE);
				}
				else {
					return json.getString(TAG_MESSAGE);
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		// Cleaning up after done
		protected void onPostExecute (String message) {
			progressDialog.dismiss();
			if (message != null) {
				Toast.makeText(Register.this, message, Toast.LENGTH_LONG).show();
			}
		}
		
	}
}
