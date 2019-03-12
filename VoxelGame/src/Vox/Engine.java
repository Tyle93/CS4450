package Vox;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Engine{
    public static double scale = 200;
    private static Vector<Cube> objects = new Vector<Cube>();
    private static Camera camera = new Camera(0,0,0);
    private static DisplayMode displayMode;
    public static void addObject(Cube cube){
        objects.add(cube);
    }
    public static void Render(){

        //glBegin(GL_QUADS);
        for(Cube c : objects){
            c.draw();
        }
        /*
        glBegin(GL_QUADS);
        glColor3f(1.0f,0.0f,1.0f, 1);


        glVertex3f( 1.0f,-1.0f,-1.0f);
        glVertex3f(-1.0f,-1.0f,-1.0f);
        glVertex3f(-1.0f, 1.0f,-1.0f);
        glVertex3f( 1.0f, 1.0f,-1.0f);

        glColor3f(0.0f,0.0f,1.0f,1);

        glVertex3f( 1.0f,-1.0f,1.0f);
        glVertex3f(-1.0f,-1.0f,1.0f);
        glVertex3f(-1.0f, 1.0f,1.0f);
        glVertex3f( 1.0f, 1.0f,1.0f);

        glColor3f(1.0f,0.0f,0.0f,1);

        glVertex3f( 1.0f,-1.0f,-1.0f);
        glVertex3f(1.0f,-1.0f,1.0f);
        glVertex3f(-1.0f, -1.0f,1.0f);
        glVertex3f( -1.0f, -1.0f,-1.0f);

        glEnd();
        */
        //glEnd();

    }
    public static void start(){
        createWindow();
        initGL();
        camera.gameLoop();
    }
    private static void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
                displayMode.getHeight(), 0.1f, 300.0f);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glEnable(GL_DEPTH_TEST);
    }
    private static void createWindow(){
        try{
            Display.setFullscreen(false);
            DisplayMode d[] =
                    Display.getAvailableDisplayModes();
            for (int i = 0; i < d.length; i++) {
                if (d[i].getWidth() == 640
                        && d[i].getHeight() == 480
                        && d[i].getBitsPerPixel() == 32) {
                    displayMode = d[i];
                    break;
                }
            }
            Display.setDisplayMode(displayMode);
            Display.setTitle("MeinKraft");
            Display.create();
        }catch (Exception e){

        }

    }
}

