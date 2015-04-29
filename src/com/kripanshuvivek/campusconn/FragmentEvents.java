package com.kripanshuvivek.campusconn;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class FragmentEvents extends Fragment implements OnItemClickListener{
	String clubname="";
	ListView l;
	AlertDialog.Builder adb;
	Context context;
	ProgressDialog pDialog;
	static String []event;
	static String []desc;
	TextView tv,textview;
	 LayoutInflater inf;
	 View v;
	 Dialog al;
	    
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_events, container, false);
        l = (ListView) rootView.findViewById(R.id.eventlist);
        l.setOnItemClickListener(this);
       // tv = (TextView) rootView.findViewById(R.id.textView3);
        new getEvents().execute(clubname);
        
        inf= LayoutInflater.from(context);
        v=inf.inflate(R.layout.dialog_layout, null);

	    al = new Dialog(getActivity(), android.R.style.Theme_Black);
	    Drawable draw = new ColorDrawable(Color.BLACK);
	    draw.setAlpha(60);
	   
	    //al.getWindow().setBackgroundDrawable(draw);
	    al.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

	    al.setContentView(v);
        textview=(TextView)v.findViewById(R.id.textmsg);
        
        return rootView; 
    }
	public FragmentEvents(Context c, String a){
		clubname = a;
		context = c;
	}
	class getEvents extends AsyncTask<String, String, String[]>{
		
		@Override
		protected String[] doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Json j = new Json();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("club", arg0[0]));
			JSONObject jobj = j.makeHttpRequest("http://www.srmaakash.in/buzz/event.php", "GET", params);
			
			
			try {
				if(jobj.getInt("success")==1&&jobj.getJSONArray("event").length()>0){
					JSONArray jarr = jobj.getJSONArray("event");
					event = new String[jarr.length()];
					desc = new String[jarr.length()];
					for(int i =0 ; i< jarr.length(); i++){
						JSONObject temp = jarr.getJSONObject(i);
						event[i] = temp.getString("name");
						desc [i] = "DESCRIPTION:  "+temp.getString("Description") +"\n VENUE:  "+temp.getString("Address")+"\n CONTACT:  " + temp.getString("email");
					}
					return event;
				}
				else{
					adb = new AlertDialog.Builder(context);
					adb.setMessage("Something went wrong!")
						.setTitle("Error")
						.setPositiveButton("OK", new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
								dialog.cancel();
							}
						});
					
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("Fragment event","AsyncTask");
			}
			return null;
		}
		@Override
		public void onPostExecute(String []a){
			List<ClubRowItem> listitems = new ArrayList<ClubRowItem>();
			for(String x : a){
				ClubRowItem temp = new ClubRowItem(x, null, "");
				listitems.add(temp);
			}
			EventListAdapter adapter = new EventListAdapter(context, listitems);
    		l.setAdapter(adapter);
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if(!event[arg2].equals("No events here")){
			textview.setText(""+desc[arg2]);
		    al.show(); 
		}
	}
}
