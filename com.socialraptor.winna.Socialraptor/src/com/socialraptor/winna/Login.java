package com.socialraptor.winna;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Login extends Controller implements OnClickListener {
	
	EditText usrText;
	EditText pswText;
	Button lBtn;
	ProgressBar prg;
	TextView error;
	Socialraptor ctrl;

	public Login(Socialraptor ctr, Bundle savedState) {
		ctrl = ctr;
		usrText = (EditText) ctrl.findViewById(R.id.username);
		pswText = (EditText) ctrl.findViewById(R.id.password);
		lBtn = (Button) ctrl.findViewById(R.id.login);
		lBtn.setOnClickListener(this);
		prg = (ProgressBar) ctrl.findViewById(R.id.loginprocess);
		error = (TextView) ctrl.findViewById(R.id.errormsg);
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
			
			Communicator c = new Communicator(this,usrText.getText().toString(),pswText.getText().toString(),"auth","");
			
			c.execute();
			// form a JSONobj and send a login request, read response and..
		}
	}
	
	public void doJSONResponse(JSONObject j)
	{
		System.out.println("login response success");
		try {
			if (j.getBoolean("success")) {
				// go to posts list screen
				ctrl.setContentView(R.layout.postlist);
			}
			else if (!j.getBoolean("success")) {
				// invalid username or password according to web services
			}
			else if (j.isNull("success")) {
				// web services is down
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
