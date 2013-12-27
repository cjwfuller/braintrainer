package com.appanddone.braintrainer;

import android.os.Bundle;

public class Verbal extends MainActivity {
	
	public final static int numProblems = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verbal);
		
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
