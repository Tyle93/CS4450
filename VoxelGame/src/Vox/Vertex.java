package Vox;

public class Vertex {
    double x;
    double y;
    double z;

    public void applyScale(){
        x = x*Engine.scale;
        y = y*Engine.scale;
        z = z*Engine.scale;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Vertex(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        applyScale();
    }

}
