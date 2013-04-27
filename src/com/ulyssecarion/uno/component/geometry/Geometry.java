package com.ulyssecarion.uno.component.geometry;

import java.nio.FloatBuffer;

import com.ulyssecarion.uno.component.Component;

public abstract class Geometry extends Component {
	public abstract FloatBuffer getPointAttribArray();
}
