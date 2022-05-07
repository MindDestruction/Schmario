import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture {
  private String textureName;
  private String texturePath;
  private BufferedImage textureImage;
  
  public Texture(String name) {
    this.textureName = name;
    this.texturePath = "new_textures/" + name + ".png";
    
    try {
      init();
    } catch(IOException e) {
      e.printStackTrace(); 
    }
  }
  
  public String getTextureName() {
    return textureName;
  }
  
  public String getTexturePath() {
    return texturePath;
  }
  
  public void setTextureImage(BufferedImage img) {
    this.textureImage = img;
  }
  
  public BufferedImage getTextureImage() {
    return textureImage;
  }
  
  public void init() throws IOException{
    this.textureImage = ImageIO.read(new File(texturePath));
  }
  
}