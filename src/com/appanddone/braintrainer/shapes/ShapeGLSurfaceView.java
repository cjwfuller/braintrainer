package com.appanddone.braintrainer.shapes;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.util.Log;
import android.view.MotionEvent;

/**
 * A view container where OpenGL ES graphics can be drawn on screen.
 * This view can also be used to capture touch events, such as a user
 * interacting with drawn objects.
 */
public class ShapeGLSurfaceView extends GLSurfaceView {

    private final ShapeGLRenderer mRenderer;

    public ShapeGLSurfaceView(Context context) {
        super(context);

        setZOrderOnTop(true);
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(PixelFormat.TRANSLUCENT);
        
        // Set the Renderer for drawing on the GLSurfaceView
        mRenderer = new ShapeGLRenderer();
        setRenderer(mRenderer);
        // Render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
    
    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;
    

    
    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

    	Log.d("ShapeGLSurfaceView", "ShapeGLSurfaceView.onTouchEvent(): Enter");
    	
        float x = e.getX();
        float y = e.getY();
        
        Log.d("ShapeGLSurfaceView", "ShapeGLSurfaceView.onTouchEvent(): x, y: " + Float.toString(x) + ", " + Float.toString(y));

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

                //float dx = x - mPreviousX;
                //float dy = y - mPreviousY;

                // reverse direction of rotation above the mid-line
                //if (y > getHeight() / 2) {
                  //dx = dx * -1 ;
                //}

                // reverse direction of rotation to left of the mid-line
                //if (x < getWidth() / 2) {
                  //dy = dy * -1 ;
                //}

               // mRenderer.setAngle(
               //         mRenderer.getAngle() +
               //         ((dx + dy) * TOUCH_SCALE_FACTOR));
               requestRender();
        }

        mPreviousX = x;
        mPreviousY = y;
        
        Log.d("ShapeGLSurfaceView", "ShapeGLSurfaceView.onTouchEvent(): Leave");
        
        return true;
    }
}

