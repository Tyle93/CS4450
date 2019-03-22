package Vox;

import org.lwjgl.util.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.FileInputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ResourceManager {
    static private boolean initialized = false;
    private static Texture DIRT_TEXTURE[]= new Texture[3];
    private static Texture SAND_TEXTURE[]= new Texture[3];
    private static Texture WATER_TEXTURE[]= new Texture[3];
    private static Texture BEDROCK_TEXTURE[] = new Texture[3];
    private static Texture GRASS_TEXTURE[] = new Texture[3];
    private static Texture STONE_TEXTURE[] = new Texture[3];
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
}
