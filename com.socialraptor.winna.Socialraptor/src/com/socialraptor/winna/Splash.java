package com.socialraptor.winna;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

public class Splash extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		WebView myWebView = new WebView(this);
		
		myWebView.loadUrl("file:///android_asset/mbt.html");
		
		setContentView(myWebView);
		
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
					sleep(5000);
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
