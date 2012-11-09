package com.socialraptor.winna;

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
}
	
