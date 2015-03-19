/*
 * XML: service_options_car
 * Show options for each car for Service people
 */

package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ServiceCarMenu extends Activity {
	private String carMake;
	private String carModel;
	private String carID;
	private String key;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_options_car);
		
		Intent getIntent = getIntent();
		carMake = getIntent.getStringExtra("make");
		carModel = getIntent.getStringExtra("model");
		carID = getIntent.getStringExtra("id");
		key = getIntent.getStringExtra("key");
		
		TextView carMakeTitle = (TextView)findViewById(R.id.serviceTextMake);
		TextView carModelTitle = (TextView)findViewById(R.id.serviceTextModel);
		TextView carIDTitle = (TextView)findViewById(R.id.serviceTextCarID);
		
		carMakeTitle.setText(carMake);
		carModelTitle.setText(carModel);
		carIDTitle.setText(carID);
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		Intent ii;
		switch (id) {
			case R.id.viewNotesButtonService:
				
				break;
			case R.id.addNotesButtonService:
				
				break;
			default:
				break;
		}

	}
	
	@Override
	public void onBackPressed() {
		Intent ii;
		if (key.isEmpty()) {
			ii = new Intent(ServiceCarMenu.this, ServiceViewCars.class);
		}
		else {
			ii = new Intent(ServiceCarMenu.this, ServiceSearchCar.class);
			ii.putExtra("key", key);
		}
		startActivity(ii);
		finish();
	}

}
