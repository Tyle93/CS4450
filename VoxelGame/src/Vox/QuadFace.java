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

            Vector3f[] vertices;

            Color.white.bind();
            faces[0].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[0].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vertices[0].x,vertices[0].y,vertices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vertices[1].x,vertices[1].y,vertices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vertices[2].x,vertices[2].y,vertices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vertices[3].x,vertices[3].y,vertices[3].z);

            GL11.glEnd();
            Color.white.bind();
            faces[1].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[1].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vertices[0].x,vertices[0].y,vertices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vertices[1].x,vertices[1].y,vertices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vertices[2].x,vertices[2].y,vertices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vertices[3].x,vertices[3].y,vertices[3].z);

            GL11.glEnd();
            Color.white.bind();
            faces[2].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[2].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vertices[0].x,vertices[0].y,vertices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vertices[1].x,vertices[1].y,vertices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vertices[2].x,vertices[2].y,vertices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vertices[3].x,vertices[3].y,vertices[3].z);

            GL11.glEnd();
            Color.white.bind();
            faces[3].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[3].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vertices[0].x,vertices[0].y,vertices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vertices[1].x,vertices[1].y,vertices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vertices[2].x,vertices[2].y,vertices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vertices[3].x,vertices[3].y,vertices[3].z);

            GL11.glEnd();
            Color.white.bind();
            faces[4].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[4].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vertices[0].x,vertices[0].y,vertices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vertices[1].x,vertices[1].y,vertices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vertices[2].x,vertices[2].y,vertices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vertices[3].x,vertices[3].y,vertices[3].z);

            GL11.glEnd();
            Color.white.bind();
            faces[5].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[5].getVertices();
            GL11.glTexCoord2f(0,0);
            GL11.glVertex3d(vertices[0].x,vertices[0].y,vertices[0].z);
            GL11.glTexCoord2f(1,0);
            GL11.glVertex3d(vertices[1].x,vertices[1].y,vertices[1].z);
            GL11.glTexCoord2f(1,1);
            GL11.glVertex3d(vertices[2].x,vertices[2].y,vertices[2].z);
            GL11.glTexCoord2f(0,1);
            GL11.glVertex3d(vertices[3].x,vertices[3].y,vertices[3].z);

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
        Texture[] tex = ResourceManager.getBlockTexture(type);
        QuadFace[] faces = {
                new QuadFace(v[0],v[1],v[2],v[3], tex[0]),
                new QuadFace(v[0],v[4],v[5],v[1], tex[2]),
                new QuadFace(v[4],v[5],v[6],v[7], tex[0]),
                new QuadFace(v[7],v[6],v[2],v[3], tex[1]),
                new QuadFace(v[0],v[4],v[7],v[3], tex[0]),
                new QuadFace(v[1],v[5],v[6],v[2], tex[0])
        };
        return faces;
    }
    public Vector3f[] getVertices() {
        return vertices;
    }
}
