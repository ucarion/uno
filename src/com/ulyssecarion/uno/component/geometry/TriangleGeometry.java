package com.ulyssecarion.uno.component.geometry;

import java.nio.FloatBuffer;

import com.jogamp.common.nio.Buffers;
import com.ulyssecarion.uno.component.Point;
import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.renderer.Renderer;
import com.ulyssecarion.uno.transformations.Position;
import com.ulyssecarion.uno.transformations.Rotation;
import com.ulyssecarion.uno.transformations.Scale;

public class TriangleGeometry extends Geometry {
	private Position position;
	private Rotation rotation;
	private Scale scale;
	
	private Point point1;
	private Point point2;
	private Point point3;
	
	public TriangleGeometry() {
		this(new Point(), new Point(), new Point());
	}
	
	public TriangleGeometry(Point point1, Point point2, Point point3) {
		position = new Position();
		rotation = new Rotation();
		scale = new Scale();
		
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
	}
	
	@Override
	public Position getPosition() {
		return position;
	}
	
	@Override
	public Rotation getRotation() {
		return rotation;
	}
	
	@Override
	public Scale getScale() {
		return scale;
	}
	
	@Override
	public FloatBuffer getPointAttribArray() {
		float[] a = point1.asArray();
		float[] b = point2.asArray();
		float[] c = point3.asArray();
		
		float[] result = new float[a.length + b.length + c.length];
		
		System.arraycopy(a, 0, result, 0, a.length);
		System.arraycopy(b, 0, result, a.length, b.length);
		System.arraycopy(c, 0, result, a.length + b.length, c.length);
		
		return Buffers.newDirectFloatBuffer(result);
	}

	@Override
	public void render(Renderer renderer, Matrix modelMatrix) {
		Matrix newModelMatrix = getTransformation().times(modelMatrix);
		
		renderer.loadPoints(getPointAttribArray());
		renderer.setModelMatrix(newModelMatrix);
		renderer.draw(3);
	}
}
