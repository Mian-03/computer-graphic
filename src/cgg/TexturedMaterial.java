package cgg;

import tools.Vec2;
import tools.Color;

public class TexturedMaterial implements Material {
    private final Sampler ka, kd, ks;
    private final Sampler base;
    private final int shininess;

    public TexturedMaterial(Sampler ka, Sampler kd, Sampler ks, Sampler base, int shininess) {
        this.ka = ka;
        this.kd = kd;
        this.ks = ks;
        this.base = base;
        this.shininess = shininess;
    }

    public Color ka(Vec2 uv)      { return ka.getColor(uv); }
    public Color kd(Vec2 uv)      { return kd.getColor(uv); }
    public Color ks(Vec2 uv)      { return ks.getColor(uv); }
    public Color baseColor(Vec2 uv) { return base.getColor(uv); }
    public double shininess()     { return shininess; }
}
