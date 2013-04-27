package com.ulyssecarion.uno.util;

import javax.media.opengl.GL2;

import com.jogamp.opengl.util.glsl.ShaderUtil;

public class ShaderUtils {
	public static int createProgram(GL2 gl, String pathToVertexShader,
			String pathToFragmentShader) {
		int program = gl.glCreateProgram();
		
		if (program == 0)
			throw new RuntimeException("Failed to create the program.");
		
		int vertexShader =
				createShader(gl, FileUtils.readFile(pathToVertexShader),
						GL2.GL_VERTEX_SHADER);
		int fragmentShader =
				createShader(gl, FileUtils.readFile(pathToFragmentShader),
						GL2.GL_FRAGMENT_SHADER);
		
		gl.glAttachShader(program, vertexShader);
		gl.glAttachShader(program, fragmentShader);
		gl.glLinkProgram(program);
		
		int[] linkStatus = new int[1];
		gl.glGetProgramiv(program, GL2.GL_LINK_STATUS, linkStatus, 0);
		
		if (linkStatus[0] == 0) {
			gl.glDeleteProgram(program);
			
			throw new RuntimeException("Failed to link the program.");
		}
		
		return program;
	}
	
	private static int createShader(GL2 gl, String source, int type) {
		int shader = gl.glCreateShader(type);
		
		if (shader == 0)
			throw new RuntimeException("Failed to create the shader.");
		
		gl.glShaderSource(shader, 1, new String[] { source }, (int[]) null, 0);
		gl.glCompileShader(shader);
		
		int[] compileStatus = new int[1];
		gl.glGetShaderiv(shader, GL2.GL_COMPILE_STATUS, compileStatus, 0);
		
		if (compileStatus[0] == 0) {
			System.err.println(source);
			System.err.println(ShaderUtil.getShaderInfoLog(gl, shader));
			gl.glDeleteShader(shader);
			throw new RuntimeException("Failed to compile the shader.");
		}
		
		return shader;
	}
}
