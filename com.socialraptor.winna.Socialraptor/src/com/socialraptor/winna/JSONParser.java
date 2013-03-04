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

import android.app.Activity;

public class JSONParser implements Controller {
	
	InputStream input = null;
	String usr, psw;
	Tabs ret;
	String type;
	
	//contructor
	public JSONParser(Tabs returnto, String user, String pasw) {
		usr = user;
		psw = pasw;
		ret = returnto;
	}
	
	//retrieve data the data from url
	public void getContacts() {
		type = "contacts";
		Communicator c = new Communicator (this,usr,psw,type,"&service=all");
		c.execute();
	}
	
	public void getActivity() {
		type = "activity";
		Communicator c = new Communicator (this,usr,psw,type,"&service=all&maxID=0&offset=0&quantity=100");
		c.execute();
	}

	
	public void setUpdate(String query){
		type = "publish";
		Communicator c = new Communicator(this, usr, psw, type, query);
		
		c.execute();
	}

	
	public void doJSONResponse(JSONArray jsa) {
		try {
			if (type.equals("contacts")) {
				String[] c = new String[((JSONArray)jsa.get(1)).length()];
				for (int i=0;i<((JSONArray)jsa.get(1)).length();i++) {
					JSONObject jso = ((JSONArray)jsa.get(1)).getJSONObject(i);
					c[i] = jso.getString("name");
				}
				ret.setupContacts(c);
			}
			else if (type.equals("activity")) {
				Post[] p = new Post[((JSONArray)jsa.get(1)).length()];
				for (int i=0;i<((JSONArray)jsa.get(1)).length();i++) {
					JSONObject jso = ((JSONArray)jsa.get(1)).getJSONObject(i);
					p[i] = new Post(jso.getString("author"),jso.getString("service"),jso.getString("content"),jso.getString("imageURL"));
				}
				ret.setupPosts(p);
			}
			else if (type.equals("services")) {
				
			}
			else if (type.equals("publish")) {
				
					ret.upToast(jsa.getJSONObject(0).getBoolean("success"));
			}
			else {
				
			}
		}
		catch (JSONException jse)
		{
			
		}
	}
}
	
