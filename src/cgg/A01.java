package cgg;

import tools.Vec2;
import tools.Color;
//import static java.lang.Math.*;
import java.util.*;
//import tools.ImageWriter;


public class A01 {
    public static void main(String[] args) {
        int width = 400;
        int height = 400;

        List<CircleObject> circleObjects = new ArrayList<>();
        Random rng = new Random();

        int numDisks = 10;
int maxTries = 1000;

while (circleObjects.size() < numDisks && maxTries-- > 0) {
    double radius = 30 + rng.nextDouble() * 50;
    double x = rng.nextDouble() * (width - 2 * radius) + radius;
    double y = rng.nextDouble() * (height - 2 * radius) + radius;
    Vec2 pos = new Vec2(x, y);
    Color color = new Color(rng.nextDouble(), rng.nextDouble(), rng.nextDouble());

    boolean overlaps = false;
    for (CircleObject other : circleObjects) {
        Vec2 otherPos = other.getCenter();
        double dx = pos.x() - otherPos.x();
        double dy = pos.y() - otherPos.y();
        double distSq = dx * dx + dy * dy;
        double minDist = radius + other.getRadius();

        if (distSq < minDist * minDist) {
            overlaps = true;
            break;
        }
    }

    if (!overlaps) {
        circleObjects.add(new CircleObject(pos, radius, color));
    }
}
        CircleLayer circleLayer = new CircleLayer(circleObjects);
        Image image = new Image(width, height);

        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Vec2 point = new Vec2(x, y);
                image.setPixel(x, y, circleLayer.getColor(point));
            }
        }

        // Speichern PNG
        image.writePng("a01");

    }
}
