package com.kripanshuvivek.campusconn;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class FragmentRegistrationForm extends Fragment {
	 public WebView webview13;
	 
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			    Bundle savedInstanceState) {
			  View view = inflater.inflate(R.layout.fragment_regestration,
			      container, false);
		
			  String cname=FragmentList.clubname1 ;
			  webview13 = (WebView) view.findViewById(R.id.webview13);
				webview13.setWebChromeClient(new WebChromeClient());
				 webview13.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
			        webview13.getSettings().setBuiltInZoomControls(true);
			        webview13.setInitialScale(2);
			        webview13.getSettings().setJavaScriptEnabled(true);
			        webview13.getSettings().setLoadWithOverviewMode(true);
			        webview13.getSettings().setUseWideViewPort(true);
			        webview13.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
			        webview13.setScrollbarFadingEnabled(false);
		 	

				webview13 = (WebView) view.findViewById(R.id.webview13);
				WebSettings webSettings = webview13.getSettings();
			    webview13.loadUrl("http://srmaakash.in/srmbuzz/registerform.php?Club_Name=" + cname);
			    webSettings.setJavaScriptEnabled(true);
			  
			  return view;
}
}