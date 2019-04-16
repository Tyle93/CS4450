package Vox;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #2
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/27/2019
    File Name: QuadFace.java
    Purpose: Simple container for each of the 4 vertices of a Blocks face.
 */

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;

public class QuadFace{
    Vector3f[] vertices;
    public QuadFace(Vector3f a, Vector3f b, Vector3f c, Vector3f d){
        vertices = new Vector3f[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
    }
    // Method: getVertices()
    // Purpose: returns the array of vertex coordinates.
    public Vector3f[] getVertices() {
        return vertices;
    }
    // Method: getFloatArray
    // Purpose: returns the x,y,z positions of all vertices in a float array.
    public float[] getFloatArray(){
        float[] array = new float[12];
        for(int i = 0; i < vertices.length; i++){
            array[i*3] = vertices[i].x;
            array[i*3 + 1] = vertices[i].y;
            array[i*3 + 2] = vertices[i].z;
        }
        return  array;
    }
}
