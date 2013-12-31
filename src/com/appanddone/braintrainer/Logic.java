package com.appanddone.braintrainer;

import java.util.ArrayList;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
 * An implementation of the '8-puzzle' game for Android
 * 
 * The Integer 0 in the grid represents the empty tile
 * 
 * @author cjwfuller
 *
 */
public class Logic extends MainActivity {
	
	public final static int numProblems = 2;
	public static int randomProblem;
	private int grids[][][] = new int[numProblems][3][3];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic);
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
		// Problem 1
		grids[0][0][0] = 0;
		grids[0][1][0] = 1;
		grids[0][2][0] = 3;
		grids[0][0][1] = 4;
		grids[0][1][1] = 2;
		grids[0][2][1] = 5;
		grids[0][0][2] = 7;
		grids[0][1][2] = 8;
		grids[0][2][2] = 6;
		// Problem 2
		grids[1][0][0] = 1;
		grids[1][1][0] = 0;
		grids[1][2][0] = 3;
		grids[1][0][1] = 6;
		grids[1][1][1] = 2;
		grids[1][2][1] = 5;
		grids[1][0][2] = 8;
		grids[1][1][2] = 7;
		grids[1][2][2] = 4;
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
				checkAnswer(false);
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
						checkAnswer(true);
					}
				}
			});
		}
	}
}
