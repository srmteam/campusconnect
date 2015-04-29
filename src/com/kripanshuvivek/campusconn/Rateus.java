package com.kripanshuvivek.campusconn;



import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Rateus extends Fragment {
public Rateus(){}
public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.rate_us, container, false);
{
Intent intent = new Intent(Intent.ACTION_VIEW);
intent.setData(Uri.parse("market://details?id=com.kripanshubhargava20.srmbuzz"));
startActivity(intent);
//this.getActivity().finish();
}
return rootView;
}

private void GoHome() {
     Intent i = new Intent(getActivity(),Home.class);
     startActivity(i);
	  //overridePendingTransition(R.anim.upin,R.anim.upout);
    this.getActivity().finish();
}
	public void onBackPressed(){
		GoHome(); 
	 }}