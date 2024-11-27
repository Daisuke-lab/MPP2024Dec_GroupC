package org.example;

public class Circle {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double computeArea() {
        return radius * radius * Math.PI;
    }
}
