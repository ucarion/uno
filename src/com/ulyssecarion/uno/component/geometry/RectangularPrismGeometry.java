package com.ulyssecarion.uno.component.geometry;

import java.awt.Color;
import java.nio.FloatBuffer;

import com.jogamp.common.nio.Buffers;
import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.renderer.Renderer;
import com.ulyssecarion.uno.transformations.Position;
import com.ulyssecarion.uno.transformations.Rotation;
import com.ulyssecarion.uno.transformations.Scale;

public class RectangularPrismGeometry extends Geometry {
	private Position position;
	private Rotation rotation;
	private Scale scale;
	
	private float x;
	private float y;
	private float z;
	private Color color;
	
	public RectangularPrismGeometry(double x, double y, double z, Color color) {
		this.position = new Position();
		this.rotation = new Rotation();
		this.scale = new Scale();
		
		this.x = (float) (x / 2);
		this.y = (float) (y / 2);
		this.z = (float) (z / 2);
		this.color = color;
	}
	
	@Override
	public FloatBuffer getPointAttribArray() {
		float r = color.getRed() / 255f;
		float g = color.getGreen() / 255f;
		float b = color.getBlue() / 255f;
		
		float[] arr = {
				 // back
		        -x, -y, -z, 1, 0, 0,  0,  0, -1, 
		         x, -y, -z, 1, 0, 0,  0,  0, -1, 
		         x,  y, -z, 1, 0, 0,  0,  0, -1, 
		        
		        -x, -y, -z, 1, 0, 0,  0,  0, -1, 
		        -x,  y, -z, 1, 0, 0,  0,  0, -1, 
		         x,  y, -z, 1, 0, 0,  0,  0, -1, 
		        
		         // front
		        -x, -y,  z, 0, 1, 0,  0,  0,  1, 
		         x, -y,  z, 0, 1, 0,  0,  0,  1, 
		         x,  y,  z, 0, 1, 0,  0,  0,  1, 
		        
		        -x, -y,  z, 0, 1, 0,  0,  0,  1, 
		        -x,  y,  z, 0, 1, 0,  0,  0,  1, 
		         x,  y,  z, 0, 1, 0,  0,  0,  1, 
		         
		         // bottom
		        -x, -y, -z, 0, 0, 1,  0, -1,  0,
		         x, -y, -z, 0, 0, 1,  0, -1,  0,
		         x, -y,  z, 0, 0, 1,  0, -1,  0,
		        
		        -x, -y, -z, 0, 0, 1,  0, -1,  0,
		        -x, -y,  z, 0, 0, 1,  0, -1,  0,
		         x, -y,  z, 0, 0, 1,  0, -1,  0,
		         
		         // top
		        -x,  y, -z, 0, 1, 1,  0,  1,  0,
		         x,  y, -z, 0, 1, 1,  0,  1,  0,
		         x,  y,  z, 0, 1, 1,  0,  1,  0,
		          
		        -x,  y, -z, 0, 1, 1,  0,  1,  0,
		        -x,  y,  z, 0, 1, 1,  0,  1,  0,
		         x,  y,  z, 0, 1, 1,  0,  1,  0,
		         
		         // left
		        -x, -y, -z, 1, 0, 1, -1,  0,  0,
		        -x,  y, -z, 1, 0, 1, -1,  0,  0,
		        -x,  y,  z, 1, 0, 1, -1,  0,  0,
		        
		        -x, -y, -z, 1, 0, 1, -1,  0,  0,
		        -x, -y,  z, 1, 0, 1, -1,  0,  0,
		        -x,  y,  z, 1, 0, 1, -1,  0,  0,
		        
		         // right
		         x, -y, -z, 1, 1, 0,  1,  0,  0,
		         x,  y, -z, 1, 1, 0,  1,  0,  0,
		         x,  y,  z, 1, 1, 0,  1,  0,  0,
		        
		         x, -y, -z, 1, 1, 0,  1,  0,  0,
		         x, -y,  z, 1, 1, 0,  1,  0,  0,
		         x,  y,  z, 1, 1, 0,  1,  0,  0,
		};
		
		return Buffers.newDirectFloatBuffer(arr);
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
	public void render(Renderer renderer, Matrix modelMatrix) {
		Matrix newModelMatrix = getTransformation().times(modelMatrix);
		
		renderer.loadPoints(getPointAttribArray());
		renderer.setModelMatrix(newModelMatrix);
		renderer.draw(6 * 2 * 3);
	}
}
