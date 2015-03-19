/*
 * Parse JSON using a PHP link
 */

package com.ateam.alleneatonautorentals;

import java.util.List;
import org.apache.http.HttpEntity;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import org.apache.http.HttpResponse;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.util.Log;

public class JSONParser {

	static InputStream is = null;
	static JSONObject jsonObj = null;
	static String json = "";
	
	public JSONParser() {
		
	}
	
	public JSONObject getJSONFromUrl (final String url) {
		// Making HTTP request
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
			HttpResponse httpResponse = httpClient.execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			
			is = httpEntity.getContent();
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader (is, "iso-8859-1"), 8);
			
			// Helpers to store JSON object
			StringBuilder str = new StringBuilder();
			String strLine = null;
			
			// Building string
			while ((strLine = reader.readLine()) != null) {
				str.append(strLine + "\n");
			}
			
			// Cleanup
			is.close();
			
			json = str.toString();
		}
		catch (Exception e) {
			Log.e("Error", "Something wrong with converting result " +e.toString());
		}
		
		// Try to parse the string to a JSON object
		try {
			jsonObj = new JSONObject(json);
		}
		catch (JSONException e) {
			Log.e("json Parser", "" + e.toString());
		}
		
		return jsonObj;
	}
	
	public JSONObject makeHttpRequest(String url, String method,
			List<NameValuePair> params) {
		try {
			if (method == "POST") {
				DefaultHttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(url);
				httpPost.setEntity(new UrlEncodedFormEntity(params));
				
				HttpResponse httpResponse = httpClient.execute(httpPost);
				HttpEntity httpEntity = httpResponse.getEntity();
				is = httpEntity.getContent();
			}
			else if (method == "GET") {
				// Request method
				DefaultHttpClient httpClient = new DefaultHttpClient();
				String paramString = URLEncodedUtils.format(params, "utf-8");
				
				url += "?" + paramString;
				HttpGet httpGet = new HttpGet(url);
				
				HttpResponse httpResponse = httpClient.execute(httpGet);
				HttpEntity httpEntity = httpResponse.getEntity();
				
				is = httpEntity.getContent();
			}
		}
		catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		catch (ClientProtocolException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			
			StringBuilder str = new StringBuilder();
			String strLine = null;
			
			while ((strLine = reader.readLine()) != null) {
				str.append(strLine + "\n");
			}
			
			is.close();
			json = str.toString();
		}
		catch (Exception e) {
			Log.e("Error", "Something wrong with converting result " +e.toString());
		}
		
		try {
			jsonObj = new JSONObject(json);
		}
		catch (JSONException e) {
			Log.e("Error", "Something wrong with converting result " +e.toString());
		}
		
		return jsonObj;
	}


}
