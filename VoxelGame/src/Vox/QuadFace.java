package Vox;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;

public class QuadFace implements Drawable{
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
    public void setColor(Color color)
    {
        this.color = color;
    }
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
