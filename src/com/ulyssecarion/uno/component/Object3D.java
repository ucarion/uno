package com.ulyssecarion.uno.component;

import java.util.ArrayList;
import java.util.List;

import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.renderer.Renderer;
import com.ulyssecarion.uno.transformations.Position;
import com.ulyssecarion.uno.transformations.Rotation;
import com.ulyssecarion.uno.transformations.Scale;

public class Object3D extends Component {
	private List<Component> children;
	
	private Position position;
	private Rotation rotation;
	private Scale scale;
	
	public Object3D() {
		position = new Position();
		rotation = new Rotation();
		scale = new Scale();
		
		children = new ArrayList<>();
	}
	
	public void add(Component component) {
		children.add(component);
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
		
		for (Component component : children) {
			component.render(renderer, newModelMatrix);
		}
	}
}
