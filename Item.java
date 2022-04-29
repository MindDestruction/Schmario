/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 26.11.2021
  * @author 
  */
import java.awt.image.BufferedImage;

public class Item {
  
  // Anfang Attribute
  private String text;
  private BufferedImage texture;
  private boolean isVisible;
  // Ende Attribute
  
  // Anfang Methoden
  public Item (String text, BufferedImage texture, boolean isVisible) {
    this.text = text;
    this.texture = texture;
    this.isVisible = isVisible;
  }
  
  public String getText() {
    return text;
  }
  
  public BufferedImage getTexture() {
    return texture;
  }

  // Ende Methoden
}
