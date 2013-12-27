package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


public class Logic extends MainActivity {
	
	public final static int numProblems = 1;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_logic);
	    // Nice game font
	    Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
	    TextView textView = (TextView)findViewById(R.id.logic_instructions);
	    textView.setTypeface(typeFace);
	    assignNumbers();
	    addClickHandlers();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
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
		return buttons;
	}
	
	/**
	 * Assign numbers to the buttons
	 */
	private void assignNumbers() {
		// Get an array of buttons
		ArrayList<Button> buttons = getButtons();
		// Randomly assign numbers to the buttons
		ArrayList<Integer> assignedNumbers = new ArrayList<Integer>();
		boolean generatedAll = false;
	    int numAssigned = 0;
	    final int numToAssign = buttons.size();
	    int rand;
	    while(!generatedAll) {
	    	rand = new Random().nextInt(numToAssign);
	    	Log.d("Logic", "Logic.assignNumbers() rand: " + rand);
	    	if(!assignedNumbers.contains(rand)) {
	    		assignedNumbers.add(rand);
	    		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
	    		buttons.get(numAssigned).setTypeface(typeFace);
	    		buttons.get(numAssigned).setText(Integer.toString(rand + 1));
	    		numAssigned++;
	    	}
	    	if(numAssigned == numToAssign) {
	    		generatedAll = true;
	    	}
	    }
	}
	
	private void addClickHandlers() {
		ArrayList<Button> buttons = new ArrayList<Button>();
		buttons = getButtons();
		for (Button b : buttons) {
			// Space to left?
			
			// Space to right?
			
			// Space under?
			
			// Space above?
		}
	}
}
