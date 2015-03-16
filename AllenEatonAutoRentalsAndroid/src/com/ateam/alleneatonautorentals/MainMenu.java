package com.ateam.alleneatonautorentals;

//import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
//import android.annotation.SuppressLint;
//import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;


public class MainMenu extends FragmentActivity implements
ActionBar.TabListener {
	
	private static String employeeType;
	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = new String[2];
     
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_sales_service);
        
        Bundle bundleType = getIntent().getExtras();
        if(bundleType != null)
        	employeeType = bundleType.getString("employeeType");
                
        if(employeeType.equals("Sales")) {
        	tabs[0] = "Sales"; 
        	tabs[1] = "Service"; 
        }
        else { 
        	tabs[0] = "Service";
        	tabs[1] = "Sales";
        }
 
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), employeeType);
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS); 
         
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
        
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
       	 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
    
	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		 // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
		
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		Intent ii;
		DialogFragment newFragment;
		switch (id) {
			case R.id.logOutButtonService:
				ii = new Intent(MainMenu.this, MainActivity.class);
				startActivity(ii);		
				finish();	
				break;
				
			case R.id.logoutButtonSalesMenu:
				ii = new Intent(MainMenu.this, MainActivity.class);
				startActivity(ii);		
				finish();	
				break;
				
			case R.id.searchCarButtonService:
				newFragment = new CarIDDialog("Service");
			    newFragment.show(getSupportFragmentManager(), "carid");	
			    break;
				
			case R.id.searchCarButtonSalesMenu:
				newFragment = new CarIDDialog("Sales");
			    newFragment.show(getSupportFragmentManager(), "carid");
			    break;
			
			case R.id.viewCarsButtonService: 
				ii = new Intent(MainMenu.this, ViewCars.class);
				startActivity(ii);		
				finish();	
				break;
				
			case R.id.viewCarsButtonSalesMenu:
				ii = new Intent(MainMenu.this, ViewCars.class);
				startActivity(ii);		
				finish();	
				break;
				
			case R.id.searchUserButtonSalesMenu: 
				newFragment = new UserIDDialog();
			    newFragment.show(getSupportFragmentManager(), "userid");
			    break;
			    
			case R.id.viewUserButtonSalesMenu: 
				ii = new Intent(MainMenu.this, ViewUsers.class);
				startActivity(ii);		
				finish();	
				break;
				
			default:
				break;
		}

	}

}
