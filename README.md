Uno
===

Three.js, except inferior and in a different language.

At the moment, this project is a total work in progress. I still have to do a bunch of stuff, including (most importantly):

* Documentation
* Adding more geometries
* Adding materials

Right now, though, the idea is sort of there. Look how easy it is to get a groovy square spinning around one of its sides!
```java
	public static void main(String[] args) {
		// specify size of the Uno environment
		Uno uno = new Uno(500, 300);
		
		JFrame frame = new JFrame("Hello World");
		frame.getContentPane().add(uno.getCanvas());
		
		// create the points ...
		Point p1 = new Point(0, 0, 0, Color.CYAN);
		Point p2 = new Point(0, 1, 0, Color.YELLOW);
		Point p3 = new Point(1, 0, 0, Color.PINK);
		Point p4 = new Point(1, 1, 0, Color.MAGENTA);
		
		// make some triangles ...
		TriangleGeometry t1 = new TriangleGeometry(p1, p2, p3);
		TriangleGeometry t2 = new TriangleGeometry(p2, p3, p4);
		
		// add a camera ...
		Camera c =
				new Camera(new Vector(2, 2, 2), new Vector(0, 0, 0), new Vector(0, 1, 0));
		uno.getScene().setCamera(c);
		
		// create a square from those two triangles ...
		// (Object3D is analogous to three.js's Object3D)
		// (square must be final because we're gonna modify it from an anonymous
		// class)
		final Object3D square = new Object3D();
		square.add(t1);
		square.add(t2);
		
		// place the square into the scene ...
		uno.getScene().add(square);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		// and ... action!
		new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double rot = square.getRotation().getY();
				square.getRotation().setY(rot + 0.01);
			}
		}).start();
	}
```
