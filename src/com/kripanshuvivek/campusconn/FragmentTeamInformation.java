package com.kripanshuvivek.campusconn;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentTeamInformation extends Fragment {
	String clubname;
	String n;
	Context context;
	TextView t1,t2,t3,t4;
	ImageView iv ;
	Drawable d;
	ProgressDialog pd;
	Button b1;
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         View rootView = inflater.inflate(R.layout.fragment_team_info, container, false);
         t4 = (TextView) rootView.findViewById(R.id.email);
         t1 = (TextView) rootView.findViewById(R.id.teamDetails);
         t2 = (TextView) rootView.findViewById(R.id.convenors);
         t3 = (TextView) rootView.findViewById(R.id.contact);
         iv = (ImageView) rootView.findViewById(R.id.events_image);
         b1=(Button) rootView.findViewById(R.id.bt1);
  		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
  		StrictMode.setThreadPolicy(policy); 
         new Data().execute();
         
         
         b1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	try {
			    		   String contact_number=n;
			    		   Intent callIntent = new Intent(Intent.ACTION_CALL);
			    		   callIntent.setData(Uri.parse("tel:" + contact_number));
			    		   startActivity(callIntent);
			    		} catch (Exception e) {
			    			Toast.makeText(getActivity(),"Could not make a call",Toast.LENGTH_LONG).show();
			    			// no activity to handle intent. show error dialog/toast whatever
			    		}   
			    }
			});
         
         
        return rootView;
    }
	public FragmentTeamInformation(Context c , String a){
		context = c;
		clubname = a;
	}
	class Data extends AsyncTask<String, String, String[]>{
		@Override
		public void onPreExecute(){
			pd = new ProgressDialog(getActivity());
    		pd.setCancelable(false);
    		pd.setMessage("Enlisting..");
    		pd.setIndeterminate(false);
    		pd.show();
		}
		@Override
		protected String[] doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			Json j = new Json();
			String coord, reg, desc, img, ph, email;
			String det[] = new String[6];
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("club",clubname));
			JSONObject jobj = j.makeHttpRequest("http://www.srmaakash.in/buzz/clubs.php", "POST", params);
			
			try {
				det[0] = jobj.getString("register");
				det[1] = jobj.getString("description");
				det[2] = jobj.getString("image");
				det[3] = jobj.getString("phone");
				det[4] = jobj.getString("coordinator");
				det[5] = jobj.getString("email");
				URL url = new URL(det[2]);
				InputStream content = (InputStream)url.getContent();
				d = Drawable.createFromStream(content , "src"); 
				return det;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				Log.e("Some JSON here","hello");
			}catch(Exception e){
				Log.e("Some null pointer here","hello");
			}
			return null;
		}
		
		@Override
		public void onPostExecute(String[] a){
			t1.setText("DESCRIPTION : "+a[1]);
			t2.setText("CONVENOR : "+a[4]+",("+a[0]+")");
			t3.setText("CONTACT NUMBER : "+a[3]);
			t4.setText("EMAIL : "+a[5]);
			iv.setImageDrawable(d);
			pd.dismiss();
			n=a[3];
		}
		
	}
}
