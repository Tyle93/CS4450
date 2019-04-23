package Vox;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL15;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.Arrays;
import java.util.Random;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #3
    Class: CS 4450 - Computer Graphics
    Last Modified: 04/15/2019
    File Name: Chunk.java
    Purpose: Acts as a container for a 3 Dimensional Array of Blocks that is used to render
    blocks in batches.
 */
public class Chunk {
    private static int ChunkCount = 0;
    private static final int CHUNKSIZE  = 64;
    private final double scale = .009;
    //private int seed = 543332;
    private static int seed = new Random().nextInt();
    private int vertHandle;
    private int textHandle;
    private int colorHandle;
    Block[][][] blocks;
    public boolean isActive = true;

    public static int getCHUNKSIZE() {
        return CHUNKSIZE;
    }

    public Chunk(){
        vertHandle = GL15.glGenBuffers();
        textHandle = GL15.glGenBuffers();
        colorHandle = GL15.glGenBuffers();
        double[][] noise = new double[CHUNKSIZE][CHUNKSIZE];
        SimplexNoise_octave simp = new SimplexNoise_octave(seed);
        blocks = new Block[CHUNKSIZE][CHUNKSIZE][CHUNKSIZE];
        for(int i = 0; i < CHUNKSIZE; i++){
            for(int j = 0; j < CHUNKSIZE; j++){
                for(int k = 0; k < CHUNKSIZE; k++){
                    float zOffset = (((float) Math.floor((double)(ChunkCount/Engine.getSIZE()))) * CHUNKSIZE) % (CHUNKSIZE*Engine.getSIZE());
                    float yOffset = ((float) Math.floor((double)(ChunkCount/(Engine.getSIZE()*Engine.getSIZE())))) * CHUNKSIZE;
                    float xOffset = (ChunkCount * CHUNKSIZE) % (Engine.getSIZE() * CHUNKSIZE);
                    blocks[i][j][k] = new Block(i + xOffset ,j - yOffset ,k + zOffset );
                }
            }
        }
        for(int i = 0; i < CHUNKSIZE; i++){
            for(int j = 0; j < CHUNKSIZE; j++){
                noise[i][j] = simp.noise(blocks[i][0][j].getxPos()* scale,blocks[i][0][j].getzPos() * scale);
            }
        }
        int currentHeight;
        for(int i = 0; i < CHUNKSIZE; i++){
            for(int j = 0; j < CHUNKSIZE; j++){
                currentHeight = (int)(CHUNKSIZE * ((noise[i][j] + 1)/2));
                if(currentHeight == 0){
                    currentHeight = 1;
                }
                //blocks[i][currentHeight][j].isActive = true;
                for(int k = 0; k < currentHeight; k++){
                    blocks[i][k][j].isActive = true;
                    System.out.println(ChunkCount);
                }
            }
        }
        Engine.addObject(this);
        ChunkCount++;
        rebuildChunk();
    }
    // Method: rebuildChunk
    // Purpose: refreshes the vertex and texture coordinate buffers for the chunk.
    public void rebuildChunk(){
        FloatBuffer vertBuff = getVertData();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, vertHandle);
        GL15.glBufferData(GL_ARRAY_BUFFER, vertBuff,GL15.GL_STATIC_DRAW);
        GL15.glBindBuffer(GL_ARRAY_BUFFER, 0);
        FloatBuffer colorBuff = getCubeColor();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, colorHandle);
        GL15.glBufferData(GL_ARRAY_BUFFER, colorBuff, GL_STATIC_DRAW);
        GL15.glBindBuffer(GL_ARRAY_BUFFER,0);
        FloatBuffer texBuff = getTexData();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, textHandle);
        GL15.glBufferData(GL_ARRAY_BUFFER, texBuff, GL_STATIC_DRAW);
        GL15.glBindBuffer(GL_ARRAY_BUFFER, 0);

    }
    // Method: getVertData
    // Purpose: Retreives the vertex data for every block in the chunk and returns it as a floatbuffer.
    private FloatBuffer getVertData(){
        FloatBuffer vertBuff = BufferUtils.createFloatBuffer(CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * 72);
        for(Block[][] b : blocks){
            for(Block[] c : b){
                for(Block d : c){
                    if(d.isActive && d !=  null){
                        vertBuff.put(d.getVertData());
                    }
                }
            }
        }
        vertBuff.flip();
        return vertBuff;
    }
    // Method:  getTexData()
    // Purpose: Gets the texture coordinate data for each of the blocks in the chunk and returns it as a floatbuffer.
    private FloatBuffer getTexData(){
        FloatBuffer texBuff = BufferUtils.createFloatBuffer(CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * 48);
        for(Block[][] b : blocks){
            for(Block[] c : b){
                for(Block d : c){
                    if(d.isActive && d !=  null){
                        texBuff.put(ResourceManager.getTexCoords(d.getType()));
                    }
                }
            }
        }
        texBuff.flip();
        return texBuff;
    }
    private FloatBuffer getCubeColor(){
        FloatBuffer buff = BufferUtils.createFloatBuffer(CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * 72);
        for(Block[][] b : blocks){
            for(Block[] c : b){
                for(Block d : c){
                    if(d.isActive && d !=  null){
                        float[] temp = {
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1,
                                1,1,1
                        };
                        buff.put(temp);
                    }
                }
            }
        }
        buff.flip();
        return buff;
    }
    // Method: draw()
    // Purpose: Binds the vertex and texture coordinate buffers and the draws the blocks based on the arrays.
    public void draw(){
        GL11.glPushMatrix();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, vertHandle);
        GL11.glVertexPointer(3, GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL_ARRAY_BUFFER, colorHandle);
        glColorPointer(3,GL_FLOAT, 0, 0L);
        GL15.glBindBuffer(GL_ARRAY_BUFFER, textHandle);
        glBindTexture(GL_TEXTURE_2D, ResourceManager.getTexMap().getTextureID());
        glTexCoordPointer(2,GL_FLOAT,0,0L);
        GL11.glDrawArrays(GL_QUADS, 0, CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * 24);
        GL11.glPopMatrix();
    }
}
