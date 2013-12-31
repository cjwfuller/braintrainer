package com.appanddone.braintrainer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Verbal extends MainActivity {
	
	private String[][] categories;
	private String[] answers;
	private String[] scrambledWords;
	public final static int numProblems = 1;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verbal);
		setProblems();
		TextView scrambledWordTextView = (TextView)findViewById(R.id.verbal_letters);
		scrambledWordTextView.setText(scrambledWords[randomProblem]);
		showButtonsAndSetupText();
		addClickListenersToButtons();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	private void addClickListenersToButtons() {
		// Make the instruction text look cool
		TextView verbalInstructionsTextView = (TextView)findViewById(R.id.verbal_instructions);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		verbalInstructionsTextView.setTypeface(typeFace);
		// Find buttons
		Button category1_button = (Button)findViewById(R.id.category1_button);
		Button category2_button = (Button)findViewById(R.id.category2_button);
		Button category3_button = (Button)findViewById(R.id.category3_button);
		Button category4_button = (Button)findViewById(R.id.category4_button);
		// Also add game font!
		category1_button.setTypeface(typeFace);
		category2_button.setTypeface(typeFace);
		category3_button.setTypeface(typeFace);
		category4_button.setTypeface(typeFace);
		category1_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		category2_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		category3_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		category4_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
	}
	
	private void setProblems() {
		categories = new String[numProblems][5];
		// Problem 0 - choices
		categories[0][0] = "City";
		categories[0][1] = "Fruit";
		categories[0][2] = "Bird";
		categories[0][3] = "Vegetable";
		// Problem 0 - scrambled letters
		scrambledWords = new String[numProblems];
		scrambledWords[0] = "RAPETEKA";
		// Answers
		answers = new String[numProblems];
		answers[0] = "Bird";
	}
	
	private void showButtonsAndSetupText() {
		// Find buttons
		Button category1 = (Button)findViewById(R.id.category1_button);
		Button category2 = (Button)findViewById(R.id.category2_button);
		Button category3 = (Button)findViewById(R.id.category3_button);
		Button category4 = (Button)findViewById(R.id.category4_button);
		// Make buttons visible
		category1.setVisibility(1);
		category2.setVisibility(1);
		category3.setVisibility(1);
		category4.setVisibility(1);
		// Assign random problem to words
		category1.setText(categories[randomProblem][0]);
		category2.setText(categories[randomProblem][1]);
		category3.setText(categories[randomProblem][2]);
		category4.setText(categories[randomProblem][3]);
	}
	
	private void checkAnswer(View v) {
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
	    boolean isCorrect = buttonText.equals(answers[randomProblem]); 
	    if(isCorrect) {
	    	brainTrainer.numCorrect++;
		} else {
			brainTrainer.numIncorrect++;
		}
	    checkAnswer(isCorrect);
	}

}
