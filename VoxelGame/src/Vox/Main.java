package Vox;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Main {

    public static void main(String[] args) {
        initDisplay();
    }
    public static void initDisplay(){
        try{
            int height = 900;
            int width = 1600;
            Display.setDisplayMode(new DisplayMode(width,height));
            Display.sync(Display.getDesktopDisplayMode().getFrequency());
            Display.create();
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            //GLU.gluPerspective();
            GL11.glOrtho(-width/2,width/2,-height/2,height/2,1,-1);
            //GL11.glFrustum(-width/2,width/2,-height/2,height/2,0,100);
            //GL11.glMatrixMode(GL11.GL_MODELVIEW);
            //GL11.glTranslated(0,0,-1);
            //GL11.glRotated(0,0,Math.PI/4.0);
            Vertex[] v = {
                    new Vertex(0,0,0),
                    new Vertex(1,0,0),
                    new Vertex(1,1,0),
                    new Vertex(0,1,0),
                    new Vertex(0,0,-1),
                    new Vertex(1,0,-1),
                    new Vertex(1,1,-1),
                    new Vertex(0,1,-1)
            };
            QuadFace[] q = {
                    new QuadFace(v[0],v[1],v[2],v[3]),
                    new QuadFace(v[0],v[1],v[2],v[3]),
                    new QuadFace(v[0],v[1],v[2],v[3]),
                    new QuadFace(v[0],v[1],v[2],v[3]),
                    new QuadFace(v[0],v[1],v[2],v[3]),
                    new QuadFace(v[0],v[1],v[2],v[3])
            };
            new Cube(q);
            Engine.Render();
        }catch(Exception e){
            System.out.println(e.getStackTrace());
        }

    }
}
