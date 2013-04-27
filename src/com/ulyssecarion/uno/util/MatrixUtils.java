package com.ulyssecarion.uno.util;

import com.ulyssecarion.uno.camera.Camera;
import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.math.Vector;

public class MatrixUtils {
	public static Matrix perspective(float fovy, float aspect, float zNear, float zFar) {
		float halfFovyRadians = (float) Math.toRadians( (fovy / 2.0f));
		float range = (float) Math.tan(halfFovyRadians) * zNear;
		float left = -range * aspect;
		float right = range * aspect;
		float bottom = -range;
		float top = range;
		
		return new Matrix( 
			(2f * zNear) / (right - left), 0, 0, 0,
			0, (2f * zNear) / (top - bottom), 0, 0,
			0, 0, - (zFar + zNear) / (zFar - zNear), - (2f * zFar * zNear) / (zFar - zNear),
			0, 0, -1, 0
		);
	}
	
	public static Matrix lookAt(Camera camera) {
		return lookAt(camera.getLocation(), camera.getTarget(), camera.getUp());
	}
	
	public static Matrix lookAt(Vector eye, Vector center, Vector up) {
		Vector f = center.sub(eye).normalize();
		Vector u = up.normalize();
		Vector s = f.cross(u).normalize();
		
		u = s.cross(f);

		return new Matrix(
			 s.getX(),  s.getY(),  s.getZ(), -s.dot(eye),
			 u.getX(),  u.getY(),  u.getZ(), -u.dot(eye),
			-f.getX(), -f.getY(), -f.getZ(),  f.dot(eye),
			        0,         0,         0,           1
		);
	}
}
