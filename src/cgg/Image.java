package cgg;

import tools.Color;
import tools.ImageWriter;

public class Image implements tools.Image {

    private double[] data;
    private int width, height;

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

    public int width() { return width; }

    public int height() { return height; }
}








