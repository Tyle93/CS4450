package FinalProject;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #1
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/11/2019

    File Name: QuadFace.java
    Purpose: This class is used to hold the vertices of the cubes. It helps us
    break down each face of the cube so that we can easily track it.
 */
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

public class QuadFace{
    public static int count = 0;
    Vertex[] vertices;
    Color color;
    public  QuadFace(Vertex[] vertices, Color color){
        this.vertices = vertices;
        this.color = color;
        count++;
    }
    public QuadFace(Vertex a, Vertex b, Vertex c, Vertex d){
        vertices = new Vertex[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
        color = new Color(0,255,0);
    }
    public QuadFace(Vertex a, Vertex b, Vertex c, Vertex d, Color color){
        vertices = new Vertex[4];
        vertices[0] = a;
        vertices[1] = b;
        vertices[2] = c;
        vertices[3] = d;
        this.color = color;
    }
    
    //name : setColor
    // purpose : holds the color for the quadface
    public void setColor(Color color)
    {
        this.color = color;
    }
    //name : draw
    // purpose : utilizes the openGl function to draw the quad.
    public void draw(){
            GL11.glColor3f((float)color.getRed()/255,(float)color.getGreen()/255,(float)color.getBlue()/255);
            GL11.glBegin(GL11.GL_QUADS);
            for(Vertex v: vertices){
                v.drawVertex();
            }
            GL11.glEnd();
    }
    public static QuadFace[] generateFaces(int size){
        QuadFace[] faces = new QuadFace[6];
        return faces;
    }
}
