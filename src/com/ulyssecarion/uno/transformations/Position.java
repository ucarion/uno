package com.ulyssecarion.uno.transformations;

import com.ulyssecarion.uno.math.Matrix;

public class Position extends Transformation {
	@Override
	public Matrix getTransformation() {
		return new Matrix(
			1, 0, 0, getX(),
			0, 1, 0, getY(),
			0, 0, 1, getZ(),
			0, 0, 0, 1
		);
	}
}
