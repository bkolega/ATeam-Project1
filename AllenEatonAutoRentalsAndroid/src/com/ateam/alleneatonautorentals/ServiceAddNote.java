package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
//import android.app.DialogFragment;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ServiceAddNote extends Activity{
	private String carID;
	private String carMake;
	private String carModel;
	private String key;
	
	private EditText textCarNote;
	private EditText textCustomerEmail;
	
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	private static final String NOTES_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/add_notes.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_MESSAGE = "message";
	
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
		
		switch (id) {
			case R.id.buttonAddNote:
				textCarNote = (EditText)findViewById(R.id.editTextCarHistory);
				textCustomerEmail = (EditText)findViewById(R.id.editCustomerEmail);
				
				//DialogFragment newFragment = new NoteSuccessDialog(carMake, carModel, carID, key);
			    //newFragment.show(getFragmentManager(), "dialog");	
				
				new AddNotes().execute();
				break; 
				
			default:
				break;
		}

	}
	
	class AddNotes extends AsyncTask <String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ServiceAddNote.this);
			progressDialog.setMessage("Adding Note...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(true);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			int success;
			
			String note = textCarNote.getText().toString();
			String customerEmail = textCustomerEmail.getText().toString();
			
			try {
				List <NameValuePair> params = new ArrayList <NameValuePair>();
				
				params.add(new BasicNameValuePair("carid", carID.substring(8)));
				params.add(new BasicNameValuePair("history", note));
				params.add(new BasicNameValuePair("email", customerEmail));
				
				Log.d("request!", "starting");
				
				JSONObject json = jsonParser.makeHttpRequest(NOTES_URL, "POST", params);
				Log.d("Add Notes Attempt", json.toString());
				
				success = json.getInt(TAG_SUCCESS);
				if (success == 1) {
					Log.d("Added Note", json.toString());
					
					Intent ii;
					
					ii = new Intent(ServiceAddNote.this, ServiceCarMenu.class);
					
					ii.putExtra("make", carMake);
					ii.putExtra("model", carModel);
					ii.putExtra("id", carID);
					ii.putExtra("key", key);
					
					startActivity(ii);
					finish();
					
					return json.getString(TAG_MESSAGE);
				}
				else {
					return json.getString(TAG_MESSAGE);
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onPostExecute (String message) {
			progressDialog.dismiss();
			if (message != null) {
				Toast.makeText(ServiceAddNote.this, message, Toast.LENGTH_LONG).show();
			}
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
