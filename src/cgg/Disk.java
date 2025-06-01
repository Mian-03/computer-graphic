package cgg;

import tools.*;
import static tools.Functions.*;

public class Disk {
    private Vec2 center;
    private double radius;
    private Vec3 color;

    public Disk(Vec2 center, double radius, Vec3 color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public boolean contains(Vec2 point) {
        return length(subtract(point, center)) <= radius;
    }

    public Vec3 getColor() {
        return color;
    }
}


