package com.kripanshuvivek.campusconn;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Home extends Activity{
	  private DrawerLayout mDrawerLayout;
	    private ListView mDrawerList;
	    private ActionBarDrawerToggle mDrawerToggle;
	    private CharSequence mDrawerTitle;
	    private CharSequence mTitle;
	    private String[] navMenuTitles;
	    private TypedArray navMenuIcons;
	    private ArrayList<NavDrawerItem> navDrawerItems;
	    private NavDrawerListAdapter adapter;
	    ProgressDialog pDialog;
	    private String str;
	    
	    Context ctx;
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
		 	super.onCreate(savedInstanceState);
		 	overridePendingTransition(R.anim.upin,R.anim.upout);
		 	setContentView(R.layout.buzzhome);
		 	
		 	getActionBar().setTitle("Campus Connect");
	        getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#71c7e2")));
	        Fragment frag = new FragmentList(Home.this, "");
	        getFragmentManager().beginTransaction().replace(R.id.frame_container, frag).commit();
	        mTitle = mDrawerTitle = getTitle();
	        
	        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
	        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
	        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);
	        navDrawerItems = new ArrayList<NavDrawerItem>();

	        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
	        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
	        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
	        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
	        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
	        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
	        
	        navMenuIcons.recycle();
	        
	        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());
	        adapter = new NavDrawerListAdapter(getApplicationContext(),navDrawerItems);
	        mDrawerList.setAdapter(adapter);
	        getActionBar().setDisplayHomeAsUpEnabled(true);
	        getActionBar().setHomeButtonEnabled(true);
	        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
	                R.drawable.ic_drawer, //nav menu toggle icon
	                R.string.app_name, // nav drawer open - description for accessibility
	                R.string.app_name // nav drawer open - description for accessibility
	        ){
	            public void onDrawerClosed(View view) {
	                getActionBar().setTitle(mTitle);
	                invalidateOptionsMenu();
	        }
	        public void onDrawerOpened(View drawerView) {
	                getActionBar().setTitle(mDrawerTitle);
	                invalidateOptionsMenu();
	            }
	        };
	        mDrawerLayout.setDrawerListener(mDrawerToggle);
	        if (savedInstanceState == null) {
	            // on first time display view for first nav item
	            displayView(0);
	        }
	    }
	 
	    /**
	     * Slide menu item click listener
	     * */
	    private class SlideMenuClickListener implements ListView.OnItemClickListener {
	        @Override
	        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	            displayView(position);
	        }
	    }
	    @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        getMenuInflater().inflate(R.menu.main, menu);
	        return true;
	    }
	    @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        if (mDrawerToggle.onOptionsItemSelected(item)) {
	            return true;
	        }
	        switch (item.getItemId()) {
	        case R.id.action_settings:
	        	exit();
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	        }
	    }
	    
	    private void exit() {
			// TODO Auto-generated method stub
	    	AlertDialog.Builder alertDialog = new AlertDialog.Builder(Home.this);
	   	    alertDialog.setTitle("EXIT");
	   	    alertDialog.setMessage("Are you sure you want to quit?");
	   	    alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	   	    	public void onClick(DialogInterface dialog,int which) {
	   	    		  	Intent i = new Intent(getApplicationContext(), Finish.class);
	   	    		  	startActivity(i);
	   	    		  	finish();
	   	    	}
	   	    });
	   	    alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();
				}
			});
	   	    alertDialog.show();
		}
		@Override
	    public boolean onPrepareOptionsMenu(Menu menu) {
	        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
	        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
	        return super.onPrepareOptionsMenu(menu);
	    }
	    
	    private void displayView(int position){
	        Fragment fragment = null;
	        switch (position) {
	        	case 0: fragment = new FragmentList(Home.this, "");
	            break;
	            case 1:
	                fragment = new About();
	                break;
	            case 2:
	            	AlertDialog.Builder alert = new AlertDialog.Builder(this);
	            	alert.setTitle("Search");
	            	alert.setMessage("Enter keyword");
	            	final EditText input = new EditText(this);
	            	alert.setView(input);
	            	alert.setPositiveButton("Search", new DialogInterface.OnClickListener() {
	            		public void onClick(DialogInterface dialog, int whichButton) {
	            			str = input.getText().toString();
	            			Fragment frag = new FragmentList(Home.this, str);
	            			getFragmentManager().beginTransaction().replace(R.id.frame_container, frag).commit();
	            			dialog.dismiss();
	            		}
	            	});
	            	alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	            		public void onClick(DialogInterface dialog, int whichButton) {
	            			dialog.dismiss();
	            		}
	            	});
        			mDrawerLayout.closeDrawer(mDrawerList);
	            	alert.show();
	                break;
	            case 3:
	                fragment = new	Help();
	                
	                break;
	            case 4:
	                fragment = new Rateus();
	                break;
	           
	            case 5:
	                fragment = new Credits();
	                break;
	     
	            default:Toast.makeText(getApplicationContext(), "Nothing Selected", Toast.LENGTH_SHORT).show();
	                break;
	        	
	        }
	        if (fragment != null) {
	            FragmentManager fragmentManager = getFragmentManager();
	            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
	            mDrawerList.setItemChecked(position, true);
	            mDrawerList.setSelection(position);
	            setTitle(navMenuTitles[position]);
	            mDrawerLayout.closeDrawer(mDrawerList);
	        } else {
	            Log.e("BuzzHomeActivity", "Error in creating fragment");
	        }
	    }
	 
	    @Override
	    public void setTitle(CharSequence title) {
	        mTitle = title;
	        getActionBar().setTitle(mTitle);
	    }
	    
	    @Override
	    protected void onPostCreate(Bundle savedInstanceState) {
	        super.onPostCreate(savedInstanceState);
	        mDrawerToggle.syncState();
	    }
	    @Override
	    public void onConfigurationChanged(Configuration newConfig) {
	        super.onConfigurationChanged(newConfig);
	        mDrawerToggle.onConfigurationChanged(newConfig);
	    }
	    
	    @Override
	   	public void onBackPressed() {
	    	exit(); 	 
	    }
	    public Home(Context context) {
	    	this.ctx = context;
	    	
	    }
	    public Home() {
	    	//empty constructor
	    }

}