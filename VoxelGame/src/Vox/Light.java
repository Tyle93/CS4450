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
import org.lwjgl.util.vector.Vector3f;
import org.newdawn.slick.util.BufferedImageUtil;
import org.omg.IOP.ENCODING_CDR_ENCAPS;

import java.io.BufferedReader;
import java.nio.FloatBuffer;

public class Light {
    private FloatBuffer lightPositionBuffer;
    private FloatBuffer ambientLightBuffer;
    private FloatBuffer diffuseLightBuffer;
    private FloatBuffer specularLightBuffer;
    private Vector3f pos;
    private Vector3f ambient;
    private Vector3f diffuse;
    private Vector3f specular;
    private Vector3f orbitPoint;
    public double orbitAngle;
    private float orbitRadius;

    public Light(){
        pos = new Vector3f(50,50,100);
        ambient = new Vector3f(.4f,.4f,.4f);
        diffuse = new Vector3f(1,1,1);
        specular = new Vector3f(.5f,.5f,.5f);
        initLightArrays();
        Engine.addLight(this);
    }
    public Light(Vector3f pos){
        this.pos = pos;
        ambient = new Vector3f(.4f,.4f,.4f);
        diffuse = new Vector3f(1,1,1);
        specular = new Vector3f(.5f,.5f,.5f);
        initLightArrays();
        Engine.addLight(this);
    }
    public Light(Vector3f pos, Vector3f ambient, Vector3f diffuse, Vector3f specular){
        this.pos = pos;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        initLightArrays();
        Engine.addLight(this);
    }

    public FloatBuffer getLightPosition() {
        return lightPositionBuffer;
    }

    public FloatBuffer getAmbientLight() {
        return ambientLightBuffer;
    }

    public FloatBuffer getDiffuseLight() {
        return diffuseLightBuffer;
    }

    public FloatBuffer getSpecularLight() {
        return specularLightBuffer;
    }

    private void initLightArrays() {
        lightPositionBuffer = BufferUtils.createFloatBuffer(4);
        lightPositionBuffer.put(pos.x).put(pos.y).put(pos.z).put(1.0f).flip();
        ambientLightBuffer = BufferUtils.createFloatBuffer(4);
        ambientLightBuffer.put(ambient.x).put(ambient.y).put(ambient.z).put(0.0f).flip();
        diffuseLightBuffer = BufferUtils.createFloatBuffer(4);
        diffuseLightBuffer.put(diffuse.x).put(diffuse.y).put(diffuse.z).put(0f).flip();
        specularLightBuffer = BufferUtils.createFloatBuffer(4);
        specularLightBuffer.put(specular.x).put(specular.y).put(specular.z).put(0f).flip();
        orbitPoint = new Vector3f(Chunk.getCHUNKSIZE() * Engine.getSIZE() /2, Chunk.getCHUNKSIZE()/2,Chunk.getCHUNKSIZE() * Engine.getSIZE()/2);
        orbitRadius = Engine.getSIZE() * Chunk.getCHUNKSIZE()/2 + Chunk.getCHUNKSIZE()/2;
        orbitAngle = 0;
    }

    public void setPos(Vector3f pos) {
        this.pos = pos;
        lightPositionBuffer = BufferUtils.createFloatBuffer(4);
        lightPositionBuffer.put(getX()).put(getY()).put(getZ()).put(0f).flip();
    }
    public float getX(){
        return pos.x;
    }
    public float getY(){
        return pos.y;
    }
    public float getZ(){
        return pos.z;
    }

    public Vector3f getOrbitPoint() {
        return orbitPoint;
    }

    public float getOrbitRadius() {
        return orbitRadius;
    }

    public double getOrbitAngle() {
        return orbitAngle;
    }
}
