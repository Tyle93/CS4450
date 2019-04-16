package Vox;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #2
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/27/2019
    File Name: Engine.java
    Purpose: Object that dictates most of our OpenGl state variables as well as the
    being responsible for the rendering of each of the chunks.
 */
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import org.lwjgl.util.glu.GLU;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Engine{
    public static double scale = .01;
    private static final int SIZE = 16;
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
    // Method: getSize()
    // Purpose: returns the current size of the chunk grid/
    public static int getSIZE() {
        return SIZE;
    }
    // Method: start()
    // Purpose: initializes our window and all of the opengl variables.
    public static void start(){
        createWindow();
        initGL();
        generateWorld();
        Game.gameLoop();
    }
    // Method: initGL()
    // Purpose: initializes our openGL states.
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
        glEnable(GL_TEXTURE_2D);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
    }
    // Method: createWindow()
    // Purpose: Creates a 640x480 window
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
    // Method: generateWorld
    // Purpose: creates a 2 dimensional chunk array.
    private static void generateWorld(){
        for(int i = 0; i < SIZE*SIZE; i++){
            new Chunk();
        }
    }
}

