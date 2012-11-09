package com.socialraptor.winna;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

import org.apache.*;
import org.json.*;

import android.util.Log;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.*;

public class RetrieveData extends ListActivity {
	
	//string url to the web-service's contents
	private static String url = "https://cs480.domsnet.net/activity";
	
	//these are the stuff they are going to provide us
	private static final String ID = "uID";
	private static final String AUTH = "auth";
	private static final String SERVICE = "service";
	private static final String FIRSTPOST = "firstPost";
	private static final String NUMPOST = "numberOfPosts";
	private static final String CONTENT = "content";
	
	//create activity when the app starts
	public void onCreate(Bundle saveInstanceState) {
		//super.onCreate(savedInstanceState);
		//setContentView(R.layout.main);
	}
	
	//probably need to call the JSONParser instance
	JSONParser jparse = new JSONParser();
	
	//calls the instance to retrieve the JSON strings from the url
	JSONObject json = jparse.getUrl(url);
	
	
	//this should be the part where the datas are put in the arrays?
	public void RetreveData() {
		
	}
}