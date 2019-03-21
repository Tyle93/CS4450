package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: QuadFace.java
 */

import org.lwjgl.opengl.GL11;
//import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.*;
import java.io.FileInputStream;
import java.io.IOException;
import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;

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
    public QuadFace(Vector3f a, Vector3f b, Vector3f c, Vector3f d, Texture texture){
        vertices = new Vector3f[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
        this.texture = texture;
        this.color = new Color(255,0,255);
    }
    public void draw(){
        GL11.glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255);
        GL11.glBegin(GL11.GL_QUADS);
        //org.newdawn.slick.Color.white.bind();
        this.texture.bind();
        for(Vector3f v: vertices){
            GL11.glVertex3d(v.x,v.y,v.z);
        }
        GL11.glEnd();
    }

    public Texture getTexture() {
        return texture;
    }

    public static void draw(QuadFace[] faces){
            //GL11.glColor3f(255,0,255);

            Vector3f[] vectices;

            Color.white.bind();
            faces[0].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vectices = faces[0].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vectices[0].x,vectices[0].y,vectices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vectices[1].x,vectices[1].y,vectices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vectices[2].x,vectices[2].y,vectices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vectices[3].x,vectices[3].y,vectices[3].z);

            GL11.glEnd();
            Color.white.bind();
            faces[1].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vectices = faces[1].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vectices[0].x,vectices[0].y,vectices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vectices[1].x,vectices[1].y,vectices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vectices[2].x,vectices[2].y,vectices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vectices[3].x,vectices[3].y,vectices[3].z);

            GL11.glEnd();
        Color.white.bind();
            faces[2].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vectices = faces[2].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vectices[0].x,vectices[0].y,vectices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vectices[1].x,vectices[1].y,vectices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vectices[2].x,vectices[2].y,vectices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vectices[3].x,vectices[3].y,vectices[3].z);

            GL11.glEnd();
        Color.white.bind();
            faces[3].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vectices = faces[3].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vectices[0].x,vectices[0].y,vectices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vectices[1].x,vectices[1].y,vectices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vectices[2].x,vectices[2].y,vectices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vectices[3].x,vectices[3].y,vectices[3].z);

            GL11.glEnd();
        Color.white.bind();
            faces[4].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vectices = faces[4].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vectices[0].x,vectices[0].y,vectices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vectices[1].x,vectices[1].y,vectices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vectices[2].x,vectices[2].y,vectices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vectices[3].x,vectices[3].y,vectices[3].z);

            GL11.glEnd();
        Color.white.bind();
            faces[5].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vectices = faces[5].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vectices[0].x,vectices[0].y,vectices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vectices[1].x,vectices[1].y,vectices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vectices[2].x,vectices[2].y,vectices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vectices[3].x,vectices[3].y,vectices[3].z);

            GL11.glEnd();
    }
    public static QuadFace[] generateFaces(float xPos, float yPos, float zPos, BlockType type){
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
        /*
        Color color;
        switch (type){
            case BLOCK_TYPE_BEDROCK: color = new Color(169,169,169);
                break;
            case BLOCK_TYPE_DIRT: color = new Color(222,184,135);
                break;
            case BLOCK_TYPE_SAND: color = new Color(255,248,220);
                break;
            case BLOCK_TYPE_GRASS: color = new Color(34,139,34);
                break;
            case BLOCK_TYPE_STONE: color = new Color(127,127,127);
                break;
            case BLOCK_TYPE_WATER: color = new Color(135,206,250);
                break;
                default: color = new Color(255,0,0);
        }
        */
        Texture tex = null;
        tex = ResourceManager.getBlockTexture(type);
        QuadFace[] faces = {
                new QuadFace(v[0],v[1],v[2],v[3], tex),
                new QuadFace(v[0],v[4],v[5],v[1], tex),
                new QuadFace(v[4],v[5],v[6],v[7], tex),
                new QuadFace(v[7],v[6],v[2],v[3], tex),
                new QuadFace(v[0],v[4],v[7],v[3], tex),
                new QuadFace(v[1],v[5],v[6],v[2], tex)
        };
        return faces;
    }

    public Vector3f[] getVertices() {
        return vertices;
    }
}
