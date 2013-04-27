package com.ulyssecarion.uno.camera;

import com.ulyssecarion.uno.math.Vector;

public class Camera {
	private Vector location;
	private Vector target;
	private Vector up;
	private double fieldOfView;
	
	public Camera() {
		this(new Vector(0, 0, 1), new Vector(0, 0, 0), new Vector(0, 1, 0));
	}
	
	public Camera(Vector location, Vector target, Vector up) {
		this(location, target, up, 45);
	}
	
	public Camera(Vector location, Vector target, Vector up, double fieldOfView) {
		this.location = location;
		this.target = target;
		this.up = up;
		this.fieldOfView = fieldOfView;
	}
	
	public Vector getLocation() {
		return location;
	}
	
	public void setLocation(Vector location) {
		this.location = location;
	}
	
	public Vector getTarget() {
		return target;
	}
	
	public void setTarget(Vector target) {
		this.target = target;
	}
	
	public Vector getUp() {
		return up;
	}
	
	public void setUp(Vector up) {
		this.up = up;
	}
	
	public double getFieldOfView() {
		return fieldOfView;
	}
	
	public void setFieldOfView(double fieldOfView) {
		this.fieldOfView = fieldOfView;
	}
}
