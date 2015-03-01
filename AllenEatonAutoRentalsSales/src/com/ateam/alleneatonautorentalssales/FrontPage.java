package com.ateam.alleneatonautorentalssales;

import com.ateam.alleneatonautorentalssales.MainActivity.AttemptLogin;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class FrontPage extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		switch (id) {
		case R.id.button_back:
			setContentView(R.layout.fragment_main);
			break;
		case R.id.button_main_logout:
			Intent ii = new Intent(FrontPage.this, MainActivity.class);
			startActivity(ii);		
			finish();	
			break;
		case R.id.button_main_viewinventory:
			setContentView(R.layout.view_inventory);
			break;
		case R.id.button_main_viewuse:
			setContentView(R.layout.cars_in_use);
			break;
		case R.id.button_main_printdetails:
			setContentView(R.layout.print_details);
			break;
		case R.id.button_main_printcontract:
			setContentView(R.layout.print_contract);
			break;
		case R.id.button_main_checkoutcar:
			setContentView(R.layout.checkout_a_car);
			break;
		default:
			break;
		}

	}
}
