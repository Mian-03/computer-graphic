package cgg;

import java.util.List;
import tools.*;
import static tools.Functions.*;


public class A06 {
    public static void main(String[] args) {
        int w = 400, h = 400;

        //  Kamera
        SimpleCamera cam = new SimpleCamera(70, w, h);
        cam.setTransform(
            Mat4.translation(0, 3, 10)
                .mul(Mat4.rotateX(-0.4))
        );

        // 💡 Lichtquellen 
        List<Light> lights = List.of(
            new DirectionalLight(new Vec3(-1, -1, -1), new Color(0.4, 0.4, 0.4)), // weniger intensiv
            new PointLight(new Vec3(4, 5, 5), new Color(0.7, 0.7, 0.7)) // sanfter
        );

        // Farbpalette
        var softPink   = new SimpleMaterial(new Color(1.0, 0.75, 0.8));
        var mint       = new SimpleMaterial(new Color(0.7, 1.0, 0.8));
        var softBlue   = new SimpleMaterial(new Color(0.6, 0.8, 1.0));
        var peach      = new SimpleMaterial(new Color(1.0, 0.85, 0.65));
        var lavender   = new SimpleMaterial(new Color(0.85, 0.7, 1.0));

        List<SimpleMaterial> colors = List.of(softPink, mint, softBlue, peach, lavender);

        // Szene
        Group scene = new Group();

        // Boden 
        var ground = new SimpleMaterial(new Color(1.0, 1.0, 0.6));
        scene.add(new Sphere(new Vec3(0, -1001, 0), 1000, ground));

        // Hintergrund
        var skyBlue = new SimpleMaterial(new Color(0.5, 0.7, 1));
        scene.add(new Sphere(new Vec3(0, 500, -1000), 500, skyBlue));

        // Bunte Schneemänner
        int rows = 5;
        int cols = 5;
        for (int row = 0; row < rows; row++) {
            for (int i = 0; i < cols; i++) {
                double x = i * 1.5 - 3;
                double z = row * 2 - 6;
                SimpleMaterial mat = colors.get(i % colors.size());
                scene.add(snowman(new Vec3(x, 0, z), mat));
            }
        }

        // Bild rendern
        Image img = new Image(w, h);
        img.sample(new RayTracer(cam, lights, scene), 1);
        img.writePng("a06");
    }

    public static Group snowman(Vec3 pos, SimpleMaterial mat) {
        Group g = new Group();

        Vec3 bottom = add(pos, new Vec3(0, 0.5, 0));
        Vec3 top = add(pos, new Vec3(0, 1.2, 0));

        // Körper
        g.add(new Sphere(bottom, 0.5, mat));
        g.add(new Sphere(top, 0.3, mat));

        // Details
        var blackMat = new SimpleMaterial(new Color(0.1, 0.1, 0.1));
        var hatMat = new SimpleMaterial(new Color(0.1, 0.1, 0.1));

        // Augen
        Vec3 eyeOffset = new Vec3(0.08, 0.05, -0.28);
        g.add(new Sphere(add(top, eyeOffset), 0.03, blackMat));
        g.add(new Sphere(add(top, new Vec3(-eyeOffset.x(), eyeOffset.y(), eyeOffset.z())), 0.03, blackMat));

        // Knöpfe
        g.add(new Sphere(add(bottom, new Vec3(0, 0.15, -0.48)), 0.03, blackMat));
        g.add(new Sphere(add(bottom, new Vec3(0, 0.00, -0.50)), 0.03, blackMat));
        g.add(new Sphere(add(bottom, new Vec3(0, -0.15, -0.48)), 0.03, blackMat));

        // Hut
        g.add(new Sphere(add(top, new Vec3(0, 0.22, 0)), 0.12, hatMat));
        g.add(new Sphere(add(top, new Vec3(0, 0.32, 0)), 0.08, hatMat));

        return g;
    }
}


