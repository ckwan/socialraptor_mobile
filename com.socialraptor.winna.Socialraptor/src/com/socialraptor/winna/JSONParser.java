package com.socialraptor.winna;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.io.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.*;

public class JSONParser {
	
	InputStream input = null;
	
	//contructor
	public JSONParser() {
		
	}
	
	//retrieve data the data from url
	public JSONObject getUrl(String url) {
		
		//b.s. jsonobject for to make the code "work" so i can test -brent
		JSONObject jObjString = new JSONObject();
		
		//tries the http resquests
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpPost httpPost = new HttpPost(url);
			
            HttpResponse httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            input = httpEntity.getContent();          
 
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
		
		/*
		{
			* I only did the testing the http request
			* I didn't do the parsing yet		
			
			
		}
		
		*/
		
		//return the JSON strings
		return jObjString;
	}	
	
	//Return MD5 hased password as Hex String
	public String mdV(String password) throws NoSuchAlgorithmException{
		
		MessageDigest md = MessageDigest.getInstance("MD5");
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
}
	
