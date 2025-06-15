package cgg;

import tools.Vec3;

public class Mat4 {
    private final double[][] m;

    public Mat4() {
        m = new double[4][4];
        for (int i = 0; i < 4; i++) {
            m[i][i] = 1.0;
        }
    }

    public Mat4(double[][] m) {
        this.m = m;
    }

    public static Mat4 identity() {
        return new Mat4();
    }

    public static Mat4 translation(double x, double y, double z) {
        Mat4 result = identity();
        result.m[0][3] = x;
        result.m[1][3] = y;
        result.m[2][3] = z;
        return result;
    }

    public static Mat4 rotateX(double angle) {
    double cos = Math.cos(angle);
    double sin = Math.sin(angle);
    return new Mat4(new double[][] {
        { 1, 0,    0,   0 },
        { 0, cos, -sin, 0 },
        { 0, sin,  cos, 0 },
        { 0, 0,    0,   1 }
    });
}
    public static Mat4 rotateY(double angle) {
        double cos = Math.cos(angle);
        double sin = Math.sin(angle);
        return new Mat4(new double[][] {
            { cos, 0, sin, 0 },
            { 0,   1, 0,   0 },
            { -sin,0, cos, 0 },
            { 0,   0, 0,   1 }
        });
    }

    public Vec3 transform(Vec3 v) {
        return mul(v);
    }

    public Vec3 transformDirection(Vec3 v) {
        return mulDirection(v);
    }

    public Vec3 mul(Vec3 v) {
        double[] r = new double[4];
        double[] vec = {v.x(), v.y(), v.z(), 1.0};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                r[i] += m[i][j] * vec[j];
            }
        }
        return new Vec3(r[0], r[1], r[2]);
    }

    public Vec3 mulDirection(Vec3 v) {
        double[] r = new double[4];
        double[] vec = {v.x(), v.y(), v.z(), 0.0};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                r[i] += m[i][j] * vec[j];
            }
        }
        return new Vec3(r[0], r[1], r[2]);
    }

    public Mat4 mul(Mat4 other) {
        Mat4 result = new Mat4();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                result.m[i][j] = 0;
                for (int k = 0; k < 4; k++) {
                    result.m[i][j] += this.m[i][k] * other.m[k][j];
                }
            }
        }
        return result;
    }

    public Mat4 invertRigid() {
    // Nur gültig für Rotation + Translation!
    Mat4 inv = new Mat4();

    // Transponiere obere linke 3x3 (Rotation)
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
            inv.m[i][j] = this.m[j][i];
        }
    }

    // Berechne -R^T * T (Translation)
    double x = -this.m[0][3];
    double y = -this.m[1][3];
    double z = -this.m[2][3];
    Vec3 trans = inv.transform(new Vec3(x, y, z));
    inv.m[0][3] = trans.x();
    inv.m[1][3] = trans.y();
    inv.m[2][3] = trans.z();

    return inv;
}

public Mat4 transpose() {
    double[][] t = new double[4][4];
    for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 4; j++) {
            t[i][j] = m[j][i];
        }
    }
    return new Mat4(t);
}


}




