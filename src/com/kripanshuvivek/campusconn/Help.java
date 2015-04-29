package com.kripanshuvivek.campusconn;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Help extends Fragment  {
	Animation animFadein;
	Button b1;
	TextView t1,t2,t3,t4,t5,t6;
	String et="";
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
		    Bundle savedInstanceState) {
		  View view = inflater.inflate(R.layout.fragment_help,
		      container, false);
		  t1 = (TextView) view.findViewById(R.id.tx1);
		  t2 = (TextView) view.findViewById(R.id.tx2);
		  t3 = (TextView) view.findViewById(R.id.tx3);
		  t4 = (TextView) view.findViewById(R.id.tx4);
		  t5 = (TextView) view.findViewById(R.id.tx5);
		  b1= (Button) view.findViewById(R.id.bt1);
		  t6 = (TextView) view.findViewById(R.id.tx9);
		  
		 t6.setAnimation(animFadein );
		 
		  
		  t1.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
            	  
            	  Toast.makeText(getActivity(),"Select any club from the list and go through  1. events  2.team information  3.registration form  4. feedback",Toast.LENGTH_LONG).show();

              }
          });
		  t2.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
            	  Toast.makeText(getActivity(),"Event Tab gives information about the current events or upcoming events of the respective club ",Toast.LENGTH_LONG).show();

              }
          });
		  t3.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
            	  Toast.makeText(getActivity(),"Team information tab gives information about the respective club ",Toast.LENGTH_LONG).show();

              }
          });
		  t4.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
            	  Toast.makeText(getActivity(),"Registration Form Tab helps you to apply for the club when recruitment is in process",Toast.LENGTH_LONG).show();

              }
          });
		  t5.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
            	  Toast.makeText(getActivity(),"Feedback Tab allows you to send feedback to the respective club",Toast.LENGTH_LONG).show();
            	  
              }
          });
		  
		  animFadein = AnimationUtils.loadAnimation(getActivity(),
	                R.anim.fade_in);
		  
		  
		  b1.setOnClickListener(new View.OnClickListener() {
			    public void onClick(View v) {
			    	try {
			    		   String contact_number="+919962938414";
			    		   Intent callIntent = new Intent(Intent.ACTION_CALL);
			    		   callIntent.setData(Uri.parse("tel:" + contact_number));
			    		   startActivity(callIntent);
			    		} catch (Exception e) {
			    			Toast.makeText(getActivity(),"Could not make a call",Toast.LENGTH_LONG).show();
			    			// no activity to handle intent. show error dialog/toast whatever
			    		}   
			    }
			});
		  
		  return view;}
	

	
	
	

}
