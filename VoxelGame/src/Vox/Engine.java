package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Engine.java
 */
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Engine{
    public static double scale = .01;
    private static final int SIZE = 4;
    private static final float OFFSET = .000f;
    private static Vector<Chunk> objects = new Vector<Chunk>();
    private static DisplayMode displayMode;
    public static void addObject(Chunk chunk){
        objects.add(chunk);
    }
    public static void Render(){
        for(Chunk c : objects){
            if(c.isActive){
                c.draw();
            }
        }
    }
    public static int getSIZE() {
        return SIZE;
    }

    public static void start(){
        createWindow();
        initGL();
        generateWorld();
        Game.gameLoop();
    }
    private static void initGL() {
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        GLU.gluPerspective(100.0f, (float)displayMode.getWidth()/(float)
                displayMode.getHeight(), 0.1f, 200);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        //glEnable(GL_LIGHTING);
        glEnable(GL_TEXTURE_2D);
    }

    public static float getOFFSET() {
        return OFFSET;
    }
    private static void createWindow(){
        try{
            Display.setFullscreen(false);
            displayMode = new DisplayMode(640,480);
            Display.setDisplayMode(displayMode);
            Display.setTitle("CubeKraft");
            Display.create();
        }catch (Exception e){
            for(StackTraceElement s: e.getStackTrace()){
                System.out.println(s.toString());
            }
        }
    }
    private static void generateWorld(){
        for(int i = 0; i < SIZE*SIZE*SIZE; i++){
            new Chunk();
        }
    }
}

