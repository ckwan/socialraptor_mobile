package com.socialraptor.winna;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class Socialraptor extends Activity {

	Button log;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        Login login = new Login(this, savedInstanceState);
        //logMeIn();
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
