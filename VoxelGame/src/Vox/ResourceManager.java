package Vox;
/*
    Name: Tyler Crouch,Brandon Helt, Kelvin Huang, Christian Munoz
    Assignment: Project Checkpoint #2
    Class: CS 4450 - Computer Graphics
    Last Modified: 03/27/2019
    File Name: ResourceManager.java
    Purpose: Utility class meant for the initial loading of various textures and shaders.
 */
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.util.Scanner;

public class ResourceManager {
    static private boolean initialized = false;
    private static Texture texMap = null;
    private static float[] stoneCoords;
    private static float[] sandCoords;
    private static float[] dirtCoords;
    private static float[] waterCoords;
    private static float[] bedrockCoords;
    private static float[] grassCoords;
    private static int grassShader = -1;
    private static int sandShader = -1;
    private static int stoneShader = -1;
    private static int bedrockShader = -1;
    private static int dirtShader = -1;
    private static int waterShader = -1;
    private static int test;
    // Method: initializeResources()
    // Purpose: Loads any textures or shaders that will be used in the program.
    public static void initializeResources(){
        try {
            try {
                texMap = TextureLoader.getTexture("PNG", new FileInputStream("terrain.png"));
                generateTexCoords();
                initialized = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }catch (Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
    }
    // Method: generateTexCoords()
    // Purpose: generates the associated texture coordinates for each of the different block types.
    private  static void generateTexCoords(){
        float offset = 1024f/16f;
        float[] temp = {
                 offset*3, offset*10,
                 offset*2, offset*10,
                 offset*2, offset*9,
                 offset*3, offset*9,
                 offset*3, offset*1,
                 offset*2, offset*1,
                 offset*2, offset*0,
                 offset*3, offset*0,
                 offset*3, offset*0,
                 offset*4, offset*0,
                 offset*4, offset*1,
                 offset*3, offset*1,
                 offset*4, offset*1,
                 offset*3, offset*1,
                 offset*3, offset*0,
                 offset*4, offset*0,
                 offset*3, offset*0,
                 offset*4, offset*0,
                 offset*4, offset*1,
                 offset*3, offset*1,
                 offset*3, offset*0,
                 offset*4, offset*0,
                 offset*4, offset*1,
                 offset*3, offset*1
        };
        dirtCoords = temp;

    }
    // Method: getTexMap
    // Purpose: returns the texture atlas.
    public static Texture getTexMap() {
        return texMap;
    }
    // Method: getTexCoords
    // Purpose: returns the associated texture coordinates for each of the block types.
    public static float[] getTexCoords(BlockType type){
        if(!initialized){
            initializeResources();
        }
        float[] retVal;
        try {
            switch (type){
                case BLOCK_TYPE_BEDROCK: retVal = bedrockCoords;
                    break;
                case BLOCK_TYPE_DIRT: retVal = dirtCoords;
                    break;
                case BLOCK_TYPE_SAND: retVal = sandCoords;
                    break;
                case BLOCK_TYPE_GRASS:retVal = grassCoords;
                    break;
                case BLOCK_TYPE_STONE:retVal = stoneCoords;
                    break;
                case BLOCK_TYPE_WATER:retVal = waterCoords;
                    break;
                default: retVal = null;
                    break;
            }
            return retVal;
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
        return null;
    }
    // Method: getShader
    // Purpose: will return the appropriate shader for each of the block types.
    public static int getShader(BlockType type) {
        switch (type) {
            case BLOCK_TYPE_BEDROCK:
                if (bedrockShader == -1) {
                    generateShader(type);
                    return bedrockShader;
                }
                return bedrockShader;
            case BLOCK_TYPE_DIRT:
                if (dirtShader == -1) {
                    generateShader(type);
                    return dirtShader;
                }
                return dirtShader;
            case BLOCK_TYPE_SAND:
                if (sandShader == -1) {
                    generateShader(type);
                    return sandShader;
                }
                return sandShader;
            case BLOCK_TYPE_GRASS:
                if (grassShader == -1) {
                    generateShader(type);
                    return grassShader;
                }
                return grassShader;
            case BLOCK_TYPE_STONE:
                if (stoneShader == -1) {
                    generateShader(type);
                    return stoneShader;
                }
                return stoneShader;
            case BLOCK_TYPE_WATER:
                if (waterShader == -1) {
                    generateShader(type);
                    return waterShader;
                }
                return waterShader;
            default:
                return -1;

        }
    }
    // Method: generateShader()
    // Purpose: creates a shader for the passed block type.
    private static void generateShader(BlockType type) {
        int shaderProg = GL20.glCreateProgram();
        int vh = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
        int fh = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
        switch (type) {
            case BLOCK_TYPE_BEDROCK:

                GL20.glShaderSource(vh, readFileToString("block.vert"));
                GL20.glShaderSource(fh, readFileToString("block.frag"));

                GL20.glCompileShader(vh);
                GL20.glCompileShader(fh);

                GL20.glAttachShader(shaderProg,vh);
                GL20.glAttachShader(shaderProg,fh);

                GL20.glLinkProgram(shaderProg);

                bedrockShader = shaderProg;

                break;
            case BLOCK_TYPE_DIRT:

                GL20.glShaderSource(vh, readFileToString("block.vert"));
                GL20.glShaderSource(fh, readFileToString("block.frag"));

                GL20.glCompileShader(vh);
                GL20.glCompileShader(fh);

                GL20.glAttachShader(shaderProg,vh);
                GL20.glAttachShader(shaderProg,fh);

                GL20.glLinkProgram(shaderProg);

                dirtShader = shaderProg;

                break;
            case BLOCK_TYPE_SAND:

                GL20.glShaderSource(vh, readFileToString("block.vert"));
                GL20.glShaderSource(fh, readFileToString("block.frag"));

                GL20.glCompileShader(vh);
                GL20.glCompileShader(fh);

                GL20.glAttachShader(shaderProg,vh);
                GL20.glAttachShader(shaderProg,fh);

                GL20.glLinkProgram(shaderProg);

                sandShader = shaderProg;

                break;
            case BLOCK_TYPE_GRASS:

                GL20.glShaderSource(vh, readFileToString("block.vert"));
                GL20.glShaderSource(fh, readFileToString("block.frag"));

                GL20.glCompileShader(vh);
                GL20.glCompileShader(fh);

                GL20.glAttachShader(shaderProg,vh);
                GL20.glAttachShader(shaderProg,fh);

                GL20.glLinkProgram(shaderProg);

                grassShader = shaderProg;

                break;
            case BLOCK_TYPE_STONE:

                GL20.glShaderSource(vh, readFileToString("block.vert"));
                GL20.glShaderSource(fh, readFileToString("block.frag"));

                GL20.glCompileShader(vh);
                GL20.glCompileShader(fh);

                GL20.glAttachShader(shaderProg,vh);
                GL20.glAttachShader(shaderProg,fh);

                GL20.glLinkProgram(shaderProg);

                stoneShader = shaderProg;

                break;
            case BLOCK_TYPE_WATER:

                GL20.glShaderSource(vh, readFileToString("block.vert"));
                GL20.glShaderSource(fh, readFileToString("block.frag"));

                GL20.glCompileShader(vh);
                GL20.glCompileShader(fh);

                GL20.glAttachShader(shaderProg,vh);
                GL20.glAttachShader(shaderProg,fh);

                GL20.glLinkProgram(shaderProg);

                waterShader = shaderProg;

                break;
            default:
                break;
        }
    }
    // Method:  readFileToString()
    // Purpose: reads a file line by line for the purpose of being used in a shader program.
    public static StringBuilder readFileToString(String filePath){
        StringBuilder str = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                str.append(line).append("\n");
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }

        return new StringBuilder(" ");
    }
}
