package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Cube.java
 */
public class Cube{
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
