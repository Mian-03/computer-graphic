package cgg;

import tools.*;

public class ConstantColor implements Sampler {
    private final Color col;

    public ConstantColor(Color col) {
        this.col = col;
    }

    public Color getColor(Vec2 point) {
        return col;
    }
}

