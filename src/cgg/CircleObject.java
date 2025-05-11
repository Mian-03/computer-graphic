package cgg;

import tools.Color;
import tools.Vec2;

public class CircleObject {
    private Vec2 pos;
    private double rad;
    private Color col;

    public CircleObject(Vec2 pos, double rad, Color col) {
        this.pos = pos;
        this.rad = rad;
        this.col = col;
    }

    public boolean coversPoint(Vec2 p) {
        double dx = p.x() - pos.x(), dy = p.y() - pos.y();
        return dx * dx + dy * dy <= rad * rad;
    }

    public Color getColor() {
        return col;
    }

    public Vec2 getCenter() {
        return pos;
    }

    public double getRadius() {
        return rad;
    }
}
