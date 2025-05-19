package cgg;

import tools.*;

public class Checkerboard implements Sampler {
    private Color c1, c2;
    private double scale;

    public Checkerboard(Color c1, Color c2, double scale) {
        this.c1 = c1;
        this.c2 = c2;
        this.scale = scale;
    }

    public Color getColor(Vec2 uv) {
        int x = (int)Math.floor(uv.x() * scale);
        int y = (int)Math.floor(uv.y() * scale);
        return (x + y) % 2 == 0 ? c1 : c2;
    }
}

