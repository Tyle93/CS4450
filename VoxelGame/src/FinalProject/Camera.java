package FinalProject;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #1
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/11/2019

    File Name: Camera.java
    Purpose: This class is used to take inputs and is used as a template 
    to move the camera. Additionally, the setters and getters are used to get
    the camera's location and set the camera's location.
 */
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;

public class Camera {
    private Vector3f position = null;
    private Vector3f lPosition = null;
    private float yaw = 0.0f;
    private float pitch = 0.0f;
    public Camera(float x, float y, float z)
    {
        position = new Vector3f(x, y, z);
        lPosition = new Vector3f(x,y,z);
        lPosition.x = 0f;
        lPosition.y = 15f;
        lPosition.z = 0f;
    }
    
    // name: yaw
    // purpose :  add a certain amount to yaw
    public void yaw(float amount)
    {
        yaw += amount;
    }
    // name: pitch
    // purpose :  subtract certain amount from pitch
    public void pitch(float amount)
    {
        pitch -= amount;
    }
    
    // name: walkforward
    // purpose :  increments x and y by a certain offset
    public void walkForward(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    // name: walkBackwards
    // purpose :  decrements x and y by a certain offset
    public void walkBackwards(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
    }
    
    // name: strafeLeft
    // purpose :  increment z and decrement x by a certain offset.
    public void strafeLeft(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw-90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw-90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    // name: strafeRight
    // purpose :  increments z and decrement X
    public void strafeRight(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw+90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90));
        position.x -= xOffset;
        position.z += zOffset;
    }
    
    // name: moveup
    // purpose :  decrements Y by an offset
    public void moveUp(float distance)
    {
        position.y -= distance;
    }
    // name: movedown
    // purpose :  increments Y by an offset
    public void moveDown(float distance)
    {
        position.y += distance;
    }
    // name: lookThrough
    // purpose :  rotate the perspective and translate it.
    public void lookThrough()
    {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.x, position.y, position.z);
    }

}



