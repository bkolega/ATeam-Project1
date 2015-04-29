package autoGui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class JsonHandler {
	JSONObject jsonObject;

	public JsonHandler(String url) {
		try {
			DefaultHttpClient http = new DefaultHttpClient();
			HttpPost post = new HttpPost(url);
			
			HttpResponse response = http.execute(post);

			readJson(response);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JsonHandler(String url, List<NameValuePair> params) {
		
		DefaultHttpClient http = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(url);
		
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			
			HttpResponse response = http.execute(post);
			
			readJson(response);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void readJson(HttpResponse response) {
		try {
			InputStream is = response.getEntity().getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
			
			StringBuilder strBuilder = new StringBuilder();
			String line = null;
			
			do {
				line = reader.readLine();
				strBuilder.append(line);
			} while (line != null);
			
			is.close();
			
//			System.out.println("Raw string: " + strBuilder.toString());
			
			jsonObject = new JSONObject(strBuilder.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public JSONObject getJsonObject() {
		return jsonObject;
	}
}
