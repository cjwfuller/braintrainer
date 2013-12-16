package com.appanddone.braintrainer;

import java.util.Random;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Verbal extends MainActivity {
	
	private final int numProblems = 2;
	
	public Verbal() {
	
	}
	
	private void setProblems() {
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_verbal);
		// Show the random maths problem
		/*TextView sequenceView = (TextView)findViewById(R.id.maths_sequence);
		String str = "";
		final int randomProblem = new Random().nextInt(numProblems);
		for(int i = 0; i < series[randomProblem].length - 1; i++) {
			str += Integer.toString(series[randomProblem][i]) + ", ";
		}
		str += " ...";
		sequenceView.setText(str);*/
		// Check the answer
		final EditText verbalAnswer = (EditText)findViewById(R.id.verbal_answer);
		Button nextButton = (Button)findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(!verbalAnswer.getText().toString().matches("")) {
					
					startRandomQuestion();
				} else {
					Toast.makeText(getApplicationContext(), "Please enter an answer", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}

}
