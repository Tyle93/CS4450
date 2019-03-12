package FinalProject;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #1
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/11/2019

    File Name: Game.java
    Purpose: This class takes keyboard input and navigates around the cube by 
    modifying the camera angles. 
 */
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

import static org.lwjgl.opengl.GL11.*;

public class Game {
    private static Camera camera;
    private static float mouseSensitivity = 0.09f;
    private static float movementSpeed = .35f;
    // name : gameLoop 
    // purpose : continues to loop until the screen is told to close. 
    public static void gameLoop() {
        camera = new Camera(0, 0, 0);
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
            Engine.Render();;
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
    }
    // name : processInput
    // purpose : checks which key is being pressed. Additionally, it will move 
    // the camera angle by a certain degree.
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
    }
}
