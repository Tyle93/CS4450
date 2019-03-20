package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: QuadFace.java
 */

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.util.vector.*;

public class QuadFace{
    Texture texture;
    Color color;
    Vector3f[] vertices;
    public  QuadFace(Vector3f[] vertices, String texturePath){
        this.vertices = vertices;
        try{
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream("texturePath"));
        }catch (IOException e){
            texture = null;
        }
    }
    public QuadFace(Vector3f a, Vector3f b, Vector3f c, Vector3f d, Color color){
        vertices = new Vector3f[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
        this.color = color;
        texture = null;
    }
    public QuadFace(Vector3f a, Vector3f b, Vector3f c, Vector3f d, String texturePath){
        vertices = new Vector3f[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
        try{
            this.texture = TextureLoader.getTexture("PNG", new FileInputStream("texturePath"));
        }catch (IOException e){
            texture = null;
        }
    }
    public void draw(){
            GL11.glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255);
            GL11.glBegin(GL11.GL_QUADS);
            //org.newdawn.slick.Color.white.bind();
            //this.texture.bind();
            for(Vector3f v: vertices){
                GL11.glVertex3d(v.x,v.y,v.z);
            }
            GL11.glEnd();
    }
    public static QuadFace[] generateFaces(float xPos, float yPos, float zPos){
        Vector3f[] v = {
                new Vector3f(xPos-.5f,yPos-.5f,zPos+.5f),
                new Vector3f(xPos+.5f,yPos-.5f,zPos+.5f),
                new Vector3f(xPos+.5f,yPos+.5f,zPos+.5f),
                new Vector3f(xPos-.5f,yPos+.5f,zPos+.5f),
                new Vector3f(xPos-.5f,yPos-.5f,zPos-.5f),
                new Vector3f(xPos+.5f,yPos-.5f,zPos-.5f),
                new Vector3f(xPos+.5f,yPos+.5f,zPos-.5f),
                new Vector3f(xPos-.5f,yPos+.5f,zPos-.5f)
        };
        QuadFace[] faces = {
                new QuadFace(v[0],v[1],v[2],v[3], new Color(0,0,255)),
                new QuadFace(v[0],v[4],v[5],v[1], new Color(0,255,0)),
                new QuadFace(v[4],v[5],v[6],v[7], new Color(255,0,0)),
                new QuadFace(v[7],v[6],v[2],v[3], new Color(255,0,255)),
                new QuadFace(v[0],v[4],v[7],v[3], new Color(0,255,255)),
                new QuadFace(v[1],v[5],v[6],v[2], new Color(255,255,0))
        };
        return faces;
    }
}
