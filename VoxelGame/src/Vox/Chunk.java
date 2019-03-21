package Vox;

import org.lwjgl.Sys;

import java.util.Arrays;

public class Chunk {
    private static int ChunkCount = 0;
    private final int CHUNKSIZE  = 32;
    Block[][][] blocks;
    public boolean isActive = true;
    public Chunk(){
        double scale = .011;
        int seed = 543332;
        double[][] noise = new double[CHUNKSIZE][CHUNKSIZE];
        SimplexNoise_octave simp = new SimplexNoise_octave(seed);
        blocks = new Block[CHUNKSIZE][CHUNKSIZE][CHUNKSIZE];
        //Arrays.fill(blocks,null);
        for(int i = 0; i < CHUNKSIZE; i++){
            for(int j = 0; j < CHUNKSIZE; j++){
                for(int k = 0; k < CHUNKSIZE; k++){
                    float zOffset = ((float) Math.floor((double)(ChunkCount/Engine.getSIZE()))-1) * CHUNKSIZE;
                    //float zOffset = (ChunkCount % Engine.getSIZE()) * CHUNKSIZE;
                    float xOffset = (ChunkCount * CHUNKSIZE) % (Engine.getSIZE() * CHUNKSIZE);
                    blocks[i][j][k] = new Block(i + xOffset ,j ,k + zOffset );
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
                //System.out.println(currentHeight);
                if(currentHeight == 0){
                    currentHeight = 1;
                }
                //blocks[i][currentHeight][j].isActive = true;
                for(int k = 0; k < currentHeight; k++){
                    //blocks[i][k][j] = new Block(i+(Engine.getOFFSET() * i),k + (Engine.getOFFSET() * k),j + (Engine.getOFFSET() * j));
                    blocks[i][k][j].isActive = true;
                }
            }
        }
        Engine.addObject(this);
        ChunkCount++;
    }
    public void draw(){
        for(Block[][] b : blocks){
            for(Block[] c : b){
                for(Block d : c){
                    if(d.isActive && d !=  null){
                        d.draw();
                    }
                }
            }
        }
    }
    public int getCHUNKSIZE() {
        return CHUNKSIZE;
    }
}
