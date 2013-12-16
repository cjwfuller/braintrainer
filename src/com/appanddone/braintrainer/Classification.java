package com.appanddone.braintrainer;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Classification extends MainActivity {

	private String[][] wordGroups;
	private String[] answers;
	private int numProblems = 6;
	private int randomProblem;
	
	private void recordQuestion(String questionType, int questionNum) {
		Set<String> set = new HashSet<String>();
		SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
		set = prefs.getStringSet("previous_questions_key", set);
		set.add(Integer.toString(questionNum));
		Editor edit = prefs.edit();
		edit.putStringSet("previous_questions_key", set);
		edit.commit();
	}
	
	private void setProblems() {
		wordGroups = new String[numProblems][5];
		// Problem 0
		wordGroups[0][0] = "Apple";
		wordGroups[0][1] = "Carrot";
		wordGroups[0][2] = "Orange";
		wordGroups[0][3] = "Cherry";
		wordGroups[0][4] = "Grape";
		// Problem 1
		wordGroups[1][0] = "Oak";
		wordGroups[1][1] = "Ash";
		wordGroups[1][2] = "Tree";
		wordGroups[1][3] = "Willow";
		wordGroups[1][4] = "Palm";
		// Problem 2
		wordGroups[2][0] = "Beer";
		wordGroups[2][1] = "Wine";
		wordGroups[2][2] = "Tonic";
		wordGroups[2][3] = "Vodka";
		wordGroups[2][4] = "Gin";
		// Problem 3
		wordGroups[3][0] = "Helicopter";
		wordGroups[3][1] = "Aeroplane";
		wordGroups[3][2] = "Glider";
		wordGroups[3][3] = "Train";
		wordGroups[3][4] = "Drone";
		// Problem 4
		wordGroups[4][0] = "Mars";
		wordGroups[4][1] = "Earth";
		wordGroups[4][2] = "Venus";
		wordGroups[4][3] = "Jupiter";
		wordGroups[4][4] = "Moon";
		// Problem 5
		wordGroups[5][0] = "Helium";
		wordGroups[5][1] = "Magnesium";
		wordGroups[5][2] = "Water";
		wordGroups[5][3] = "Oxygen";
		wordGroups[5][4] = "Iron";
		// Answers
		answers = new String[numProblems];
		answers[0] = "Carrot";
		answers[1] = "Tree";
		answers[2] = "Tonic";
		answers[3] = "Train";
		answers[4] = "Moon";
		answers[5] = "Water";
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
		// Look for a question of this type that has never been asked
		boolean found = false;
		while(!found) {
			randomProblem = new Random().nextInt(numProblems);
			Set<String> set = new HashSet<String>();
			SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
			set = prefs.getStringSet("previous_questions_key", set);
			if(!set.contains(Integer.toString(randomProblem))) {
				found = true;
			}
		}
		setProblems();
		showButtonsAndSetupText();
		addClickListenersToButtons();
		// Keep track of questions that have been asked
		recordQuestion("classification", randomProblem);
	}
}
