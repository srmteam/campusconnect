package com.kripanshuvivek.campusconn;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class Credits extends Fragment {
	 public WebView webview12;
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
			    Bundle savedInstanceState) {
			  View view = inflater.inflate(R.layout.fragment_credits,
			      container, false);
			  
			  webview12 = (WebView) view.findViewById(R.id.webview12);
				webview12.setWebChromeClient(new WebChromeClient());
				 webview12.getSettings().setDefaultZoom(WebSettings.ZoomDensity.FAR);
			        webview12.getSettings().setBuiltInZoomControls(true);
				
			       
			        webview12.setInitialScale(1);
			        webview12.getSettings().setJavaScriptEnabled(true);
			        webview12.getSettings().setLoadWithOverviewMode(true);
			        webview12.getSettings().setUseWideViewPort(true);
			        webview12.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
			        webview12.setScrollbarFadingEnabled(false);

				webview12 = (WebView) view.findViewById(R.id.webview12);
				WebSettings webSettings = webview12.getSettings();
			    webview12.loadUrl("http://www.srmaakash.in/team/CoreTeam.html");
			    webSettings.setJavaScriptEnabled(true);
			  
			  return view;
}
}