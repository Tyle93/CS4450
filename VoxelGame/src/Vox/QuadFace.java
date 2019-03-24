package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: QuadFace.java
 */

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;

public class QuadFace{
    Texture texture;
    Vector3f[] vertices;
    public QuadFace(Vector3f a, Vector3f b, Vector3f c, Vector3f d, Texture texture){
        vertices = new Vector3f[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
        this.texture = texture;
    }
    public Texture getTexture() {
        return texture;
    }
    public static void draw(QuadFace[] faces){

    }
    public Vector3f[] getVertices() {
        return vertices;
    }
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
