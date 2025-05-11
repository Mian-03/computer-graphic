package cgg;

import tools.Vec2;
import tools.Vec3;

import java.util.List;

public class DiskCollection {
    private final List<Disk> disks;

    public DiskCollection(List<Disk> disks) {
        this.disks = disks;
    }

    public Vec3 getColor(Vec2 p) {
        for (Disk disk : disks) {
            if (disk.contains(p)) {
                return disk.getColor();
            }
        }
        return new Vec3(1, 1, 1); 
    }
}

