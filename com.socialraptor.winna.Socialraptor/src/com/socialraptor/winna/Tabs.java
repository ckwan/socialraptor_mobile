package com.socialraptor.winna;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

public class Tabs extends Activity {

	TabHost dash;
	ListView sview;
	ListView fview;
	Button post;
	
	//Use this to update status
	public String update, usn, psw;
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_socialraptor, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		SharedPreferences settings = getSharedPreferences("socialraptor",0);
		SharedPreferences.Editor e = settings.edit();
		e.putString("usn", "badusr");
		e.putString("psw", "badpsw");
		e.commit();
		returnUI();
        return true;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		SharedPreferences settings = getSharedPreferences("socialraptor",0);
		usn = settings.getString("usn", "badusr");
		psw = settings.getString("psw", "badpsw");
		if (usn.equals("badusr")&&psw.equals("badpsw"))
		{
			returnUI();
		}
		
		setTabs();
		JSONParser jsp = new JSONParser(this,usn,psw);
		jsp.getActivity();
		JSONParser jsp2 = new JSONParser(this,usn,psw);
		jsp2.getContacts();
		
		sendUpdate();
	}
	
	public void setupPosts(Post[] p) {
		//Status
		sview = (ListView) findViewById(R.id.status);
		
		CustomAdapter<Post> cadapter = new CustomAdapter<Post>(this, R.layout.custom, p);
		sview.setAdapter(cadapter);
	}
	
	public void setupContacts (String[] con) {
		
		//***************************************
		//Friend List
				
		fview = (ListView) findViewById(R.id.flist);
		fview.setAdapter(new ArrayAdapter<String>(this, R.layout.contact, con));
	}
	
	public void sendUpdate() {
		
		//***************************************
		//Update part
		post = (Button) findViewById(R.id.upbutton);
		post.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				EditText temp = (EditText) findViewById(R.id.upload);
				update = temp.getText().toString();
				temp.setText("");
				
				upMe();
			}
		});	
	}
	
	public void upMe(){
		JSONParser jsp = new JSONParser(this, usn, psw);
		jsp.setUpdate(updateQuery());
	}

	private void setTabs() {
		dash = (TabHost) findViewById(R.id.tabhost);
		dash.setup();
		
		//tab1
		TabSpec d1 = dash.newTabSpec("tag1");
		d1.setContent(R.id.tab1);
		d1.setIndicator("Status");
		dash.addTab(d1);
		
		//tab2
		d1 = dash.newTabSpec("tag2");
		d1.setContent(R.id.tab2);
		d1.setIndicator("Update");
		dash.addTab(d1);
		
		//tab3
		d1 = dash.newTabSpec("tag3");
		d1.setContent(R.id.tab3);
		d1.setIndicator("About Us");
		dash.addTab(d1);
	}
	
	public String updateQuery(){
		String query = null;
		//String content = update.replace(" ", "_");
		String content = Uri.encode(update);
		query = "&service=facebook&content="+content; 
		return query;
	}
	
	public void upToast(boolean result){
		
		Context context = getApplicationContext();
		CharSequence text;
		if(result)
			text = "Success!";
		else
			text = "failed";
		int duration = Toast.LENGTH_SHORT;

		Toast toast = Toast.makeText(context, text, duration);
		toast.show();
		
	}
	
	private void returnUI(){
		Thread reset = new Thread(){
			public void run(){
				Intent launch = new Intent("com.socialraptor.winna.SOCIALRAPTOR");
				startActivity(launch);
			}
		};
		reset.start();
		finish();
	}
	
}
