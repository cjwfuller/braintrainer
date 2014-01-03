package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.os.StrictMode;
import android.util.Log;

public class BrainTrainer extends Application {
	
	public int numCorrect = 0;
	public int numIncorrect = 0;
	public HashMap<String, ArrayList<Integer>> questionsAsked;
	public HashMap<String, Integer> numCorrectBreakdown;
	
	@Override
	public void onCreate() {
		if(BuildConfig.DEBUG) {
			Log.d("BrainTrainer", "BrainTrainer.onCreate() Enabling debug mode");
			StrictMode.enableDefaults();
	    }
		super.onCreate();
		reset();
	}
	
	public void reset() {
		// Correct/incorrect totals
		numCorrect = 0;
		numIncorrect = 0;
		// Keep track of questions asked so we don't repeat questions
		questionsAsked = new HashMap<String, ArrayList<Integer>>();
		questionsAsked.put(Classification.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Mathematics.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Visual.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Logic.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Spatial.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Verbal.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Visual.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(PatternRecognition.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Memory.class.getSimpleName(), new ArrayList<Integer>());
		// Keep track of scores for each question type
		numCorrectBreakdown = new HashMap<String, Integer>();
		numCorrectBreakdown.put(Classification.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Mathematics.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Visual.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Logic.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Spatial.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Verbal.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Visual.class.getSimpleName(), 0);
		numCorrectBreakdown.put(PatternRecognition.class.getSimpleName(), 0);
		numCorrectBreakdown.put(Memory.class.getSimpleName(), 0);
		Log.d("MainActivity", "BrainTrainer.reset()");
	}
	
	protected void recordQuestion(String activityName, Integer questionNum) {
		questionsAsked.get(activityName).add(questionNum);
	}
}
