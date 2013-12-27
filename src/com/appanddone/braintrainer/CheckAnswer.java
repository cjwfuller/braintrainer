package com.appanddone.braintrainer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class CheckAnswer extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_check_answer);
		// Show whether user was correct/incorrect
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		TextView textView = (TextView)findViewById(R.id.check_answer_title);
		textView.setTypeface(typeFace);
		TextView answerTextView = (TextView)findViewById(R.id.correct_or_incorrect_text);
		if(getIntent().getExtras().getBoolean("isCorrect")) {
			answerTextView.setText("YES :-)");
		} else {
			answerTextView.setText("NO :-(");
		}
		// Go to next random activity after 1 second
		new CountDownTimer(900, 1000) {
		     public void onTick(long millisUntilFinished) { }
		     public void onFinish() {
		    	 try {
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
		  }.start();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
