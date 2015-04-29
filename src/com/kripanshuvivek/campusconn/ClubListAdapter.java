package com.kripanshuvivek.campusconn;

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ClubListAdapter extends BaseAdapter {
	Context context;
	 List<ClubRowItem> rowItems;

	 ClubListAdapter(Context context, List<ClubRowItem> rowItems) {
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
	  ImageView profile_pic;
	  TextView member_name;
	  TextView status;
	 }
	 

	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
	  
		 ViewHolder holder = null;
	  LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
	  if (convertView == null) {
	   convertView = mInflater.inflate(R.layout.club_list_item, null);
	   holder = new ViewHolder();

	   holder.member_name = (TextView) convertView.findViewById(R.id.member_name);
	   holder.profile_pic = (ImageView) convertView.findViewById(R.id.profile_pic);
	   holder.status = (TextView) convertView.findViewById(R.id.status);
	   
	   ClubRowItem row_pos = rowItems.get(position);

	   holder.profile_pic.setImageDrawable(row_pos.getProfile_pic());
	   holder.member_name.setText(row_pos.getMember_name());
	   holder.status.setText(row_pos.getStatus());
	   convertView.setTag(holder);
	  }
	  
	  else {
	   holder = (ViewHolder) convertView.getTag();
	  }
	  
	  return convertView;
	 }
}
