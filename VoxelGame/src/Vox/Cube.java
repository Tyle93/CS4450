package Vox;

public class Cube implements Drawable{
    private QuadFace[] faces;
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
        for(QuadFace q : faces){
            q.draw();
        }
    }
}
