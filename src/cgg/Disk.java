package cgg;

import tools.Vec2;
import tools.Color;
//import static tools.Functions.*; 


public class Disk {
    private Vec2 center;
    private double radius;
    private Color color;

    public Disk(Vec2 center, double radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public boolean coversPoint(Vec2 point) {
        double dx = center.x() - point.x();
        double dy = center.y() - point.y();
        return dx * dx + dy * dy <= radius * radius;
    }
    

    public Color getColor() {
        return color;
    }
    public Vec2 getCenter() {
        return center;
    }
    
    public double getRadius() {
        return radius;
    }
    
}
