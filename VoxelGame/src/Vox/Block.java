package Vox;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
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
    private BlockType type;
    public  boolean isActive = false;
    public Block(float xPos, float yPos, float zPos){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        setBlockType();
        generateFaces();
    }
    private void generateFaces(){
        faces = QuadFace.generateFaces(xPos,yPos,zPos,type);
    }
    public void draw(){
        QuadFace.draw(faces);
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
