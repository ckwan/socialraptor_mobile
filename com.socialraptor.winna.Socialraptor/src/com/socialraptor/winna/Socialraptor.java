package com.socialraptor.winna;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class Socialraptor extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginscreen);
        Login login = new Login(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_socialraptor, menu);
        return true;
    }
}
