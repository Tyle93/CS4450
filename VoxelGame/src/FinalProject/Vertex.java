package FinalProject;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #1
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/11/2019

    File Name: Vertex.java
    Purpose: Basic vertex class which holds 3 inputs (x,y,z).
 */
import org.lwjgl.opengl.GL11;

public class Vertex {
    private double x;
    private double y;
    private double z;
    // name : applyScale
    // used to scale the size of the cube by a certain static scaling integer.
    public void applyScale(){
        x = x*Engine.scale;
        y = y*Engine.scale;
        z = z*Engine.scale;
    }
    
    // name: getX
    // purpose : returns x
    public double getX() {
        return x;
    }
    // name: getY
    // purpose :  returns y
    public double getY() {
        return y;
    }
    // name: getZ
    // purpose :  returns Z
    public double getZ() {
        return z;
    }
    // name: drawVertex
    // purpose :  draws the vertex
    public void drawVertex(){
        GL11.glVertex4d(x,y,z,1);
    }
    public Vertex(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        applyScale();
    }

}
