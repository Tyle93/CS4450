package Vox;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.*;

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
    boolean isActive = false;
    public Block(float xPos, float yPos, float zPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        generateFaces();
        //Engine.addObject(this);
    }
    public Block(QuadFace[] faces){
        this.faces = faces;
        //Engine.addObject(this);
    }
    private void generateFaces(){
        faces = QuadFace.generateFaces(xPos,yPos,zPos);
    }
    public void draw(){
        for(QuadFace q : faces){
            q.draw();
        }
        rotation += 1;
        yPos++;
        xPos += .05;
    }
}
