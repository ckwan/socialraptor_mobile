package com.socialraptor.winna;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

import android.os.AsyncTask;

public class Communicator extends AsyncTask<Object, Object, JSONArray> {
	
	String authstr, type, query;
	Controller c;
	
	public Communicator (Controller con, String usn, String psw, String type, String query)
	{
		c = con;
		this.type = type;
		this.query = query;
		String auth = psw;
		
		authstr = "uID="+usn+"&auth="+auth;
	}
	
	public static String getHTML(String url) {
		String line="", result="";
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            InputStream input = httpEntity.getContent();
            BufferedReader rd = new BufferedReader(new InputStreamReader(input));
			while ((line = rd.readLine()) != null) {
			   result += line;
			}
			rd.close();
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
		return result;
	}
	
	public static String convertStreamToString(InputStream is) {
	    Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
	    return s.hasNext() ? s.next() : "";
	}

	@Override
	protected JSONArray doInBackground(Object... params) {
		JSONArray jsa = null;
		String response = getHTML("http://webservices.socialraptor.com/"+type+"/?"+authstr+query);

		
		try {
			jsa = new JSONArray(response);
		} catch (JSONException e) {
			try {
				JSONObject jso = new JSONObject(response);
				jsa = new JSONArray();
				jsa.put(jso);
			}
			catch (JSONException e1) {
			e1.printStackTrace();
			}
		}
		return jsa;
	}
	
	protected void onPostExecute(JSONArray jsa)
	{
        c.doJSONResponse(jsa);
    }
}
