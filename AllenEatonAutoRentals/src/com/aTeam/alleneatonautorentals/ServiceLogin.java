package com.aTeam.alleneatonautorentals;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ServiceLogin extends ActionBarActivity {
	private EditText email;
	private EditText password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_service_login);
		setupVariables();

	}
	
	private void setupVariables() {
		email = (EditText) findViewById(R.id.editEmail);
		password = (EditText) findViewById(R.id.editPassword);
	}
	
	public void authenticateLogin(View view) {
		
		        if (email.getText().toString().equals("admin") && password.getText().toString().equals("12345")) {
		        	//Toast.makeText(getApplicationContext(), "Hello Admin!", Toast.LENGTH_SHORT).show();
		        	Intent i=new Intent(ServiceLogin.this, ServiceMenu.class);
		            startActivity(i);
		
		        } 
		        else {
		            Toast.makeText(getApplicationContext(), "Seems like you 're not admin!", Toast.LENGTH_SHORT).show();
		        }
		
		
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.service_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
