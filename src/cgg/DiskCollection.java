package cgg;

//import java.util.ArrayList;
import java.util.List;
import tools.Vec2;
import tools.Color;
import tools.Sampler;


public class DiskCollection implements Sampler {
    private List<Disk> disks;

    public DiskCollection(List<Disk> disks) {
        this.disks = disks;
    }

    @Override
    public Color getColor(Vec2 point) {
        for (int i = disks.size() - 1; i >= 0; i--) {
            Disk d = disks.get(i);
            if (d.coversPoint(point)) {
                return d.getColor();
            }
        }
        return new Color(0.0, 0.0, 0.0); 
 
    }
}
