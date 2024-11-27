package org.example;

public class Cylinder {
    private double height;
    private Circle circle;

    public Cylinder(double height, double radius) {
        this.height = height;
        this.circle = new Circle(radius);
    }

    public double computeVolume() {
        return height * circle.computeArea();
    }
}
