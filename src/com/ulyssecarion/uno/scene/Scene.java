package com.ulyssecarion.uno.scene;

import com.ulyssecarion.uno.camera.Camera;
import com.ulyssecarion.uno.component.Component;
import com.ulyssecarion.uno.component.Object3D;
import com.ulyssecarion.uno.math.Matrix;
import com.ulyssecarion.uno.math.Vector;
import com.ulyssecarion.uno.renderer.Renderer;
import com.ulyssecarion.uno.util.MatrixUtils;

public class Scene {
	private int height;
	private int width;
	
	private Object3D root;
	private Camera camera;
	private Vector light;
	private double ambience;
	private double diffusiveness;
	
	public Scene() {
		this(new Camera());
	}
	
	public Scene(Camera camera) {
		this(camera, 300, 300);
	}
	
	public Scene(Camera camera, int width, int height) {
		this.root = new Object3D();
		this.camera = camera;
		this.width = width;
		this.height = height;
		this.ambience = 1;
		this.diffusiveness = 0;
		this.light = new Vector(1, 0, 0);
	}
	
	public void add(Component component) {
		root.add(component);
	}
	
	public void render(Renderer renderer) {
		Matrix view = MatrixUtils.lookAt(camera);
		
		float fov = (float) camera.getFieldOfView();
		float aspect = width / (float) height;
		
		Matrix projection = MatrixUtils.perspective(fov, aspect, 0.1f, 100f);
		
		renderer.setView(view);
		renderer.setProjection(projection);
		renderer.setLightDirection(light);
		renderer.setDiffuse(diffusiveness);
		renderer.setAmbient(ambience);
		
		root.render(renderer, new Matrix());
	}
	
	public Camera getCamera() {
		return camera;
	}
	
	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setLight(Vector light) {
		this.light = light;
	}

	public void setAmbience(double ambience) {
		this.ambience = ambience;
	}

	public void setDiffusiveness(double diffusiveness) {
		this.diffusiveness = diffusiveness;
	}
}
