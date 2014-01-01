package com.appanddone.braintrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.os.Bundle;
import android.widget.TextView;

public class Finish extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		
		BrainTrainer brainTrainer = (BrainTrainer)getApplicationContext();
		TextView correct = (TextView)findViewById(R.id.score_text);
		correct.setText("Score: " + Integer.toString(brainTrainer.numCorrect * 100));
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
