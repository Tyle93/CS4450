package Vox;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.Vector;

public class Engine{
    public static double scale = 1;
    private static Vector<Cube> objects = new Vector<Cube>();
    private static double dy = 0;
    private static double dx = 0;
    private static double dz = 0;
    public static void addObject(Cube cube){
        objects.add(cube);
    }
    private static void processInput(){
        if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
            System.exit(0);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D)){
            dx++;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A)){
            dx--;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_W)){
            dy++;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_X)){
            dy--;
        }

    }
    public static void Render(){
        while (!Display.isCloseRequested()){
            processInput();
            GL11.glTranslated(dx,dy,dz);
            if(!objects.isEmpty()){
                for (Cube c : objects){
                    c.draw();
                }
            }
            Display.update();
        }
        Display.destroy();
    }
    //Method: AddObj
}
