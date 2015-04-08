/*
 * XML: view_reservations
 * List all reservations for the user selected
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

public class SalesViewReservations extends ListActivity{
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();

	ArrayList<HashMap<String, String>> resList;
	
	private static final String RES_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_reservations.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_RESERVATIONS = "reservations";
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
	private static final String TAG_PERWEEK = "per_week";
	private static final String TAG_CARTYPE = "car_type";
	private static final String TAG_CARMAKE = "car_make";
	private static final String TAG_CARMODEL = "car_model";
	private static final String TAG_CARLICENSE = "car_license_plate";
	/*private String car_id;
	private String car_make;
	private String car_model;
	private String car_type;
	private String city;
	private String state;
	private String license;
	private String start_date;
	private String end_date;
	private String per_week;
	private String GPS;
	private String child_seat;
	private String k_tag;
	private String assistance;
	private String dinsurance;
	private String ainsurance;*/
	private String userEmail;
	private String name;
	private String key;
	
	JSONArray res = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_reservations);
		
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
				String gps = ((TextView)view.findViewById(R.id.carres_GPS_list)).getText().toString();
				String child_seat = ((TextView)view.findViewById(R.id.carres_child_seat_list)).getText().toString();
				String k_tag = ((TextView)view.findViewById(R.id.carres_k_tag_list)).getText().toString();
				String assistance = ((TextView)view.findViewById(R.id.carres_assistance_list)).getText().toString();
				String dinsurance = ((TextView)view.findViewById(R.id.carres_dinsurance_list)).getText().toString();
				String ainsurance = ((TextView)view.findViewById(R.id.carres_ainsurance_list)).getText().toString();
				String state = ((TextView)view.findViewById(R.id.carres_state_list)).getText().toString();
				String city = ((TextView)view.findViewById(R.id.carres_city_list)).getText().toString();
				String start_date = ((TextView)view.findViewById(R.id.carres_start_list)).getText().toString();
				String end_date = ((TextView)view.findViewById(R.id.carres_end_list)).getText().toString();
				String per_week = ((TextView)view.findViewById(R.id.carres_per_week_list)).getText().toString();
				String carid = ((TextView)view.findViewById(R.id.carres_id_list)).getText().toString();
				
				Intent ii = new Intent(getApplicationContext(), SalesCheckoutCar.class);

				ii.putExtra("carid", carid);
				ii.putExtra("state", state); 
				ii.putExtra("city", city); 
				ii.putExtra("start_date", start_date); 
				ii.putExtra("end_date", end_date); 
				ii.putExtra("per_week", per_week); 
				ii.putExtra("gps", gps);
				ii.putExtra("child_seat", child_seat);
				ii.putExtra("ktag", k_tag);
				ii.putExtra("assistance", assistance);
				ii.putExtra("dinsurance", dinsurance);
				ii.putExtra("ainsurance", ainsurance);
				ii.putExtra("reservation", "1");
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
			progressDialog = new ProgressDialog(SalesViewReservations.this);
			progressDialog.setMessage("Loading reservations...");
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
					res = json.getJSONArray(TAG_RESERVATIONS);
					
					for (int i = 0; i < res.length(); i++) {
						JSONObject c = res.getJSONObject(i);
						
						String car_id = c.getString(TAG_CARID);
						String car_make = c.getString(TAG_CARMAKE);
						String car_model = c.getString(TAG_CARMODEL);
						String car_type = c.getString(TAG_CARTYPE);
						String city = c.getString(TAG_CITY);
						String state = c.getString(TAG_STATE);
						String license = c.getString(TAG_CARLICENSE);
						String start_date = c.getString(TAG_START);
						String end_date = c.getString(TAG_END);
						String per_week = c.getString(TAG_PERWEEK);
						String GPS = c.getString(TAG_GPS);
						String child_seat = c.getString(TAG_CHILDSEAT);
						String k_tag = c.getString(TAG_KTAG);
						String assistance = c.getString(TAG_ASSISTANCE);
						String dinsurance = c.getString(TAG_DINSURANCE);
						String ainsurance = c.getString(TAG_AINSURANCE);
						
						HashMap<String, String> map = new HashMap<String, String>();
						
						map.put(TAG_CARID, "Car ID: ".concat(car_id));
						map.put(TAG_CARMAKE, car_make);
						map.put(TAG_CARMODEL, "Car Model: ".concat(car_model));
						map.put(TAG_CARTYPE, "Car Type: ".concat(car_type));
						map.put(TAG_CITY, "Reservation City: ".concat(city));
						map.put(TAG_STATE, "Reservation State: ".concat(state));
						map.put(TAG_CARLICENSE, "Car License Plate: ".concat(license));
						map.put(TAG_START, "Reservation Start Date: ".concat(start_date));
						map.put(TAG_END, "Reservation End Date: ".concat(end_date));
						
						if (per_week.equals("1")) {
							map.put(TAG_PERWEEK, "Rental per week");
						}
						else {
							map.put(TAG_PERWEEK, "Rental per day");
						}
						
						map.put(TAG_GPS, "GPS : ".concat(GPS));
						map.put(TAG_CHILDSEAT, "Child Seats: ".concat(child_seat));
						map.put(TAG_KTAG, "K-tag: ".concat(k_tag));
						map.put(TAG_ASSISTANCE, "Roadside Assistance: ".concat(assistance));
						map.put(TAG_DINSURANCE, "Loss Damage Waiver Insurance: ".concat(dinsurance));
						map.put(TAG_AINSURANCE, "Personal Accident Insurance: ".concat(ainsurance));
						
						resList.add(map);
					}
				}
				else {
					Intent ii = new Intent(SalesViewReservations.this, SalesCheckoutCarMenu.class);
					
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
				Toast.makeText(SalesViewReservations.this, message, Toast.LENGTH_LONG).show();
			}
			else {
				// Puts all the strings into the list
				runOnUiThread(new Runnable() {
					public void run() {
						ListAdapter adapter = new SimpleAdapter 
								(SalesViewReservations.this, resList,
								 R.layout.listitem_reservation, new String[] 
										 {TAG_CARID, TAG_GPS, TAG_CHILDSEAT, TAG_KTAG, 
										  TAG_ASSISTANCE, TAG_DINSURANCE, TAG_AINSURANCE,
										  TAG_START, TAG_END, TAG_CITY, TAG_STATE,
										  TAG_PERWEEK, TAG_CARTYPE, TAG_CARMAKE,
										  TAG_CARMODEL, TAG_CARLICENSE },
										  new int[] {R.id.carres_id_list, R.id.carres_GPS_list,
													 R.id.carres_child_seat_list, R.id.carres_k_tag_list,
													 R.id.carres_assistance_list, R.id.carres_dinsurance_list,
													 R.id.carres_ainsurance_list, R.id.carres_start_list,
													 R.id.carres_end_list, R.id.carres_city_list,
													 R.id.carres_state_list, R.id.carres_per_week_list,
													 R.id.carres_type_list, R.id.carres_make_list,
													 R.id.carres_model_list, R.id.carres_license_plate_list
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
		ii = new Intent(SalesViewReservations.this, SalesCheckoutCarMenu.class);
		
		ii.putExtra("email", userEmail);
		ii.putExtra("key", key);
		ii.putExtra("name", name);
		
		startActivity(ii);
		finish();
	}
}
