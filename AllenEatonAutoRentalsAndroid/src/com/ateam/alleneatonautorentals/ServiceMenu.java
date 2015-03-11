package com.ateam.alleneatonautorentals;

//import com.ateam.alleneatonautorentalssales.MainActivity.AttemptLogin;

import com.ateam.alleneatonautorentals.R;

//import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
//import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class ServiceMenu extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.service_menu, container, false);
         
        return rootView;
    }

	/*@Override
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
		Intent ii;
		switch (id) {
			case R.id.logOutButton:
				ii = new Intent(ServiceMenu.this, MainActivity.class);
				startActivity(ii);		
				finish();	
				break;
			case R.id.serviceViewCars:
				//ii = new Intent(ServiceMenu.this, ViewCarsUse.class);
				//startActivity(ii);		
				//finish();	
				break;
			case R.id.serviceSearchCar:
				//ii = new Intent(ServiceMenu.this, CheckInCar.class);
				//startActivity(ii);		
				//finish();	
				break;
			default:
				break;
		}

	}*/
    
}
