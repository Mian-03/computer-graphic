package cgg;

import tools.Vec2;
import tools.Vec3;
import static tools.Functions.normalize;

public class SimpleCamera implements Camera {
    private final double fov;
    private final int width, height;
    private final double aspect;
    private Mat4 transform;

    public SimpleCamera(double fovDeg, int width, int height) {
        this.fov = Math.toRadians(fovDeg);
        this.width = width;
        this.height = height;
        this.aspect = (double) width / height;
        this.transform = Mat4.identity();
    }

    public void setTransform(Mat4 transform) {
        this.transform = transform;
    }

    @Override
    public Ray shootRay(Vec2 point) {
        double px = (2 * (point.x() / width) - 1) * aspect * Math.tan(fov / 2);
        double py = (1 - 2 * (point.y() / height)) * Math.tan(fov / 2);
        Vec3 dir = normalize(new Vec3(px, py, -1));
        Vec3 origin = new Vec3(0, 0, 0);

        Vec3 worldOrigin = transform.transform(origin);
        Vec3 worldDir = normalize(transform.transformDirection(dir));


        return new Ray(worldOrigin, worldDir, 0.0, Double.POSITIVE_INFINITY);
    }
}

