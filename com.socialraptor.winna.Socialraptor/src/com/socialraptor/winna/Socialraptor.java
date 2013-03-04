package com.socialraptor.winna;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Socialraptor extends Activity {

	Button log;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        SharedPreferences settings = getSharedPreferences("socialraptor", 0);
        if (!settings.getString("psw", "badpsw").equals("badpsw")) {
	    	try {
				Class t = Class.forName("com.socialraptor.winna.Tabs");
				Intent ourIntent = new Intent(this, t);
				this.startActivity(ourIntent);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
        }
        else {
        	Login login = new Login(this, settings);
        }
    }
    
    

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_socialraptor, menu);
        return true;
    }

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
}
