package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Vertex.java
 */
import org.lwjgl.opengl.GL11;

public class Vertex {
    private double x;
    private double y;
    private double z;

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

    public void drawVertex(){

    }
    public Vertex(int x, int y, int z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
        applyScale();
    }

}
