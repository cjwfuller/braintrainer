package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;
import android.content.Intent;
import android.util.Log;

public class BrainTrainer extends Application {
	
	public int numCorrect = 0;
	public int numIncorrect = 0;
	public int totalNumQuestionsAsked = 0;
	public final int numTurns = 10;
	public HashMap<String, ArrayList<Integer>> questionsAsked;
	
	@Override
	public void onCreate() {
		super.onCreate();
		reset();
	}
	
	public void reset() {
		numCorrect = 0;
		numIncorrect = 0;
		totalNumQuestionsAsked = 0;
		questionsAsked = new HashMap<String, ArrayList<Integer>>();
		questionsAsked.put(Classification.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Mathematics.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Visual.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Logic.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Spatial.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Verbal.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(Visual.class.getSimpleName(), new ArrayList<Integer>());
		questionsAsked.put(PatternRecognition.class.getSimpleName(), new ArrayList<Integer>());
		Log.d("MainActivity", "BrainTrainer.reset()");
	}
	
	protected void recordQuestion(String activityName, Integer questionNum) {
		questionsAsked.get(activityName).add(questionNum);
		totalNumQuestionsAsked++;
		Log.d("MainActivity", "BrainTrainer.recordQuestion() totalNumQuestionsAsked: " + totalNumQuestionsAsked);
	}
	
	protected void recordQuestion(String activityName) {
		totalNumQuestionsAsked++;
		Log.d("MainActivity", "BrainTrainer.recordQuestion() totalNumQuestionsAsked: " + totalNumQuestionsAsked);
	}
}
