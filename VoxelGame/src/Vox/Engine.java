package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Engine.java
 */
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.util.glu.GLU;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Engine{
    public static double scale = 2;
    private static Vector<Cube> objects = new Vector<Cube>();
    private static DisplayMode displayMode;
    public static void addObject(Cube cube){
        objects.add(cube);
    }
    public static void Render(){
        for(Cube c : objects){
            c.draw();
        }
    }
    public static void start(){
        createWindow();
        initGL();
        Game.gameLoop();
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
            displayMode = new DisplayMode(640,480);
            Display.setDisplayMode(displayMode);
            Display.setTitle("MeinKraft");
            Display.create();
        }catch (Exception e){

        }
    }
}

