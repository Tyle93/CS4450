package Vox;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #3
    Class: CS 4450 - Computer Graphics
    Last Modified: 04/15/2019
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
import org.lwjgl.util.vector.Vector3f;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.Vector;

import static org.lwjgl.opengl.GL11.*;

public class Engine{
    public static double scale = .01;
    private static final int SIZE = 4;
    private static Vector<Chunk> objects = new Vector<Chunk>();
    private static Vector<Light> lights = new Vector<Light>();
    private static DisplayMode displayMode;
    public static void addObject(Chunk chunk){
        objects.add(chunk);
    }
    public static void addLight(Light light){
        lights.add(light);
    }
    public static void Render(){
        updateLights();
        for(Chunk c : objects){
            if(c.isActive){
                c.draw();
            }
        }
    }
    private static int getSize(){
        return SIZE;
    }
    public static void updateLights(){
        for(Light l : lights){
            l.setPos(new Vector3f((float)(l.getOrbitPoint().x + Math.cos(Math.toRadians(l.orbitAngle))*l.getOrbitRadius()), (float)(l.getOrbitPoint().y + Math.sin(Math.toRadians(l.orbitAngle))*l.getOrbitRadius()),l.getOrbitPoint().z));
            l.orbitAngle += 1;
            glLight(GL_LIGHT0, GL_POSITION, l.getLightPosition()); //sets our light’s position
            glLight(GL_LIGHT0, GL_SPECULAR, l.getSpecularLight());//sets our specular light
            glLight(GL_LIGHT0, GL_DIFFUSE, l.getDiffuseLight());//sets our diffuse light
            glLight(GL_LIGHT0, GL_AMBIENT, l.getAmbientLight());//sets our ambient light
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
        new Light(new Vector3f(50,50,100));
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
                displayMode.getHeight(), 0.1f, 500);
        glMatrixMode(GL_MODELVIEW);
        glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);
        glClearDepth(1.0f);
        glEnable(GL_DEPTH_TEST);
        glEnable(GL_TEXTURE_2D);
        glEnableClientState(GL_VERTEX_ARRAY);
        glEnableClientState(GL_COLOR_ARRAY);
        glEnableClientState(GL_TEXTURE_COORD_ARRAY);
        glEnable(GL_LIGHTING);
        glEnable(GL_LIGHT0);

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

