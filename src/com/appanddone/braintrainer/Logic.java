package com.appanddone.braintrainer;

import java.util.ArrayList;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An implementation of the '8-puzzle' game for Android
 * 
 * The Integer 0 in the grid represents the empty tile
 * 
 * @author cjwfuller
 *
 */
public class Logic extends MainActivity {
	
	public final static int numProblems = 21;
	public static int randomProblem;
	private int grids[][][] = new int[numProblems][3][3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic);
		showLives();
		AdView adView = (AdView)this.findViewById(R.id.adView);
	    AdRequest adRequest = new AdRequest.Builder().build();
	    adView.loadAd(adRequest);
		// Build a solvable grid
		setProblems();
		displayGrid();
		printGrid();
		addClickHandlers();
	    // Nice game font
	    Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
	    TextView textView = (TextView)findViewById(R.id.logic_instructions);
	    textView.setTypeface(typeFace);
	    addClickHandlers();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	/**
	 * Set hardcoded problems.  This class used to generate random, solvable
	 * and configurably simple problems but computational overhead was too 
	 * great
	 * 
	 * @return void
	 */
	private void setProblems() {
		// Problem 0
		grids[0][0][0] = 0;
		grids[0][1][0] = 1;
		grids[0][2][0] = 3;
		grids[0][0][1] = 4;
		grids[0][1][1] = 2;
		grids[0][2][1] = 5;
		grids[0][0][2] = 7;
		grids[0][1][2] = 8;
		grids[0][2][2] = 6;
		// Problem 1
		grids[1][0][0] = 1;
		grids[1][1][0] = 0;
		grids[1][2][0] = 3;
		grids[1][0][1] = 6;
		grids[1][1][1] = 2;
		grids[1][2][1] = 5;
		grids[1][0][2] = 8;
		grids[1][1][2] = 7;
		grids[1][2][2] = 4;
		// Problem 2
		grids[2][0][0] = 1;
		grids[2][1][0] = 4;
		grids[2][2][0] = 2;
		grids[2][0][1] = 3;
		grids[2][1][1] = 6;
		grids[2][2][1] = 8;
		grids[2][0][2] = 0;
		grids[2][1][2] = 7;
		grids[2][2][2] = 5;		
		// Problem 3
		grids[3][0][0] = 2;
		grids[3][1][0] = 5;
		grids[3][2][0] = 3;
		grids[3][0][1] = 6;
		grids[3][1][1] = 1;
		grids[3][2][1] = 4;
		grids[3][0][2] = 8;
		grids[3][1][2] = 7;
		grids[3][2][2] = 0;
		// Problem 4
		grids[4][0][0] = 2;
		grids[4][1][0] = 6;
		grids[4][2][0] = 3;
		grids[4][0][1] = 1;
		grids[4][1][1] = 4;
		grids[4][2][1] = 7;
		grids[4][0][2] = 0;
		grids[4][1][2] = 8;
		grids[4][2][2] = 5;
		// Problem 5
		grids[5][0][0] = 1;
		grids[5][1][0] = 2;
		grids[5][2][0] = 3;
		grids[5][0][1] = 6;
		grids[5][1][1] = 4;
		grids[5][2][1] = 5;
		grids[5][0][2] = 7;
		grids[5][1][2] = 8;
		grids[5][2][2] = 0;	
		// Problem 6
		grids[6][0][0] = 2;
		grids[6][1][0] = 1;
		grids[6][2][0] = 3;
		grids[6][0][1] = 7;
		grids[6][1][1] = 4;
		grids[6][2][1] = 5;
		grids[6][0][2] = 6;
		grids[6][1][2] = 8;
		grids[6][2][2] = 0;		
		// Problem 7
		grids[7][0][0] = 1;
		grids[7][1][0] = 5;
		grids[7][2][0] = 2;
		grids[7][0][1] = 0;
		grids[7][1][1] = 4;
		grids[7][2][1] = 6;
		grids[7][0][2] = 7;
		grids[7][1][2] = 3;
		grids[7][2][2] = 8;
		// Problem 8
		grids[8][0][0] = 4;
		grids[8][1][0] = 2;
		grids[8][2][0] = 1;
		grids[8][0][1] = 3;
		grids[8][1][1] = 5;
		grids[8][2][1] = 6;
		grids[8][0][2] = 7;
		grids[8][1][2] = 8;
		grids[8][2][2] = 0;
		// Problem 9
		grids[9][0][0] = 2;
		grids[9][1][0] = 3;
		grids[9][2][0] = 5;
		grids[9][0][1] = 1;
		grids[9][1][1] = 6;
		grids[9][2][1] = 8;
		grids[9][0][2] = 7;
		grids[9][1][2] = 4;
		grids[9][2][2] = 0;
		// Problem 10
		grids[10][0][0] = 1;
		grids[10][1][0] = 5;
		grids[10][2][0] = 8;
		grids[10][0][1] = 4;
		grids[10][1][1] = 7;
		grids[10][2][1] = 3;
		grids[10][0][2] = 2;
		grids[10][1][2] = 0;
		grids[10][2][2] = 6;
		// Problem 11
		grids[11][0][0] = 3;
		grids[11][1][0] = 4;
		grids[11][2][0] = 7;
		grids[11][0][1] = 1;
		grids[11][1][1] = 2;
		grids[11][2][1] = 5;
		grids[11][0][2] = 6;
		grids[11][1][2] = 0;
		grids[11][2][2] = 8;
		// Problem 12
		grids[12][0][0] = 4;
		grids[12][1][0] = 5;
		grids[12][2][0] = 3;
		grids[12][0][1] = 1;
		grids[12][1][1] = 8;
		grids[12][2][1] = 2;
		grids[12][0][2] = 0;
		grids[12][1][2] = 7;
		grids[12][2][2] = 6;
		// Problem 13
		grids[13][0][0] = 1;
		grids[13][1][0] = 5;
		grids[13][2][0] = 3;
		grids[13][0][1] = 8;
		grids[13][1][1] = 4;
		grids[13][2][1] = 6;
		grids[13][0][2] = 2;
		grids[13][1][2] = 0;
		grids[13][2][2] = 7;
		// Problem 14
		grids[14][0][0] = 7;
		grids[14][1][0] = 2;
		grids[14][2][0] = 5;
		grids[14][0][1] = 1;
		grids[14][1][1] = 6;
		grids[14][2][1] = 8;
		grids[14][0][2] = 3;
		grids[14][1][2] = 0;
		grids[14][2][2] = 4;
		// Problem 15
		grids[15][0][0] = 4;
		grids[15][1][0] = 6;
		grids[15][2][0] = 3;
		grids[15][0][1] = 1;
		grids[15][1][1] = 2;
		grids[15][2][1] = 5;
		grids[15][0][2] = 8;
		grids[15][1][2] = 0;
		grids[15][2][2] = 7;
		// Problem 16
		grids[16][0][0] = 2;
		grids[16][1][0] = 3;
		grids[16][2][0] = 4;
		grids[16][0][1] = 1;
		grids[16][1][1] = 0;
		grids[16][2][1] = 5;
		grids[16][0][2] = 7;
		grids[16][1][2] = 6;
		grids[16][2][2] = 8;
		// Problem 17
		grids[17][0][0] = 1;
		grids[17][1][0] = 4;
		grids[17][2][0] = 2;
		grids[17][0][1] = 3;
		grids[17][1][1] = 5;
		grids[17][2][1] = 8;
		grids[17][0][2] = 6;
		grids[17][1][2] = 7;
		grids[17][2][2] = 0;
		// Problem 18
		grids[18][0][0] = 2;
		grids[18][1][0] = 1;
		grids[18][2][0] = 4;
		grids[18][0][1] = 5;
		grids[18][1][1] = 6;
		grids[18][2][1] = 8;
		grids[18][0][2] = 0;
		grids[18][1][2] = 3;
		grids[18][2][2] = 7;
		// Problem 19
		grids[19][0][0] = 3;
		grids[19][1][0] = 4;
		grids[19][2][0] = 7;
		grids[19][0][1] = 2;
		grids[19][1][1] = 5;
		grids[19][2][1] = 1;
		grids[19][0][2] = 0;
		grids[19][1][2] = 6;
		grids[19][2][2] = 8;
		// Problem 20
		grids[20][0][0] = 2;
		grids[20][1][0] = 1;
		grids[20][2][0] = 3;
		grids[20][0][1] = 4;
		grids[20][1][1] = 7;
		grids[20][2][1] = 6;
		grids[20][0][2] = 5;
		grids[20][1][2] = 8;
		grids[20][2][2] = 0;		
	}
	
	/**
	 * Print the grid to the terminal for debugging
	 */
	private void printGrid() {
		int gridSize = grids[randomProblem].length;
		String row = "";
		Log.d("Logic", "Logic.printGrid()");
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				row += Integer.toString(grids[randomProblem][j][i]) + " ";
			}
			Log.d("Logic", row);
			row = "";
		}
	}
	
	/**
	 * Print the grid to the terminal for debugging but flattened
	 */
	private void printFlatGrid() {
		int gridSize = grids[randomProblem].length;
		String row = "";
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grids[randomProblem][j][i] != 0) {
					row += Integer.toString(grids[randomProblem][j][i]) + " ";
				}
			}
		}
		Log.d("Logic", "Logic.printFlatGrid() " + row);
	}
	
	/**
	 * Display the grid on screen as a series of buttons
	 */
	private void displayGrid() {
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		ArrayList<Button> buttons = getButtons();
		int gridSize = grids[randomProblem].length;
		int count = 0;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				buttons.get(count).setText(Integer.toString(grids[randomProblem][j][i]));
				buttons.get(count).setTypeface(typeFace);
				if(grids[randomProblem][j][i] != 0) {
					buttons.get(count).setVisibility(View.VISIBLE);
				} else {
					buttons.get(count).setVisibility(View.INVISIBLE);
				}
				count++;
			}
		}
	}
	
	/**
	 * Move left
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveLeft(int i, int j) {
		int tmp = grids[randomProblem][i][j];
		if(i != 0) {
			grids[randomProblem][i][j] = grids[randomProblem][i-1][j];
			grids[randomProblem][i-1][j] = tmp;
		}
	}
	
	/**
	 * Move right
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveRight(int i, int j) {
		int tmp = grids[randomProblem][i][j];
		int gridSize = grids[randomProblem].length;
		if(gridSize - 1 != i) {
			grids[randomProblem][i][j] = grids[randomProblem][i+1][j];
			grids[randomProblem][i+1][j] = tmp;
		}
	}
	
	/**
	 * Move down
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveDown(int i, int j) {
		int tmp = grids[randomProblem][i][j];
		int gridSize = grids[randomProblem].length;
		if(gridSize - 1 != j) {
			grids[randomProblem][i][j] = grids[randomProblem][i][j+1];
			grids[randomProblem][i][j+1] = tmp;
		}
	}
	
	/**
	 * Move up
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveUp(int i, int j) {
		int tmp = grids[randomProblem][i][j];
		if(j != 0) {
			grids[randomProblem][i][j] = grids[randomProblem][i][j-1];
			grids[randomProblem][i][j-1] = tmp;
		}
	}
	
	/**
	 * Get all the buttons on the form, shove them in an array and return them
	 * 
	 * @return ArrayList buttons
	 */
	private ArrayList<Button> getButtons() {
		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons.add((Button)findViewById(R.id.logicButton1));
		buttons.add((Button)findViewById(R.id.logicButton2));
		buttons.add((Button)findViewById(R.id.logicButton3));
		buttons.add((Button)findViewById(R.id.logicButton4));
		buttons.add((Button)findViewById(R.id.logicButton5));
		buttons.add((Button)findViewById(R.id.logicButton6));
		buttons.add((Button)findViewById(R.id.logicButton7));
		buttons.add((Button)findViewById(R.id.logicButton8));
		buttons.add((Button)findViewById(R.id.logicButton9));
		return buttons;
	}
	
	/**
	 * Get the x index for a given button value
	 * 
	 * @return int x index
	 */
	private int getButtonX(int value) {
		int result = -1;
		int gridSize = grids[randomProblem].length;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grids[randomProblem][i][j] == value) {
					result = i;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * Get the y index for a given button value
	 * 
	 * @return int y index
	 */
	private int getButtonY(int value) {
		int result = -1;
		int gridSize = grids[randomProblem].length;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grids[randomProblem][i][j] == value) {
					result = j;
					break;
				}
			}
		}
		return result;
	}
	
	/**
	 * Get the (ith, jth) value in the grid
	 */
	private int getValueAt(int i, int j) {
		int result = -1;
		int gridSize = grids[randomProblem].length;
		if(i >= 0 && i < gridSize) {
			if(j >= 0 && j < gridSize) {
				result = grids[randomProblem][i][j];
			}	
		}
		return result;
	}
	
	/**
	 * @return int number of inversions
	 */
	private int getNumInversions() {
		int numInversions = 0;
		// Start by flattening the grid into a list
		ArrayList<Integer> flatGrid = new ArrayList<Integer>();
		int gridSize = grids[randomProblem].length;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				// Zero doesn't count
				if(grids[randomProblem][j][i] != 0) {
					flatGrid.add(grids[randomProblem][j][i]);
				}
			}
		}
		// Now we use this grid to get the pairs
		int flatGridSize = flatGrid.size();
		for(int i = 0; i < flatGridSize; i++) {
			for(int j = i; j < flatGridSize; j++) {
				if(flatGrid.get(i) > flatGrid.get(j)) {
					numInversions++;
				}
			}
		}
		printFlatGrid();
		Log.d("Logic", "Logic.getNumInversions(): num inversions: " + Integer.toString(numInversions));
		return numInversions;
	}
	
	/**
	 * When a button is clicked, get its value, using the value get its 
	 * position in the grid, find where the button can move, move it and 
	 * display the new grid
	 */
	private void addClickHandlers() {
		// Skip button
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
	    Button skipButton = (Button)findViewById(R.id.skipButton);
	    skipButton.setTypeface(typeFace);
	    skipButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				brainTrainer.numIncorrect++;
				checkAnswer(false, "Logic");
			}
		});
		ArrayList<Button> buttons = getButtons();
		for (final Button b : buttons) {
			b.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					int xPosn;
					int yPosn;
					int clickedValue;
					clickedValue = Integer.parseInt(b.getText().toString());
					xPosn = getButtonX(clickedValue);
					yPosn = getButtonY(clickedValue);
					if(getValueAt(xPosn-1, yPosn) == 0) {
						Log.d("Logic", "Logic.addClickHandlers() Move left");
						moveLeft(xPosn, yPosn);
					}
					if(getValueAt(xPosn+1, yPosn) == 0) {
						Log.d("Logic", "Logic.addClickHandlers() Move right");
						moveRight(xPosn, yPosn);
					}
					if(getValueAt(xPosn, yPosn-1) == 0) {
						Log.d("Logic", "Logic.addClickHandlers() Move up");
						moveUp(xPosn, yPosn);
					}
					if(getValueAt(xPosn, yPosn+1) == 0) {
						Log.d("Logic", "Logic.addClickHandlers() Move down");
						moveDown(xPosn, yPosn);
					}
					printGrid();
					displayGrid();
					if(getNumInversions() == 0) {
						Log.d("Logic", "Logic.addClickHandlers() Game complete!");
						brainTrainer.numCorrect++;
						// Pause a little before moving onto next question with
						// logic task so user has a chance to see how they 
						// solved the question
						Toast.makeText(getApplicationContext(), "Solution found!", Toast.LENGTH_SHORT).show();
						Handler handler = new Handler(); 
					    handler.postDelayed(new Runnable() { 
					         public void run() { 
					        	 checkAnswer(true, "Logic");
					         } 
					    }, 800); 
					}
				}
			});
		}
	}
}
