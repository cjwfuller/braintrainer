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
 * The Integer 0 in the grid represents a 'space'
 * 
 * @author cjwfuller
 *
 */
public class Logic extends MainActivity {
	
	public final static int numProblems = 1;
	public static int randomProblem;
	private int grid[][] = new int[3][3];;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic);
		buildGrid();
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
	 * Print the grid to the terminal for debuggin
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
	
	private void moveLeft(int i, int j) {
		int tmp = grid[i][j];
		if(i != 0) {
			grid[i][j] = grid[i-1][j];
			grid[i-1][j] = tmp;
		}
	}
	
	private void moveRight(int i, int j) {
		int tmp = grid[i][j];
		int gridSize = grid.length;
		if(gridSize - 1 != i) {
			grid[i][j] = grid[i+1][j];
			grid[i+1][j] = tmp;
		}
	}
	
	private void moveDown(int i, int j) {
		int tmp = grid[i][j];
		int gridSize = grid.length;
		if(gridSize - 1 != j) {
			grid[i][j] = grid[i][j+1];
			grid[i][j+1] = tmp;
		}
	}
	
	private void moveUp(int i, int j) {
		int tmp = grid[i][j];
		if(j != 0) {
			grid[i][j] = grid[i][j-1];
			grid[i][j-1] = tmp;
		}
	}
	
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
	
	private boolean gameComplete() {
		boolean result = true;
		int gridSize = grid.length;
		int count = 1;
		// Start from top left
		for(int i = 0; i < gridSize; i++) {
			for(int j = 0; j < gridSize; j++) {
				if(grid[j][i] != count) {
					result = false;
					break;
				}
				count++;
			}
		}
		// Start from x=1, y=0, only bother if the game has already found to be
		// incomplete
		count = 1;
		/*if(!result) {
			for(int i = 0; i < gridSize; i++) {
				for(int j = 1; j < gridSize; j++) {
					if(grid[j][i] != count) {
						result = false;
						break;
					}
					count++;
				}
			}
		}*/
		return result;
	}
	
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
				}
			});
		}
	}
}
