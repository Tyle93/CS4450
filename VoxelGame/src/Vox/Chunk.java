package Vox;

public class Chunk {
    private final int CHUNKSIZE  = 16;
    Block[][][] blocks;
    public Chunk(){
        blocks = new Block[CHUNKSIZE][CHUNKSIZE][CHUNKSIZE];
        for(int i = 0; i < CHUNKSIZE; i++){
            for(int j = 0; j < CHUNKSIZE; j++){
                for(int k = 0; k < CHUNKSIZE; k++){
                    blocks[i][j][k] = new Block(i+(Engine.getOFFSET() * i),j + (Engine.getOFFSET() * j),k + (Engine.getOFFSET() *k));
                }
            }
        }
        Engine.addObject(this);
    }
    public void draw(){
        for(Block[][] b : blocks){
            for(Block[] c : b){
                for(Block d : c){
                    if(d.isActive){
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
