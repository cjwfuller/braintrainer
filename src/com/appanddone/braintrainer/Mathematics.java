package com.appanddone.braintrainer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Mathematics extends MainActivity {
	
	private int[][] series;
	public final static int numProblems = 2;
	public static int randomProblem;
	
	public Mathematics() {
		setProblems();
	}
	
	private void setProblems() {
		series = new int[numProblems][6];
		// Progressively decreasing pattern 
		series[0][0] = 81;
		series[0][1] = 80;
		series[0][2] = 78;
		series[0][3] = 75;
		series[0][4] = 71;
		series[0][5] = 66;
		// Pair sums
		series[1][0] = 3;
		series[1][1] = 6;
		series[1][2] = 9;
		series[1][3] = 15;
		series[1][4] = 24;
		series[1][5] = 39;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mathematics);
		//brainTrainer.questionsAsked.put("mathematics", brainTrainer.questionsAsked.get("mathematics") + 1);
		// Show the random maths problem
		TextView sequenceView = (TextView)findViewById(R.id.maths_sequence);
		String str = "";
		for(int i = 0; i < series[randomProblem].length - 1; i++) {
			str += Integer.toString(series[randomProblem][i]) + ", ";
		}
		str += " ...";
		sequenceView.setText(str);
		// Check the answer
		final EditText mathsAnswer = (EditText)findViewById(R.id.maths_answer);
		Button nextButton = (Button)findViewById(R.id.next_button);
		nextButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(!mathsAnswer.getText().toString().matches("")) {
					if(Integer.parseInt(mathsAnswer.getText().toString()) == series[randomProblem][5]) {
						brainTrainer.numCorrect++;
					} else {
						brainTrainer.numIncorrect++;
					}
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
				} else {
					Toast.makeText(getApplicationContext(), "Please enter an answer", Toast.LENGTH_SHORT).show();
				}
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
