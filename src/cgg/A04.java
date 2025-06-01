package cgg;

import tools.*;
import java.util.*;
import static tools.Functions.*;

public class A04 {
    public static void main(String[] args) {
        int w = 400, h = 400;
        SimpleCamera cam = new SimpleCamera(60, w, h);
        Image img = new Image(w, h);

        // Checkerboard-Farben definieren (Cyan und Magenta)
        Sampler checker = new Checkerboard(
            new Color(0.0, 1.0, 1.0),   // Cyan
            new Color(1.0, 0.0, 1.0),   // Magenta
            10.0                        // Dichte des Musters
        );

        Sampler white = new ConstantColor(new Color(1.0, 1.0, 1.0));

        // Texturiertes Material mit Checkerboard
        Material texMat = new TexturedMaterial(
            multiplySampler(0.1, checker),   // ka
            multiplySampler(0.6, checker),   // kd
            multiplySampler(0.3, white),     // ks
            checker,                         // baseColor
            100                              // shininess
        );

        // Szene mit texturierter Kugel und Boden
        List<Sphere> scene = List.of(
            new Sphere(new Vec3(0, 0, -5), 1.0, texMat),  // Kugel
            new Sphere(new Vec3(0, -1001, -5), 1000, texMat) // Boden
        );

        // Lichtquellen
        List<Light> lights = List.of(
            new DirectionalLight(new Vec3(1, 1, 1), new Color(1.0, 0.9, 0.8)),
            new PointLight(new Vec3(2, 2, -3), new Color(1.0, 0.0, 0.6))
        );

        // Rendering
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Ray ray = cam.shootRay(new Vec2(x + 0.5, y + 0.5));
                Color c = getColor(ray, scene, lights);
                img.setPixel(x, y, c);
            }
        }

        img.writePng("a04");
    }

    static Color getColor(Ray ray, List<Sphere> scene, List<Light> lights) {
        Hit closest = null;
        for (Sphere s : scene) {
            Hit hit = s.intersect(ray);
            if (hit != null && (closest == null || hit.t() < closest.t())) {
                closest = hit;
            }
        }
        return (closest != null)
            ? shade(closest, scene, lights, ray)
            : new Color(0.6, 0.8, 1.0); // Hintergrundfarbe
    }

    static Color shade(Hit hit, List<Sphere> scene, List<Light> lights, Ray ray) {
        Vec3 N = hit.normal();
        Vec3 V = normalize(multiply(-1, ray.direction()));

        Vec2 uv = hit.uv();
        Material m = hit.material();
        Color ka = m.ka(uv);
        Color kd = m.kd(uv);
        Color ks = m.ks(uv);
        Color base = m.baseColor(uv);
        double ke = m.shininess();

        Color result = ka;

        for (Light light : lights) {
            Vec3 L = light.direction(hit);
            Color Ii = light.intensity();
            Ray shadow = new Ray(hit.point(), L, 1e-4, light.distance(hit));
            boolean inShadow = scene.stream().anyMatch(s -> s.intersect(shadow) != null);
            if (inShadow) continue;

            double nDotL = Math.max(0, dot(N, L));
            Vec3 R = reflect(N, multiply(-1, L));
            double rDotV = Math.max(0, dot(R, V));

            Color diff = multiply(nDotL, multiply(kd, Ii));
            Color spec = multiply(Math.pow(rDotV, ke), multiply(ks, Ii));

            result = add(result, diff);
            result = add(result, spec);
        }

        return clamp(result);
    }

    // Hilfsmethode zur Skalierung von Samplern
    static Sampler multiplySampler(double f, Sampler s) {
        return new Sampler() {
            public Color getColor(Vec2 point) {
                return multiply(f, s.getColor(point));
            }
        };
    }
}
