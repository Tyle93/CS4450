/*
    Name: Tyler Crouch
    Assignment: Project Checkpoint #1
    Class: CS 4450
    Last Modified: 03/11/2019

    File Name: Main.java
 */
package Vox;

import org.lwjgl.util.vector.Vector3f;

import java.io.PrintWriter;
import java.io.StringWriter;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #2
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/27/2019
    File Name: Main.java
    Purpose: Starts the program.
 */
public class Main {

    public static void main(String[] args) {
        initDisplay();
    }
    // Method: initDisplay
    // Purpose: Starts the program.
    public static void initDisplay(){
        try{
            Engine.start();
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }

    }
}
