package com.appanddone.braintrainer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	public BrainTrainer brainTrainer;
	public static String PACKAGE_NAME;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PreferenceManager.setDefaultValues(this, R.xml.pref_questions, false);
		brainTrainer = (BrainTrainer)getApplicationContext();
		PACKAGE_NAME = getApplicationContext().getPackageName();
		
		Button startButton = (Button)findViewById(R.id.start_button);
		startButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
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
	
	@Override
	public void onBackPressed() {
		brainTrainer.reset();
		Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
	    return;
	}
	
	private boolean areQuestionsOfTypeRemaining(String activityStr) throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException, ClassNotFoundException {
		boolean result = true;
		Class<?> clazz = Class.forName(getApplicationContext().getPackageName() + "." + activityStr);
		Field myField = clazz.getDeclaredField("numProblems");
		if(brainTrainer.questionsAsked.get(activityStr).size() == myField.getInt(null)) {
			result =  false;
		}
		Log.d("MainActivity", "MainActivity.areQuestionsOfTypeRemaining(" + activityStr + ") " + Boolean.toString(result));
		return result;
	}
	
	private boolean askedAlready(String activityStr, Integer questionNum) {
		boolean result = false;
		if(brainTrainer.questionsAsked.get(activityStr).contains(questionNum)) {
			result = true;
		}
		Log.d("MainActivity", "MainActivity.askedAlready(" + activityStr + ", " + questionNum + ") " + Boolean.toString(result));
		return result;
	}
	
	private ArrayList<String> getEnabledAndAvailableQuestions(SharedPreferences settings) throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException, ClassNotFoundException {
		Map<String, ?> keys = new HashMap<String, List<String>>();
		keys = settings.getAll();
		ArrayList<String> enabledAvailableQuestionTypes = new ArrayList<String>();
		for(Map.Entry<String, ?> entry : keys.entrySet()) {
			if(entry.getKey().toString().endsWith("_question_type")) {
				if(settings.getBoolean(entry.getKey().toString(), true)) {
					if(areQuestionsOfTypeRemaining(entry.getKey().toString().replace("_question_type", ""))) {
						String enabledQuestionType = entry.getKey().toString().replace("_question_type", "");
						enabledAvailableQuestionTypes.add(enabledQuestionType);
						Log.d("MainActivity", "MainActivity.getEnabledAndAvailableQuestions() enabled: " + enabledQuestionType);
					}
				}
			}
		}
		return enabledAvailableQuestionTypes;
	}
	
	public boolean startRandomQuestion() throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException, ClassNotFoundException {
		if(brainTrainer.totalNumQuestionsAsked > brainTrainer.numTurns) {
			Intent intent = new Intent(MainActivity.this, Finish.class);
			MainActivity.this.startActivity(intent);
			finish();
			return true;
		}
		// Get enabled question types
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		ArrayList<String> enabledAvailableQuestionTypes = getEnabledAndAvailableQuestions(settings);
		
		Intent intent;
		// No question types selected.  Tell the user and redirect them to the
		// settings page
		if(enabledAvailableQuestionTypes.size() == 0) {
			intent = new Intent(MainActivity.this, Finish.class);
			MainActivity.this.startActivity(intent);
			Toast.makeText(getApplicationContext(), "No questions remaining!", Toast.LENGTH_LONG).show();
			finish();
			return true;
		}
		
		// Get a random enabled question type
		int randomQuestionType = new Random().nextInt(enabledAvailableQuestionTypes.size());
		Log.d("MainActivity", "MainActivity.startRandomQuestion() type: " + randomQuestionType);
		Log.d("MainActivity", "MainActivity.startRandomQuestion() type name: " + enabledAvailableQuestionTypes.get(randomQuestionType));
		int randomQuestionNum;
		boolean foundQuestion = false;
		while(!foundQuestion) {
			Class<?> clazz = Class.forName(getApplicationContext().getPackageName() + "." + enabledAvailableQuestionTypes.get(randomQuestionType));
			Field myField = clazz.getDeclaredField("numProblems");
			randomQuestionNum = new Random().nextInt(myField.getInt(null));
			if(!askedAlready(enabledAvailableQuestionTypes.get(randomQuestionType), randomQuestionNum)) {
				foundQuestion = true;
				brainTrainer.recordQuestion(enabledAvailableQuestionTypes.get(randomQuestionType), randomQuestionNum);
				Log.d("MainActivity", "MainActivity.startRandomQuestion() found unasked: " + randomQuestionNum);
			}
		}
		// Start corresponding activity
		
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
		return true;
	}

}
