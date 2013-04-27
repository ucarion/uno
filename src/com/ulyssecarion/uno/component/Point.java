package com.ulyssecarion.uno.component;

import java.awt.Color;

import com.ulyssecarion.uno.math.Vector;

public class Point {
	private Vector location;
	private Color color;
	
	public Point() {
		this(0, 0, 0, Color.BLACK);
	}
	
	public Point(float x, float y, float z, Color color) {
		this(new Vector(x, y, z), color);
	}
	
	public Point(Vector location, Color color) {
		this.location = location;
		this.color = color;
	}
	
	public float[] asArray() {
		float r = color.getRed() / 255f;
		float g = color.getGreen() / 255f;
		float b = color.getBlue() / 255f;
		float[] f = { location.getX(), location.getY(), location.getZ(), r, g, b };
		return f;
	}
}
