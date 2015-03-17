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
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ViewCars extends ListActivity {
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> carsList;
	
	private static final String CARS_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_cars.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_CARS = "cars";
	private static final String TAG_ID = "id";
	private static final String TAG_TYPE = "type";
	private static final String TAG_MAKE = "make";
	private static final String TAG_MODEL = "model";
	private static final String TAG_YEAR = "year";
	private static final String TAG_LICENSE_PLATE = "license_plate";
	private static final String TAG_LICENSE_STATE = "license_state";
	private static final String TAG_MILES = "miles";
	private static final String TAG_OIL_DATE = "oil_change_date";
	private static final String TAG_OIL_MILE = "oil_change_mile";
	private static final String TAG_TIRE_DATE = "tire_rotation_date";
	private static final String TAG_TIRE_MILE = "tire_rotation_mile";
	private static final String TAG_FILTER_DATE = "air_filters_date";
	private static final String TAG_FILTER_MILE = "air_filters_mile";
	private static final String TAG_LOCATION = "location";
	
	String employeeType;
	
	JSONArray cars = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_inventory);

		Intent getIntent = getIntent();
		employeeType = getIntent.getStringExtra("employeeType");
		
		carsList = new ArrayList<HashMap<String, String>>();
	
		new LoadAllCars().execute();
	
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	class LoadAllCars extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ViewCars.this);
			progressDialog.setMessage("Loading inventory...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			JSONObject json = jsonParser.makeHttpRequest(CARS_URL, "GET", params);
			
			Log.d("All Cars: ", json.toString());
			
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					cars = json.getJSONArray(TAG_CARS);
					
					// Go through each users in JSON
					for (int i = 0; i < cars.length(); i++) {
						JSONObject c = cars.getJSONObject(i);
					
						// Get the string from JSON
						String id = c.getString(TAG_ID);
						String type = c.getString(TAG_TYPE);
						String make = c.getString(TAG_MAKE);
						String model = c.getString(TAG_MODEL);
						String year = c.getString(TAG_YEAR);
						String license_state = c.getString(TAG_LICENSE_STATE);
						String license_plate = c.getString(TAG_LICENSE_PLATE);
						String miles = c.getString(TAG_MILES);
						String oil_date = c.getString(TAG_OIL_DATE);
						String oil_mile = c.getString(TAG_OIL_MILE);
						String tire_date = c.getString(TAG_TIRE_DATE);
						String tire_mile = c.getString(TAG_TIRE_MILE);
						String filter_date = c.getString(TAG_FILTER_DATE);
						String filter_mile = c.getString(TAG_FILTER_MILE);
						String location = c.getString(TAG_LOCATION);
						
						
						HashMap<String, String> map = new HashMap<String, String>();
						
						// Store the strings
						map.put(TAG_ID, "Car ID: ".concat(id));
						map.put(TAG_TYPE, "Car Type: ".concat(type));
						map.put(TAG_MAKE, make);
						map.put(TAG_MODEL, "Car Model: ".concat(model));
						map.put(TAG_YEAR, "Car Year: ".concat(year));
						map.put(TAG_LICENSE_PLATE, "License Plate: ".concat(license_plate));
						map.put(TAG_LICENSE_STATE, "Issued In: ".concat(license_state));
						map.put(TAG_MILES, "Miles: ".concat(miles));
						map.put(TAG_OIL_DATE, "Oil Change Date: ".concat(oil_date));
						map.put(TAG_OIL_MILE, "Oil Use Miles: ".concat(oil_mile));
						map.put(TAG_TIRE_DATE, "Tire Rotation Date: ".concat(tire_date));
						map.put(TAG_TIRE_MILE, "Tire Miles: ".concat(tire_mile));
						map.put(TAG_FILTER_DATE, "Air Filter Replacement Date: ".concat(filter_date));
						map.put(TAG_FILTER_MILE, "Air Filter Miles: ".concat(filter_mile));
						map.put(TAG_LOCATION, "Car's Current Location: ".concat(location));
						
						carsList.add(map);
					}
					
				}
				else {
					Intent  ii = new Intent(ViewCars.this, MainMenu.class);
					Bundle b = new Bundle();
					b.putString("employeeType", employeeType); 
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
				Toast.makeText(ViewCars.this, message, Toast.LENGTH_LONG).show();
			}
			else {
				// Puts all the strings into the list
				runOnUiThread(new Runnable() {
					public void run() {
						ListAdapter adapter = new SimpleAdapter 
								(ViewCars.this, carsList,
								 R.layout.listitem_car, new String[] 
										 {TAG_ID, TAG_TYPE, TAG_MAKE, TAG_MODEL, 
										  TAG_YEAR, TAG_LICENSE_PLATE, TAG_LICENSE_STATE,
										  TAG_MILES, TAG_OIL_DATE, TAG_OIL_MILE, TAG_TIRE_DATE,
										  TAG_TIRE_MILE, TAG_FILTER_DATE, TAG_FILTER_MILE,
										  TAG_LOCATION },
										  new int[] {R.id.car_id_list, R.id.car_type_list,
													 R.id.car_make_list, R.id.car_model_list,
													 R.id.car_year_list, R.id.car_license_plate_list,
													 R.id.car_license_state_list, R.id.car_miles_list,
													 R.id.car_oil_date_list, R.id.car_oil_mile_list,
													 R.id.car_tire_date_list, R.id.car_tire_mile_list,
													 R.id.car_filter_date_list, R.id.car_filter_mile_list,
													 R.id.car_location_list
								});
						setListAdapter(adapter);
					}
				});
			}
		}
		
	}
	
	@Override
	public void onBackPressed() {
		Intent  ii = new Intent(ViewCars.this, MainMenu.class);
		Bundle b = new Bundle();
		b.putString("employeeType", employeeType); 
		ii.putExtras(b);
		
		startActivity(ii);					
		finish();				
	}
	
}


