package com.appanddone.braintrainer.shapes;

import java.util.ArrayList;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;
import android.opengl.GLU;
import android.util.Log;

/**
 * Provides drawing instructions for a GLSurfaceView object. This class
 * must override the OpenGL ES drawing lifecycle methods:
 * <ul>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceCreated}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onDrawFrame}</li>
 *   <li>{@link android.opengl.GLSurfaceView.Renderer#onSurfaceChanged}</li>
 * </ul>
 */
public class ShapeGLRenderer implements GLSurfaceView.Renderer {

    private Triangle mTriangle1;
    private Triangle mTriangle2;
    private Square mSquare1;
    private Square mSquare2;
    private Rectangle mRectangle;
    private float mAngle;
    private ArrayList<CoOrdPair> existingCoOrds;

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        mTriangle1 = new Triangle();
        mTriangle2 = new Triangle();
        mSquare1 = new Square();
        mSquare2 = new Square();
        mRectangle = new Rectangle();
        existingCoOrds = new ArrayList<CoOrdPair>();
    }

    @Override
    public void onDrawFrame(GL10 gl) {

        // Draw background color
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);

        // Set GL_MODELVIEW transformation mode
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        gl.glLoadIdentity();   // reset the matrix to its default state

        // When using GL_MODELVIEW, you must set the view point
        GLU.gluLookAt(gl, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
        
        CoOrdPair coOrd;        
        
        coOrd = getRandomCoOrd();
        coOrd.printCoOrds();
        gl.glPushMatrix();
        gl.glTranslatef(coOrd.x, coOrd.y, 0.0f);        
        mSquare1.draw(gl);
        gl.glPopMatrix();
        
        coOrd = getRandomCoOrd();
        coOrd.printCoOrds();
        gl.glPushMatrix();
        gl.glTranslatef(coOrd.x, coOrd.y, 0.0f);        
        mSquare2.draw(gl);
        gl.glPopMatrix();
        
        coOrd = getRandomCoOrd();
        coOrd.printCoOrds();
        gl.glPushMatrix();
        gl.glTranslatef(coOrd.x, coOrd.y, 0.0f);        
        mRectangle.draw(gl);
        gl.glPopMatrix();

        coOrd = getRandomCoOrd();
        coOrd.printCoOrds();
        gl.glPushMatrix();
        gl.glTranslatef(coOrd.x, coOrd.y, 0.0f);        
        mTriangle1.draw(gl);
        gl.glPopMatrix();
        
        coOrd = getRandomCoOrd();
        coOrd.printCoOrds();
        gl.glPushMatrix();
        gl.glTranslatef(coOrd.x, coOrd.y, 0.0f);        
        mTriangle2.draw(gl);
        gl.glPopMatrix();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        // Adjust the viewport based on geometry changes
        // such as screen rotations
        gl.glViewport(0, 0, width, height);
        // make adjustments for screen ratio
        float ratio = (float) width / height;
        gl.glMatrixMode(GL10.GL_PROJECTION); // set matrix to projection mode
        gl.glLoadIdentity();  // reset the matrix to its default state
        gl.glFrustumf(-ratio, ratio, -1, 1, 3, 7); // apply tprojection matrix
    }
    
    /**
     * Returns a random co-ordinate that doesn't overlap with any other shapes
     * 
     * @return CoOrdPair
     */
    private CoOrdPair getRandomCoOrd() {
    	CoOrdPair coOrd = null;
    	boolean overlappingCoOrd = true;
        while(overlappingCoOrd) {
        	coOrd = new CoOrdPair();
        	if(!coOrdOverlaps(coOrd)) {
        		overlappingCoOrd = false;
        	}
        }
        existingCoOrds.add(coOrd);
        return coOrd;
    }
    
    /**
     * Returns whether a co-ordinate overlaps with another shape
     * 
     * @param coOrdToAdd
     * @return - A boolean representing whether there's an overlap
     */
    private boolean coOrdOverlaps(CoOrdPair coOrdToAdd) {
    	if(existingCoOrds.size() == 0) {
    		return false;
    	}
    	for (CoOrdPair pair : existingCoOrds) {
    		Log.d("ShapeGLRenderer", "ShapeGLRenderer.coOrdOverlaps() X diff: " + Math.abs(pair.x - coOrdToAdd.x));
    		Log.d("ShapeGLRenderer", "ShapeGLRenderer.coOrdOverlaps() Y diff: " + Math.abs(pair.y - coOrdToAdd.y));
    		if((Math.abs(pair.x - coOrdToAdd.x) < 0.15) || (Math.abs(pair.y - coOrdToAdd.y) < 0.15)) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Returns the rotation angle of the triangle shape (mTriangle).
     *
     * @return - A float representing the rotation angle.
     */
    public float getAngle() {
        return mAngle;
    }

    /**
     * Sets the rotation angle of the triangle shape (mTriangle).
     */
    public void setAngle(float angle) {
        mAngle = angle;
    }
}

/**
 * Random co-ordinates in 2D space 
 * 
 * @author cjwfuller
 *
 */
class CoOrdPair {
	
	final float x;
	final float y;
	final float limit;
	
	CoOrdPair() {
		limit = 0.6f; // stops shapes going off the screen
		Random rand = new Random();
		this.x = rand.nextFloat() * (limit - -limit) + -limit;
		this.y = rand.nextFloat() * (limit - -limit) + -limit;
	}
	
	/**
	 * Used for debugging
	 */
	public void printCoOrds() {
		Log.d("ShapeGLRenderer", "CoOrdPair.printCoOrds(): (" + Float.toString(x) + ", " + Float.toString(y));
	}
}