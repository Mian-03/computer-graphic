package cgg;

import tools.Vec2;
import tools.Vec3;
import tools.Color;
import java.util.ArrayList;
import java.util.List;
import static tools.Functions.*;

public class A02 {
    public static void main(String[] args) {
        int w = 400, h = 400;
        SimpleCamera cam = new SimpleCamera(60, w, h);
        Image img = new Image(w, h);

        List<Sphere> scene = new ArrayList<>();
        scene.add(new Sphere(new Vec3( 0,   0, -5), 1.0, new SimpleMaterial(new Color(1,0,0))));
        scene.add(new Sphere(new Vec3( 2,   0, -6), 1.2, new SimpleMaterial(new Color(0,1,0))));

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Ray ray = cam.shootRay(new Vec2(x + 0.5, y + 0.5));
                Color c = getColor(ray, scene);
                img.setPixel(x, y, c);
            }
        }
        img.writePng("a02");
    }

    static Color getColor(Ray ray, List<Sphere> scene) {
        Hit closest = null;
        for (Sphere s : scene) {
            Hit hit = s.intersect(ray);
            if (hit != null && (closest == null || hit.t() < closest.t())) {
                closest = hit;
            }
        }
        return (closest != null) ? shade(closest)
                                 : new Color(0.2, 0.3, 0.4);
    }

    static Color shade(Hit hit) {
        Vec3 lightDir = normalize(new Vec3(1, 1, 0.7));
        Color baseColor = hit.material().baseColor(hit.uv());
        Color ambient = multiply(0.1, baseColor);
        double diff = Math.max(0, dot(hit.normal(), lightDir));
        Color diffuse = multiply(0.9 * diff, baseColor);
        return add(ambient, diffuse);
    }
}




