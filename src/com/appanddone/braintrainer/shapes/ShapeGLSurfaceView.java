package com.appanddone.braintrainer.shapes;

import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;

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

}

