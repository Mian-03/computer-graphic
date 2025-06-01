package cgg;

import tools.Vec2;
import tools.Vec3;
import static tools.Functions.normalize;

public class SimpleCamera implements Camera {
    private final double fov;
    private final int width, height;
    private final double aspect;

    public SimpleCamera(double fovDeg, int width, int height) {
        this.fov = Math.toRadians(fovDeg);
        this.width = width;
        this.height = height;
        this.aspect = (double) width / height;
    }

    @Override
    public Ray shootRay(Vec2 point) {
        double px = (2 * (point.x() / width) - 1) * aspect * Math.tan(fov / 2);
        double py = (1 - 2 * (point.y() / height)) * Math.tan(fov / 2);
        Vec3 dir = normalize(new Vec3(px, py, -1));
        return new Ray(new Vec3(0, 0, 0), dir, 0.0, Double.POSITIVE_INFINITY);
    }
}







