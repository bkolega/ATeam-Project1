package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
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

import com.ateam.alleneatonautorentals.SalesCheckinCar.CheckinCar;
import com.ateam.alleneatonautorentals.SalesViewReservations.LoadAllUserRes;

public class SalesPrintContract extends Activity {
	private String userEmail, name, key, carid, gps, ktag, childseat, assistance, dinsurance, ainsurance,
					start_date, end_date, city, state, eemail, car_type, car_model, car_make, 
					license_plate, license_state, car_year, checkin, card_number;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sales_print_contract);

		Intent getIntent = getIntent();

		userEmail = getIntent.getStringExtra("email"); 
		name= getIntent.getStringExtra("name"); 
		key = getIntent.getStringExtra("key");

		carid = getIntent.getStringExtra("carid"); 
		gps = getIntent.getStringExtra("gps");
		ktag = getIntent.getStringExtra("ktag");
		childseat = getIntent.getStringExtra("child_seat");
		assistance = getIntent.getStringExtra("assistance"); 
		dinsurance = getIntent.getStringExtra("dinsurance"); 
		ainsurance = getIntent.getStringExtra("ainsurance"); 
		start_date = getIntent.getStringExtra("start_date");
		end_date = getIntent.getStringExtra("end_date");
		city = getIntent.getStringExtra("city"); 
		state = getIntent.getStringExtra("state"); 
		eemail = getIntent.getStringExtra("eemail"); 
		car_type = getIntent.getStringExtra("cartype");
		car_model = getIntent.getStringExtra("carmodel");
		car_make = getIntent.getStringExtra("carmake");
		license_plate = getIntent.getStringExtra("lplate");
		license_state = getIntent.getStringExtra("lstate"); 
		car_year = getIntent.getStringExtra("caryear");
		checkin = getIntent.getStringExtra("checkin");
		card_number = getIntent.getStringExtra("cardnumber"); 
			
		TextView tview = (TextView)findViewById(R.id.contract_user_name);
		tview.setText(name);
		tview = (TextView)findViewById(R.id.contract_user_email);
		tview.setText(userEmail);
		tview = (TextView)findViewById(R.id.contract_card_number);
		tview.setText(card_number);
		
		tview = (TextView)findViewById(R.id.contract_employee_email);
		tview.setText(eemail);
		
		tview = (TextView)findViewById(R.id.contract_car_id);
		tview.setText(carid);
		tview = (TextView)findViewById(R.id.contract_car_type);
		tview.setText(car_type);
		tview = (TextView)findViewById(R.id.contract_car_make);
		tview.setText(car_make);
		tview = (TextView)findViewById(R.id.contract_car_model);
		tview.setText(car_model);
		tview = (TextView)findViewById(R.id.contract_car_year);
		tview.setText(car_year);
		tview = (TextView)findViewById(R.id.contract_license_plate);
		tview.setText(license_plate);
		tview = (TextView)findViewById(R.id.contract_license_plate_state);
		tview.setText(license_state);
		
		tview = (TextView)findViewById(R.id.contract_start_date);
		tview.setText(start_date);
		tview = (TextView)findViewById(R.id.contract_end_date);
		tview.setText(end_date);
		tview = (TextView)findViewById(R.id.contract_city_reservation);
		tview.setText(city);
		tview = (TextView)findViewById(R.id.contract_state_reservation);
		tview.setText(state);
		//tview = (TextView)findViewById(R.id.contract_price);
		//tview.setText(userEmail);
		tview = (TextView)findViewById(R.id.contract_gps);
		tview.setText(gps);
		tview = (TextView)findViewById(R.id.contract_ktag);
		tview.setText(ktag);
		tview = (TextView)findViewById(R.id.contract_child_seat);
		tview.setText(childseat);
		tview = (TextView)findViewById(R.id.contract_assistence);
		tview.setText(assistance);
		tview = (TextView)findViewById(R.id.contract_dinsurance);
		tview.setText(dinsurance);
		tview = (TextView)findViewById(R.id.contract_ainsurance);
		tview.setText(ainsurance);
		tview = (TextView)findViewById(R.id.contract_checkin_date);
		tview.setText(checkin);

	}

	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onBackPressed() {
		Intent ii;
		ii = new Intent(SalesPrintContract.this, SalesViewContracts.class);

		ii.putExtra("email", userEmail);
		ii.putExtra("key", key);
		ii.putExtra("name", name);

		startActivity(ii);		
		finish();			
	}
}
