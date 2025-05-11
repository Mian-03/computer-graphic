// File: src/cgg/PointLight.java
package cgg;

import tools.Color;
import tools.Vec3;
import static tools.Functions.*;

/**
 * Repräsentiert eine punktförmige Lichtquelle.
 */
public class PointLight implements Light {
    private final Vec3 position;
    private final Color color;

    public PointLight(Vec3 position, Color color) {
        this.position = position;
        this.color    = color;
    }

    @Override
    public Color intensity() {
        return color;
    }

    @Override
    public Vec3 direction(Hit hit) {
        return normalize(subtract(position, hit.point()));
    }

    @Override
    public double distance(Hit hit) {
        return length(subtract(position, hit.point()));
    }
}


