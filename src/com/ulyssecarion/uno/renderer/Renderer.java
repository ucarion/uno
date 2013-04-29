package com.ulyssecarion.uno.renderer;

import java.nio.FloatBuffer;

import javax.media.opengl.GL2;

import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.math.Vector;
import com.ulyssecarion.uno.util.ShaderUtils;

public class Renderer {
	private static final int BYTES_PER_FLOAT = 4;
	private static final int POSITION_COMPONENTS = 3;
	private static final int COLOR_COMPONENTS = 3;
	private static final int STRIDE =
			(POSITION_COMPONENTS + COLOR_COMPONENTS + POSITION_COMPONENTS)
					* BYTES_PER_FLOAT;
	
	private static final String A_POSITION = "a_Position";
	private static final String A_COLOR = "a_Color";
	private static final String A_NORMAL = "a_Normal";
	private static final String U_VIEW = "u_View";
	private static final String U_PROJ = "u_Proj";
	private static final String U_MODEL = "u_Model";
	private static final String U_DIRTOLIGHT = "u_DirToLight";
	private static final String U_DIFFUSE = "u_Diffuse";
	private static final String U_AMBIENT = "u_Ambient";
	
	private GL2 gl;
	private int aPosition;
	private int aColor;
	private int aNormal;
	private int uView;
	private int uProj;
	private int uModel;
	private int uDirToLight;
	private int uDiffuse;
	private int uAmbient;
	private int program;
	
	private FloatBuffer view;
	private FloatBuffer proj;
	private FloatBuffer model;
	private FloatBuffer light;
	private FloatBuffer diffuse;
	private FloatBuffer ambient;
	
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
		aNormal = gl.glGetAttribLocation(program, A_NORMAL);
		uView = gl.glGetUniformLocation(program, U_VIEW);
		uProj = gl.glGetUniformLocation(program, U_PROJ);
		uModel = gl.glGetUniformLocation(program, U_MODEL);
		uDirToLight = gl.glGetUniformLocation(program, U_DIRTOLIGHT);
		uDiffuse = gl.glGetUniformLocation(program, U_DIFFUSE);
		uAmbient = gl.glGetUniformLocation(program, U_AMBIENT);
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
		
		points.position(POSITION_COMPONENTS + COLOR_COMPONENTS);
		gl.glVertexAttribPointer(aNormal, POSITION_COMPONENTS, GL2.GL_FLOAT, false,
				STRIDE, points);
		gl.glEnableVertexAttribArray(aNormal);
	}
	
	public void setModelMatrix(Matrix matrix) {
		FloatBuffer fb = matrix.asFloatBuffer();
		fb.position(0);
		model = fb;
	}
	
	public void draw(int numPoints) {
		gl.glUniform3fv(uDiffuse, 1, diffuse);
		gl.glUniform3fv(uAmbient, 1, ambient);
		gl.glUniform3fv(uDirToLight, 1, light);
		gl.glUniformMatrix4fv(uModel, 1, false, model);
		gl.glUniformMatrix4fv(uView, 1, false, view);
		gl.glUniformMatrix4fv(uProj, 1, false, proj);
		gl.glDrawArrays(GL2.GL_TRIANGLES, 0, numPoints);
	}
	
	public void setDiffuse(double diffusiveness) {
		Vector v = new Vector(diffusiveness, diffusiveness, diffusiveness);
		diffuse = v.asFloatBuffer();
	}
	
	public void setAmbient(double ambience) {
		Vector v = new Vector(ambience, ambience, ambience);
		ambient = v.asFloatBuffer();
	}
	
	public void setLightDirection(Vector lightDirection) {
		light = lightDirection.asFloatBuffer();
	}
	
	public void setView(Matrix viewMatrix) {
		view = viewMatrix.asFloatBuffer();
	}
	
	public void setProjection(Matrix projMatrix) {
		proj = projMatrix.asFloatBuffer();
	}
}
