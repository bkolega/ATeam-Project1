/*
 *  XML: search_car.xml
 *  Dialog for searching for cars
 */

package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class SearchCarDialog extends DialogFragment {
	private String employeeType; 
	private String userEmail;
	private String keyUser;
	private String name;
	private EditText textSearch;
	
	public SearchCarDialog(String employeeType, String userEmail, 
			String keyUser, String name) {
		this.employeeType = employeeType; 
		this.userEmail = userEmail;
		this.keyUser = keyUser;
		this.name = name;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    final View view = inflater.inflate(R.layout.search_car, null);
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(view)
	    // Add action buttons
	           .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   
	            	   textSearch = (EditText)view.findViewById(R.id.search_car_edit);
	            	   String keyword = textSearch.getText().toString();
	            	   
	            	   if(employeeType == "Sales"){
	                	   	Activity temp = getActivity();
	                	   	Intent ii = new Intent(temp, SalesSearchCar.class);
	                	   	ii.putExtra("key", keyword);
	                	   	ii.putExtra("email", userEmail);
	                	   	ii.putExtra("keyUser", keyUser);
	                	   	ii.putExtra("name", name);
	       					startActivity(ii);		
	       					temp.finish();		                	   
	                   }
	                   else if(employeeType == "Service"){
	                	   	Activity temp = getActivity();
	                	   	Intent ii = new Intent(temp, ServiceSearchCar.class); 
	                	   	ii.putExtra("key", keyword);
	       					startActivity(ii);		
	       					temp.finish();	
	                   }
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   SearchCarDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}
	    
}
