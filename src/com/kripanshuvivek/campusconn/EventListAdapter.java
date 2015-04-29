package com.kripanshuvivek.campusconn;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class EventListAdapter extends BaseAdapter {
	Context context;
	 List<ClubRowItem> rowItems;

	 EventListAdapter(Context context, List<ClubRowItem> rowItems) {
	  this.context = context;
	  this.rowItems = rowItems;
	 }

	 @Override
	 public int getCount() {
	  return rowItems.size();
	 }

	 @Override
	 public Object getItem(int position) {
	  return rowItems.get(position);
	 }

	 @Override
	 public long getItemId(int position) {
	  return rowItems.indexOf(getItem(position));
	 }

	 /* private view holder class */
	 private class ViewHolder {
	  TextView member_name;
	 }
	 

	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  
		 ViewHolder holder = null;
	  LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.event_list_item, null);
	   holder = new ViewHolder();

	   holder.member_name = (TextView) convertView.findViewById(R.id.member_name);
	   
	   ClubRowItem row_pos = rowItems.get(position);

	   holder.member_name.setText(row_pos.getMember_name());
	   convertView.setTag(holder);
	  }
	  
	  else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  return convertView;
	 }
}
