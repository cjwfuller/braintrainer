package com.appanddone.braintrainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BrainTrainer extends Application {
	
	public int score = 0;
	public int totalNumQuestionsAsked = 0;
	public final int numTurns = 5;
	public HashMap<String, ArrayList<Integer>> questionsAsked;
	
	@Override
	public void onCreate() {
		super.onCreate();
		// Keep track of questions asked for each question type
		questionsAsked = new HashMap<String, ArrayList<Integer>>();
		questionsAsked.put("mathematics", new ArrayList<Integer>());
		questionsAsked.put("classification", new ArrayList<Integer>());
		//clearScore();
		//setupNonDuplicatingQuestions();
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
