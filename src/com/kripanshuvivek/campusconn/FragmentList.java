package com.kripanshuvivek.campusconn;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources.NotFoundException;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class FragmentList extends Fragment implements OnItemClickListener{ 
    ListView list;
    List<ClubRowItem> rowItems;
    Context context;
    ProgressDialog pDialog;
    public static final String IMAGE_RESOURCE_ID = "iconResourceID";
    public static final String ITEM_NAME = "itemName";
    private String str;
    public static String clubname1="";
   // Club []club;
    public FragmentList(Context context, String text){
  	  this.context = context;
  	  str=text;
    }
    public FragmentList()
    {
    	
    	//this constructor is made empty to avoid start up error
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          View view = inflater.inflate(R.layout.fragment_list, container, false);
          new getClubs().execute();
          list = (ListView)view.findViewById(R.id.listview);
          list.setOnItemClickListener(this);
          return view;
    }
    /*
     * Generics:
     * #1: Types of references to be passed to doInBackound()
     * #2: Types of references passed to onProgressUpdate()
     * #3: Types of references returned by doInBackground()/Values pa
     */
    
    public class getClubs extends AsyncTask<String, String, Club[]>{
	    	@Override
	    	protected void onPreExecute(){
	    		pDialog = new ProgressDialog(context);
	    		pDialog.setCancelable(false);
	    		pDialog.setMessage("Enlisting.. Please Wait");
	    		pDialog.setIndeterminate(false);
	    		pDialog.show();
	    		
	    	}
	    	@Override
	    	protected Club[] doInBackground(String...  arg0) {
	    		Json j = new Json();
	    		List<NameValuePair> l = new ArrayList<NameValuePair>();
	    		l.add(new BasicNameValuePair("c","null"));
	    		JSONObject jobj = j.makeHttpRequest("http://www.srmaakash.in/buzz/clubs.php", "POST", l);
	    		int i = 0;
	    		Club []club = null;
	    		JSONObject []list = null;
	    		try {
	    			if(jobj.getInt("success")==1){
					JSONArray jarr = jobj.getJSONArray("clubs");
					club = new Club[jarr.length()];
					list = new JSONObject[jarr.length()];
					for(i=0; i<jarr.length(); i++){
						list[i] = jarr.getJSONObject(i);
						URL url = new URL(list[i].getString("compressed"));
						InputStream content = (InputStream)url.getContent();
						Drawable d = Drawable.createFromStream(content , "src");
  					club[i] = new Club(list[i].getString("name"), list[i].getString("type"), d);
					}
					return club;
	    			}
				} catch(JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}catch(IOException e) {
					// TODO Auto-generated catch block
					for(;i<club.length;i++){
						try {
							club[i] = new Club(list[i].getString("name"), list[i].getString("type"), getResources().getDrawable( R.drawable.buzzfinal));
						} catch (NotFoundException | JSONException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
	    		return null;
	    	}
	    	@Override
	    	protected void onPostExecute(Club[] clublist){
	    		rowItems = new ArrayList<ClubRowItem>();
	    		if(!str.equals("")){
	    			for (int i = 0; i < clublist.length; i++) {
	    				if(clublist[i].name.toLowerCase().contains(str.toLowerCase().trim())){   
	    					ClubRowItem item = new ClubRowItem(clublist[i].name, clublist[i].d , clublist[i].type);
	    					rowItems.add(item);
	    				}
	    			}
	    		}
	    		else{
	    			for (int i = 0; i < clublist.length; i++) { 
		            	ClubRowItem item = new ClubRowItem(clublist[i].name,clublist[i].d, clublist[i].type);
		            	rowItems.add(item);
		            }
	    		}
	            ClubListAdapter adapter = new ClubListAdapter(context, rowItems);
	    		list.setAdapter(adapter);
	    		pDialog.dismiss();
	    	}
	 }
    
    
	@Override
	public  void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		TextView texty = (TextView) arg1.findViewById(R.id.member_name);
		String clubname = texty.getText().toString();
		Intent in = new Intent(context, ClubHome.class);
		in.putExtra("name", clubname);
		Toast.makeText(context,"Welcome to "+clubname, Toast.LENGTH_LONG).show();
		startActivity(in);
		this.getActivity().finish();
		clubname1=clubname;
	}
	
	private class Club{
		String name= "",type= "";
		Drawable d = null;
		public Club(String a, String b, Drawable c)
		{
			name = a;
			type = b;
			d =c; 
		}
	}
}