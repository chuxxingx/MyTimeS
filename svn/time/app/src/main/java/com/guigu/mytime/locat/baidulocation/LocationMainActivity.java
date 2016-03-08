package com.guigu.mytime.locat.baidulocation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.guigu.mytime.R;

public class LocationMainActivity extends Activity {
	private TextView locationInfoTextView = null;
	private Button startButton = null;
	
	MyBaiduLotion myLotion;
	MyLocation myLocation;
	String strlocation = "";
	@Override
    public void	 onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location_activity_main);
        
        locationInfoTextView = (TextView) this.findViewById(R.id.tv_loc_info);
        startButton = (Button) this.findViewById(R.id.btn_start);
        
        
        startButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myLotion = new MyBaiduLotion(LocationMainActivity.this);
				myLocation = new MyLocation();
				myLotion.opetateClient();
				new LocationTHread().start();
			}
			});
        
    }
	class LocationTHread extends Thread{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			if(myLotion != null)
			while(!myLotion.getIsFinish()){
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(myLotion.myBDcoordinate != null){
				strlocation =  myLocation.getAddress(myLotion.getLatValue() +"", myLotion.getLongValue() + "");
				myHandler.sendEmptyMessage(1);
			}
			
		}
		
	}
	
	Handler myHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			locationInfoTextView.setText(strlocation);
		}
		
	};
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//myLotion.desClient();
	}
    
	

}
