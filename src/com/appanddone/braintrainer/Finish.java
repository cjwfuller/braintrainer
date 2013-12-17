package com.appanddone.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Finish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_finish);
		
		BrainTrainer brainTrainer = (BrainTrainer)getApplicationContext();
		TextView correct = (TextView)findViewById(R.id.correct_answers_text);
		correct.setText("Correct answers: " + Integer.toString(brainTrainer.numCorrect));

		TextView incorrect = (TextView)findViewById(R.id.incorrect_answers_text);
		incorrect.setText("Incorrect answers: " + Integer.toString(brainTrainer.numIncorrect));
		
		TextView percentage_correct = (TextView)findViewById(R.id.percentage_correct_answers_text);
		float percent = (brainTrainer.numCorrect * 100.0f) / brainTrainer.totalNumQuestionsAsked;
		percentage_correct.setText( "Percentage correct: " + Float.toString(percent) + "%");		
	}
}
