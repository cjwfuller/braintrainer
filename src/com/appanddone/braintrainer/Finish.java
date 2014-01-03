package com.appanddone.braintrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class Finish extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		enableButtons();
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		// Load ad
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		BrainTrainer brainTrainer = (BrainTrainer)getApplicationContext();
		// Get text views to put scores in
		TextView instructions = (TextView)findViewById(R.id.answers_intro_text);
		TextView totalCorrect = (TextView)findViewById(R.id.score_text);
		TextView logic = (TextView)findViewById(R.id.logic_score_text);
		TextView classification = (TextView)findViewById(R.id.classification_score_text);
		TextView verbal = (TextView)findViewById(R.id.verbal_score_text);
		TextView memory = (TextView)findViewById(R.id.memory_score_text);
		TextView mathematics = (TextView)findViewById(R.id.mathematical_score_text);
		// Game font
		instructions.setTypeface(typeFace);
		totalCorrect.setTypeface(typeFace);
		logic.setTypeface(typeFace);
		classification.setTypeface(typeFace);
		verbal.setTypeface(typeFace);
		memory.setTypeface(typeFace);
		mathematics.setTypeface(typeFace);
		// Show scores
		totalCorrect.setText("Total correct: " + Integer.toString(brainTrainer.numCorrect) + "\n");
		logic.setText("Logic: " + Integer.toString(brainTrainer.numCorrectBreakdown.get("Logic")) + " / " + Integer.toString(brainTrainer.questionsAsked.get("Logic").size()));
		classification.setText("Classification: " + Integer.toString(brainTrainer.numCorrectBreakdown.get("Classification")) + " / " + Integer.toString(brainTrainer.questionsAsked.get("Classification").size()));
		verbal.setText("Verbal: " + Integer.toString(brainTrainer.numCorrectBreakdown.get("Verbal")) + " / " + Integer.toString(brainTrainer.questionsAsked.get("Verbal").size()));
		memory.setText("Memory: " + Integer.toString(brainTrainer.numCorrectBreakdown.get("Memory")) + " / " + Integer.toString(brainTrainer.questionsAsked.get("Memory").size()));
		mathematics.setText("Mathematics: " + Integer.toString(brainTrainer.numCorrectBreakdown.get("Mathematics")) + " / " + Integer.toString(brainTrainer.questionsAsked.get("Mathematics").size()));
		// If it's a new highscore then save the score
		SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
		int score = prefs.getInt("highscore", 0);
		if(score < brainTrainer.numCorrect) {
			// Store
			Editor editor = prefs.edit();
			editor.putInt("highscore", brainTrainer.numCorrect);
			editor.commit();
			// Play clap  sound
			MediaPlayer mp = MediaPlayer.create(this, R.raw.clap);
			SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
			if(settings.getBoolean("sound", false)) {
				mp.start();
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	private void enableButtons() {
		Button mainMenuButton = (Button)findViewById(R.id.main_menu_button);
		mainMenuButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				brainTrainer.reset();
				Intent intent_main = new Intent(Finish.this, MainActivity.class);
				Finish.this.startActivity(intent_main);
			}
		});
		
		Button playAgainButton = (Button)findViewById(R.id.play_again_button);
		playAgainButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					brainTrainer.reset();
					startRandomQuestion();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
