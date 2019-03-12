package Vox;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.Color;
import org.lwjgl.util.glu.GLU;

public class Main {

    public static void main(String[] args) {
        initDisplay();
    }
    public static void initDisplay(){
        try{
            Vertex[] v = {
                    new Vertex(1,1,1),
                    new Vertex(-1,1,1),
                    new Vertex(1,-1,1),
                    new Vertex(-1,-1,1),
                    new Vertex(1,1,-1),
                    new Vertex(-1,1,-1),
                    new Vertex(1,-1,-1),
                    new Vertex(-1,-1,-1)
            };
            QuadFace[] q = {
                    new QuadFace(v[0],v[2],v[3],v[1],new Color(255,0,0)),
                    new QuadFace(v[0],v[4],v[6],v[2],new Color(0,255,0)),
                    new QuadFace(v[0],v[1],v[5],v[4],new Color(0,0,255)),
                    new QuadFace(v[4],v[5],v[7],v[6],new Color(255,0,255)),
                    new QuadFace(v[5],v[1],v[3],v[7],new Color(0,255,255)),
                    new QuadFace(v[4],v[5],v[1],v[0],new Color(255,255,0))
            };
            new Cube(q);
            Engine.start();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }

    }
}
