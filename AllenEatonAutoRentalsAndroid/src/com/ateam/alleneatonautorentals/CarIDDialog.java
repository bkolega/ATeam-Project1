package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;

public class CarIDDialog extends DialogFragment {
	private String employeeType; 
	
	public CarIDDialog(String employeeType) {
		this.employeeType = employeeType; 		
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.search_car_id, null))
	    // Add action buttons
	           .setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	                   if(employeeType == "Sales"){
	                	   	Activity temp = getActivity();
	                	   	Intent ii = new Intent(temp, SalesOptionsCar.class); 
	       					startActivity(ii);		
	       					temp.finish();		                	   
	                   }
	                   else if(employeeType == "Service"){
	                	   	Activity temp = getActivity();
	                	   	Intent ii = new Intent(temp, ServiceOptionsCar.class); 
	       					startActivity(ii);		
	       					temp.finish();	
	                   }
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   CarIDDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}
	    
}
