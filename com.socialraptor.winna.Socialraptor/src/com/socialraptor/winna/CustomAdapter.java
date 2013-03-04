package com.socialraptor.winna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter<T> extends ArrayAdapter<Post>{

	Context context;
	int layoutId;
	Post data[] = null;
	TextView txt, txt1;
	ImageView img;
	
	public CustomAdapter(Context context, int layoutResourceId, Post[] data){
		super(context, layoutResourceId, data);
		this.context = context;
		layoutId = layoutResourceId;
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		Post activity = data[position];
		
		LayoutInflater in = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		row = in.inflate(layoutId, null);
		row.setTag(activity);
			
		txt = (TextView) row.findViewById(R.id.name);
		txt1 = (TextView) row.findViewById(R.id.blah);
		img = (ImageView) row.findViewById(R.id.contact);
		
		
		txt.setText(activity.name);
		txt1.setText(activity.status);
		
		//img.setImageBitmap(activity.pic);
		
		return row;
	}
	
	
}
