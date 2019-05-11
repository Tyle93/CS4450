package Vox;
/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Game.java
 */
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import java.sql.Date;
import java.sql.Time;

import static org.lwjgl.opengl.GL11.*;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #3
    Class: CS 4450 - Computer Graphics
    Last Modified: 04/15/2019
    File Name: Game.java
    Purpose: Simple class that contains our camera and our main logic loop.
 */
public class Game {
    private static final long SECOND = 1000;
    private static Camera camera;
    private static float mouseSensitivity = 0.09f;
    private static float movementSpeed = .9f;
    private static long lastEvent;
    private static boolean swapEligible = true;
    // Method:  gameLoop
    // Purpose: Refreshes camera position and draws all objects to screen.
    public static void gameLoop() {
        camera = new Camera(-Engine.getSIZE()*Chunk.getCHUNKSIZE()/2, -Chunk.getCHUNKSIZE()*1.2f, -Engine.getSIZE()*Chunk.getCHUNKSIZE()/2);
        float dx = 0.0f;
        float dy = 0.0f;
        Mouse.setGrabbed(true);
        while (!Display.isCloseRequested() && !Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            dx = Mouse.getDX();
            dy = Mouse.getDY();
            camera.yaw(dx * mouseSensitivity);
            camera.pitch(dy * mouseSensitivity);
            processInput();
            glLoadIdentity();
            camera.lookThrough();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            Engine.Render();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
    // Method: processInput()
    // Purpose: Handles and keyboard input
    private static void processInput(){
        if (Keyboard.isKeyDown(Keyboard.KEY_W)){
            camera.walkForward(movementSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
            camera.walkBackwards(movementSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
            camera.strafeLeft(movementSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
            camera.strafeRight(movementSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
            camera.moveUp(movementSpeed);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_E) || Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
            camera.moveDown(movementSpeed);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_T) && Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
            if(swapEligible){
                ResourceManager.swapTexture();
                lastEvent = System.currentTimeMillis();
                swapEligible = false;
            }
            checkTimeout(System.currentTimeMillis());
        }
    }
    private static void checkTimeout(long current){
        if((current - lastEvent) >= 1000){
            swapEligible = true;
        }
    }

}
