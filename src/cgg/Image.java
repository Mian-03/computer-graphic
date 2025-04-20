
package cgg;

import tools.*;

public class Image implements tools.Image {

    private double[] data;
    private int width;
    private int height;

    // ---8<--- missing-implementation
    // TODO Provides storage for the image data. For each pixel in the image
    // three double values are needed to store the pixel components.
    public Image(int width, int height) {
        this.data = new double[3*width * height];
        this.width = width;
        this.height = height;
    }

    // TODO Stores the RGB color components for one particular pixel addressed
    // by it's coordinates in the image.
    public void setPixel(int x, int y, Color color) {
       int index = (y * width + x) * 3;
       
       data[index]     = color.r();
       data[index + 1] = color.g();
       data[index + 2] = color.b();
        
    }

    // TODO Retrieves the RGB color components for one particular pixel addressed
    // by it's coordinates in the image.
    public Color getPixel(int x, int y) {
        int index = (y * width + x) * 3;
        return new Color(data[index + 0], data[index +1], data[index + 2]);
    }
    // --->8---

    public void writePng(String name) {
        ImageWriter.writePng(name, data, width, height);

    }

    public void writeHdr(String name) {
        ImageWriter.writeHdr(name, data, width, height);

    }

    public int width() {
        // TODO This is just a dummy value to make the compiler happy. This
        // needs to be adjusted such that the actual width of the Image is
        // returned.
        return width;
    }

    public int height() {
        // TODO This is just a dummy value to make the compiler happy. This
        // needs to be adjusted such that the actual height of the Image is
        // returned.
        return height;
    }
}
