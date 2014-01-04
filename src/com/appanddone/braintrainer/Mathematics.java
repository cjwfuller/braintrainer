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
	public final static int numProblems = 21;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mathematics);
		showLives();
		
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
		// Problem 2 - evens
		series[2][0] = 2;
		series[2][1] = 4;
		series[2][2] = 6;
		series[2][3] = 8;
		series[2][4] = 10;
		series[2][5] = 12;
		// Problem 3 - primes
		series[3][0] = 3;
		series[3][1] = 5;
		series[3][2] = 7;
		series[3][3] = 11;
		series[3][4] = 13;
		series[3][5] = 17;		
		// Problem 4 - odds
		series[4][0] = 3;
		series[4][1] = 5;
		series[4][2] = 7;
		series[4][3] = 9;
		series[4][4] = 11;
		series[4][5] = 13;	
		// Problem 5 - alt. 0s and 1s
		series[5][0] = 0;
		series[5][1] = 1;
		series[5][2] = 0;
		series[5][3] = 1;
		series[5][4] = 0;
		series[5][5] = 1;
		// Problem 6 - +2s
		series[6][0] = -10;
		series[6][1] = -8;
		series[6][2] = -6;
		series[6][3] = -4;
		series[6][4] = -2;
		series[6][5] = 0;
		// Problem 7 - 3 times table but skip one each time
		series[7][0] = 3;
		series[7][1] = 9;
		series[7][2] = 15;
		series[7][3] = 21;
		series[7][4] = 27;
		series[7][5] = 33;
		// Problem 8 - 2 times table but *-1 every other
		series[8][0] = -2;
		series[8][1] = 4;
		series[8][2] = -6;
		series[8][3] = 8;
		series[8][4] = -10;
		series[8][5] = 12;
		// Problem 9 - triangular numbers
		series[9][0] = 1;
		series[9][1] = 3;
		series[9][2] = 6;
		series[9][3] = 10;
		series[9][4] = 15;
		series[9][5] = 21;	
		// Problem 10 - square numbers
		series[10][0] = 1;
		series[10][1] = 4;
		series[10][2] = 9;
		series[10][3] = 16;
		series[10][4] = 25;
		series[10][5] = 36;
		// Problem 11 - 
		series[11][0] = 1;
		series[11][1] = 2;
		series[11][2] = 4;
		series[11][3] = 8;
		series[11][4] = 16;
		series[11][5] = 32;
		// Problem 12 - cube numbers
		series[12][0] = 1;
		series[12][1] = 8;
		series[12][2] = 27;
		series[12][3] = 64;
		series[12][4] = 125;
		series[12][5] = 216;
		// Problem 13 - hailstone with n=11 (If n is even, divide it by 2.  
		// If n is odd, multiply it by 3 and add 1)
		series[13][0] = 11;
		series[13][1] = 34;
		series[13][2] = 17;
		series[13][3] = 52;
		series[13][4] = 26;
		series[13][5] = 13;
		// Problem 14 - speak and say (next 13112221)
		series[14][0] = 1;
		series[14][1] = 11;
		series[14][2] = 21;
		series[14][3] = 1211;
		series[14][4] = 111221;
		series[14][5] = 312211;
		// Problem 15 - factorials (next 5040)
		series[15][0] = 1;
		series[15][1] = 2;
		series[15][2] = 6;
		series[15][3] = 24;
		series[15][4] = 120;
		series[15][5] = 720;
		// Problem 16 - 4n+2 (next 30)
		series[16][0] = 4;
		series[16][1] = 10;
		series[16][2] = 14;
		series[16][3] = 18;
		series[16][4] = 22;
		series[16][5] = 26;	
		// Problem 17 - 6+7+6+7... (next 45)
		series[17][0] = 6;
		series[17][1] = 13;
		series[17][2] = 19;
		series[17][3] = 26;
		series[17][4] = 32;
		series[17][5] = 39;	
		// Problem 18 - Pentagonal numbers (next 70)
		series[18][0] = 1;
		series[18][1] = 5;
		series[18][2] = 12;
		series[18][3] = 22;
		series[18][4] = 35;
		series[18][5] = 51;
		// Problem 19 - Decrease by 13 every other (next 14)
		series[19][0] = 53;
		series[19][1] = 53;
		series[19][2] = 40;
		series[19][3] = 40;
		series[19][4] = 27;
		series[19][5] = 27;
		// Problem 20 - add, subtract, add, ... (next 25)
		series[20][0] = 22;
		series[20][1] = 21;
		series[20][2] = 23;
		series[20][3] = 22;
		series[20][4] = 24;
		series[20][5] = 23;			
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
		// 
		possibleSolutions[2][0] = 12;
		possibleSolutions[2][1] = 14;
		possibleSolutions[2][2] = 10;
		possibleSolutions[2][3] = 8;
		possibleSolutions[2][4] = 16;
		// 
		possibleSolutions[3][0] = 12;
		possibleSolutions[3][1] = 11;
		possibleSolutions[3][2] = 17;
		possibleSolutions[3][3] = 19;
		possibleSolutions[3][4] = 18;
		// 
		possibleSolutions[4][0] = 17;
		possibleSolutions[4][1] = 9;
		possibleSolutions[4][2] = 7;
		possibleSolutions[4][3] = 13;
		possibleSolutions[4][4] = 15;
		// 
		possibleSolutions[5][0] = -1;
		possibleSolutions[5][1] = 1;
		possibleSolutions[5][2] = 0;
		possibleSolutions[5][3] = 2;
		possibleSolutions[5][4] = 3;
		// 
		possibleSolutions[6][0] = -1;
		possibleSolutions[6][1] = 2;
		possibleSolutions[6][2] = 0;
		possibleSolutions[6][3] = -4;
		possibleSolutions[6][4] = 4;
		// 
		possibleSolutions[7][0] = 32;
		possibleSolutions[7][1] = 35;
		possibleSolutions[7][2] = 34;
		possibleSolutions[7][3] = 33;
		possibleSolutions[7][4] = 31;
		// 
		possibleSolutions[8][0] = 12;
		possibleSolutions[8][1] = -12;
		possibleSolutions[8][2] = 14;
		possibleSolutions[8][3] = -14;
		possibleSolutions[8][4] = -16;
		// 
		possibleSolutions[9][0] = 19;
		possibleSolutions[9][1] = 21;
		possibleSolutions[9][2] = 22;
		possibleSolutions[9][3] = 20;
		possibleSolutions[9][4] = 23;
		// 
		possibleSolutions[10][0] = 37;
		possibleSolutions[10][1] = 36;
		possibleSolutions[10][2] = 35;
		possibleSolutions[10][3] = 34;
		possibleSolutions[10][4] = 33;
		// 
		possibleSolutions[11][0] = 32;
		possibleSolutions[11][1] = 64;
		possibleSolutions[11][2] = 128;
		possibleSolutions[11][3] = 16;
		possibleSolutions[11][4] = 256;
		// 
		possibleSolutions[12][0] = 211;
		possibleSolutions[12][1] = 216;
		possibleSolutions[12][2] = 217;
		possibleSolutions[12][3] = 215;
		possibleSolutions[12][4] = 212;
		// 
		possibleSolutions[13][0] = 67;
		possibleSolutions[13][1] = 26;
		possibleSolutions[13][2] = 13;
		possibleSolutions[13][3] = 14;
		possibleSolutions[13][4] = 13;
		// 
		possibleSolutions[14][0] = 312111;
		possibleSolutions[14][1] = 311211;
		possibleSolutions[14][2] = 322211;
		possibleSolutions[14][3] = 312221;
		possibleSolutions[14][4] = 312211;
		// 
		possibleSolutions[15][0] = 140;
		possibleSolutions[15][1] = 530;
		possibleSolutions[15][2] = 720;
		possibleSolutions[15][3] = 620;
		possibleSolutions[15][4] = 740;
		// 
		possibleSolutions[16][0] = 24;
		possibleSolutions[16][1] = 22;
		possibleSolutions[16][2] = 26;
		possibleSolutions[16][3] = 28;
		possibleSolutions[16][4] = 20;
		// 
		possibleSolutions[17][0] = 38;
		possibleSolutions[17][1] = 40;
		possibleSolutions[17][2] = 42;
		possibleSolutions[17][3] = 45;
		possibleSolutions[17][4] = 39;
		// 
		possibleSolutions[18][0] = 52;
		possibleSolutions[18][1] = 51;
		possibleSolutions[18][2] = 50;
		possibleSolutions[18][3] = 48;
		possibleSolutions[18][4] = 42;
		// 
		possibleSolutions[19][0] = 27;
		possibleSolutions[19][1] = 28;
		possibleSolutions[19][2] = 14;
		possibleSolutions[19][3] = 25;
		possibleSolutions[19][4] = 24;
		// 
		possibleSolutions[20][0] = 20;
		possibleSolutions[20][1] = 22;
		possibleSolutions[20][2] = 23;
		possibleSolutions[20][3] = 25;
		possibleSolutions[20][4] = 30;		
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
				checkAnswer(isCorrect, "Mathematics");
			}
		});
	}
}
