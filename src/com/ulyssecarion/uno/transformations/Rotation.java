package com.ulyssecarion.uno.transformations;

import com.ulyssecarion.uno.math.Matrix;

public class Rotation extends Transformation {
	@Override
	public Matrix getTransformation() {
		float s1 = (float) Math.sin(getX());
		float c1 = (float) Math.cos(getX());
		float s2 = (float) Math.sin(getY());
		float c2 = (float) Math.cos(getY());
		float s3 = (float) Math.sin(getZ());
		float c3 = (float) Math.cos(getZ());
		
		return new Matrix(
			c2 * c3, s1 * s2 * c3 - c1 * s3, s1 * s3 + c1 * s2 * c3, 0f,
			c2 * s3, c1 * c3 + s1 * s2 * s3, c1 * s2 * s3 - s1 * c3, 0f,
			    -s2,                s1 * c2,                c1 * c2, 0f,
			     0f,                     0f,                     0f, 1f
		);
	}
}
