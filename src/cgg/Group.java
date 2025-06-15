package cgg;

import tools.*;
import static tools.Functions.*;
import java.util.ArrayList;
import java.util.List;

public class Group implements Shape {
    private final List<Shape> children;
    private final Mat4 transform;
    private final Mat4 inverse;

    
    public Group() {
        this.children = new ArrayList<>();
        this.transform = Mat4.identity();
        this.inverse = transform.invertRigid();
    }

    
    public Group(List<Shape> children, Mat4 transform) {
        this.children = children;
        this.transform = transform;
        this.inverse = transform.invertRigid();
    }

   
    public void add(Shape shape) {
        children.add(shape);
    }

    @Override
    public Hit intersect(Ray ray) {
        Vec3 newOrigin = inverse.mul(ray.origin());
        Vec3 newDirection = inverse.mulDirection(ray.direction());
        Ray localRay = new Ray(newOrigin, newDirection, ray.tMin(), ray.tMax());

        Hit closestHit = null;

        for (Shape s : children) {
            Hit hit = s.intersect(localRay);
            if (hit != null && hit.isHit() && (closestHit == null || hit.t() < closestHit.t())) {
                closestHit = hit;
            }
        }

        if (closestHit == null) {
            return null;
        }

        Vec3 worldPoint = transform.mul(closestHit.point());
        Vec3 worldNormal = normalize(transform.invertRigid().transpose().mulDirection(closestHit.normal()));

        return new Hit(
            closestHit.t(),
            worldPoint,
            worldNormal,
            closestHit.material(),
            closestHit.uv()
        );
    }
}


