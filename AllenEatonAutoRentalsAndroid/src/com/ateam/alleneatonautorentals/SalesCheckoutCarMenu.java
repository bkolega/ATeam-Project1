/*
 * XML: checkout_car_menu
 * Menu for different ways to checkout cars
 */

package com.ateam.alleneatonautorentals;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;

public class SalesCheckoutCarMenu extends FragmentActivity {
	private String userEmail;
	private String name;
	private String key;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.checkout_car_menu);
		
		Intent getIntent = getIntent();
		userEmail = getIntent.getStringExtra("email");
		key = getIntent.getStringExtra("key");
		name = getIntent.getStringExtra("name");
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		Intent ii;
		DialogFragment newFragment;
		switch (id) {
			case R.id.searchCarButtonSalesMenu:
				newFragment = new SearchCarDialog("Sales", userEmail, key, name);
				newFragment.show(getSupportFragmentManager(), "carid");
				break;
			case R.id.viewCarsButtonSalesMenu:
				ii = new Intent(SalesCheckoutCarMenu.this, SalesViewCars.class);
				
				ii.putExtra("email", userEmail);
				ii.putExtra("key", key);
				ii.putExtra("name", name);
				
				startActivity(ii);
				finish();	
				break;
			case R.id.viewReservationsButtonSalesMenu:
				ii = new Intent(SalesCheckoutCarMenu.this, SalesViewReservations.class);
				
				ii.putExtra("email", userEmail);
				ii.putExtra("key", key);
				ii.putExtra("name", name);
				
				startActivity(ii);
				finish();
				break;
			default:
				break;
		}
	}
	
	@Override
	public void onBackPressed() {
		Intent ii;
		ii = new Intent(SalesCheckoutCarMenu.this, SalesUserMenu.class);
		
		ii.putExtra("email", userEmail);
		ii.putExtra("key", key);
		ii.putExtra("name", name);
		
		startActivity(ii);
		finish();	
	}
}
