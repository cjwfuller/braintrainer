package com.appanddone.braintrainer;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class CheckAnswer extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_answer);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		TextView textView = (TextView)findViewById(R.id.check_answer_title);
		textView.setTypeface(typeFace);
		TextView answerTextView = (TextView)findViewById(R.id.correct_or_incorrect_text);
		// Play sounds for incorrect/correct answers and display message
		MediaPlayer mp;
		String className = getIntent().getExtras().getString("className");
		Integer count = brainTrainer.numCorrectBreakdown.get(className);
		Log.d("CheckAnswer", "CheckAnswer.onCreate() Checking answer for " + className);
		Log.d("CheckAnswer", "CheckAnswer.onCreate() Count for " + className + " is: " + count);
		if(getIntent().getExtras().getBoolean("isCorrect")) {
			mp = MediaPlayer.create(this, R.raw.correct);
			answerTextView.setText("YES :-)");
			brainTrainer.numCorrectBreakdown.put(className, count+1);
		} else {
			mp = MediaPlayer.create(this, R.raw.incorrect);
			answerTextView.setText("NO :-(");
		}
		mp.start();
		// Go to next random activity after 1 second
		new CountDownTimer(1000, 1000) {
		     public void onTick(long millisUntilFinished) { }
		     public void onFinish() {
		    	 try {
					 if(!finishIfNoLives()) {
						 startRandomQuestion();
					 }
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
		  }.start();
	}
		
	/**
	 * Finish game if all lives used up
	 */
	private boolean finishIfNoLives() {
		if(brainTrainer.numIncorrect == numLives) {
			Intent intent = new Intent(this, Finish.class);
			CheckAnswer.this.startActivity(intent);
			Toast.makeText(getApplicationContext(), "No lives left!", Toast.LENGTH_LONG).show();
			return true;
		}
		return false;
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
