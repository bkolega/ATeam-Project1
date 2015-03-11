package com.ateam.alleneatonautorentals;

import com.ateam.alleneatonautorentals.R;

//import android.app.Activity;
//import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
//import android.support.v4.app.FragmentActivity;
//import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;

public class SalesMenu extends Fragment {
 
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
 
        View rootView = inflater.inflate(R.layout.sales_menu, container, false);
         
        return rootView;
    }
	/*@Override
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
		Intent ii;
		switch (id) {
		case R.id.button_main_logout:
			//ii = new Intent(SalesMenu.this, MainActivity.class);
			//startActivity(ii);		
			//finish();	
			break;
		case R.id.salesViewCars:
			//ii = new Intent(SalesMenu.this, ViewCarsUse.class);
			//startActivity(ii);		
			//finish();	
			break;
		case R.id.salesSearchUser:
			//ii = new Intent(SalesMenu.this, CheckOutCar.class);
			//startActivity(ii);		
			//finish();	
			break;
		case R.id.salesViewUsers:
			//ii = new Intent(SalesMenu.this, CheckOutCar.class);
			//startActivity(ii);		
			//finish();	
			break;
		default:
			break;
		}

	}*/
}
