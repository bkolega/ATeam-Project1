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

import com.ateam.alleneatonautorentals.SalesViewReservations.LoadAllUserRes;

public class SalesViewContracts extends ListActivity{
		private ProgressDialog progressDialog;
		JSONParser jsonParser = new JSONParser();

		ArrayList<HashMap<String, String>> resList;
		
		private static final String RES_URL =
				"http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_contracts.php";
		private static final String TAG_SUCCESS = "success";
		private static final String TAG_CONTRACTS = "contracts";
		//private static final String TAG_UNAME = 
		//private static final String TAG_UEMAIL = 
		private static final String TAG_CARID = "car_id";
		private static final String TAG_GPS = "GPS";
		private static final String TAG_CHILDSEAT = "child_seat";
		private static final String TAG_KTAG = "k_tag";
		private static final String TAG_ASSISTANCE = "assistance";
		private static final String TAG_DINSURANCE = "damage_insurance";
		private static final String TAG_AINSURANCE = "accident_insurance";
		private static final String TAG_START = "start_date";
		private static final String TAG_END = "end_date";
		private static final String TAG_CITY = "city";
		private static final String TAG_STATE = "state";
		private static final String TAG_EMPLOYEEEMAIL = "employee_email";
		private static final String TAG_CARTYPE = "car_type";
		private static final String TAG_CARMAKE = "car_make";
		private static final String TAG_CARMODEL = "car_model";
		private static final String TAG_CARLICENSE = "car_license_plate";
		private static final String TAG_CARLICENSESTATE = "car_license_state";
		private static final String TAG_CARYEAR = "car_year"; 
		private static final String TAG_CHECKINDATE = "checkedin_date";
		private static final String TAG_CARDNUMBER = "card_number";
		private String userEmail;
		private String name;
		private String key;
		
		JSONArray res = null;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.view_contracts);
			
			Intent getIntent = getIntent();
			userEmail = getIntent.getStringExtra("email");
			key = getIntent.getStringExtra("key");
			name = getIntent.getStringExtra("name");
			
			resList = new ArrayList<HashMap<String, String>>();
			
			new LoadAllUserRes().execute();
			
			ListView lv = getListView();
			
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					String card_number = ((TextView)view.findViewById(R.id.list_contract_cardnumber)).getText().toString();
					String carid = ((TextView)view.findViewById(R.id.list_contract_car_id)).getText().toString();
					String car_type = ((TextView)view.findViewById(R.id.list_contract_car_type)).getText().toString();
					String car_make = ((TextView)view.findViewById(R.id.list_contract_car_make)).getText().toString();
					String car_model = ((TextView)view.findViewById(R.id.list_contract_car_model)).getText().toString();
					String car_year = ((TextView)view.findViewById(R.id.list_contract_car_year)).getText().toString();
					String license_plate = ((TextView)view.findViewById(R.id.list_contract_license_plate)).getText().toString();
					String license_state = ((TextView)view.findViewById(R.id.list_contract_license_state)).getText().toString();
					String employee_email = ((TextView)view.findViewById(R.id.list_contract_employee_email)).getText().toString();
					String state = ((TextView)view.findViewById(R.id.list_contract_state)).getText().toString();
					String city = ((TextView)view.findViewById(R.id.list_contract_city)).getText().toString();
					String start_date = ((TextView)view.findViewById(R.id.list_contract_start_date)).getText().toString();
					String end_date = ((TextView)view.findViewById(R.id.list_contract_end_date)).getText().toString();
					String gps = ((TextView)view.findViewById(R.id.list_contract_gps)).getText().toString();
					String child_seat = ((TextView)view.findViewById(R.id.list_contract_child_seat)).getText().toString();
					String k_tag = ((TextView)view.findViewById(R.id.list_contract_ktag)).getText().toString();
					String assistance = ((TextView)view.findViewById(R.id.list_contract_assistance)).getText().toString();
					String dinsurance = ((TextView)view.findViewById(R.id.list_contract_dinsurance)).getText().toString();
					String ainsurance = ((TextView)view.findViewById(R.id.list_contract_ainsurance)).getText().toString();
					String checkin = ((TextView)view.findViewById(R.id.list_contract_checkin_date)).getText().toString();
					
					Intent ii = new Intent(getApplicationContext(), SalesPrintContract.class);
					
					ii.putExtra("cardnumber", card_number);
					ii.putExtra("carid", carid);
					ii.putExtra("cartype", car_type); 
					ii.putExtra("carmake", car_make); 
					ii.putExtra("carmodel", car_model); 
					ii.putExtra("caryear", car_year); 
					ii.putExtra("lplate", license_plate);
					ii.putExtra("lstate", license_state);
					ii.putExtra("ktag", k_tag);
					ii.putExtra("eemail", employee_email);
					ii.putExtra("state", state); 
					ii.putExtra("city", city);
					ii.putExtra("start_date", start_date); 
					ii.putExtra("end_date", end_date);	
					ii.putExtra("gps", gps);
					ii.putExtra("child_seat", child_seat);
					ii.putExtra("ktag", k_tag);
					ii.putExtra("assistance", assistance);
					ii.putExtra("dinsurance", dinsurance);
					ii.putExtra("ainsurance", ainsurance);
					ii.putExtra("checkin", checkin);
					ii.putExtra("email", userEmail); 
					ii.putExtra("key", key);
					ii.putExtra("name", name);
					
					startActivity(ii);
					finish();
				}
				
			});
		}
		
		
		@Override
		public boolean onCreateOptionsMenu (Menu menu) {
			getMenuInflater().inflate(R.menu.main, menu);
			return true;
		}
		
		class LoadAllUserRes extends AsyncTask<String, String, String> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				progressDialog = new ProgressDialog(SalesViewContracts.this);
				progressDialog.setMessage("Loading contracts...");
				progressDialog.setIndeterminate(false);
				progressDialog.setCancelable(false);
				progressDialog.show();
			}
			
			@Override
			protected String doInBackground(String... args) {
				List<NameValuePair> params = new ArrayList<NameValuePair>();
				
				params.add(new BasicNameValuePair("username", userEmail));
				
				JSONObject json = jsonParser.makeHttpRequest(RES_URL, "POST", params);
				
				Log.d("All Reservations: ", json.toString());
				
				try {
					int success = json.getInt(TAG_SUCCESS);
					
					if (success == 1) {
						res = json.getJSONArray(TAG_CONTRACTS);
						
						for (int i = 0; i < res.length(); i++) {
							JSONObject c = res.getJSONObject(i);
							
							String car_id = c.getString(TAG_CARID);
							String GPS = c.getString(TAG_GPS);
							String child_seat = c.getString(TAG_CHILDSEAT);
							String k_tag = c.getString(TAG_KTAG);
							String assistance = c.getString(TAG_ASSISTANCE);
							String dinsurance = c.getString(TAG_DINSURANCE);
							String ainsurance = c.getString(TAG_AINSURANCE);
							String startdate = c.getString(TAG_START); 
							String enddate = c.getString(TAG_END);
							String city = c.getString(TAG_CITY);
							String state = c.getString(TAG_STATE);
							String eemail = c.getString(TAG_EMPLOYEEEMAIL);
							String car_type = c.getString(TAG_CARTYPE);
							String car_make = c.getString(TAG_CARMAKE);
							String car_model = c.getString(TAG_CARMODEL);
							String license = c.getString(TAG_CARLICENSE);
							String licensestate = c.getString(TAG_CARLICENSESTATE);
							String year = c.getString(TAG_CARYEAR);
							String checkin = c.getString(TAG_CHECKINDATE); 
							String cardnumber = c.getString(TAG_CARDNUMBER);
							
							HashMap<String, String> map = new HashMap<String, String>();
							
							map.put(TAG_CARDNUMBER, "Card Number: ".concat(cardnumber));
							map.put(TAG_EMPLOYEEEMAIL, "Employee Email: ".concat(eemail));
							map.put(TAG_CARID, "Car ID: ".concat(car_id));
							map.put(TAG_CARTYPE, "Car Type: ".concat(car_type));
							map.put(TAG_CARMAKE, "Car Make: ".concat(car_make));
							map.put(TAG_CARMODEL, "Car Model: ".concat(car_model));
							map.put(TAG_CARYEAR, "Car Year: ".concat(year));
							map.put(TAG_CARLICENSE, "License Plate: ".concat(license));
							map.put(TAG_CARLICENSESTATE, "License State: ".concat(licensestate));
							map.put(TAG_START, "Reservation Start Date: ".concat(startdate)); 
							map.put(TAG_END, "Reservation End Date: ".concat(enddate));
							map.put(TAG_CITY, "Reservation City: ".concat(city));
							map.put(TAG_STATE, "Reservation State: ".concat(state));
							map.put(TAG_GPS, "GPS: ".concat(GPS));
							map.put(TAG_CHILDSEAT, "Child seat: ".concat(child_seat));
							map.put(TAG_KTAG,"K-Tag: ".concat(k_tag));
							map.put(TAG_ASSISTANCE, "Road Assistance: ".concat(assistance));
							map.put(TAG_DINSURANCE, "Damage Insurance: ".concat(dinsurance));
							map.put(TAG_AINSURANCE, "Accident Insurance: ".concat(ainsurance));
							if(checkin.equals("null"))
								map.put(TAG_CHECKINDATE, "Check in date: "); 
							else 
								map.put(TAG_CHECKINDATE, "Check in date: ".concat(checkin));
						
							
							resList.add(map);
						}
					}
					else {
						Intent ii = new Intent(SalesViewContracts.this, SalesUserMenu.class);
						
						ii.putExtra("email", userEmail);
						ii.putExtra("key", key);
						ii.putExtra("name", name);
						
						startActivity(ii);
						finish();
					}
				}
				catch (JSONException e) {
					e.printStackTrace();
				}
				
				return null;
			}
			
			protected void onPostExecute (String message) {
				progressDialog.dismiss();
				
				if (message != null) {
					Toast.makeText(SalesViewContracts.this, message, Toast.LENGTH_LONG).show();
				}
				else {
					// Puts all the strings into the list
					runOnUiThread(new Runnable() {
						public void run() {
							ListAdapter adapter = new SimpleAdapter 
									(SalesViewContracts.this, resList,
									 R.layout.listitem_contract, new String[] 
											 {TAG_CARID, TAG_GPS, TAG_CHILDSEAT, TAG_KTAG, 
											  TAG_ASSISTANCE, TAG_DINSURANCE, TAG_AINSURANCE,
											  TAG_START, TAG_END, TAG_CITY, TAG_STATE,
											  TAG_EMPLOYEEEMAIL, TAG_CARTYPE, TAG_CARMAKE,
											  TAG_CARMODEL, TAG_CARLICENSE, TAG_CARLICENSESTATE, TAG_CARYEAR,
											  TAG_CHECKINDATE, TAG_CARDNUMBER, name, userEmail},
											  new int[] {R.id.list_contract_car_id, R.id.list_contract_gps,
														 R.id.list_contract_child_seat, R.id.list_contract_ktag,
														 R.id.list_contract_assistance, R.id.list_contract_dinsurance,
														 R.id.list_contract_ainsurance, R.id.list_contract_start_date,
														 R.id.list_contract_end_date, R.id.list_contract_city,
														 R.id.list_contract_state, R.id.list_contract_employee_email, 
														 R.id.list_contract_car_type, R.id.list_contract_car_make, 
														 R.id.list_contract_car_model,R.id.list_contract_license_plate, 
														 R.id.list_contract_license_state,R.id.list_contract_car_year, 
														 R.id.list_contract_checkin_date,R.id.list_contract_cardnumber, 
														 R.id.list_contract_customer_name, R.id.list_contract_customer_email
									});
							setListAdapter(adapter);
						}
					});
				}
			}
		}
		
		@Override
		public void onBackPressed() {
			Intent ii;
			ii = new Intent(SalesViewContracts.this, SalesUserMenu.class);
			
			ii.putExtra("email", userEmail);
			ii.putExtra("key", key);
			ii.putExtra("name", name);
			
			startActivity(ii);
			finish();
		}
	}
