package com.ulyssecarion.uno.renderer;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.util.ShaderUtils;

public class Renderer {
	private static final int BYTES_PER_FLOAT = 4;
	private static final int POSITION_COMPONENTS = 3;
	private static final int COLOR_COMPONENTS = 3;
	private static final int STRIDE = (POSITION_COMPONENTS + COLOR_COMPONENTS)
			* BYTES_PER_FLOAT;
	
	private static final String A_POSITION = "a_Position";
	private static final String A_COLOR = "a_Color";
	private static final String U_VIEWPROJ = "u_ViewProjection";
	private static final String U_MODEL = "u_Model";
	
	private GL2 gl;
	private int aPosition;
	private int aColor;
	private int uViewProjection;
	private int uModel;
	private int program;
	
	private FloatBuffer viewProj;
	private FloatBuffer model;
	
	public Renderer(GL2 gl) {
		this.gl = gl;
		init();
	}
	
	private void init() {
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);
		
		program =
				ShaderUtils.createProgram(gl, "shaders\\basic.vrtx",
						"shaders\\basic.frag");
		gl.glUseProgram(program);
		
		aPosition = gl.glGetAttribLocation(program, A_POSITION);
		aColor = gl.glGetAttribLocation(program, A_COLOR);
		uViewProjection = gl.glGetUniformLocation(program, U_VIEWPROJ);
		uModel = gl.glGetUniformLocation(program, U_MODEL);
	}
	
	public void loadPoints(FloatBuffer points) {
		points.position(0);
		gl.glVertexAttribPointer(aPosition, POSITION_COMPONENTS, GL2.GL_FLOAT, false,
				STRIDE, points);
		gl.glEnableVertexAttribArray(aPosition);
		
		points.position(POSITION_COMPONENTS);
		gl.glVertexAttribPointer(aColor, COLOR_COMPONENTS, GL2.GL_FLOAT, false, STRIDE,
				points);
		gl.glEnableVertexAttribArray(aColor);
	}
	
	public void setModelMatrix(Matrix matrix) {
		FloatBuffer fb = matrix.asFloatBuffer();
		fb.position(0);
		model = fb;
	}
	
	public void draw(int numPoints) {
		gl.glUniformMatrix4fv(uModel, 1, false, model);
		gl.glUniformMatrix4fv(uViewProjection, 1, false, viewProj);
		gl.glDrawArrays(GL2.GL_TRIANGLES, 0, numPoints);
	}
	
	public void setViewProjection(Matrix viewProjection) {
		FloatBuffer fb = viewProjection.asFloatBuffer();
		fb.position(0);
		viewProj = fb;
	}
}
