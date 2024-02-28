package assignmeent2;

import java.awt.Color;

import java.awt.Shape;

public class MyShape {
private Shape shape;
private Color color;

//constructor
public MyShape(Shape s, Color c) {
//initialize data fields
this.shape = s;
this.color = c;
//getters
}
public Shape getShape() {
return shape;
}
public Color getColor() {
return color;
}
}