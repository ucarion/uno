package com.ulyssecarion.uno.component;

import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.renderer.Renderer;
import com.ulyssecarion.uno.transformations.Position;
import com.ulyssecarion.uno.transformations.Rotation;
import com.ulyssecarion.uno.transformations.Scale;

public abstract class Component {
	public abstract Position getPosition();
	public abstract Rotation getRotation();
	public abstract Scale getScale();
	
	public Matrix getTransformation() {
		Matrix p = getPosition().getTransformation();
		Matrix r = getRotation().getTransformation();
		Matrix s = getScale().getTransformation();
		
		return p.times(r).times(s);
	}
	
	public abstract void render(Renderer renderer, Matrix modelMatrix);
}