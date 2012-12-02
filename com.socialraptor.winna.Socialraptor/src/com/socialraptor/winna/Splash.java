package com.socialraptor.winna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		splashMe();		
	}

	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	private void splashMe() {
		Thread splashMe = new Thread(){
			public void run(){
				try {
					sleep(1800);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					Intent launch = new Intent("com.socialraptor.winna.SOCIALRAPTOR");
					startActivity(launch);
				}
			}
		};
		splashMe.start();
	}
}
