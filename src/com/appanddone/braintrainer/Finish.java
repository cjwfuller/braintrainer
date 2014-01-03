package com.appanddone.braintrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.media.MediaPlayer;
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
		
		// Show how many questions were correct and give a breakdown of what
		// the user got right and wrong
		String finishStr = "";
		TextView correct = (TextView)findViewById(R.id.score_text);
		finishStr += "Questions correct: " + Integer.toString(brainTrainer.numCorrect) + "\n\n\n\n";
		finishStr = finishStr.trim();
		finishStr += "Breakdown: \n";
		correct.setText(finishStr);
		
		MediaPlayer mp = MediaPlayer.create(this, R.raw.clap);
	    mp.start();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
