package Vox;

import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResourceManager {
    static private boolean initialized = false;
    private static final String DIRT_PATH = "Dirt.png";
    private static final String SAND_PATH = "Sand.png";
    private static final String WATER_PATH = "Water.png";
    private static final String BEDROCK_PATH = "Bedrock.png";
    private static final String GRASS_PATH = "Grass.png";
    private static final String STONE_PATH = "Stone.png";
    private static Texture DIRT_TEXTURE = null;
    private static Texture SAND_TEXTURE = null;
    private static Texture WATER_TEXTURE = null;
    private static Texture BEDROCK_TEXTURE = null;
    private static Texture GRASS_TEXTURE = null;
    private static Texture STONE_TEXTURE = null;
    public static void initializeResources(){
        try {
            BEDROCK_TEXTURE = TextureLoader.getTexture("PNG", new FileInputStream(BEDROCK_PATH));
            DIRT_TEXTURE = TextureLoader.getTexture("PNG", new FileInputStream(DIRT_PATH));
            SAND_TEXTURE = TextureLoader.getTexture("PNG", new FileInputStream(SAND_PATH));
            GRASS_TEXTURE = TextureLoader.getTexture("PNG", new FileInputStream(GRASS_PATH));
            STONE_TEXTURE = TextureLoader.getTexture("PNG", new FileInputStream(STONE_PATH));
            WATER_TEXTURE = TextureLoader.getTexture("PNG", new FileInputStream(WATER_PATH));
            initialized = true;
        }catch (Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            System.out.println(sw.toString());
        }
    }
    public static Texture getBlockTexture(BlockType type){
        if(!initialized){
            initializeResources();
        }
        Texture retVal;
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
}
