package com.socialraptor.winna;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;

public class Login implements OnClickListener {
	
	EditText usrText;
	EditText pswText;
	Button lBtn;
	ProgressBar prg;
	TextView error;
	Socialraptor ctrl;

	public Login(Socialraptor ctr) {
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
			
			// form a JSONobj and send a login request, read response and..
			String response = "success"; //pseudo, change to whatever
			
			if (response.equals("success")) {
				// go to posts list screen
				ctrl.setContentView(R.layout.postlist);
				TextView txt = (TextView) ctrl.findViewById(R.id.msg);
				txt.setText("username: '" + usrText.getText() + "' \n password: '" + pswText.getText() + "'");
			}
			else if (response.equals("invalid")) {
				// invalid username or password according to web services
			}
			else if (response.equals("serviceinterrupt")) {
				// web services is down
			}
			
		}
		
	}
}
