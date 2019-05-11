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
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
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
    private static final int SIZE = 12;
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
    public static void updateLights(){
        for(Light l : lights){
            l.setPos(new Vector3f((float)l.getOrbitPoint().x, (float)(l.getOrbitPoint().y - Math.sin(Math.toRadians(l.orbitAngle))*l.getOrbitRadius()),(float)(l.getOrbitPoint().z - Math.cos(Math.toRadians(l.orbitAngle))*l.getOrbitRadius())));
            l.orbitAngle += .25;
            glLight(GL_LIGHT0, GL_POSITION, l.getLightPosition()); //sets our light’s position
            glLight(GL_LIGHT0, GL_SPECULAR, l.getSpecularLight());//sets our specular light
            glLight(GL_LIGHT0, GL_DIFFUSE, l.getDiffuseLight());//sets our diffuse light
            glLight(GL_LIGHT0, GL_AMBIENT, l.getAmbientLight());//sets our ambient light
            glLight(GL_LIGHT0, GL_SPOT_DIRECTION, l.getLightDirection());

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
        new Light(new Vector3f(getSIZE()*Chunk.getCHUNKSIZE()/2,getSIZE()*Chunk.getCHUNKSIZE()/2,getSIZE()*Chunk.getCHUNKSIZE()/2));
        Keyboard.enableRepeatEvents(true);
        createWindow();
        initGL();
        generateWorld();
        Game.gameLoop();
    }
    // Method: initGL()
    // Purpose: initializes our openGL states.
    private static void initGL() {
        glClearColor(152/255f, 175/255f, 199/255f, 0.0f);
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
        glEnable(GL_NORMALIZE);

    }
    // Method: createWindow()
    // Purpose: Creates a 640x480 window
    private static void createWindow(){
        try{

            displayMode = new DisplayMode(1600 ,900);
            Display.setDisplayMode(displayMode);

            /*
            Display.setDisplayMode(getDisplayMode(1920 ,1080,false));
            if(displayMode.isFullscreenCapable()){
                Display.setFullscreen(true);
            }else{
                Display.setFullscreen(false);
            }
            */
            Display.setTitle("CubeKraft");
            Display.create();
        }catch (Exception e){
            for(StackTraceElement s: e.getStackTrace()){
                System.out.println(s.toString());
            }
        }
    }
    private static DisplayMode getDisplayMode(int width, int height, boolean fullscreen){
        try{
            DisplayMode displayMode = null;
            DisplayMode[] modes = Display.getAvailableDisplayModes();

            for (int i = 0; i < modes.length; i++)
            {
                if (modes[i].getWidth() == width
                        && modes[i].getHeight() == height
                        && modes[i].isFullscreenCapable())
                {
                    displayMode = modes[i];
                    return displayMode;
                }
            }
        }catch (LWJGLException e){
            return new DisplayMode(1600,900);
        }
        return new DisplayMode(1600,900);

    }
    // Method: generateWorld
    // Purpose: creates a 2 dimensional chunk array.
    private static void generateWorld(){
        for(int i = 0; i < SIZE*SIZE; i++){
            new Chunk();
        }
    }

}

