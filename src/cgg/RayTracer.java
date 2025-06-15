package cgg;

import tools.*;
import static tools.Functions.*;

import java.util.List;

public class RayTracer implements Sampler {
    private final SimpleCamera cam;
    private final List<Light> lights;
    private final Shape scene;

    public RayTracer(SimpleCamera cam, List<Light> lights, Shape scene) {
        this.cam = cam;
        this.lights = lights;
        this.scene = scene;
    }

    @Override
    public Color getColor(Vec2 uv) {
        Ray ray = cam.shootRay(uv);
        Hit hit = scene.intersect(ray);
        if (hit == null) {
            return Color.black;
        }

        Color result = Color.black;
        Vec3 point = hit.point();
        Vec3 normal = hit.normal();
        Vec3 viewDir = negate(ray.direction());

        Material mat = hit.material();

        for (Light light : lights) {
            Vec3 lightDir = light.direction(hit);
            double lightDistance = light.distance(hit);

            Ray shadowRay = new Ray(add(point, multiply(1e-4, lightDir)), lightDir, 0.0, lightDistance);
            Hit shadowHit = scene.intersect(shadowRay);
            if (shadowHit != null) continue;

            double diffuseFactor = Math.max(0.0, dot(normal, lightDir));
            Vec3 reflectDir = subtract(multiply(2 * dot(normal, lightDir), normal), lightDir);
            double specularFactor = Math.pow(Math.max(0.0, dot(reflectDir, viewDir)), mat.shininess());

            Color diffuse = multiply(diffuseFactor, multiply(mat.kd(hit.uv()), light.intensity()));
            Color specular = multiply(specularFactor, multiply(mat.ks(hit.uv()), light.intensity()));
            result = add(result, add(diffuse, specular));
        }

        Color base = mat.baseColor(hit.uv());
        return clamp(multiply(result, base));
    }
}




