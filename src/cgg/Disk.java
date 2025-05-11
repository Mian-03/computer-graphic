package cgg;

import tools.Vec2;
import tools.Vec3;

public class Disk {
    private final Vec2 center;
    private final double radius;
    private final Vec3 color;

    public Disk(Vec2 center, double radius, Vec3 color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Vec2 getCenter() {
        return center;
    }

    public double getRadius() {
        return radius;
    }

    public Vec3 getColor() {
        return color;
    }

    public boolean contains(Vec2 p) {
        double dx = p.x() - center.x();
        double dy = p.y() - center.y();
        return dx * dx + dy * dy <= radius * radius;
    }
}

