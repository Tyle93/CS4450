package Vox;

import org.lwjgl.opengl.GL20;
import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.*;
import java.util.Scanner;

public class ResourceManager {
    static private boolean initialized = false;
    private static Texture DIRT_TEXTURE[]= new Texture[3];
    private static Texture SAND_TEXTURE[]= new Texture[3];
    private static Texture WATER_TEXTURE[]= new Texture[3];
    private static Texture BEDROCK_TEXTURE[] = new Texture[3];
    private static Texture GRASS_TEXTURE[] = new Texture[3];
    private static Texture STONE_TEXTURE[] = new Texture[3];
    private static int grassShader = -1;
    private static int sandShader = -1;
    private static int stoneShader = -1;
    private static int bedrockShader = -1;
    private static int dirtShader = -1;
    private static int waterShader = -1;

    public static void initializeResources(){
        try {
            BEDROCK_TEXTURE[0] = TextureLoader.getTexture("PNG", new FileInputStream("Bedrock.png"));
            BEDROCK_TEXTURE[1] = TextureLoader.getTexture("PNG", new FileInputStream("Bedrock.png"));
            BEDROCK_TEXTURE[2] = TextureLoader.getTexture("PNG", new FileInputStream("Bedrock.png"));
            DIRT_TEXTURE[0] = TextureLoader.getTexture("PNG", new FileInputStream("Dirt.png"));
            DIRT_TEXTURE[1] = TextureLoader.getTexture("PNG", new FileInputStream("Dirt.png"));
            DIRT_TEXTURE[2] = TextureLoader.getTexture("PNG", new FileInputStream("Dirt.png"));
            SAND_TEXTURE[0] = TextureLoader.getTexture("PNG", new FileInputStream("Sand.png"));
            SAND_TEXTURE[1] = TextureLoader.getTexture("PNG", new FileInputStream("Sand.png"));
            SAND_TEXTURE[2] = TextureLoader.getTexture("PNG", new FileInputStream("Sand.png"));
            GRASS_TEXTURE[0] = TextureLoader.getTexture("PNG", new FileInputStream("Grass_side.png"));
            GRASS_TEXTURE[1] = TextureLoader.getTexture("PNG", new FileInputStream("Grass_top.png"));
            GRASS_TEXTURE[2] = TextureLoader.getTexture("PNG", new FileInputStream("Grass_bottom.png"));
            STONE_TEXTURE[0] = TextureLoader.getTexture("PNG", new FileInputStream("Stone.png"));
            STONE_TEXTURE[1] = TextureLoader.getTexture("PNG", new FileInputStream("Stone.png"));
            STONE_TEXTURE[2] = TextureLoader.getTexture("PNG", new FileInputStream("Stone.png"));
            WATER_TEXTURE[0] = TextureLoader.getTexture("PNG", new FileInputStream("Water.png"));
            WATER_TEXTURE[1] = TextureLoader.getTexture("PNG", new FileInputStream("Water.png"));
            WATER_TEXTURE[2] = TextureLoader.getTexture("PNG", new FileInputStream("Water.png"));
            initialized = true;
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
    }
    public static Texture[] getBlockTexture(BlockType type){
        if(!initialized){
            initializeResources();
        }
        Texture[] retVal;
        try {
            switch (type){
                case BLOCK_TYPE_BEDROCK: retVal = BEDROCK_TEXTURE;
                    break;
                case BLOCK_TYPE_DIRT: retVal = DIRT_TEXTURE;
                    break;
                case BLOCK_TYPE_SAND: retVal = SAND_TEXTURE;
                    break;
                case BLOCK_TYPE_GRASS:retVal = GRASS_TEXTURE;
                    break;
                case BLOCK_TYPE_STONE:retVal = STONE_TEXTURE;
                    break;
                case BLOCK_TYPE_WATER:retVal = WATER_TEXTURE;
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
