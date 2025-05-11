package cgg;

import tools.Color;
import tools.Vec3;
import static tools.Functions.normalize;

public class Hit {
    private final double t;
    private final Vec3 point;
    private final Vec3 normal;
    private final Color color;

    public Hit(double t, Vec3 point, Vec3 normal, Color color) {
        this.t = t;
        this.point = point;
        this.normal = normalize(normal);
        this.color = color;
    }

    public double t() { return t; }
    public Vec3 point() { return point; }
    public Vec3 normal() { return normal; }
    public Color color() { return color; }

    @Override
    public String toString() {
        return String.format("Hit[t=%.2f, point=%s, normal=%s, color=%s]", t, point, normal, color);
    }
}


