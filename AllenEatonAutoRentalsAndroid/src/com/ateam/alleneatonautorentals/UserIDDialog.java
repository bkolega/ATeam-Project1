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

public class UserIDDialog extends DialogFragment {
	private EditText textSearch;	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();
	    final View view = inflater.inflate(R.layout.search_user_id, null);
	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(view);
	    // Add action buttons
	    builder.setPositiveButton(R.string.search, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	   
	            	   textSearch = (EditText)view.findViewById(R.id.search_user_edit);
	            	   String keyword = textSearch.getText().toString();
	            	   
	            	   Activity temp = getActivity();
 
	            	   Intent ii = new Intent(temp, SalesSearchUser.class);
	            	   ii.putExtra("key", keyword);
	            	   
	            	   startActivity(ii);		
	            	   temp.finish();		                	   
	                   
	               }
	           })
	           .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int id) {
	            	   UserIDDialog.this.getDialog().cancel();
	               }
	           });      
	    return builder.create();
	}

}
