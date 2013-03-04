package com.socialraptor.winna;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class Contact {

	public String name;
	public String service;
	public static Bitmap pic;
	
	public Contact(String name, String service, String imageurl){
		this.name = name;
		this.service = service;
		loadBitmap(imageurl);
		//System.out.println(imageurl);
	}
	
	public static void loadBitmap(final String url) {
		new Thread(new Runnable() {
	        public void run() {
	            Bitmap bitmap = null;
        		try {
        			bitmap = BitmapFactory.decodeStream((InputStream)new URL(url).getContent());
  				} catch (MalformedURLException e) {
  					e.printStackTrace();
  				} catch (IOException e) {
  					e.printStackTrace();
  				}
        		pic = bitmap;
	        }
	    }).start();
	}
}
