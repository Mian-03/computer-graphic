// File: src/cgg/DirectionalLight.java
package cgg;

import tools.Color;
import tools.Vec3;
import static tools.Functions.normalize;

/**
 * Repräsentiert eine unendlich entfernte, richtungsgebundene Lichtquelle.
 */
public class DirectionalLight implements Light {
    private final Vec3 dir;
    private final Color color;

    public DirectionalLight(Vec3 direction, Color color) {
        this.dir   = normalize(direction);
        this.color = color;
    }

    @Override
    public Color intensity() {
        return color;
    }

    @Override
    public Vec3 direction(Hit hit) {
        return dir;
    }

    @Override
    public double distance(Hit hit) {
        return Double.POSITIVE_INFINITY;
    }
}

