package com.ulyssecarion.uno.math;

import java.nio.FloatBuffer;

import com.jogamp.common.nio.Buffers;

public class Matrix {
	private float m00, m10, m20, m30;
	private float m01, m11, m21, m31;
	private float m02, m12, m22, m32;
	private float m03, m13, m23, m33;
	
	public Matrix() {
		this(
			1, 0, 0, 0,
			0, 1, 0, 0,
			0, 0, 1, 0,
			0, 0, 0, 1
		);
	}
	
	public Matrix(
			float x00, float x01, float x02, float x03,
			float x10, float x11, float x12, float x13,
			float x20, float x21, float x22, float x23,
			float x30, float x31, float x32, float x33 ) {
		m00 = x00; m10 = x10; m20 = x20; m30 = x30;
		m01 = x01; m11 = x11; m21 = x21; m31 = x31;
		m02 = x02; m12 = x12; m22 = x22; m32 = x32;
		m03 = x03; m13 = x13; m23 = x23; m33 = x33;
	}
	
	public Matrix times(Matrix other) {
		return new Matrix(
			m00 * other.m00 + m01 * other.m10 + m02 * other.m20 + m03 * other.m30,
			m00 * other.m01 + m01 * other.m11 + m02 * other.m21 + m03 * other.m31,
			m00 * other.m02 + m01 * other.m12 + m02 * other.m22 + m03 * other.m32,
			m00 * other.m03 + m01 * other.m13 + m02 * other.m23 + m03 * other.m33,
			
			m10 * other.m00 + m11 * other.m10 + m12 * other.m20 + m13 * other.m30,
			m10 * other.m01 + m11 * other.m11 + m12 * other.m21 + m13 * other.m31,
			m10 * other.m02 + m11 * other.m12 + m12 * other.m22 + m13 * other.m32,
			m10 * other.m03 + m11 * other.m13 + m12 * other.m23 + m13 * other.m33,
			
			m20 * other.m00 + m21 * other.m10 + m22 * other.m20 + m23 * other.m30,
			m20 * other.m01 + m21 * other.m11 + m22 * other.m21 + m23 * other.m31,
			m20 * other.m02 + m21 * other.m12 + m22 * other.m22 + m23 * other.m32,
			m20 * other.m03 + m21 * other.m13 + m22 * other.m23 + m23 * other.m33,
			
			m30 * other.m00 + m31 * other.m10 + m32 * other.m20 + m33 * other.m30,
			m30 * other.m01 + m31 * other.m11 + m32 * other.m21 + m33 * other.m31,
			m30 * other.m02 + m31 * other.m12 + m32 * other.m22 + m33 * other.m32,
			m30 * other.m03 + m31 * other.m13 + m32 * other.m23 + m33 * other.m33
		);
	}

	public float[] asArray() {
		float[] f = { 
				m00, m10, m20, m30,
				m01, m11, m21, m31,
				m02, m12, m22, m32,
				m03, m13, m23, m33 
		};
		
		return f;
	}
	
	public FloatBuffer asFloatBuffer() {
		return Buffers.newDirectFloatBuffer(asArray());
	}
	
	@Override
	public String toString() {
		return String.format(
				"% .5f % .5f % .5f % .5f\n" +
				"% .5f % .5f % .5f % .5f\n" +
				"% .5f % .5f % .5f % .5f\n" +
				"% .5f % .5f % .5f % .5f",
				
				m00, m01, m02, m03,
				m10, m11, m12, m13,
				m20, m21, m22, m23,
				m30, m31, m32, m33);
	}
}
