package com.ateam.alleneatonautorentals;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ServiceAddNote extends Activity{
	private String carID;
	private String carMake;
	private String carModel;
	private String key;
	
	private EditText textCarNote;
	private EditText textCustomerEmail;
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_notes);
		
		Intent getIntent = getIntent();
		carID = getIntent.getStringExtra("id");
		carMake = getIntent.getStringExtra("make"); 
		carModel = getIntent.getStringExtra("model"); 
		key = getIntent.getStringExtra("key"); 
		
		TextView carIDTitle = (TextView)findViewById(R.id.textCarId);
		//TextView carModelTitle = (TextView)findViewById(R.id.textCarModel);
		//TextView carMakeTitle = (TextView)findViewById(R.id.textCarMake);
		
		carIDTitle.setText(carID);
		//carModelTitle.setText(carModel);
		//carMakeTitle.setText(carMake); 
		
	}
	
	public void clickButtons(View v) {
		final int id = v.getId();
		Intent ii;
		switch (id) {
			case R.id.buttonAddNote:
				textCarNote = (EditText)findViewById(R.id.editTextCarHistory);
				textCustomerEmail = (EditText)findViewById(R.id.editCustomerEmail);
				
				DialogFragment newFragment = new NoteSuccessDialog(carMake, carModel, carID, key);
			    newFragment.show(getFragmentManager(), "dialog");	
								
				break; 
				
			default:
				break;
		}

	}
	
	@Override
	public void onBackPressed() {
		Intent ii;
		
		ii = new Intent(ServiceAddNote.this, ServiceCarMenu.class);
		
		ii.putExtra("make", carMake);
		ii.putExtra("model", carModel);
		ii.putExtra("id", carID);
		ii.putExtra("key", key);
		
		startActivity(ii);
		finish();
	}
	

}
