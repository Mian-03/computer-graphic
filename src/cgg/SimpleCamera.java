package cgg;

import tools.Vec2;
import tools.Vec3;
import static tools.Functions.normalize;

public class SimpleCamera {
    private final double fov;
    private final int width, height;
    private final double aspect;

    public SimpleCamera(double fovDeg, int width, int height) {
        this.fov = Math.toRadians(fovDeg);
        this.width = width;
        this.height = height;
        this.aspect = (double) width / height;
    }

    public Ray generateRay(Vec2 sample) {
        double px = (2 * (sample.x() / width) - 1) * aspect * Math.tan(fov / 2);
        double py = (1 - 2 * (sample.y() / height)) * Math.tan(fov / 2);
        Vec3 dir = normalize(new Vec3(px, py, -1));
        return new Ray(new Vec3(1, 1, 1), dir, 0.0, Double.POSITIVE_INFINITY);
    }
}






