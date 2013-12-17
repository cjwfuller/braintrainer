package com.appanddone.braintrainer;

import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.RelativeLayout;

import com.appanddone.braintrainer.shapes.ShapeGLSurfaceView;

public class Memory extends MainActivity {

	public final static int numProblems = 1;
	public static int randomProblem;
	private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);
        mGLView = new ShapeGLSurfaceView(this);        
        RelativeLayout layout = (RelativeLayout)findViewById(R.id.memory_layout);
        layout.addView(mGLView, 0);
    }

    @Override
    protected void onPause() {
        // The following call pauses the rendering thread.
        // If your OpenGL application is memory intensive,
        // you should consider de-allocating objects that
        // consume significant memory here.
        super.onPause();
        mGLView.onPause();
    }

    @Override
    protected void onResume() {
        // The following call resumes a paused rendering thread.
        // If you de-allocated graphic objects for onPause()
        // this is a good place to re-allocate them.
        super.onResume();
        mGLView.onResume();
    }
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

}
