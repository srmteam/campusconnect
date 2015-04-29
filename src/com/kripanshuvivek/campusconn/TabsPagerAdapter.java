package com.kripanshuvivek.campusconn;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {
	String clubname;
	Context context;
	Fragment e, t, r, f;
	public TabsPagerAdapter(FragmentManager fm, String a, Context c) {
		super(fm);
		clubname = a;
		context = c;
		e = new FragmentEvents(context , clubname);
		t = new FragmentTeamInformation(context, clubname);
		r = new FragmentRegistrationForm();
		f = new FragmentFeedback(context, clubname);
		
	}
	@Override
    public Fragment getItem(int index) {
		switch (index){
			case 0: return e;
			case 1: return t;
			case 2: return r;
			case 3: return f;
			
		}
		return null;
    }
    @Override
    public int getCount() {
        return 4;
    }

}
