/*
 * XML: view_notes
 * View all of the notes for car
 */

package com.ateam.alleneatonautorentals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ServiceViewNotes extends ListActivity {
	private ProgressDialog progressDialog;
	JSONParser jsonParser = new JSONParser();
	
	ArrayList<HashMap<String, String>> notesList;
	
	private static final String NOTES_URL =
			"http://people.eecs.ku.edu/~kwu96/ATeamScripts/list_car_notes.php";
	private static final String TAG_SUCCESS = "success";
	private static final String TAG_NOTES = "notes";
	private static final String TAG_HISTORY = "history";
	private static final String TAG_EMAIL = "user_email";

	
	private String carMake;
	private String carID;
	private String carModel;
	private String keyCarSearch;
	
	JSONArray notes = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_notes);
		
		Intent getIntent = getIntent();
		carMake = getIntent.getStringExtra("make");		
		carModel = getIntent.getStringExtra("model");
		carID = getIntent.getStringExtra("id");
		keyCarSearch = getIntent.getStringExtra("key");
		
		notesList = new ArrayList<HashMap<String, String>>();
		
		new LoadAllCarNotes().execute();
	}
	
	@Override
	public boolean onCreateOptionsMenu (Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	class LoadAllCarNotes extends AsyncTask<String, String, String> {
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(ServiceViewNotes.this);
			progressDialog.setMessage("Loading car notes...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			
			params.add(new BasicNameValuePair("id", carID.substring(8)));
			
			JSONObject json = jsonParser.makeHttpRequest(NOTES_URL, "POST", params);
			
			Log.d("All Notes: ", json.toString());
			
			try {
				int success = json.getInt(TAG_SUCCESS);
				
				if (success == 1) {
					notes = json.getJSONArray(TAG_NOTES);
					
					// Go through each users in JSON
					for (int i = 0; i < notes.length(); i++) {
						JSONObject c = notes.getJSONObject(i);
					
						// Get the string from JSON
						String history = c.getString(TAG_HISTORY);
						String email = c.getString(TAG_EMAIL);
					
						HashMap<String, String> map = new HashMap<String, String>();
						
						// Store the strings
						map.put("car_id", carID);
						map.put("car_make", "Car Make: ".concat(carMake));
						map.put("car_model", carModel);
						map.put(TAG_HISTORY, "Note: ".concat(history));
						map.put(TAG_EMAIL, email);
	
						notesList.add(map);
					}
				}
				else {
					Intent ii = new Intent(ServiceViewNotes.this, ServiceCarMenu.class);

					ii.putExtra("make", carMake);
					ii.putExtra("model", carModel);
					ii.putExtra("id", carID);
					ii.putExtra("key", keyCarSearch);
					
					startActivity(ii);
					finish();
					
					return "Error in Database";
				}
			}
			catch (JSONException e) {
				e.printStackTrace();
			}
			
			return null;
		}
		
		protected void onPostExecute(String message) {
			progressDialog.dismiss();
			
			if (message != null) {
				Toast.makeText(ServiceViewNotes.this, message, Toast.LENGTH_LONG).show();
			}
			else {
				// Puts all the strings into the list
				runOnUiThread(new Runnable() {
					public void run() {
						ListAdapter adapter = new SimpleAdapter 
								(ServiceViewNotes.this, notesList,
								 R.layout.listitem_note, new String[] 
										 {"car_id", "car_make", "car_model", TAG_HISTORY, 
										  TAG_EMAIL },
										  new int[] {R.id.notes_carid_list, R.id.notes_carmake_list,
													 R.id.notes_carmodel_list, R.id.notes_history_list,
													 R.id.notes_useremail_list
								});
						setListAdapter(adapter);
					}
				});
			}
		}

		
	}
	
	@Override
	public void onBackPressed() {
		Intent ii = new Intent(ServiceViewNotes.this, ServiceCarMenu.class);

		ii.putExtra("make", carMake);
		ii.putExtra("model", carModel);
		ii.putExtra("id", carID);
		ii.putExtra("key", keyCarSearch);
		
		startActivity(ii);
		finish();
	}

}

