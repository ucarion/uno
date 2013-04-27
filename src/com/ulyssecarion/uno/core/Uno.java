package com.ulyssecarion.uno.core;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.GLProfile;
import javax.media.opengl.awt.GLCanvas;

import com.jogamp.opengl.util.FPSAnimator;
import com.ulyssecarion.uno.camera.Camera;
import com.ulyssecarion.uno.renderer.Renderer;
import com.ulyssecarion.uno.scene.Scene;

public class Uno {
	private static final int FPS = 60;
	
	private GLCanvas glcanvas;
	
	private Scene scene;
	private Renderer renderer;
	
	public Uno(int width, int height) {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		
		glcanvas = new GLCanvas(capabilities);
		glcanvas.addGLEventListener(new GLEventListener() {
			@Override
			public void init(GLAutoDrawable drawable) {
				renderer = new Renderer(drawable.getGL().getGL2());
			}
			
			@Override
			public void display(GLAutoDrawable drawable) {
				GL2 gl = drawable.getGL().getGL2();
				
				gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
				scene.render(renderer);
			}
			
			@Override
			public void dispose(GLAutoDrawable drawable) {
				// TODO figure out what goes here
			}
			
			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y, int width,
					int height) {
				scene.setHeight(height);
				scene.setWidth(width);
			}
		});
		glcanvas.setSize(width, height);
		
		FPSAnimator animator = new FPSAnimator(glcanvas, FPS);
		animator.start();
		
		scene = new Scene(new Camera(), width, height);
	}
	
	public GLCanvas getCanvas() {
		return glcanvas;
	}
	
	public Scene getScene() {
		return scene;
	}
}