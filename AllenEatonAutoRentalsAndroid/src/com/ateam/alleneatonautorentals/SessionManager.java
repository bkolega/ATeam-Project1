package com.ateam.alleneatonautorentals;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SessionManager {
	SharedPreferences pref;
	SharedPreferences.Editor editor;
	Context _context;
	
	private static final String KEY_USERNAME = "username";
	private static final String KEY_ROLE = "role";
	private static final String KEY_NAME = "name";
	
	public SessionManager(Context context) {
		_context = context;
		pref = PreferenceManager.getDefaultSharedPreferences(_context);
		editor = pref.edit();
	}
	
	public void createLoginSession(String user, String role, String name) {
		editor.putString(KEY_USERNAME, user);
		editor.putString(KEY_ROLE, role);
		editor.putString(KEY_NAME, name);
		editor.commit();
	}
	
	public String getUsername() {
		return pref.getString(KEY_USERNAME, null);
	}
	
	public String getRole() {
		return pref.getString(KEY_ROLE, null);
	}
	
	public String getName() {
		return pref.getString(KEY_NAME, null);
	}
	
	public void logoutUser() {
		editor.clear();
		editor.commit();
		
		Intent ii = new Intent(_context, MainActivity.class);
		ii.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		ii.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		
		_context.startActivity(ii);
	}
}
