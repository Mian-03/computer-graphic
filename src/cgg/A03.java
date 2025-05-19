package cgg;

import tools.Vec2;
import tools.Vec3;
import tools.Color;
import java.util.List;
import java.util.ArrayList;
import static tools.Functions.*;

public class A03 {
    public static void main(String[] args) {
        int w = 400, h = 400;
        SimpleCamera cam = new SimpleCamera(60, w, h);
        Image img = new Image(w, h);

        List<Sphere> scene = new ArrayList<>();
        scene.add(new Sphere(new Vec3( 0,  0, -5), 1.0, new SimpleMaterial(new Color(0.2, 1.0, 1.0))));
        scene.add(new Sphere(new Vec3( 2,  0, -6), 1.2, new SimpleMaterial(new Color(1.0, 0.5, 0.0))));
        scene.add(new Sphere(new Vec3( 0, -1001, -5), 1000, new SimpleMaterial(new Color(0.8, 0.8, 0.8))));

        List<Light> lights = new ArrayList<>();
        lights.add(new DirectionalLight(new Vec3( 1,  1,  0.5), new Color(1.0, 0.9, 0.8))); 
        lights.add(new PointLight      (new Vec3(-3,  2, -3), new Color(0.0, 1.0, 0.6)));   
        lights.add(new PointLight      (new Vec3( 3,  2, -3), new Color(1.0, 0.0, 0.8)));   

        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                Ray ray = cam.generateRay(new Vec2(x + 0.5, y + 0.5));
                Color c = getColor(ray, scene, lights);
                img.setPixel(x, y, c);
            }
        }

        img.writePng("a03");
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
            : new Color(0.9, 0.95, 0.1);
    }

    static Color shade(Hit hit, List<Sphere> scene, List<Light> lights, Ray ray) {
        Color baseColor = hit.material().baseColor(hit.uv());

        Color ka = multiply(0.1, baseColor);
        Color kd = multiply(0.6, baseColor);
        Color ks = multiply(0.3, new Color(1,1,1));
        double ke = 100;

        Color result = ka;

        Vec3 N = hit.normal();
        Vec3 V = normalize(multiply(-1, ray.direction()));

        for (Light L : lights) {
            Vec3   Li = L.direction(hit);
            Color Ii = L.intensity();
            Ray shadow = new Ray(hit.point(), Li, 1e-4, L.distance(hit));
            boolean inShadow = false;
            for (Sphere s : scene) {
                if (s.intersect(shadow) != null) { inShadow = true; break; }
            }
            if (inShadow) continue;

            double nDotL = Math.max(0, dot(N, Li));
            Color diff  = multiply(nDotL, multiply(kd, Ii));
            Vec3 R = reflect(N, multiply(-1, Li));
            double rDotV = Math.max(0, dot(R, V));
            Color spec = multiply(Math.pow(rDotV, ke), multiply(ks, Ii));

            result = add(result, diff);
            result = add(result, spec);
        }

        return clamp(result);
    }
}




