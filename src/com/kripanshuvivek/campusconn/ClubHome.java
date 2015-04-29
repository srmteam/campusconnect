package com.kripanshuvivek.campusconn;

import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar.TabListener;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ClubHome extends ActionBarActivity  implements TabListener {
	 private ViewPager viewPager;
	 
	    private TabsPagerAdapter mAdapter;
	    private ActionBar actionBar;
	    Intent intent;
	    Boolean isInternetPresent = false;
	     
	    // Connection detector class
	    ConnectionDetector cd;
	    // Tab titles
	    private String[] tabs = { "Events", "Team Information","Registration Form","Feedback" };
	    Button fb1,go1,aak1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		cd = new ConnectionDetector(getApplicationContext());
	 	isInternetPresent = cd.isConnectingToInternet();
	 	getActionBar().setIcon(new ColorDrawable(getResources().getColor(android.R.color.transparent)));  
	 	if (isInternetPresent){
	 		// its oky for working
	 		
	 	} 
	 	else{
	 		finish();
	 		Toast.makeText(getApplicationContext(), "Internet connection is not present", Toast.LENGTH_SHORT).show();
	 		
	 	}
	 	overridePendingTransition(R.anim.upin,R.anim.upout);
	 	intent = this.getIntent();
		setContentView(R.layout.activity_club_home);
		viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getSupportActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), getIntent().getStringExtra("name"), getApplicationContext());
        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#71c7e2")));
        getActionBar().setTitle("SRMBuzz");
        getActionBar().setDisplayHomeAsUpEnabled(true);
        viewPager.setAdapter(mAdapter);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
       
        
        fb1=(Button)findViewById(R.id.fb);
        go1=(Button)findViewById(R.id.google);
        aak1=(Button)findViewById(R.id.aak);
    
        fb1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/srmteamaakash"));
				startActivity(browserIntent);
			
			}
		});
        go1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://plus.google.com/u/0/113324796450190195347/posts"));
				startActivity(browserIntent);
				
			}
		});
        aak1.setOnClickListener(new View.OnClickListener(){
			public void onClick(View view){
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.srmaakash.in"));
				startActivity(browserIntent);
				
			}
		});

	 
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        	@Override
        	public void onPageSelected(int position) {
             actionBar.setSelectedNavigationItem(position);
        	}

        	@Override
        	public void onPageScrolled(int arg0, float arg1, int arg2) {}

        	@Override
        	public void onPageScrollStateChanged(int arg0) {}
        });
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {}
	public void onTabReselected(android.app.ActionBar.Tab arg0,android.app.FragmentTransaction arg1){}
	public void onTabSelected(android.app.ActionBar.Tab arg0,android.app.FragmentTransaction arg1) {}
	public void onTabUnselected(android.app.ActionBar.Tab arg0,android.app.FragmentTransaction arg1) {}
	 @Override
	 public boolean onCreateOptionsMenu(Menu menu) {
	     MenuInflater inflater = getMenuInflater();
	     inflater.inflate(R.menu.action_button, menu);
         return super.onCreateOptionsMenu(menu);
	 }

	 @Override
	 public boolean onOptionsItemSelected(MenuItem item) {
	        // Take appropriate action for each action item click
	        switch (item.getItemId()) {
	        case android.R.id.home:
	            // location found
	        	 Intent i = new Intent(getApplicationContext(),Home.class);
	   	      startActivity(i);
	   		  //overridePendingTransition(R.anim.upin,R.anim.upout);
	   		  finish();
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	 }
	 
	 
	 private void GoHome() {
	      Intent i = new Intent(getApplicationContext(),Home.class);
	      startActivity(i);
		  //overridePendingTransition(R.anim.upin,R.anim.upout);
		  finish();
	 }
	 	@Override
		public void onBackPressed(){
	 		GoHome(); 
		 }
}

