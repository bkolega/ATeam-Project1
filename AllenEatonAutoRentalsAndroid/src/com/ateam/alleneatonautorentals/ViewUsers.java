package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
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
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ViewUsers extends ListActivity {
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> usersList;
	
	private static final String USERS_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/listCustomers.php";
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
		
		usersList = new ArrayList<HashMap<String, String>>();
	
		new LoadAllUsers().execute();
		
		ListView lv = getListView();	
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		Intent ii;
		switch (id) {
			case R.id.backButtonViewUsers:
				ii = new Intent(ViewUsers.this, MainMenu.class);
				startActivity(ii);		
				finish();	
				break;
			default:
				break;
		}

	}

	class LoadAllUsers extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ViewUsers.this);
			progressDialog.setMessage("Loading users...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			JSONObject json = jsonParser.makeHttpRequest(USERS_URL, "GET", params);
			
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
					// Not successful if the database is down
					Toast.makeText(ViewUsers.this, "Error in database", Toast.LENGTH_LONG).show();
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onPostExecute(String file_url) {
			progressDialog.dismiss();
			
			// Puts all the strings into the list
			runOnUiThread(new Runnable() {
				public void run() {
					ListAdapter adapter = new SimpleAdapter 
							(ViewUsers.this, usersList,
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


