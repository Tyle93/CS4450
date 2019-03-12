package Vox;

import org.lwjgl.opengl.GL11;

public class Cube implements Drawable{
    private QuadFace[] faces;
    //private Vertex[] vertices;
    private int size;
    public Cube(int size){
        this.size = size;
        generateFaces();
        Engine.addObject(this);
    }
    public Cube(QuadFace[] faces){
        this.faces = faces;
        Engine.addObject(this);

    }
    private void generateFaces(){
        faces = QuadFace.generateFaces(size);
    }
    public void draw(){
        //GL11.glBegin(GL11.GL_QUADS);
        for(QuadFace q : faces){
            q.draw();
        }
        //GL11.glEnd();
    }
}
