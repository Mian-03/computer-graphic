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

        List<Disk> disks = new ArrayList<>();
        Random rng = new Random();

        int numDisks = 10;
int maxTries = 1000;

while (disks.size() < numDisks && maxTries-- > 0) {
    double radius = 30 + rng.nextDouble() * 50;
    double x = rng.nextDouble() * (width - 2 * radius) + radius;
    double y = rng.nextDouble() * (height - 2 * radius) + radius;
    Vec2 pos = new Vec2(x, y);
    Color color = new Color(rng.nextDouble(), rng.nextDouble(), rng.nextDouble());

    boolean overlaps = false;
    for (Disk other : disks) {
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
        disks.add(new Disk(pos, radius, color));
    }
}
        DiskCollection diskCollection = new DiskCollection(disks);
        Image image = new Image(width, height);

        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Vec2 point = new Vec2(x, y);
                image.setPixel(x, y, diskCollection.getColor(point));
            }
        }

        // Speichern PNG
        image.writePng("images/a01.png");

    }
}


