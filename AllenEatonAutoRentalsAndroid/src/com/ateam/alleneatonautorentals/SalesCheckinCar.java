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
import android.widget.TextView;
import android.widget.Toast;

import com.ateam.alleneatonautorentals.SalesCheckoutCar.CheckoutCar;

public class SalesCheckinCar extends Activity {
	private String userEmail, name, key, carid, cartype, gps, child_seat, ktag,
				   assistance, dinsurance, ainsurance, start_date, end_date,
				   state, city;
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String CHECKIN_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/checkin_car.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkin_car);
		
		Intent getIntent = getIntent();
		
		userEmail = getIntent.getStringExtra("email"); 
		name= getIntent.getStringExtra("name"); 
		key = getIntent.getStringExtra("key");
		
		carid = getIntent.getStringExtra("carid");
		cartype = getIntent.getStringExtra("cartype");
		gps = getIntent.getStringExtra("gps");
		child_seat = getIntent.getStringExtra("child_seat");
		ktag = getIntent.getStringExtra("ktag");
		assistance = getIntent.getStringExtra("assistance");
		dinsurance = getIntent.getStringExtra("dinsurance");
		ainsurance = getIntent.getStringExtra("ainsurance");			
		start_date = getIntent.getStringExtra("start_date"); 
		end_date = getIntent.getStringExtra("end_date"); 
		state = getIntent.getStringExtra("state"); 
		city = getIntent.getStringExtra("city");
						
		TextView tview = (TextView)findViewById(R.id.checkin_carID);
		tview.setText(carid);
		tview = (TextView)findViewById(R.id.checkin_carType);
		tview.setText(cartype);
		tview = (TextView)findViewById(R.id.checkin_gps);
		tview.setText(gps);
		tview = (TextView)findViewById(R.id.checkin_child_seat);
		tview.setText(child_seat);
		tview = (TextView)findViewById(R.id.checkin_ktag);
		tview.setText(ktag);
		tview = (TextView)findViewById(R.id.checkin_road_assistance);
		tview.setText(assistance);
		tview = (TextView)findViewById(R.id.checkin_damage_insurance);
		tview.setText(dinsurance);
		tview = (TextView)findViewById(R.id.checkin_personal_insurance);
		tview.setText(ainsurance);
		tview = (TextView)findViewById(R.id.checkin_start_date);
		tview.setText(start_date);
		tview = (TextView)findViewById(R.id.checkin_end_date);
		tview.setText(end_date);
		tview = (TextView)findViewById(R.id.checkin_state);
		tview.setText(state);
		tview = (TextView)findViewById(R.id.checkin_city);
		tview.setText(city);
	
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		
		switch (id) {
			case R.id.checkin_car_button: 
				new CheckinCar().execute();
				break;
			default:
				break;
		}

	}
	
	class CheckinCar extends AsyncTask <String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(SalesCheckinCar.this);
			progressDialog.setMessage("Attempting to check in car...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			int success;
			
			try {
				List <NameValuePair> params = new ArrayList <NameValuePair>();
				
				params.add(new BasicNameValuePair("username", userEmail));
				params.add(new BasicNameValuePair("carid", carid.substring(8)));
			
				Log.d("request!", "starting");
				
				JSONObject json = jsonParser.makeHttpRequest(CHECKIN_URL, "POST", params);
				Log.d("Checkin Attempt", json.toString());
				
				success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					Log.d("Car successfully checked in!", json.toString());
					
					Intent ii;
					ii = new Intent(SalesCheckinCar.this, SalesUserMenu.class);
					
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
				Toast.makeText(SalesCheckinCar.this, message, Toast.LENGTH_LONG).show();
			}
		}
	}
		
	@Override
	public void onBackPressed() {
		Intent ii;
		ii = new Intent(SalesCheckinCar.this, SalesViewCheckedOutCars.class);
		
		ii.putExtra("email", userEmail);
		ii.putExtra("key", key);
		ii.putExtra("name", name);
		
		startActivity(ii);		
		finish();			
	}

}
