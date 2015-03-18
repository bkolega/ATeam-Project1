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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	private EditText textUser, textPass;
	private RadioGroup radioGroupId;
	private RadioButton radioEmployeeButton;
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String LOGIN_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/login.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	private static final String TAG_ROLE = "role";
	private static final String TAG_NAME = "name";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set up the action bar.
		final ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// What all the buttons do
	public void clickButtons(View v) {
		final int id = v.getId();
		switch (id) {
		case R.id.button_login_submit:
			//setContentView(R.layout.fragment_main);
			
			// Get Text from login text boxes
			textUser = (EditText)findViewById(R.id.input_login_email);
			textPass = (EditText)findViewById(R.id.input_password_email);
			
			//get the radio button option 
			radioGroupId = (RadioGroup) findViewById(R.id.radioEmployeeGroup);
			int selectedOption = radioGroupId.getCheckedRadioButtonId();
			radioEmployeeButton = (RadioButton) findViewById(selectedOption);

			new AttemptLogin().execute();
			break;
			
		case R.id.button_register: 
			Intent  ii = new Intent(MainActivity.this, Register.class);	
			startActivity(ii);
			finish();	
			break;
		default:
			break;
		}
	}
	
	class AttemptLogin extends AsyncTask <String, String, String> {

		boolean failure = false;
		
		@Override
		protected void onPreExecute() {
			// Initiate
			super.onPreExecute();
			progressDialog = new ProgressDialog(MainActivity.this);
			progressDialog.setMessage("Attempting to login...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			int success;
			
			String username = textUser.getText().toString();
			String password = textPass.getText().toString();
			String radioEmployee = radioEmployeeButton.getText().toString();
			
			try {
				List <NameValuePair> params = new ArrayList <NameValuePair>();
				
				// Make the JSON to send to PHP server
				params.add(new BasicNameValuePair("username", username));
				params.add(new BasicNameValuePair("password", password));
				
				Log.d("request!", "starting");
				
				// Getting the JSON back from PHP server
				JSONObject json = jsonParser.makeHttpRequest (LOGIN_URL, "POST", params);
				Log.d("Login Attempt", json.toString());
				
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Successfully Login!", json.toString());
					
					String role = json.getString(TAG_ROLE);
					String name = json.getString(TAG_NAME);
					
					SessionManager session = new SessionManager(getApplicationContext());
					session.createLoginSession(username, role, name);
					
					// Move onto next activity
					Intent  ii = new Intent(MainActivity.this, MainMenu.class);
					Bundle b = new Bundle();
					b.putString("employeeType", radioEmployee); //Your id
					ii.putExtras(b);
					
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
				Toast.makeText(MainActivity.this, message, Toast.LENGTH_LONG).show();
			}
		}
		
	}

}


	

