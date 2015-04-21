/*
 * XML: sales_options_user
 * Show options for each user for Sales people
 */

package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SalesUserMenu extends Activity {
	private String userEmail;
	private String name;
	private String key;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sales_options_user);
		
		Intent getIntent = getIntent();
		userEmail = getIntent.getStringExtra("email");
		key = getIntent.getStringExtra("key");
		name = getIntent.getStringExtra("name");
		
		TextView userTitle = (TextView)findViewById(R.id.userTitleTextSales);
		userTitle.setText(name);
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		Intent ii;
		switch (id) {
			case R.id.printContractButtonSales:
				ii = new Intent(SalesUserMenu.this, SalesViewContracts.class);
				
				ii.putExtra("email", userEmail);
				ii.putExtra("key", key);
				ii.putExtra("name", name);
				
				startActivity(ii);		
				finish();
				break;
			case R.id.CheckinCarButtonSales:
				ii = new Intent(SalesUserMenu.this, SalesViewCheckedOutCars.class);
				
				ii.putExtra("email", userEmail);
				ii.putExtra("key", key);
				ii.putExtra("name", name);
				
				startActivity(ii);		
				finish();
				break;
			case R.id.CheckoutCarButtonSales:
				ii = new Intent(SalesUserMenu.this, SalesCheckoutCarMenu.class);
				
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
		if (key.isEmpty()) {
			ii = new Intent(SalesUserMenu.this, SalesViewUsers.class);
		}
		else {
			ii = new Intent(SalesUserMenu.this, SalesSearchUser.class);
			ii.putExtra("key", key);		
		}
		startActivity(ii);					
		finish();	
	}
}
