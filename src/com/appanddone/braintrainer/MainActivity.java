package com.appanddone.braintrainer;

import com.appanddone.braintrainer.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button startButton = (Button)findViewById(R.id.start_button);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startRandomQuestion();
			}
		});
		
		Button settingsButton = (Button)findViewById(R.id.settings_button);
		settingsButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_settings = new Intent(MainActivity.this, Settings.class);
				MainActivity.this.startActivity(intent_settings);
			}
		});
		
		Button aboutButton = (Button)findViewById(R.id.about_button);
		aboutButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent_about = new Intent(MainActivity.this, About.class);
				MainActivity.this.startActivity(intent_about);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_main:
			Intent intent_main = new Intent(this, MainActivity.class);
			this.startActivity(intent_main);
			break;
		case R.id.menu_settings:
			Intent intent_settings = new Intent(this, Settings.class);
			this.startActivity(intent_settings);
			break;	
		case R.id.menu_about:
			Intent intent_about = new Intent(this, About.class);
			this.startActivity(intent_about);
			break;				
		default:
			return super.onOptionsItemSelected(item);
		}
		return true;
	}
	
	/**
	 * TODO actually make the question random
	 */
	public void startRandomQuestion() {
		// Get enabled question types
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		ArrayList<String> enabledQuestionTypes = new ArrayList<String>();
		Map<String, ?> keys = new HashMap<String, List<String>>();
		keys = settings.getAll();
		for(Map.Entry<String, ?> entry : keys.entrySet()) {
			if(entry.getKey().toString().endsWith("question_type")) {
				if(settings.getBoolean(entry.getKey().toString(), true)) {
					enabledQuestionTypes.add(entry.getKey().toString());
				}
			}
		}
		// Get a random enabled question type
		int idx = new Random().nextInt(enabledQuestionTypes.size());
		String randomQuestionType = enabledQuestionTypes.get(idx);
		// Start corresponding activity
		Intent intent;
		// TODO enable block in production
		intent = new Intent(MainActivity.this, Classification.class);
		/*if(randomQuestionType.equals("pref_key_memory_question_type")) {
			intent = new Intent(MainActivity.this, Memory.class);
		} else if(randomQuestionType.equals("pref_key_mathematics_question_type")) {
			intent = new Intent(MainActivity.this, Mathematics.class);
		} else if(randomQuestionType.equals("pref_key_logic_question_type")) {
			intent = new Intent(MainActivity.this, Logic.class);
		} else if(randomQuestionType.equals("pref_key_spatial_question_type")) {
			intent = new Intent(MainActivity.this, Spatial.class);
		} else if(randomQuestionType.equals("pref_key_classification_question_type")) {
			intent = new Intent(MainActivity.this, Classification.class);
		} else if(randomQuestionType.equals("pref_key_pattern_recognition_question_type")) {
			intent = new Intent(MainActivity.this, PatternRecognition.class);
		} else if(randomQuestionType.equals("pref_key_verbal_question_type")) {
			intent = new Intent(MainActivity.this, Verbal.class);
		} else if(randomQuestionType.equals("pref_key_visual_question_type")) {
			intent = new Intent(MainActivity.this, Visual.class);
		} else {
			// Question type unknown, use mathematics
			intent = new Intent(MainActivity.this, Mathematics.class);
		}*/
		MainActivity.this.startActivity(intent);
	}
	
	/**
	 * 
	 */
	protected void incrementScore() {
		SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
		int oldScore = prefs.getInt("scoreKey", 0);  
	    Editor edit = prefs.edit();
	    edit.putInt("scoreKey", oldScore + 1);
	    edit.commit();
	}
	
	/**
	 * 
	 */
	protected int getScore() {
		SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
		return prefs.getInt("scoreKey", 0);
	}

}
