package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Memory extends MainActivity {

	public final static int numProblems = 1; // TODO set to -1 or something
	public static int randomProblem; // TODO not needed
	private int fewestToRemember = 5;
	private int mostToRemember = 7;
	private int numToRemember;
	private final int numCircles = 16;
	private static Toast toast;
	
	ArrayList<ImageView> images = new ArrayList<ImageView>();
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_memory);
	    Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
	    TextView textView = (TextView)findViewById(R.id.memory_instructions);
	    textView.setTypeface(typeFace);
	    numToRemember = new Random().nextInt((mostToRemember - fewestToRemember) + 1) + fewestToRemember;
	    toast = Toast.makeText(getBaseContext(), "Time remaining: 3s", Toast.LENGTH_LONG);
		toast.show();
	    turnLightsOn();	
	    startTimer();
	}
	
	private void startTimer() {
		 new CountDownTimer(3000, 1000) {
		     public void onTick(long millisUntilFinished) {
		    	 toast.cancel();
	    		 toast = Toast.makeText(getBaseContext(), "Time remaining: " + millisUntilFinished / 1000 + "s", Toast.LENGTH_LONG);
	    		 toast.show();
		     }

		     public void onFinish() {
		    	 for(ImageView image : images) {
		    		 toast.cancel();
		    		 image.setImageDrawable(getResources().getDrawable(R.drawable.red_oval));
		    	 }
		     }
		  }.start();
	}
	
	private void turnLightsOn() {
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
	    
	    boolean generatedGreens = false;
	    int numGreens = 0;
	    int rand;
	    ArrayList<Integer> greensSoFar = new ArrayList<Integer>();
	    while(!generatedGreens) {
	    	rand = new Random().nextInt(numCircles);
	    	if(!greensSoFar.contains(rand)) {
	    		images.get(rand).setImageDrawable(getResources().getDrawable(R.drawable.green_oval));
	    		greensSoFar.add(rand);
	    		numGreens++;
	    	}
	    	if(numGreens == numToRemember) {
	    		generatedGreens = true;
	    	}
	    }
	}

}
