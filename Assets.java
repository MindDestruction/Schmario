import java.awt.image.BufferedImage;
import java.io.IOException;

public class Assets {
  
  public static String[] texturesList = {"armor", "bookshelf_bottom", "bookshelf_left", "bookshelf_right", "bookshelf_top", "bricks", "carpet", "chest", "dirt", "door_top", "door_right", "door_bottom", "door_left", "door_opened_top", "door_opened_right", "door_opened_bottom", "door_opened_left", "goblin", "grass", "key", "note_a3", "note_b7", "note_c5", "note_d4", "opened_chest", "p1n", "p1e", "p1s", "p1w", "p2n", "p2e", "p2s", "p2w", "rat", "skeleton", "snake", "stone_floor", "sword", "wall_bottom", "wall_left", "wall_right", "wall_top", "water_0", "water_1", "wooden_floor"};
  
  public static Texture[] texturesArray = new Texture[64];
  
  public static BufferedImage grass;

  public static void init() throws IOException{
    for (int i = 0; i < texturesList.length; i++) {
      texturesArray[i] = new Texture(texturesList[i]);
    } // end of for
  }
  
  public static BufferedImage getTexture(String textureName) {
    BufferedImage requestedTexture = null;
    Boolean textureFound = false;
    int i = 0;
  
    while (!textureFound) { 
      if (textureName.equals(texturesArray[i].getTextureName())){
        requestedTexture = texturesArray[i].getTextureImage();
        
        textureFound = true;
      }
      
      i++;
    } // end of while
    
    return requestedTexture;
  }
  
}