package com.appanddone.braintrainer;

import android.opengl.GLSurfaceView;
import android.os.Bundle;

import com.appanddone.braintrainer.shapes.ShapeGLSurfaceView;

public class Memory extends MainActivity {

	public final static int numProblems = 1;
	public static int randomProblem;
	private GLSurfaceView mGLView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a GLSurfaceView instance and set it
        // as the ContentView for this Activity.
        mGLView = new ShapeGLSurfaceView(this);
        setContentView(mGLView);
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
