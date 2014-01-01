package com.appanddone.braintrainer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.*;

public class Mathematics extends MainActivity {
	
	private int[][] series;
	private int[][] possibleSolutions;
	public final static int numProblems = 2;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mathematics);
		
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		
		setProblems();
		setPossibleSolutions();
		setUpButtons();
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		// Show the random maths problem
		TextView sequenceTextView = (TextView)findViewById(R.id.maths_sequence);
		TextView instructionsTextView = (TextView)findViewById(R.id.maths_instructions);
		sequenceTextView.setTypeface(typeFace);
		instructionsTextView.setTypeface(typeFace);
		String str = "";
		for(int i = 0; i < series[randomProblem].length - 1; i++) {
			str += Integer.toString(series[randomProblem][i]) + ", ";
		}
		str += " ...\n\n";
		sequenceTextView.setText(str);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	private void setProblems() {
		series = new int[numProblems][6];
		// Progressively decreasing pattern 
		series[0][0] = 81;
		series[0][1] = 80;
		series[0][2] = 78;
		series[0][3] = 75;
		series[0][4] = 71;
		series[0][5] = 66;
		// Pair sums
		series[1][0] = 3;
		series[1][1] = 6;
		series[1][2] = 9;
		series[1][3] = 15;
		series[1][4] = 24;
		series[1][5] = 39;
	}
	
	private void setPossibleSolutions() {
		possibleSolutions = new int[numProblems][5];
		// Progressively decreasing pattern 
		possibleSolutions[0][0] = 82;
		possibleSolutions[0][1] = 63;
		possibleSolutions[0][2] = 66;
		possibleSolutions[0][3] = 65;
		possibleSolutions[0][4] = 70;
		// Pair sums
		possibleSolutions[1][0] = 39;
		possibleSolutions[1][1] = 40;
		possibleSolutions[1][2] = 66;
		possibleSolutions[1][3] = 65;
		possibleSolutions[1][4] = 64;
	}
	
	private void setUpButtons() {
		// Get buttons
		Button answer1 = (Button)findViewById(R.id.answer1_button);
		Button answer2 = (Button)findViewById(R.id.answer2_button);
		Button answer3 = (Button)findViewById(R.id.answer3_button);
		Button answer4 = (Button)findViewById(R.id.answer4_button);
		Button answer5 = (Button)findViewById(R.id.answer5_button);
		// Assign possible answers to their text
		answer1.setText(Integer.toString(possibleSolutions[randomProblem][0]));
		answer2.setText(Integer.toString(possibleSolutions[randomProblem][1]));
		answer3.setText(Integer.toString(possibleSolutions[randomProblem][2]));
		answer4.setText(Integer.toString(possibleSolutions[randomProblem][3]));
		answer5.setText(Integer.toString(possibleSolutions[randomProblem][4]));
		// Give the button text the game font
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		answer1.setTypeface(typeFace);
		answer2.setTypeface(typeFace);
		answer3.setTypeface(typeFace);
		answer4.setTypeface(typeFace);
		answer5.setTypeface(typeFace);
		// Set up button events
		setButtonEvents(answer1);
		setButtonEvents(answer2);
		setButtonEvents(answer3);
		setButtonEvents(answer4);
		setButtonEvents(answer5);
	}
	
	private void setButtonEvents(final Button button) {
		button.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				boolean isCorrect = button.getText().toString().equals(Integer.toString(series[randomProblem][5]));
				if(isCorrect) {
					brainTrainer.numCorrect++;
				} else {
					brainTrainer.numIncorrect++;
				}
				checkAnswer(isCorrect);
			}
		});
	}
}
