package com.ulyssecarion.uno.math;

public class Vector {
	private float x;
	private float y;
	private float z;
	
	public Vector(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector(double x, double y, double z) {
		this.x = (float) x;
		this.y = (float) y;
		this.z = (float) z;
	}
	
	public float getX() {
		return x;
	}
	
	public float getY() {
		return y;
	}
	
	public float getZ() {
		return z;
	}
	
	public float[] asArray() {
		float[] f = { x, y, z };
		return f;
	}
	
	public Vector add(Vector other) {
		return new Vector(x + other.x, y + other.y, z + other.z);
	}
	
	public Vector sub(Vector other) {
		return new Vector(x - other.x, y - other.y, z - other.z);
	}
	
	public Vector normalize() {
		float norm = (float) Math.sqrt(x * x + y * y + z * z);
		
		return new Vector(x / norm, y / norm, z / norm);
	}
	
	public float dot(Vector other) {
		return x * other.x + y * other.y + z * other.z;
	}
	
	public Vector cross(Vector other) {
		return new Vector(
			y * other.z - z * other.y,
			z * other.x - x * other.z,
			x * other.y - y * other.x
		);
	}
	
	@Override
	public String toString() {
		return "[ " + x + " " + y + " " + z + " ]";
	}
}
