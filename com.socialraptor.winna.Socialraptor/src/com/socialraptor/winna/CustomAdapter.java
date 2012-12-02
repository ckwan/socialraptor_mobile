package com.socialraptor.winna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CustomAdapter<T> extends ArrayAdapter<Friend>{

	Context context;
	int layoutId;
	Friend data[] = null;
	TextView txt, txt1;
	
	public CustomAdapter(Context context, int layoutResourceId, Friend[] data){
		super(context, layoutResourceId, data);
		this.context = context;
		layoutId = layoutResourceId;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Friend friend = data[position];
		
		LayoutInflater in = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		row = in.inflate(layoutId, null);
		row.setTag(friend);
			
		txt = (TextView) row.findViewById(R.id.name);
		txt1 = (TextView) row.findViewById(R.id.blah);
		
		
		txt.setText(friend.name);
		txt1.setText(friend.bs);
		
		return row;
	}
	
	
}
