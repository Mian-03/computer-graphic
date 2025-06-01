package cgg;

import tools.Color;
import tools.ImageWriter;
import tools.Vec2;
import static tools.Functions.*;

public class Image implements tools.Image {

    private final double[] data;
    private final int width, height;

    public Image(int width, int height) {
        this.width = width;
        this.height = height;
        this.data = new double[3 * width * height];
    }

    public void setPixel(int x, int y, Color c) {
        int i = (y * width + x) * 3;
        data[i] = c.r();
        data[i + 1] = c.g();
        data[i + 2] = c.b();
    }

    public Color getPixel(int x, int y) {
        int i = (y * width + x) * 3;
        return new Color(data[i], data[i + 1], data[i + 2]);
    }

    public void writePng(String filename) {
        ImageWriter.writePng(filename, data, width, height);
    }

    public void writeHdr(String filename) {
        ImageWriter.writeHdr(filename, data, width, height);
    }

    public int width() {
        return width;
    }

    public int height() {
        return height;
    }

    // Supersampling: samplesPerPixel = wie oft ein Pixel zufällig unterteilt wird
    public void sample(Sampler sampler, int samplesPerPixel) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Color sum = Color.black;

                for (int s = 0; s < samplesPerPixel; s++) {
                    double dx = Math.random();
                    double dy = Math.random();
                    double u = x + dx;
                    double v = y + dy;
                    Color c = sampler.getColor(new Vec2(u, v));
                    sum = add(sum, c);
                }

                Color avg = divide(sum, samplesPerPixel);
                setPixel(x, y, avg);
            }
        }
    }
}









