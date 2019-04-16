package Vox;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #3
    Class: CS 4450 - Computer Graphics
    Last Modified: 04/15/2019
    File Name: SimpleNoise.java
    Purpose: Utility class given to us in order to generate sound for procedural generation.
 */
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

public class Light {
    private FloatBuffer lightPosition;
    private FloatBuffer ambientLight;
    private FloatBuffer diffuseLight;
    private FloatBuffer specularLight;

    public Light(){
        initLightArrays();
        Engine.addLight(this);
    }

    public FloatBuffer getLightPosition() {
        return lightPosition;
    }

    public FloatBuffer getAmbientLight() {
        return ambientLight;
    }

    public FloatBuffer getDiffuseLight() {
        return diffuseLight;
    }

    public FloatBuffer getSpecularLight() {
        return specularLight;
    }

    private void initLightArrays() {
        lightPosition = BufferUtils.createFloatBuffer(4);
        lightPosition.put(50f).put(50f).put(100f).put(1.0f).flip();
        ambientLight = BufferUtils.createFloatBuffer(4);
        ambientLight.put(.4f).put(.4f).put(.4f).put(0.0f).flip();
        diffuseLight = BufferUtils.createFloatBuffer(4);
        diffuseLight.put(.9f).put(.9f).put(.9f).put(0f).flip();
        specularLight = BufferUtils.createFloatBuffer(4);
        specularLight.put(.5f).put(.5f).put(.5f).put(0f).flip();

    }
}
