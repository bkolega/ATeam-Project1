package com.ateam.alleneatonautorentalssales;

//import com.ateam.alleneatonautorentalssales.MainActivity.AttemptLogin;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class ServiceMenu extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service_menu);
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		switch (id) {
			case R.id.logOutButton:
				Intent ii = new Intent(ServiceMenu.this, MainActivity.class);
				startActivity(ii);		
				finish();	
				break;
			default:
				break;
		}

	}
}
