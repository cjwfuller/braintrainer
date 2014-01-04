package com.appanddone.braintrainer;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ShareActionProvider;
import android.widget.TextView;

/**
 * TODO how many things can you do with a brick (for example)
 * 
 * @author cjwfuller
 *
 */
public class MainActivity extends Activity {
	
	private ShareActionProvider mShareActionProvider;
	public BrainTrainer brainTrainer;
	public static String PACKAGE_NAME;
	protected final int numLives = 3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		PreferenceManager.setDefaultValues(this, R.xml.pref_questions, false);
		brainTrainer = (BrainTrainer)getApplicationContext();
		PACKAGE_NAME = getApplicationContext().getPackageName();
		
		Button startButton = (Button)findViewById(R.id.start_button);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		startButton.setTypeface(typeFace);
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
		settingsButton.setTypeface(typeFace);
		settingsButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent_settings = new Intent(MainActivity.this, Settings.class);
				MainActivity.this.startActivity(intent_settings);
			}
		});
		
		Button aboutButton = (Button)findViewById(R.id.about_button);
		aboutButton.setTypeface(typeFace);
		aboutButton.setOnClickListener(new View.OnClickListener() {	
			@Override
			public void onClick(View v) {
				Intent intent_about = new Intent(MainActivity.this, About.class);
				MainActivity.this.startActivity(intent_about);
			}
		});
		
		// Show the highscore
		TextView highscoreTextView = (TextView)findViewById(R.id.highscore_text);
		highscoreTextView.setTypeface(typeFace);
		SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
		int score = prefs.getInt("highscore", 0);
		highscoreTextView.setText(getFormattedScore(score));
	}
	
	/**
	 * Given an integer score, get a 3-char score with arrows either side
	 * If score is fewer than 2 or 3 digits then pad with zeroes 
	 * 
	 * @param int score to format
	 * @return String formatted score
	 */
	private String getFormattedScore(int score) {
		String str = Integer.toString(score);
		int strLen = str.length();
		if(strLen == 1) {
			str = "00" + str;
		} else if(strLen == 2) {
			str = "0" + str;
		}
		str = "\u25B6\u25B6\u25B6 Highscore \u25B6\u25B6\u25B6 " + str;
		return str;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		getMenuInflater().inflate(R.menu.share_menu, menu);
	    MenuItem item = menu.findItem(R.id.menu_item_share);
	    mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	    setShareIntent(createShareIntent());
		return true;
	}

	private void setShareIntent(Intent shareIntent) {
	    if (mShareActionProvider != null) {
	        mShareActionProvider.setShareIntent(shareIntent);
	    }
	}

	// TODO use actual link!!!
	private Intent createShareIntent() {  
        Intent shareIntent = new Intent(Intent.ACTION_SEND);  
        shareIntent.setType("text/plain");  
        shareIntent.putExtra(Intent.EXTRA_TEXT, "I'm playing Brain Trainer!  Download for Android here: http://");  
        return shareIntent;  
   }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.menu_main:
			brainTrainer.reset();
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
		Intent intent = new Intent(MainActivity.this, MainActivity.class);
	    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);   
	    startActivity(intent);
	}
		
	/**
	 * Show lives - users have 3 lives
	 * Red hearts are available lives
	 * Black hearts are used lives
	 */
	protected void showLives() {
		ImageView heart1 = (ImageView)findViewById(R.id.heart1);
		ImageView heart2 = (ImageView)findViewById(R.id.heart2);
		if(brainTrainer.numIncorrect == 1) {
			heart1.setImageResource(R.drawable.black_heart);
		} else if(brainTrainer.numIncorrect == 2) {
			heart1.setImageResource(R.drawable.black_heart);
			heart2.setImageResource(R.drawable.black_heart);
		}
	}
	
	/**
	 * Determine whether a class exists with the name of activityStr
	 * 
	 * @param activityStr
	 * @return boolean true if questions remaining, false otherwise
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 */
	private boolean areQuestionsOfTypeRemaining(String activityStr) throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException, ClassNotFoundException {
		boolean result = true;
		Log.d("MainActivity", "MainActivity.areQuestionsOfTypeRemaining(" + activityStr + ") ");
		if(activityStr.equals("Memory")) {
			return true;
		}
		Class<?> clazz = Class.forName(getApplicationContext().getPackageName() + "." + activityStr);
		Field myField = clazz.getDeclaredField("numProblems");
		if(brainTrainer.questionsAsked.get(activityStr).size() == myField.getInt(null)) {
			result = false;
		}
		Log.d("MainActivity", "MainActivity.areQuestionsOfTypeRemaining(" + activityStr + ") " + Boolean.toString(result));
		return result;
	}
	
	/**
	 * Determine whether a question has been asked before
	 * 
	 * @param activityStr
	 * @param questionNum
	 * @return true if question asked before, false otherwise
	 */
	private boolean askedAlready(String activityStr, Integer questionNum) {
		boolean result = false;
		if(brainTrainer.questionsAsked.get(activityStr).contains(questionNum)) {
			result = true;
		}
		Log.d("MainActivity", "MainActivity.askedAlready(" + activityStr + ", " + questionNum + ") " + Boolean.toString(result));
		return result;
	}
	
	/**
	 * Get questions that belong to question types that are enabled and have 
	 * never been asked before
	 * 
	 * @param settings
	 * @return ArrayList<String> questions that are enabled and not asked
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 */
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
	
	/**
	 * Ends game if all questions have been asked
	 * 
	 * @param enabledAvailableQuestionTypes
	 */
	private void finishIfNoQuestions(ArrayList<String> enabledAvailableQuestionTypes) {
		// No question types selected.  Tell the user and redirect them to the
		// settings page
		if(enabledAvailableQuestionTypes.size() == 0) {
			Intent intent = new Intent(MainActivity.this, Finish.class);
			MainActivity.this.startActivity(intent);
			finish();
		}
	}
	
	/**
	 * Show activity that tells the user if they were correct or not
	 * 
	 * @param isCorrect
	 * @return 
	 */
	public boolean checkAnswer(boolean isCorrect, String className) {
		Intent intent = new Intent(this, CheckAnswer.class);
		intent.putExtra("isCorrect", isCorrect);
		intent.putExtra("className", className);
		MainActivity.this.startActivity(intent);
		return true;
	}
	
	/**
	 * Start a random question
	 * Ensured that the question is from a question type that is enabled and 
	 * makes sure that the question has not been asked before
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws NoSuchFieldException
	 * @throws ClassNotFoundException
	 */
	public boolean startRandomQuestion() throws IllegalAccessException, IllegalArgumentException, NoSuchFieldException, ClassNotFoundException {
		SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(this);
		ArrayList<String> enabledAvailableQuestionTypes = getEnabledAndAvailableQuestions(settings);
		finishIfNoQuestions(enabledAvailableQuestionTypes);
		// Get a random enabled question type
		int randomQuestionType = new Random().nextInt(enabledAvailableQuestionTypes.size());
		int randomQuestionNum = 0;
		boolean foundQuestion = false;
		Class<?> clazz = null;
		Log.d("MainActivity", "MainActivity.startRandomQuestion() type: " + randomQuestionType);
		Log.d("MainActivity", "MainActivity.startRandomQuestion() type name: " + enabledAvailableQuestionTypes.get(randomQuestionType));
		while(!foundQuestion) {
			clazz = Class.forName(getApplicationContext().getPackageName() + "." + enabledAvailableQuestionTypes.get(randomQuestionType));
			if(enabledAvailableQuestionTypes.get(randomQuestionType).equals("Memory")) {
				// We don't need to keep track of memory questions because 
				// they're random but store something so we know how many 
				// memory questions were asked
				brainTrainer.recordQuestion(enabledAvailableQuestionTypes.get(randomQuestionType), -1);
				break;
			}
			Field myField = clazz.getDeclaredField("numProblems");
			randomQuestionNum = new Random().nextInt(myField.getInt(null));
			if(!askedAlready(enabledAvailableQuestionTypes.get(randomQuestionType), randomQuestionNum)) {
				foundQuestion = true;
				brainTrainer.recordQuestion(enabledAvailableQuestionTypes.get(randomQuestionType), randomQuestionNum);
				Log.d("MainActivity", "MainActivity.startRandomQuestion() found unasked: " + randomQuestionNum);
			} else {
				Log.d("MainActivity", "MainActivity.startRandomQuestion() question: " + randomQuestionNum + " already asked");
			}
		}
		// Start corresponding activity
		Intent intent;
		intent = new Intent(MainActivity.this, clazz);
		if(clazz.toString() != "Memory") {
			Field myField = clazz.getDeclaredField("randomProblem");
			myField.setInt(null, randomQuestionNum);
		}
		MainActivity.this.startActivity(intent);
		return true;
	}

}
