package Vox;

import org.lwjgl.BufferUtils;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL15;

import java.nio.FloatBuffer;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;

public class Chunk {
    private static int ChunkCount = 0;
    private final int CHUNKSIZE  = 16;
    private final double scale = .008;
    private final int seed = 543332;
    private int vertHandle;
    private int textHandle;
    Block[][][] blocks;
    public boolean isActive = true;
    public Chunk(){
        vertHandle = GL15.glGenBuffers();
        textHandle = GL15.glGenBuffers();
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
                blocks[i][currentHeight][j].isActive = true;
                for(int k = 0; k < currentHeight; k++){
                    //blocks[i][k][j].isActive = true;
                }
            }
        }
        Engine.addObject(this);
        ChunkCount++;
        rebuildChunk();
    }
    public void rebuildChunk(){

        FloatBuffer vertBuff = getBlockData();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, vertHandle);
        GL15.glBufferData(GL_ARRAY_BUFFER, vertBuff,GL15.GL_DYNAMIC_DRAW);
        GL15.glBindBuffer(GL_ARRAY_BUFFER, 0);
        //glGenTextures()
       //GL15.glBindBuffer(GL_ARRAY_BUFFER, textHandle);
       //GL15.glBufferData(GL_ARRAY_BUFFER, ResourceManager.getBlockTexture(BlockType.BLOCK_TYPE_GRASS)[0].getTextureData(),GL_DYNAMIC_DRAW);
    }
    private FloatBuffer getBlockData(){
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
    public void draw(){
        //for(Block[][] b : blocks){
        //    for(Block[] c : b){
        //        for(Block d : c){
        //            if(d.isActive && d !=  null){
        //                d.draw();
        //            }
        //        }
        //    }
        //}
        GL11.glPushMatrix();
        GL15.glBindBuffer(GL_ARRAY_BUFFER, vertHandle);
        GL11.glVertexPointer(3, GL_FLOAT, 0, 0L);
        //GL15.glBindBuffer(GL_ARRAY_BUFFER, ResourceManager.getBlockTexture(BlockType.BLOCK_TYPE_GRASS)[0].getTextureID());
        //GL11.glColorPointer(3,GL_BYTE,0,0L);
        GL11.glDrawArrays(GL_QUADS, 0, CHUNKSIZE * CHUNKSIZE * CHUNKSIZE * 24);
        GL11.glPopMatrix();
    }
}
