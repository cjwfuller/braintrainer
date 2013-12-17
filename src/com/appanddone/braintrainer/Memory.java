package com.appanddone.braintrainer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.app.Activity;

public class Memory extends MainActivity {

	public final static int numProblems = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_memory);
		
		final TextView timerView = (TextView)findViewById(R.id.countdown_timer);
		CountDownTimer countDownTimer = new CountDownTimer(5000, 1000) {

		     public void onTick(long millisUntilFinished) {
		    	 timerView.setText("Seconds remaining: " + millisUntilFinished / 1000);
		     }

		     public void onFinish() {
		    	 //timerView.setText("done!");
		     }
		  }.start();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
