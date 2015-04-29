package com.kripanshuvivek.campusconn;



import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.widget.ProgressBar;



public class Opening extends Activity {
	private ProgressBar pbar;
	Thread splash_thread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_srm_buzz_main);
	
        
      //  pbar = (ProgressBar) findViewById(R.id.progressBar1);
		splash_thread = new Thread(new Runnable() {

	        @Override
	        public void run() {
	            // TODO Auto-generated method stub
	            int i = 0;
	              for( i=0;i<100;i++){

	    //              pbar.setProgress(i);

	                 // counter_txt.setText(i+" %");
	                  try {
	                    splash_thread.sleep(40);
	                } catch (InterruptedException e) {
	                    // TODO Auto-generated catch block
	                    e.printStackTrace();
	                }
	              }
	              if(i==100){
	                  Intent intent = new Intent(getBaseContext(),Home.class);
	                  startActivity(intent);
	                  finish();
	              }
	        }
	    });
	    splash_thread.start();

	}
	@Override
	protected void onDestroy() {
	    // TODO Auto-generated method stub
	    super.onDestroy();
	}
    }
    


   

