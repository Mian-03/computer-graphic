package cgg;

import tools.Color;
import tools.Vec3;
import static tools.Functions.*;

public class Sphere {
    private final Vec3 center;
    private final double radius;
    private final Color color;

    public Sphere(Vec3 center, double radius, Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    public Hit intersect(Ray r) {
        Vec3 oc = subtract(r.origin(), center);
        double a = dot(r.direction(), r.direction());
        double b = 2 * dot(oc, r.direction());
        double c = dot(oc, oc) - radius * radius;
        double disc = b * b - 4 * a * c;
        if (disc < 0) return null;
        double sqrtD = Math.sqrt(disc);
        double t0 = (-b - sqrtD) / (2 * a);
        double t1 = (-b + sqrtD) / (2 * a);
        double tHit = t0;
        if (!r.valid(tHit)) {
            tHit = t1;
            if (!r.valid(tHit)) return null;
        }
        Vec3 p = r.pointAt(tHit);
        Vec3 n = normalize(subtract(p, center));
        return new Hit(tHit, p, n, color);
    }
}


