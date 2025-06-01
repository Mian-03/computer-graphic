package cgg;

import java.util.List;

public class ShapeGroup implements Shape {
    private final List<Shape> shapes;

    public ShapeGroup(List<Shape> shapes) {
        this.shapes = shapes;
    }

    @Override
    public Hit intersect(Ray ray) {
        Hit closestHit = null;

        for (Shape shape : shapes) {
            Hit hit = shape.intersect(ray);
            if (hit != null) {
                if (closestHit == null || hit.t() < closestHit.t()) {
                    closestHit = hit;
                }
            }
        }

        return closestHit;
    }
}

