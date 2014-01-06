package com.appanddone.braintrainer;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Verbal extends MainActivity {
	
	private String[][] categories;
	private String[] answers;
	private String[] scrambledWords;
	public final static int numProblems = 15;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verbal);
		showLives();
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
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
		scrambledWords = new String[numProblems];
		answers = new String[numProblems];
		// Problem 0
		categories[0][0] = "City";
		categories[0][1] = "Fruit";
		categories[0][2] = "Bird";
		categories[0][3] = "Vegetable";
		scrambledWords[0] = "RAPETEKA";
		answers[0] = "Bird";
		// Problem 1
		categories[1][0] = "Dog";
		categories[1][1] = "Food";
		categories[1][2] = "Drink";
		categories[1][3] = "Wood";
		scrambledWords[1] = "AERDB"; // bread
		answers[1] = "Bread";
		// Problem 2
		categories[2][0] = "Country";
		categories[2][1] = "Politician";
		categories[2][2] = "Tree";
		categories[2][3] = "Metal";
		scrambledWords[2] = "ZABLIR"; // brazil
		answers[2] = "Country";
		// Problem 3
		categories[3][0] = "Tool";
		categories[3][1] = "Wood";
		categories[3][2] = "Planet";
		categories[3][3] = "Drink";
		scrambledWords[3] = "IEPN"; // pine
		answers[3] = "Wood";
		// Problem 4
		categories[4][0] = "Sport";
		categories[4][1] = "Planet";
		categories[4][2] = "Ocean";
		categories[4][3] = "Rock";
		scrambledWords[4] = "ETURJIP"; // jupiter
		answers[4] = "Planet";	
		// Problem 5
		categories[5][0] = "Ocean";
		categories[5][1] = "Mountain";
		categories[5][2] = "Tree";
		categories[5][3] = "Beach";
		scrambledWords[5] = "TLNCTIAA"; // atlantic
		answers[5] = "Ocean";	
		// Problem 6
		categories[6][0] = "Alcohol";
		categories[6][1] = "Animal";
		categories[6][2] = "Race";
		categories[6][3] = "Instrument";
		scrambledWords[6] = "POOSNEXAH"; // saxophone
		answers[6] = "Instrument";
		// Problem 7
		categories[7][0] = "Gas";
		categories[7][1] = "Furniture";
		categories[7][2] = "Rock";
		categories[7][3] = "Weather";
		scrambledWords[7] = "AGEINRT"; // granite
		answers[7] = "Rock";
		// Problem 8
		categories[8][0] = "Pig";
		categories[8][1] = "Plant";
		categories[8][2] = "Month";
		categories[8][3] = "Season";
		scrambledWords[8] = "TIRNWE"; // winter
		answers[8] = "Season";
		// Problem 9
		categories[9][0] = "Meat";
		categories[9][1] = "Vegetable";
		categories[9][2] = "Spice";
		categories[9][3] = "Herb";
		scrambledWords[9] = "KRIAAPP";
		answers[9] = "Spice";
		// Problem 10
		categories[10][0] = "Month";
		categories[10][1] = "Clothing";
		categories[10][2] = "Seed";
		categories[10][3] = "Cat";
		scrambledWords[10] = "THSIR";
		answers[10] = "Clothing";
		// Problem 11
		categories[11][0] = "City";
		categories[11][1] = "Muscle";
		categories[11][2] = "Building";
		categories[11][3] = "Bone";
		scrambledWords[11] = "CAUSLPA";
		answers[11] = "Bone";
		// Problem 12
		categories[12][0] = "Shape";
		categories[12][1] = "River";
		categories[12][2] = "Sport";
		categories[12][3] = "Road";
		scrambledWords[12] = "HSAMTE";
		answers[12] = "River";
		// Problem 13
		categories[13][0] = "Dog";
		categories[13][1] = "Cattle";
		categories[13][2] = "Fish";
		categories[13][3] = "Bird";
		scrambledWords[13] = "LLCIEO";
		answers[13] = "Dog";
		// Problem 14
		categories[14][0] = "Herb";
		categories[14][1] = "Weather";
		categories[14][2] = "Plant";
		categories[14][3] = "Boat";
		scrambledWords[14] = "YHMET";
		answers[14] = "Herb";	
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
	    checkAnswer(isCorrect, "Verbal");
	}

}
