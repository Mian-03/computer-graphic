package cgg;

import tools.Vec3;
import static tools.Functions.*;

public class Ray {
    private final Vec3 origin;
    private final Vec3 dir;
    private final double tMin, tMax;

    public Ray(Vec3 origin, Vec3 dir, double tMin, double tMax) {
        this.origin = origin;
        this.dir = normalize(dir);
        this.tMin = tMin;
        this.tMax = tMax;
    }

    public Vec3 origin() {
        return origin;
    }

    public Vec3 direction() {
        return dir;
    }

    public double tMin() {
        return tMin;
    }

    public double tMax() {
        return tMax;
    }

    public Vec3 pointAt(double t) {
        return add(origin, multiply(t, dir));
    }

    // ➕ Alias-Methode für RayTracer-Kompatibilität
    public Vec3 at(double t) {
        return pointAt(t);
    }

    public boolean valid(double t) {
        return t >= tMin && t <= tMax;
    }
}

