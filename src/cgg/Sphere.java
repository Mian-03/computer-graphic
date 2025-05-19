package cgg;

import tools.Vec2;
import tools.Vec3;
import static tools.Functions.*;

public class Sphere {
    private final Vec3 center;
    private final double radius;
    private final Material material;

    public Sphere(Vec3 center, double radius, Material material) {
        this.center = center;
        this.radius = radius;
        this.material = material;
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

        // UV-Koordinaten berechnen (Kugel-Textur)
        Vec3 np = normalize(subtract(p, center));
        double u = 0.5 + (Math.atan2(np.z(), np.x()) / (2 * Math.PI));
        double v = 0.5 - (Math.asin(np.y()) / Math.PI);
        Vec2 uv = new Vec2(u, v);

        return new Hit(tHit, p, n, material, uv);
    }
}