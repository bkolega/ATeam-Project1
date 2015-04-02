package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

public class NoteSuccessDialog extends DialogFragment {
	private String carMake; 
	private String carModel;
	private String carID;
	private String key;
	
	public NoteSuccessDialog(String carMake, String carModel, 
			String carID, String key) {
		this.carMake = carMake; 
		this.carModel = carModel;
		this.carID = carID;
		this.key = key;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    final View view = inflater.inflate(R.layout.add_note_success, null);
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(view).setTitle("Sucess!")
	    			.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
	        	   public void onClick(DialogInterface dialog, int which) {
	        		   	Activity temp = getActivity();
	        		   	Intent ii = new Intent(temp, ServiceCarMenu.class);
	        			
	        			ii.putExtra("make", carMake);
	        			ii.putExtra("model", carModel);
	        			ii.putExtra("id", carID);
	        			ii.putExtra("key", key);
	        			
	        			startActivity(ii);
	        			temp.finish();	                	   		     
	    
	        	   }
	           });
	    return builder.create();
	}
	    
}
