package cgg;

import tools.*;
import java.util.List;

public class A05 {
    public static void main(String[] args) {
        int w = 400, h = 400;
        Camera cam = new SimpleCamera(60, w, h);

        // Lichtquellen
        List<Light> lights = List.of(
            new DirectionalLight(new Vec3(1, 1, 1), new Color(1.0, 0.9, 0.8)),
            new PointLight(new Vec3(2, 2, -3), new Color(1.0, 0.0, 0.6))
        );

        // Checkerboard-Material für Boden & Kugel
        Material checkerMat = new TexturedMaterial(
            new Checkerboard(new Color(1, 1, 1), new Color(0, 0, 0), 10),
            new Checkerboard(new Color(1, 1, 1), new Color(0, 0, 0), 10),
            new ConstantColor(new Color(1.0, 1.0, 1.0)),
            new ConstantColor(new Color(1.0, 1.0, 1.0)),
            100
        );

        // Szene mit Hauptkugel, Boden und zusätzlichen Kugeln im Hintergrund
        ShapeGroup scene = new ShapeGroup(List.of(
            // Hauptkugel mit Checkerboard
            new Sphere(new Vec3(0, 0, -5), 1.0, checkerMat),

            // Boden als riesige Kugel unter der Szene (auch Checkerboard)
            new Sphere(new Vec3(0, -1001, -5), 1000, checkerMat),

            // Zusätzliche Kugeln zur Erzeugung von Schattenmustern
            new Sphere(new Vec3(1.5, -0.5, -4), 0.3, new SimpleMaterial(new Color(0.9, 0.9, 0.9))),
            new Sphere(new Vec3(-1.5, -0.5, -6), 0.3, new SimpleMaterial(new Color(0.2, 0.2, 0.2)))
        ));

        // Bild vorbereiten
        Image img = new Image(w, h);
        int samples = 10; // Supersampling
        img.sample(new RayTracer((SimpleCamera) cam, lights, scene), samples);
        img.writePng("a05"); // Bild speichern
    }
}



