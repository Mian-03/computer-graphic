package cgg;

import tools.Vec2;
import tools.Vec3;
import static tools.Functions.normalize;


public class Hit {
    private final double t;
    private final Vec3 point;
    private final Vec3 normal;
    private final Material material;
    private final Vec2 uv;

    public Hit(double t, Vec3 point, Vec3 normal, Material material, Vec2 uv) {
        this.t = t;
        this.point = point;
        this.normal = normalize(normal);
        this.material = material;
        this.uv = uv;
    }

    public double t() { return t; }
    public Vec3 point() { return point; }
    public Vec3 normal() { return normal; }
    public Material material() { return material; }
    public Vec2 uv() { return uv; }
}




