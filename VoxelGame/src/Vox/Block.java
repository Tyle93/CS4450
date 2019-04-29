package Vox;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.*;

import java.nio.FloatBuffer;

/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #3
    Class: CS 4450 - Computer Graphics
    Last Modified: 04/15/2019
    File Name: Chunk.java
    Purpose: This class holds the 6 Quadface object classes. The main function
    of this class is basically drawing the 6 quads that were created. This class
    has potential to be used in future checkpoints.
 */
public class Block {
    private float xPos = 0;
    private float yPos = 0;
    private float zPos = 0;
    private QuadFace[] faces;
    private float[] verts = null;
    private float[] texCoords = null;
    private BlockType type;
    private Chunk parent;
    private Vector3f index;
    public  boolean isActive = false;
    public Block(float xPos, float yPos, float zPos, Chunk parent, Vector3f index){
        this.xPos = xPos;
        this.yPos = yPos;
        this.zPos = zPos;
        setIndex(index);
        setParent(parent);
        setBlockType();
        generateFaces();
    }

    public void setParent(Chunk parent) {
        this.parent = parent;
    }

    // Method: getVertData
    // Purpose: returns a float array containing the coordinates of all 6 faces vertices.
    public float[] getVertData(){
        if(verts == null){
            float[] retVal = new float[faces.length  * 12];
            for(int i = 0; i < faces.length; i++){
                float[] temp = faces[i].getFloatArray();
                for(int j = 0; j < temp.length; j++){
                    //System.out.println(i * temp.length + j);
                    retVal[i * temp.length + j] =  temp[j];
                }
            }
            verts = retVal;
            return verts;
        }else{
            return verts;
        }
    }

    public void setIndex(Vector3f index) {
        this.index = index;
    }

    // Method:  getTexCoords
    // Purpose: returns the texture mapping coordinates for this block.
    public float[] getTexCoords(){
        return texCoords;
    }
    private void generateFaces() {
        Vector3f[] v = {
                new Vector3f(xPos - .5f, yPos - .5f, zPos + .5f), //[0] Front,Bottom,Left
                new Vector3f(xPos + .5f, yPos - .5f, zPos + .5f), //[1] Front,Bottom,Right
                new Vector3f(xPos + .5f, yPos + .5f, zPos + .5f), //[2] Front,Top,Right,
                new Vector3f(xPos - .5f, yPos + .5f, zPos + .5f), //[3] Front,Top,Left
                new Vector3f(xPos - .5f, yPos - .5f, zPos - .5f), //[4] Back,Bottom,Left
                new Vector3f(xPos + .5f, yPos - .5f, zPos - .5f), //[5] Back,Bottom,Right
                new Vector3f(xPos + .5f, yPos + .5f, zPos - .5f), //[6] Back,Top,Right,
                new Vector3f(xPos - .5f, yPos + .5f, zPos - .5f)  //[7] Back,Top,Left
        };
        //Texture[] tex = ResourceManager.getBlockTexture(type);
        QuadFace[] faces = {
                new QuadFace(v[0], v[1], v[2], v[3]), // Front face
                new QuadFace(v[1], v[5], v[6], v[2]), // Right Face
                new QuadFace(v[4], v[5], v[6], v[7]), // Back Face
                new QuadFace(v[0], v[4], v[7], v[3]), // Left Face
                new QuadFace(v[0], v[4], v[5], v[1]), // Bottom Face
                new QuadFace(v[7], v[6], v[2], v[3])  // Top Face
        };
        this.faces = faces;
    }
    // Method: setBlockType
    // Purpose: Sets the type of block based on its y position.
    public void setBlockType(){
        if(yPos >= 0){
            if(yPos >= 18) {
                type = BlockType.BLOCK_TYPE_STONE;
            }else if(yPos > 8){
                if(!parent.blockCheck((int)index.x,(int)index.y+1,(int)index.z)){
                    type = BlockType.BLOCK_TYPE_GRASS;

                }else{
                    type = BlockType.BLOCK_TYPE_DIRT;
                }
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
        texCoords = ResourceManager.getTexCoords(type);
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

    public BlockType getType() {
        return type;
    }
}
