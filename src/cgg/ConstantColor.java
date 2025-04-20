
package cgg;

import tools.*;

public class ConstantColor implements Sampler {
    private final Color color;
    public ConstantColor(Color color) {
        this.color = color;
    }

    public Color getColor(Vec2 point) {
        return color;
    }
}
