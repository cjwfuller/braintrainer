package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Memory extends MainActivity {

	public final static int numProblems = -1;
	public static int randomProblem = -1;
	private int fewestToRemember = 5;
	private int mostToRemember = 7;
	private int numToRemember;
	private final int numCircles = 16;
	private static Toast toast;
	private int numGreensFound = 0;
	
	private TextView textView;
	
	private ArrayList<ImageView> images = new ArrayList<ImageView>();
	private ArrayList<Integer> greens = new ArrayList<Integer>();
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_memory);
	    textView = (TextView)findViewById(R.id.memory_instructions);
	    // Nice game font
	    Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
	    TextView textView = (TextView)findViewById(R.id.memory_instructions);
	    textView.setTypeface(typeFace);
	    // Users have to remember varying numbers of things
	    numToRemember = new Random().nextInt((mostToRemember - fewestToRemember) + 1) + fewestToRemember;
	    // Users have 3 seconds to remember
	    toast = Toast.makeText(getBaseContext(), "Time remaining: 2s", Toast.LENGTH_LONG);
		toast.show();
		turnGreensOn();	
	    startTimer();
	}
	
	/**
	 * Show a timer every 1s for 2s and turn all the shapes red after 2s
	 */
	private void startTimer() {
		 new CountDownTimer(2000, 1000) {
		     public void onTick(long millisUntilFinished) {
		    	 toast.cancel();
	    		 toast = Toast.makeText(getBaseContext(), "Time remaining: " + millisUntilFinished / 1000 + "s", Toast.LENGTH_LONG);
	    		 toast.show();
		     }

		     public void onFinish() {
		    	 for(ImageView image : images) {
		    		 toast.cancel();
		    		 image.setImageDrawable(getResources().getDrawable(R.drawable.red_oval));
		    		 textView.setText(R.string.memory_instructions_after_green_text);
		    		 addClickHandlers();
		    	 }
		     }
		  }.start();
	}
	
	/**
	 * Each button needs a click handler.  If the user clicks a red button then
	 * they have failed the game.  The number of green buttons needs to be 
	 * recorded so when they have all been found, the user wins
	 */
	private void addClickHandlers() {
		for(int i = 0; i < images.size(); i++) {
			if(greens.contains(i)) {
				final ImageView currentImageView = images.get(i);
				images.get(i).setOnClickListener(new OnClickListener() {
					@Override
					// When user clicks green
					public void onClick(View v) {
						numGreensFound++;
						if(numGreensFound == numToRemember) {
							brainTrainer.numCorrect++;
							try {
								startRandomQuestion();
							} catch (IllegalAccessException e) {
								e.printStackTrace();
							} catch (IllegalArgumentException e) {
								e.printStackTrace();
							} catch (NoSuchFieldException e) {
								e.printStackTrace();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						}
						currentImageView.setImageDrawable(getResources().getDrawable(R.drawable.green_oval));
					}
				});
			} else {
				images.get(i).setOnClickListener(new OnClickListener() {
					@Override
					// When user clicks red
					public void onClick(View v) {
						brainTrainer.numIncorrect++;
						try {
							startRandomQuestion();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (NoSuchFieldException e) {
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}
					}
				});
			}
		}
	}
	
	/**
	 * Turn a random number of the red shapes to green
	 */
	private void turnGreensOn() {
	    ImageView memoryImageView1 = (ImageView)findViewById(R.id.memoryImageView1);
	    ImageView memoryImageView2 = (ImageView)findViewById(R.id.memoryImageView2);
	    ImageView memoryImageView3 = (ImageView)findViewById(R.id.memoryImageView3);
	    ImageView memoryImageView4 = (ImageView)findViewById(R.id.memoryImageView4);
	    ImageView memoryImageView5 = (ImageView)findViewById(R.id.memoryImageView5);
	    ImageView memoryImageView6 = (ImageView)findViewById(R.id.memoryImageView6);
	    ImageView memoryImageView7 = (ImageView)findViewById(R.id.memoryImageView7);
	    ImageView memoryImageView8 = (ImageView)findViewById(R.id.memoryImageView8);
	    ImageView memoryImageView9 = (ImageView)findViewById(R.id.memoryImageView9);
	    ImageView memoryImageView10 = (ImageView)findViewById(R.id.memoryImageView10);
	    ImageView memoryImageView11 = (ImageView)findViewById(R.id.memoryImageView11);
	    ImageView memoryImageView12 = (ImageView)findViewById(R.id.memoryImageView12);
	    ImageView memoryImageView13 = (ImageView)findViewById(R.id.memoryImageView13);
	    ImageView memoryImageView14 = (ImageView)findViewById(R.id.memoryImageView14);
	    ImageView memoryImageView15 = (ImageView)findViewById(R.id.memoryImageView15);
	    ImageView memoryImageView16 = (ImageView)findViewById(R.id.memoryImageView16);
	    
	    images.add(memoryImageView1);
	    images.add(memoryImageView2);
	    images.add(memoryImageView3);
	    images.add(memoryImageView4);
	    images.add(memoryImageView5);
	    images.add(memoryImageView6);
	    images.add(memoryImageView7);
	    images.add(memoryImageView8);
	    images.add(memoryImageView9);
	    images.add(memoryImageView10);
	    images.add(memoryImageView11);
	    images.add(memoryImageView12);
	    images.add(memoryImageView13);
	    images.add(memoryImageView14);
	    images.add(memoryImageView15);
	    images.add(memoryImageView16);
	    
	    // Randomly turn a random number of greens on.  Ensures that the same
	    // shape doesn't turn green more than once too
	    boolean generatedGreens = false;
	    int numGreens = 0;
	    int rand;
	    while(!generatedGreens) {
	    	rand = new Random().nextInt(numCircles);
	    	if(!greens.contains(rand)) {
	    		images.get(rand).setImageDrawable(getResources().getDrawable(R.drawable.green_oval));
	    		greens.add(rand);
	    		numGreens++;
	    	}
	    	if(numGreens == numToRemember) {
	    		generatedGreens = true;
	    	}
	    }
	}

}
