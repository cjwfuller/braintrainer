package com.appanddone.braintrainer;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Classification extends MainActivity {

	private String[][] wordGroups;
	private String[] answers;
	private static int numProblems;
	private int randomProblem;
	
	public Classification() {
		numProblems = 1;
		randomProblem = new Random().nextInt(numProblems);
		setProblems();
	}
	
	private void setProblems() {
		wordGroups = new String[numProblems][5];
		wordGroups[0][0] = "Apple";
		wordGroups[0][1] = "Carrot";
		wordGroups[0][2] = "Orange";
		wordGroups[0][3] = "Cherry";
		wordGroups[0][4] = "Grape";
		answers = new String[numProblems];
		answers[0] = "Carrot";
	}
	
	private void showButtonsAndSetupText() {
		// Find buttons
		Button word1 = (Button)findViewById(R.id.word1_button);
		Button word2 = (Button)findViewById(R.id.word2_button);
		Button word3 = (Button)findViewById(R.id.word3_button);
		Button word4 = (Button)findViewById(R.id.word4_button);
		Button word5 = (Button)findViewById(R.id.word5_button);
		// Make buttons visible
		word1.setVisibility(1);
		word2.setVisibility(1);
		word3.setVisibility(1);
		word4.setVisibility(1);
		word5.setVisibility(1);
		// Assign random problem to words
		word1.setText(wordGroups[randomProblem][0]);
		word2.setText(wordGroups[randomProblem][1]);
		word3.setText(wordGroups[randomProblem][2]);
		word4.setText(wordGroups[randomProblem][3]);
		word5.setText(wordGroups[randomProblem][4]);
	}
	
	private void checkAnswer(View v) {
		Button b = (Button)v;
	    String buttonText = b.getText().toString();
	    if(buttonText.equals(answers[randomProblem])) {
	    	incrementScore(); // Correct
		}
		startRandomQuestion();
	}
	
	private void addClickListenersToButtons() {
		// Find buttons
		Button word1 = (Button)findViewById(R.id.word1_button);
		Button word2 = (Button)findViewById(R.id.word2_button);
		Button word3 = (Button)findViewById(R.id.word3_button);
		Button word4 = (Button)findViewById(R.id.word4_button);
		Button word5 = (Button)findViewById(R.id.word5_button);
		word1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		word2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		word3.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		word4.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
		word5.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) { checkAnswer(v); }
		});
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classification);
		showButtonsAndSetupText();
		addClickListenersToButtons();
	}
}
