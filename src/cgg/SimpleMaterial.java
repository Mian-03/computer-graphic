
package cgg;

import tools.Color;
import tools.Vec2;
import static tools.Functions.multiply;

public class SimpleMaterial implements Material {
    private final Color color;

    public SimpleMaterial(Color color) {
        this.color = color;
    }

    @Override
    public Color baseColor(Vec2 uv) {
        return color;
    }

    @Override
    public Color ka(Vec2 uv) {
        return multiply(0.1, color);
    }

    @Override
    public Color kd(Vec2 uv) {
        return multiply(0.6, color);
    }

    @Override
    public Color ks(Vec2 uv) {
        return multiply(0.3, new Color(1, 1, 1));
    }

    @Override
    public double shininess() {
        return 100;
    }
}

