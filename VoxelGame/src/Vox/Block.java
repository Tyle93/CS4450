package Vox;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.*;

import java.nio.FloatBuffer;

/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Block.java
 */
public class Block {
    private double rotation = 0;
    private float xPos = 0;
    private float yPos = 0;
    private float zPos = 0;
    private QuadFace[] faces;
    private BlockType type;
    public  boolean isActive = false;
    public Block(float xPos, float yPos, float zPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        setBlockType();
        generateFaces();
    }
    public float[] getVertData(){
        float[] retVal = new float[faces.length  * 12];
        for(int i = 0; i < faces.length; i++){
            float[] temp = faces[i].getFloatArray();
            for(int j = 0; j < temp.length; j++){
                System.out.println(i * temp.length + j);
                retVal[i * temp.length + j] =  temp[j];
            }
        }
        return retVal;
    }
    private void generateFaces(){
        Vector3f[] v = {
                new Vector3f(xPos-.5f,yPos-.5f,zPos+.5f), //[0] Front,Bottom,Left
                new Vector3f(xPos+.5f,yPos-.5f,zPos+.5f), //[1] Front,Bottom,Right
                new Vector3f(xPos+.5f,yPos+.5f,zPos+.5f), //[2] Front,Top,Right,
                new Vector3f(xPos-.5f,yPos+.5f,zPos+.5f), //[3] Front,Top,Left
                new Vector3f(xPos-.5f,yPos-.5f,zPos-.5f), //[4] Back,Bottom,Left
                new Vector3f(xPos+.5f,yPos-.5f,zPos-.5f), //[5] Back,Bottom,Right
                new Vector3f(xPos+.5f,yPos+.5f,zPos-.5f), //[6] Back,Top,Right,
                new Vector3f(xPos-.5f,yPos+.5f,zPos-.5f)  //[7] Back,Top,Left
        };
        Texture[] tex = ResourceManager.getBlockTexture(type);
        QuadFace[] faces = {
                new QuadFace(v[0],v[1],v[2],v[3], tex[0]), // Front face
                new QuadFace(v[1],v[5],v[6],v[2], tex[0]), // Right Face
                new QuadFace(v[4],v[5],v[6],v[7], tex[0]), // Back Face
                new QuadFace(v[0],v[4],v[7],v[3], tex[0]), // Left Face
                new QuadFace(v[0],v[4],v[5],v[1], tex[2]), // Bottom Face
                new QuadFace(v[7],v[6],v[2],v[3], tex[1])  // Top Face
        };
        this.faces = faces;
    }
    public void draw(){
            Vector3f[] vertices;
            Color.white.bind();
            faces[0].getTexture().bind();
            GL11.glBegin(GL11.GL_QUADS);

            vertices = faces[0].getVertices();
            GL11.glNormal3d(0,0,1);
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
    public void setBlockType(){
        if(yPos >= 0){
            if(yPos >= 24) {
                type = BlockType.BLOCK_TYPE_STONE;
            }else if(yPos > 10){
                type = BlockType.BLOCK_TYPE_GRASS;
            }
            else{
                type = BlockType.BLOCK_TYPE_DIRT;
            }
        }else if(yPos < 0 ){
            if(yPos > -16){
                type = BlockType.BLOCK_TYPE_DIRT;
            }else if(yPos > -48){
                type = BlockType.BLOCK_TYPE_STONE;
            }
            else{
                type = BlockType.BLOCK_TYPE_BEDROCK;
            }
        }
    }
    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public float getzPos() {
        return zPos;
    }
}
