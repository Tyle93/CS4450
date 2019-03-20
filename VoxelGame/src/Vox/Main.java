/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Main.java
 */
package Vox;

import org.lwjgl.util.vector.Vector3f;

public class Main {

    public static void main(String[] args) {
        initDisplay();
    }
    public static void initDisplay(){
        String path = "ArchitectureLayout.png";
        try{
            Engine.start();
        }catch(Exception e){
            StackTraceElement[] s = e.getStackTrace();
            for(StackTraceElement p : s){
                System.out.println(p.toString());
            }
        }

    }
}
