/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 10.01.2022
  * @author 
  */
import java.awt.image.BufferedImage;

public class Weapon extends Item{

  private int strength = 0;
  private String nameForTexts;
  
  public Weapon(String txt, BufferedImage texture, boolean isVisible, int str) {
    super(txt, texture, isVisible);
  
    strength = str;
  }
  
  public int getStrength() {
    return strength;
  }

} // end of class Weapon
