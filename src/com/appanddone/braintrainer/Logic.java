package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * An implementation of the '8-puzzle' game for Android
 * 
 * The Integer 0 in the grid represents a 'space'
 * 
 * @author cjwfuller
 *
 */
public class Logic extends MainActivity {
	
	public final static int numProblems = 1;
	private final static int maxNumInversions = 3;
	public static int randomProblem;
	private int grid[][] = new int[3][3];;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic);
		// Build a solvable grid
		while(!isSolvable() || isTooDifficult()) {
			buildGrid();
		}
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
	 * Build a n*n grid of random integers
	 */
	private void buildGrid() {
		ArrayList<Integer> usedRands = new ArrayList<Integer>();
		int gridSize = grid.length;
		int numGridItems = gridSize * gridSize;
		int rand;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				rand = new Random().nextInt(numGridItems);
				while(usedRands.contains(rand)) {
					rand = new Random().nextInt(numGridItems);
				}
				usedRands.add(rand);
				grid[i][j] = rand;
			}	
		}
	}
	
	/**
	 * Print the grid to the terminal for debugging
	 */
	private void printGrid() {
		int gridSize = grid.length;
		String row = "";
		Log.d("Logic", "Logic.printGrid()");
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				row += Integer.toString(grid[j][i]) + " ";
			}
			Log.d("Logic", row);
			row = "";
		}
	}
	
	/**
	 * Print the grid to the terminal for debugging but flattened
	 */
	private void printFlatGrid() {
		int gridSize = grid.length;
		String row = "";
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grid[j][i] != 0) {
					row += Integer.toString(grid[j][i]) + " ";
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
		int gridSize = grid.length;
		int count = 0;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				buttons.get(count).setText(Integer.toString(grid[j][i]));
				buttons.get(count).setTypeface(typeFace);
				if(grid[j][i] != 0) {
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
		int tmp = grid[i][j];
		if(i != 0) {
			grid[i][j] = grid[i-1][j];
			grid[i-1][j] = tmp;
		}
	}
	
	/**
	 * Move right
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveRight(int i, int j) {
		int tmp = grid[i][j];
		int gridSize = grid.length;
		if(gridSize - 1 != i) {
			grid[i][j] = grid[i+1][j];
			grid[i+1][j] = tmp;
		}
	}
	
	/**
	 * Move down
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveDown(int i, int j) {
		int tmp = grid[i][j];
		int gridSize = grid.length;
		if(gridSize - 1 != j) {
			grid[i][j] = grid[i][j+1];
			grid[i][j+1] = tmp;
		}
	}
	
	/**
	 * Move up
	 * 
	 * @param i horizontal
	 * @param j vertical
	 */
	private void moveUp(int i, int j) {
		int tmp = grid[i][j];
		if(j != 0) {
			grid[i][j] = grid[i][j-1];
			grid[i][j-1] = tmp;
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
		int gridSize = grid.length;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grid[i][j] == value) {
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
		int gridSize = grid.length;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grid[i][j] == value) {
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
		int gridSize = grid.length;
		if(i >= 0 && i < gridSize) {
			if(j >= 0 && j < gridSize) {
				result = grid[i][j];
			}	
		}
		return result;
	}
	
	/**
	 * 
	 * @todo
	 * 
	 * @return int number of inversions
	 */
	private int getNumInversions() {
		int numInversions = 0;
		// Start by flattening the grid into a list
		ArrayList<Integer> flatGrid = new ArrayList<Integer>();
		int gridSize = grid.length;
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				// Zero doesn't count
				if(grid[j][i] != 0) {
					flatGrid.add(grid[j][i]);
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
	 * Not all grids are solvable, here we determine whether a grid is solvable
	 * If grid size is odd then the number of inversions needs to be even.  Our
	 * grid size is always even (8 numbers) so for the grid to be solvable, the
	 * number of inversions should be odd
	 * 
	 * @return boolean true if solvable, false if not
	 */
	private boolean isSolvable() {
		boolean result = false;
		// Even number check
		if(getNumInversions() % 2 != 0) {
			result = true;
		}
		return result;
	}
	
	private boolean isTooDifficult() {
		boolean result = false;
		int numInversions = getNumInversions();
		if(getNumInversions() > maxNumInversions) {
			Log.d("Logic", "Logic.isTooDifficult() num inversions: " + Integer.toString(numInversions) + " (too difficult)");
			result = true;
		}
		return result;		
	}
	
	/**
	 * When a button is clicked, get its value, using the value get its 
	 * position in the grid, find where the button can move, move it and 
	 * display the new grid
	 */
	private void addClickHandlers() {
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
					/*if(getNumInversions() == 0) {
						Log.d("Logic", "Logic.addClickHandlers() Game complete!");
					}*/
				}
			});
		}
	}
}
