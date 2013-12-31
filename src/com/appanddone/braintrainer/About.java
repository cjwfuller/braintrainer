package com.appanddone.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.text.util.Linkify;
import android.widget.TextView;

public class About extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// Turn email address into a mailto link (seems pretty magic)
		TextView aboutTextView = (TextView)findViewById(R.id.about_description_text);
		Linkify.addLinks(aboutTextView, Linkify.ALL);
	}
}
