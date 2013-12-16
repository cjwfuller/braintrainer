package com.appanddone.braintrainer;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class BrainTrainer extends Application {
	
	@Override
	public void onCreate() {
		super.onCreate();
		clearScore();
	}
	
	private void clearScore() {
		SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
		Editor edit = prefs.edit();
	    edit.remove("scoreKey");
	    edit.commit();
	}
}
