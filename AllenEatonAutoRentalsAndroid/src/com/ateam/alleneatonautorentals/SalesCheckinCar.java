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
	private String userEmail;
	private String name;
	private String key;
	
	
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkin_car);
		
		Intent getIntent = getIntent();
		
		userEmail = getIntent.getStringExtra("email"); 
		name= getIntent.getStringExtra("name"); 
		key = getIntent.getStringExtra("key");
		
		String carid = getIntent.getStringExtra("carid");
		String cartype = getIntent.getStringExtra("cartype");
		String gps = getIntent.getStringExtra("gps");
		String child_seat = getIntent.getStringExtra("child_seat");
		String ktag = getIntent.getStringExtra("ktag");
		String assistance = getIntent.getStringExtra("assistance");
		String dinsurance = getIntent.getStringExtra("dinsurance");
		String ainsurance = getIntent.getStringExtra("ainsurance");			
		String start_date = getIntent.getStringExtra("start_date"); 
		String end_date = getIntent.getStringExtra("end_date"); 
		String per_week = getIntent.getStringExtra("per_week");
		String state = getIntent.getStringExtra("state"); 
		String city = getIntent.getStringExtra("city");
						
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
		tview = (TextView)findViewById(R.id.checkin_rental_type);
		tview.setText(per_week);
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
				break;
			default:
				break;
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
