package com.appanddone.braintrainer;

import android.os.Bundle;
import android.widget.TextView;

public class Finish extends MainActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		
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
