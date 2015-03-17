package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SalesSearchCar extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sales_options_car);
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
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
		Intent ii;
		ii = new Intent(SalesSearchCar.this, MainMenu.class);
		startActivity(ii);		
		finish();			
	}

}
