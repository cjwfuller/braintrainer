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
		TextView correct = (TextView)findViewById(R.id.correct_answers_text);
		correct.setText("Correct answers: " + Integer.toString(brainTrainer.numCorrect));

		TextView incorrect = (TextView)findViewById(R.id.incorrect_answers_text);
		incorrect.setText("Incorrect answers: " + Integer.toString(brainTrainer.numIncorrect));
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
