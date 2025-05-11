package cgg;

import java.util.List;
import tools.*;

public class CircleLayer implements Sampler {
    private List<CircleObject> elements;

    public CircleLayer(List<CircleObject> elements) {
        this.elements = elements;
    }

    @Override
    public Color getColor(Vec2 p) {
        for (int i = elements.size() - 1; i >= 0; i--) {
            if (elements.get(i).coversPoint(p)) {
                return elements.get(i).getColor();
            }
        }
        return new Color(0, 0, 0);
    }
}
