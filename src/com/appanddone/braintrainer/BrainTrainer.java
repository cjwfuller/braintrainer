package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Application;

public class BrainTrainer extends Application {
	
	public int score = 0;
	public int totalNumQuestionsAsked = 0;
	public final int numTurns = 5;
	public HashMap<String, ArrayList<Integer>> questionsAsked;
	
	@Override
	public void onCreate() {
		super.onCreate();
		//clearScore();
		//setupNonDuplicatingQuestions();
		reset();
	}
	
	public void reset() {
		score = 0;
		totalNumQuestionsAsked = 0;
		questionsAsked = new HashMap<String, ArrayList<Integer>>();
		questionsAsked.put("mathematics", new ArrayList<Integer>());
		questionsAsked.put("classification", new ArrayList<Integer>());
	}
	
	/*private void clearScore() {
		SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
		Editor edit = prefs.edit();
	    edit.remove("scoreKey");
	    edit.commit();
	}*/
	
	/**
	 * Set up data structures that ensure questions are not asked more than once
	 */
	/*private void setupNonDuplicatingQuestions() {
		// Save an empty HashSet for recording questions asked
		SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
		Editor edit = prefs.edit();
		Set<String> previousQuestions = new HashSet<String>();
		edit.putStringSet("previous_questions_key", previousQuestions);
		edit.commit();
	}*/
}
