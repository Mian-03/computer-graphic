
package cgg;

import tools.Color;
import tools.Vec2;

public interface Material {
    Color baseColor(Vec2 uv);
    Color ka(Vec2 uv);
    Color kd(Vec2 uv);
    Color ks(Vec2 uv);
    double shininess();
}
