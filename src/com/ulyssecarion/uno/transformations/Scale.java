package com.ulyssecarion.uno.transformations;

import com.ulyssecarion.uno.math.Matrix;

public class Scale extends Transformation {
	public Scale() {
		super(1, 1, 1);
	}
	
	@Override
	public Matrix getTransformation() {
		return new Matrix(
			getX(), 0, 0, 0,
			0, getY(), 0, 0,
			0, 0, getZ(), 0,
			0, 0, 0, 1
		);
	}
}
