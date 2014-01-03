package com.appanddone.braintrainer;


import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Classification extends MainActivity {

	private String[][] wordGroups;
	private String[] answers;
	public final static int numProblems = 21;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_classification);
		showLives();
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		setProblems();
		showButtonsAndSetupText();
		addClickListenersToButtons();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	private void addClickListenersToButtons() {
		TextView textView = (TextView)findViewById(R.id.classification_instructions);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		textView.setTypeface(typeFace);
		// Find buttons
		Button word1 = (Button)findViewById(R.id.word1_button);
		Button word2 = (Button)findViewById(R.id.word2_button);
		Button word3 = (Button)findViewById(R.id.word3_button);
		Button word4 = (Button)findViewById(R.id.word4_button);
		Button word5 = (Button)findViewById(R.id.word5_button);
		// Also add game font!
		word1.setTypeface(typeFace);
		word2.setTypeface(typeFace);
		word3.setTypeface(typeFace);
		word4.setTypeface(typeFace);
		word5.setTypeface(typeFace);
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
		wordGroups[3][0] = "Bee";
		wordGroups[3][1] = "Aeroplane";
		wordGroups[3][2] = "Glider";
		wordGroups[3][3] = "Train";
		wordGroups[3][4] = "Bird";
		// Problem 4
		wordGroups[4][0] = "Mars";
		wordGroups[4][1] = "Earth";
		wordGroups[4][2] = "Venus";
		wordGroups[4][3] = "Jupiter";
		wordGroups[4][4] = "Moon";
		// Problem 5
		wordGroups[5][0] = "Tennis";
		wordGroups[5][1] = "Cricket";
		wordGroups[5][2] = "Badminton";
		wordGroups[5][3] = "Netball";
		wordGroups[5][4] = "Football";
		// Problem 6
		wordGroups[6][0] = "Square";
		wordGroups[6][1] = "Circle";
		wordGroups[6][2] = "Sphere";
		wordGroups[6][3] = "Rectangle";
		wordGroups[6][4] = "Rhombus";
		// Problem 7
		wordGroups[7][0] = "Trout";
		wordGroups[7][1] = "Sole";
		wordGroups[7][2] = "Plaice";
		wordGroups[7][3] = "Tuna";
		wordGroups[7][4] = "Fish";
		// Problem 8
		wordGroups[8][0] = "Hammer";
		wordGroups[8][1] = "Screwdriver";
		wordGroups[8][2] = "Saw";
		wordGroups[8][3] = "Drill";
		wordGroups[8][4] = "Wood";
		// Problem 9
		wordGroups[9][0] = "Americano";
		wordGroups[9][1] = "Espresso";
		wordGroups[9][2] = "Latte";
		wordGroups[9][3] = "Tea";
		wordGroups[9][4] = "Cappuccino";
		// Problem 10
		wordGroups[10][0] = "Shirt";
		wordGroups[10][1] = "Cardigan";
		wordGroups[10][2] = "Jumper";
		wordGroups[10][3] = "Vest";
		wordGroups[10][4] = "Socks";
		// Problem 11
		wordGroups[11][0] = "Red";
		wordGroups[11][1] = "Blue";
		wordGroups[11][2] = "Orange";
		wordGroups[11][3] = "Light";
		wordGroups[11][4] = "Purple";	
		// Problem 12
		wordGroups[12][0] = "Wind";
		wordGroups[12][1] = "Summer";
		wordGroups[12][2] = "Snow";
		wordGroups[12][3] = "Rain";
		wordGroups[12][4] = "Hail";	
		// Problem 13
		wordGroups[13][0] = "Ocean";
		wordGroups[13][1] = "Lake";
		wordGroups[13][2] = "Water";
		wordGroups[13][3] = "Pond";
		wordGroups[13][4] = "Sea";	
		// Problem 14
		wordGroups[14][0] = "Brazilian";
		wordGroups[14][1] = "Chinese";
		wordGroups[14][2] = "Africa";
		wordGroups[14][3] = "English";
		wordGroups[14][4] = "American";
		// Problem 15
		wordGroups[15][0] = "Harp";
		wordGroups[15][1] = "Guitar";
		wordGroups[15][2] = "Cello";
		wordGroups[15][3] = "Violin";
		wordGroups[15][4] = "Saxophone";	
		// Problem 16
		wordGroups[16][0] = "Paragraph";
		wordGroups[16][1] = "Sentence";
		wordGroups[16][2] = "Grammar";
		wordGroups[16][3] = "Word";
		wordGroups[16][4] = "Chapter";
		// Problem 17
		wordGroups[17][0] = "Two";
		wordGroups[17][1] = "Third";
		wordGroups[17][2] = "Quarter";
		wordGroups[17][3] = "Tenth";
		wordGroups[17][4] = "Half";	
		// Problem 18
		wordGroups[18][0] = "Heart";
		wordGroups[18][1] = "Lung";
		wordGroups[18][2] = "Ear";
		wordGroups[18][3] = "Liver";
		wordGroups[18][4] = "Kidney";
		// Problem 19
		wordGroups[19][0] = "Shout";
		wordGroups[19][1] = "Sing";
		wordGroups[19][2] = "Phone";
		wordGroups[19][3] = "Talk";
		wordGroups[19][4] = "Whisper";
		// Problem 20
		wordGroups[20][0] = "Bulb";
		wordGroups[20][1] = "Candle";
		wordGroups[20][2] = "Torch";
		wordGroups[20][3] = "Light";
		wordGroups[20][4] = "Glow Stick";		
		// Answers
		answers = new String[numProblems];
		answers[0] = "Carrot";
		answers[1] = "Tree";
		answers[2] = "Tonic";
		answers[3] = "Train";
		answers[4] = "Moon";
		answers[5] = "Badminton";
		answers[6] = "Sphere";
		answers[7] = "Fish";
		answers[8] = "Wood";
		answers[9] = "Tea";
		answers[10] = "Socks";
		answers[11] = "Light";
		answers[12] = "Summer";
		answers[13] = "Water";
		answers[14] = "Africa";
		answers[15] = "Saxophone";
		answers[16] = "Grammar";
		answers[17] = "Two";
		answers[18] = "Ear";
		answers[19] = "Phone";
		answers[20] = "Lights";
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
	    boolean isCorrect = buttonText.equals(answers[randomProblem]); 
	    if(isCorrect) {
	    	brainTrainer.numCorrect++;
		} else {
			brainTrainer.numIncorrect++;
		}
	    checkAnswer(isCorrect, "Classification");
	}

}
