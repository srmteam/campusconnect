package com.kripanshuvivek.campusconn;

import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FragmentFeedback extends Fragment {
	Context context;
	String clubname;
	ProgressDialog pDialog;
	Button b;
	EditText e1, e2;
	AlertDialog.Builder adb;
	String a1,b1;
	public FragmentFeedback(Context context, String clubname) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.clubname = clubname;
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_feedback, container, false);
        b = (Button) rootView.findViewById(R.id.button1);
        e1 = (EditText) rootView.findViewById(R.id.nom) ;
        
        e2 = (EditText) rootView.findViewById(R.id.comm);
        b.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				 a1 = e1.getText().toString();
				 b1 = e2.getText().toString();
				 String strUserName = e1.getText().toString();
				 String strUserName1=e2.getText().toString();
				 if(TextUtils.isEmpty(strUserName)|| TextUtils.isEmpty(strUserName1) ) {
				    
					 Toast.makeText(getActivity(), "Empty Feedback not allowed ", Toast.LENGTH_LONG).show();
				 }else
				 { new Feed().execute(a1,b1);}
				
			}
		});
        return rootView;
    }
	public class Feed extends AsyncTask<String, String, String>{
    	@Override
    	public void onPreExecute(){
    		
				pDialog = new ProgressDialog(getActivity());
    		pDialog.setCancelable(false);
    		pDialog.setMessage("Adding..");
    		pDialog.setIndeterminate(false);
    		pDialog.show();
    	}
    		
    	@Override
    	protected String doInBackground(String...  arg0) {
    		
    		try {
				List<NameValuePair> parameters = new ArrayList<NameValuePair>();
				parameters.add(new BasicNameValuePair("club",clubname));
				parameters.add(new BasicNameValuePair("comment",arg0[1]));
				parameters.add(new BasicNameValuePair("email",arg0[0]));
				Json j = new Json();
				JSONObject jobj = j.makeHttpRequest("http://www.srmaakash.in/buzz/feedback.php", "POST", parameters);
				if(jobj.getInt("success")==1)
		    		return "1";
				else 
		    		return jobj.getString("message");
			} catch(Exception e) {
				e.printStackTrace();
			}
    		return null;
    	}
    	
    	@Override
    	protected void onPostExecute(String file_url){
    		
				pDialog.dismiss();
    		adb = new AlertDialog.Builder(getActivity());
			adb.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
		        public void onClick(DialogInterface dialog, int which) { 
		            dialog.dismiss();
		        }
		     });
			if(file_url.equals("1"))adb.setTitle("Done!");
			else{
				adb.setTitle("error");
				adb.setMessage(file_url);
			
			adb.show();
    	}}
 }
}
