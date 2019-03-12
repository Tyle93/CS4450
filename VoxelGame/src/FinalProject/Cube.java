package FinalProject;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #1
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/11/2019

    File Name: Cube.java
    Purpose: This class holds the 6 Quadface object classes. The main function
    of this class is basically drawing the 6 quads that were created. This class
    has potential to be used in future checkpoints. 
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
    
    //name : generateFaces
    // purpose : calls the method generateFaces from quadface to create 6 faces.
    private void generateFaces(){
        faces = QuadFace.generateFaces(size);
    }
    
    // name: draw
    // purpose : calls each draw method for all quadface in the vector.
    public void draw(){
        for(QuadFace q : faces){
            q.draw();
        }
    }
}
