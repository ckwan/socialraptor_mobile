package com.socialraptor.winna;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class Communicator extends AsyncTask<Object, Object, JSONObject> {
	
	String authstr, type, query;
	Controller c;
	
	public Communicator (Controller con, String usn, String psw, String type, String query)
	{
		c = con;
		this.type = type;
		this.query = query;
		String auth = md5(psw);
		
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
	
	public static String md5(String password) {
		
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(password.getBytes());
		
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<byteData.length; i++){
			String hex = Integer.toHexString(0xff & byteData[i]);
			if(hex.length() == 1) 
				sb.append('0');
			sb.append(hex);
		}
		
		return sb.toString();
	}

	@Override
	protected JSONObject doInBackground(Object... params) {
		JSONObject jso = null;
		String response = getHTML("http://webservices.socialraptor.com/"+type+"/?"+authstr+query);
		try {
			jso = new JSONObject(response);
		} catch (JSONException e1) {
			e1.printStackTrace();
		}
		return jso;
	}
	
	protected void onPostExecute(JSONObject jso)
	{
		System.out.println("onpostexecute found.");
        c.doJSONResponse(jso);
    }
}
