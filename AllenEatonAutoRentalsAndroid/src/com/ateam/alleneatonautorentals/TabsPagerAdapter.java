/*
 * Allow swipe tabs
 */

package com.ateam.alleneatonautorentals;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
	private String employeeType; 
    public TabsPagerAdapter(FragmentManager fm, String employeeType) {
        super(fm);
        this.employeeType = employeeType; 
    }
 
    @Override
    public Fragment getItem(int index) {
    	if(employeeType.equals("Sales")) {
    		switch (index) {
    		case 0:
    			// Sales fragment activity
    			return new SalesMenu();
    		case 1:
    			// Service fragment activity
    			return new ServiceMenu();
    		}
 
    		return null;
    	}
    	else {
    		switch (index) {
    		case 0:
    			// Sales fragment activity
    			return new ServiceMenu();
    		case 1:
    			// Service fragment activity
    			return new SalesMenu();
    		}
 
    		return null;
    	}
    }
 
    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
 
}