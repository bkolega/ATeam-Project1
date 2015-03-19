/*
 * XML: view_users
 * Search for users in Sales
 */

package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class SalesSearchUser extends ListActivity {
	String keyword = "";
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> usersList;
	
	private static final String USERS_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/search_customers.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_USERS = "users";
	private static final String TAG_EMAIL = "email";
	private static final String TAG_CARD = "card_number";
	private static final String TAG_LICENSE = "license";
	private static final String TAG_LICENSE_STATE = "license_state";
	private static final String TAG_NAME = "name";
	private static final String TAG_PHONE = "phone";
	private static final String TAG_ADDRESS = "address";
	private static final String TAG_ADDRESS2 = "address2";
	private static final String TAG_CITY = "city";
	private static final String TAG_STATE = "state";
	private static final String TAG_ZIP = "zip";	
	
	JSONArray users = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_users);
		
		Intent getIntent = getIntent();
		keyword = getIntent.getStringExtra("key");
		Log.d("Key Searched: ", keyword);
		usersList = new ArrayList<HashMap<String, String>>();
		
		new LoadFoundUsers().execute();
		
		SessionManager session = new SessionManager(getApplicationContext());
		String role = session.getRole();
		
		if (role.equals("Sales") || role.equals("Manager")) {
			ListView lv = getListView();
			
			lv.setOnItemClickListener(new OnItemClickListener() {
	
				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String email = ((TextView)view.findViewById(R.id.user_email_list)).getText().toString();
					String name = ((TextView)view.findViewById(R.id.user_name_list)).getText().toString();
					Intent ii = new Intent(getApplicationContext(), SalesUserMenu.class);
					ii.putExtra(TAG_EMAIL, email);
					ii.putExtra("key", keyword);
					ii.putExtra(TAG_NAME, name);
					startActivity(ii);
					finish();
				}
				
			});
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class LoadFoundUsers extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(SalesSearchUser.this);
			progressDialog.setMessage("Loading users...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("keyword", keyword));
			
			JSONObject json = jsonParser.makeHttpRequest(USERS_URL, "POST", params);
			
			Log.d("All Users: ", json.toString());
			
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					users = json.getJSONArray(TAG_USERS);
					
					// Go through each users in JSON
					for (int i = 0; i < users.length(); i++) {
						JSONObject c = users.getJSONObject(i);
					
						// Get the string from JSON
						String email = c.getString(TAG_EMAIL);
						String card_num = c.getString(TAG_CARD);
						String license = c.getString(TAG_LICENSE);
						String license_state = c.getString(TAG_LICENSE_STATE);
						String name = c.getString(TAG_NAME);
						String phone = c.getString(TAG_PHONE);
						String address = c.getString(TAG_ADDRESS);
						String address2 = c.getString(TAG_ADDRESS2);
						String city = c.getString(TAG_CITY);
						String state = c.getString(TAG_STATE);
						String zip = c.getString(TAG_ZIP);
						
						HashMap<String, String> map = new HashMap<String, String>();
						
						// Store the strings
						map.put(TAG_EMAIL, "Email: ".concat(email));
						map.put(TAG_CARD, "Card Number: ".concat(card_num.substring(card_num.length() - 4)));
						map.put(TAG_LICENSE, "Driver's License: ".concat(license));
						map.put(TAG_LICENSE_STATE, "Issued In: ".concat(license_state));
						map.put(TAG_NAME, name);
						map.put(TAG_PHONE, "Phone Number: ".concat(phone));
						map.put(TAG_ADDRESS, "Street Address: ".concat(address));
						map.put(TAG_ADDRESS2, "Street Address 2: ".concat(address2));
						map.put(TAG_CITY, "Current City: ".concat(city));
						map.put(TAG_STATE, "Current State: ".concat(state));
						map.put(TAG_ZIP, "Current Zipcode: ".concat(zip));
						
						usersList.add(map);
					}
				}
				else {
					Intent  ii = new Intent(SalesSearchUser.this, MainMenu.class);
					Bundle b = new Bundle();
					b.putString("employeeType", "Sales"); 
					ii.putExtras(b);
					
					startActivity(ii);					
					finish();
					
					return "Error in Database";					
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onPostExecute(String message) {
			progressDialog.dismiss();
			
			if (message != null) {
				Toast.makeText(SalesSearchUser.this, message, Toast.LENGTH_LONG).show();
			}
			else {
				// Puts all the strings into the list
				runOnUiThread(new Runnable() {
					public void run() {
						ListAdapter adapter = new SimpleAdapter 
								(SalesSearchUser.this, usersList,
								 R.layout.listitem_user, new String[] 
										 {TAG_NAME, TAG_EMAIL, TAG_CARD, TAG_LICENSE, 
										  TAG_LICENSE_STATE, TAG_PHONE, TAG_ADDRESS,
										  TAG_ADDRESS2, TAG_CITY, TAG_STATE, TAG_ZIP},
										  new int[] {R.id.user_name_list, R.id.user_email_list,
													 R.id.user_cardnum_list, R.id.user_license_list,
													 R.id.user_licensestate_list, R.id.user_phone_list,
													 R.id.user_address_list, R.id.user_address2_list,
													 R.id.user_city_list, R.id.user_state_list,
													 R.id.user_zip_list
								});
						setListAdapter(adapter);
					}
				});
			}
		}

		
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();

		switch (id) {

			default:
				break;
		}

	}
	
	@Override
	public void onBackPressed() {
		Intent  ii = new Intent(SalesSearchUser.this, MainMenu.class);
		Bundle b = new Bundle();
		b.putString("employeeType", "Sales"); 
		ii.putExtras(b);
		
		startActivity(ii);					
		finish();	
	}

}
