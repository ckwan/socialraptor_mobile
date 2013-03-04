package com.socialraptor.winna;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Login implements Controller, OnClickListener {
	
	EditText usrText;
	EditText pswText;
	Button lBtn;
	ProgressBar prg;
	TextView error;
	Socialraptor ctrl;
	String usn,psw;
	SharedPreferences set;

	public Login(Socialraptor ctr, SharedPreferences settings) {
		ctrl = ctr;
		set = settings;
		usrText = (EditText) ctrl.findViewById(R.id.username);
		pswText = (EditText) ctrl.findViewById(R.id.password);
		lBtn = (Button) ctrl.findViewById(R.id.login);
		lBtn.setOnClickListener(this);
		prg = (ProgressBar) ctrl.findViewById(R.id.loginprocess);
		error = (TextView) ctrl.findViewById(R.id.errormsg);
		usn = "";
		psw = "";
	}

	public void onClick(View v) {
		if (v == lBtn) {
			//error.setText("'" + usrText.getText() + "' & '" + pswText.getText() + "'");
			if (usrText.getText().toString().equals("")||pswText.getText().toString().equals("")) {
				error.setText("username or password (or both) is blank, not allowed.");
				return;
			}
			lBtn.setVisibility(android.view.View.INVISIBLE);
			prg.setVisibility(android.view.View.VISIBLE);
			usn = usrText.getText().toString();
			psw = md5(pswText.getText().toString());
			
			Communicator c = new Communicator(this,usn,psw,"auth","");
			
			c.execute();
		}
	}
	
	public static String md5(String password) {
		
		MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		md.update(password.getBytes());
		
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<byteData.length; i++){
			String hex = Integer.toHexString(0xff & byteData[i]);
			if(hex.length() == 1) 
				sb.append('0');
			sb.append(hex);
		}
		
		return sb.toString();
	}
	
	public void doJSONResponse(JSONArray j)
	{
		System.out.println(j.toString());
		try {
			if (j.getJSONObject(0).getBoolean("success")) {
				// go to posts list screen
				SharedPreferences.Editor edit = set.edit();
				edit.putString("usn", usn);
				edit.putString("psw", psw);
				edit.commit();
				try {
					Class t = Class.forName("com.socialraptor.winna.Tabs");
					Intent ourIntent = new Intent(ctrl, t);
					ctrl.startActivity(ourIntent);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (!((JSONObject) j.get(0)).getBoolean("success")) {
				// invalid username or password according to web services
				error.setText("username or password (or both) is wrong.");
				lBtn.setVisibility(android.view.View.VISIBLE);
				prg.setVisibility(android.view.View.INVISIBLE);
			}
			else if (j.isNull(0)) {
				// web services is down
				error.setText("unknown error (server down?)");
				lBtn.setVisibility(android.view.View.VISIBLE);
				prg.setVisibility(android.view.View.INVISIBLE);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
