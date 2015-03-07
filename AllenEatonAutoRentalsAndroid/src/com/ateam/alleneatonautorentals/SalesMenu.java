package com.ateam.alleneatonautorentals;

import com.ateam.alleneatonautorentals.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class SalesMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sales_menu);
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		switch (id) {
		case R.id.button_main_logout:
			Intent ii = new Intent(SalesMenu.this, MainActivity.class);
			startActivity(ii);		
			finish();	
			break;
		default:
			break;
		}

	}
}
