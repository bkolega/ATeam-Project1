package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SalesCheckoutCar extends Activity {
	private String userEmail;
	private String name;
	private String key;
	private String reservation;
	private String carid;
	private CheckBox cb_gps;
	private CheckBox cb_ktag; 
	private CheckBox cb_assistance; 
	private CheckBox cb_dinsurance; 
	private CheckBox cb_ainsurance;
	private EditText ed_childseat;			
	private EditText et_start;
	private EditText et_end;
	private EditText et_state; 
	private EditText et_city;
	private RadioGroup rg_per_week;
	private RadioButton rb_per_week; 
	private RadioButton rb_per_day;
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String CHECKOUT_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/checkout_car.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_car);
		
		Intent getIntent = getIntent();
		reservation = getIntent.getStringExtra("reservation");
		userEmail = getIntent.getStringExtra("email"); 
		name= getIntent.getStringExtra("name"); 
		key = getIntent.getStringExtra("key");
		carid = getIntent.getStringExtra("carid");
						
		cb_gps = (CheckBox)findViewById(R.id.checkBox_gps);
		cb_ktag = (CheckBox)findViewById(R.id.checkBox_ktag); 
		cb_assistance = (CheckBox)findViewById(R.id.checkBox_roadside); 
		cb_dinsurance = (CheckBox)findViewById(R.id.checkBox_damage_insurance); 
		cb_ainsurance = (CheckBox)findViewById(R.id.checkBox_accident_insurance);
		ed_childseat = (EditText)findViewById(R.id.child_seat_number);			
		et_start = (EditText)findViewById(R.id.start_date_reservation);
		et_end = (EditText)findViewById(R.id.end_date_reservation);
		et_state = (EditText)findViewById(R.id.state_reservation_field); 
		et_city =  (EditText)findViewById(R.id.city_reservation_field);
		rg_per_week = (RadioGroup)findViewById(R.id.radio_type_payment);
		rb_per_week = (RadioButton)findViewById(R.id.radio_week); 
		rb_per_day = (RadioButton)findViewById(R.id.radio_day);
		
		if(reservation.equals("1")){
			String gps = getIntent.getStringExtra("gps");
			String child_seat = getIntent.getStringExtra("child_seat");
			String ktag = getIntent.getStringExtra("ktag");
			String assistance = getIntent.getStringExtra("assistance");
			String dinsurance = getIntent.getStringExtra("dinsurance");
			String ainsurance = getIntent.getStringExtra("ainsurance");			
			String start_date_full = getIntent.getStringExtra("start_date"); 
			String end_date_full = getIntent.getStringExtra("end_date"); 
			String per_week_full = getIntent.getStringExtra("per_week");
			String state_full = getIntent.getStringExtra("state"); 
			String city_full = getIntent.getStringExtra("city");
						
			int begin, end;
			
			end = child_seat.length(); 
			begin = child_seat.lastIndexOf(":")+2; 
			String cs_num = child_seat.substring(begin+1, end);
			
			begin = state_full.indexOf(":")+2;
			end = state_full.length(); 
			String state = state_full.substring(begin, end);
			
			begin = city_full.indexOf(":")+2;
			end = city_full.length(); 
			String city = city_full.substring(begin, end);
			
			begin = start_date_full.indexOf(":")+2;
			end = start_date_full.length(); 
			String start_date = start_date_full.substring(begin, end);
			
			begin = end_date_full.indexOf(":")+2;
			end = end_date_full.length(); 
			String end_date = end_date_full.substring(begin, end);
			
			ed_childseat.setText(cs_num); 
			
			if(gps.contains("1")){
				cb_gps.setChecked(true);
			}
			else if(ktag.contains("1")){
				cb_ktag.setChecked(true);
			}
			else if(assistance.contains("1")){
				cb_assistance.setChecked(true);
			}
			else if(dinsurance.contains("1")){
				cb_dinsurance.setChecked(true);
			}
			else if(ainsurance.contains("1")){
				cb_ainsurance.setChecked(true);
			}
			
			et_start.setText(start_date);
			et_end.setText(end_date);
			
			if(per_week_full.contains("1")){
				rb_per_week.setChecked(true); 
			}
			else { 
				rb_per_day.setChecked(true); 
			}
			
			et_state.setText(state);
			et_city.setText(city);
				
			
		}
	
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		
		switch (id) {
			case R.id.check_out_button: 
				new CheckoutCar().execute();
				break;
			default:
				break;
		}

	}
	
	class CheckoutCar extends AsyncTask <String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(SalesCheckoutCar.this);
			progressDialog.setMessage("Attempting to check out car...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			int success;
			
			String gps, ktag, assistance, dinsurance, ainsurance;
			String child_seat, start_date, end_date, state, city, per_week;
			if(cb_gps.isChecked()) {
				gps = "1"; 
			}
			else {
				gps = "0";
			}
			
			if(cb_ktag.isChecked()) {
				ktag = "1"; 
			}
			else {
				ktag = "0";
			}
			
			if(cb_assistance.isChecked()) {
				assistance = "1"; 
			}
			else {
				assistance = "0";
			}
			
			if(cb_dinsurance.isChecked()) {
				dinsurance = "1"; 
			}
			else {
				dinsurance = "0";
			}
			
			if(cb_ainsurance.isChecked()) {
				ainsurance = "1"; 
			}
			else {
				ainsurance = "0";
			}
			
			child_seat = ed_childseat.getText().toString();			
			start_date = et_start.getText().toString();
			end_date = et_end.getText().toString();
			state = et_state.getText().toString(); 
			city = et_city.getText().toString();
			
			int radio_button_id = rg_per_week.getCheckedRadioButtonId();
			
			if(radio_button_id == rb_per_week.getId())
				per_week = "1"; 
			else
				per_week = "0";
			
			SessionManager session = new SessionManager(getApplicationContext());
			String empemail = session.getUsername();
			
			try {
				List <NameValuePair> params = new ArrayList <NameValuePair>();
				
				params.add(new BasicNameValuePair("username", userEmail));
				params.add(new BasicNameValuePair("carid", carid.substring(8)));
				params.add(new BasicNameValuePair("gps", gps));
				params.add(new BasicNameValuePair("childseat", child_seat));
				params.add(new BasicNameValuePair("ktag", ktag));
				params.add(new BasicNameValuePair("assistance", assistance));
				params.add(new BasicNameValuePair("dinsurance", dinsurance));
				params.add(new BasicNameValuePair("ainsurance", ainsurance));
				params.add(new BasicNameValuePair("start", start_date));
				params.add(new BasicNameValuePair("end", end_date));
				params.add(new BasicNameValuePair("city", city));
				params.add(new BasicNameValuePair("state", state));
				params.add(new BasicNameValuePair("perweek", per_week));
				params.add(new BasicNameValuePair("empemail", empemail));
				
				Log.d("request!", "starting");
				
				JSONObject json = jsonParser.makeHttpRequest(CHECKOUT_URL, "POST", params);
				Log.d("Checkout Attempt", json.toString());
				
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Car successfully checked out!", json.toString());
					
					Intent ii;
					ii = new Intent(SalesCheckoutCar.this, SalesCheckoutCarMenu.class);
					
					ii.putExtra("email", userEmail);
					ii.putExtra("key", key);
					ii.putExtra("name", name);
					
					startActivity(ii);		
					finish();
				}
				return json.getString(TAG_MESSAGE);
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onPostExecute (String message) {
			progressDialog.dismiss();
			if (message != null) {
				Toast.makeText(SalesCheckoutCar.this, message, Toast.LENGTH_LONG).show();
			}
		}
		
	}
	
	@Override
	public void onBackPressed() {
		Intent ii;
		ii = new Intent(SalesCheckoutCar.this, SalesCheckoutCarMenu.class);
		
		ii.putExtra("email", userEmail);
		ii.putExtra("key", key);
		ii.putExtra("name", name);
		
		startActivity(ii);		
		finish();			
	}

}