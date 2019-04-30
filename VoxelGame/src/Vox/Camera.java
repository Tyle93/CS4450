package Vox;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #3
    Class: CS 4450 - Computer Graphics
    Last Modified: 04/15/2019
    File Name: Camera.java
    Purpose: User controlled camera that allows us to modify how we are viewing the 3d space.
 */
import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;

public class Camera {
    private Vector3f position = null;
    private Vector3f lPosition = null;
    private float yaw = 0.0f;
    private float pitch = 0.0f;

    public Vector3f getPosition() {
        return position;
    }

    public Camera(float x, float y, float z)
    {
        position = new Vector3f(x, y, z);
        lPosition = new Vector3f(x,y,z);
        lPosition.x = 0f;
        lPosition.y = 15f;
        lPosition.z = 0f;
    }
    public void yaw(float amount)
    {
        yaw += amount;
    }
    public void pitch(float amount)
    {
        pitch -= amount;
    }
    public void walkForward(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x -= xOffset;
        position.z += zOffset;
        checkPosition();
        FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(lPosition.x-=xOffset).put(
                lPosition.y).put(lPosition.z+=zOffset).put(1.0f).flip();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
    }
    public void walkBackwards(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw));
        position.x += xOffset;
        position.z -= zOffset;
        checkPosition();
        FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(lPosition.x-=xOffset).put(
                lPosition.y).put(lPosition.z+=zOffset).put(1.0f).flip();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
    }
    public void strafeLeft(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw-90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw-90));
        position.x -= xOffset;
        position.z += zOffset;
        checkPosition();
        FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(lPosition.x-=xOffset).put(
                lPosition.y).put(lPosition.z+=zOffset).put(1.0f).flip();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
    }
    public void strafeRight(float distance)
    {
        float xOffset = distance * (float)Math.sin(Math.toRadians(yaw+90));
        float zOffset = distance * (float)Math.cos(Math.toRadians(yaw+90));
        position.x -= xOffset;
        position.z += zOffset;
        checkPosition();
        FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(lPosition.x-=xOffset).put(
                lPosition.y).put(lPosition.z+=zOffset).put(1.0f).flip();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
    }
    public void moveUp(float distance)
    {
        position.y -= distance;
        checkPosition();
    }

    public void moveDown(float distance)
    {
        position.y += distance;
        checkPosition();
    }

    public void lookThrough()
    {
        glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        glTranslatef(position.x, position.y, position.z);
        FloatBuffer lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(lPosition.x).put(lPosition.y).put(lPosition.z).put(1.0f).flip();
        glLight(GL_LIGHT0, GL_POSITION, lightPosition);
    }
    //Method: checkPosition
    //purpose: Checks the x,y, and z coordinates of the camera to see if it has gone outside
    // the bounds of the world. If any of the coordinates are outside of the world they the
    // camera is wrapped back around to the other side.
    private void checkPosition(){
        System.out.println("\n\nX: " + position.x + "\nY: " + position.y + "\nZ: " + position.z);
        float xMax = -Engine.getSIZE()*Chunk.getCHUNKSIZE();
        float xMin = 0;
        float yMax = -Chunk.getCHUNKSIZE()*1.5f;
        float yMin = 0;
        float zMax = xMax;
        float zMin = 0;
        if(position.x < xMax){
            position.x = xMin;
        }else if(position.x > xMin){
            position.x = xMax;
        }else if(position.y < yMax){
            position.y = yMin;
        }else if(position.y > yMin){
            position.y = yMax;
        }else if(position.z < zMax){
            position.z = zMin;
        }else if(position.z > zMin){
            position.z = zMax;
        }
    }
}



