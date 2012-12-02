package com.socialraptor.winna;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Tabs extends Activity {

	TabHost dash;
	ListView sview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		
		setTabs();
		
		sview = (ListView) findViewById(R.id.status);
		
		Friend f[] = new Friend[]
		{
			new Friend("Fernando", "Hey What s up?"),
			new Friend("Luke", "How are you doing"),
			new Friend("Tester", "This is a very nice app"),
			new Friend("CoolGuy", "Very nice indeed, GJ"),
			new Friend("Ashley", "I wanna see you"),
			new Friend("Thomas", "What are you doing?"),
			new Friend("Allan", "Wanna go see movie tonight?"),
			new Friend("Richard", "She is gorgeous!"),
			new Friend("Ivan", "Hey, how is your project?"),
			new Friend("Ashley", "Are you free tomorrow?")
		};
		
		CustomAdapter<Friend> cadapter = new CustomAdapter<Friend>(this, R.layout.custom, f);
		sview.setAdapter(cadapter);
	}

	private void setTabs() {
		// TODO Auto-generated method stub
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
		d1.setIndicator("Settings");
		dash.addTab(d1);
	}
}
