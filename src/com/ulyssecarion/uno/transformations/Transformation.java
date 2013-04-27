package com.ulyssecarion.uno.transformations;

import com.ulyssecarion.uno.math.Matrix;

public abstract class Transformation {
	private float x;
	private float y;
	private float z;
	
	public Transformation() {
		x = 0;
		y = 0;
		z = 0;
	}
	
	public Transformation(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Transformation(double x, double y, double z) {
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
	}
	
	public abstract Matrix getTransformation();

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}
	
	public void setX(double x) {
		this.x = (float) x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void setY(double y) {
		this.y = (float) y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	public void setZ(double z) {
		this.z = (float) z;
	}
}
