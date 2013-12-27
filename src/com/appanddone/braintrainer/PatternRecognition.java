package com.appanddone.braintrainer;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;


public class PatternRecognition extends MainActivity {

	public final static int numProblems = 1;
	public static int randomProblem;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pattern_recognition);
		Typeface typeFace = Typeface.createFromAsset(getAssets(),"fonts/Arvil_Sans.ttf");
		TextView textView = (TextView)findViewById(R.id.pattern_recognition_instructions);
		textView.setTypeface(typeFace);
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
}
