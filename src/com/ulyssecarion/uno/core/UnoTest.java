package com.ulyssecarion.uno.core;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

import com.ulyssecarion.uno.camera.Camera;
import com.ulyssecarion.uno.component.Object3D;
import com.ulyssecarion.uno.component.Point;
import com.ulyssecarion.uno.component.geometry.TriangleGeometry;
import com.ulyssecarion.uno.math.Vector;

public class UnoTest {
	public static void main(String[] args) {
		Uno uno = new Uno(500, 300);
		
		JFrame frame = new JFrame("Hello World");
		frame.getContentPane().add(uno.getCanvas());
		
		Point p1 = new Point(0, 0, 0, Color.CYAN);
		Point p2 = new Point(0, 1, 0, Color.YELLOW);
		Point p3 = new Point(1, 0, 0, Color.PINK);
		Point p4 = new Point(1, 1, 0, Color.MAGENTA);
		
		TriangleGeometry t = new TriangleGeometry(p1, p2, p3);
		TriangleGeometry u = new TriangleGeometry(p2, p3, p4);
		
		Camera c =
				new Camera(new Vector(2, 2, 2), new Vector(0, 0, 0), new Vector(0, 1, 0));
		
		uno.getScene().setCamera(c);
		
		final Object3D square = new Object3D();
		square.add(t);
		square.add(u);
		
		uno.getScene().add(square);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		
		new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double rot = square.getRotation().getY();
				square.getRotation().setY(rot + 0.01);
			}
		}).start();
	}
}
