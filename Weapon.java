public class Weapon extends Item{
  private int strength = 0;

  
  
  public Weapon(String txt, String texture, boolean isVisible, int str) {
    super(txt, texture, isVisible);
  
    strength = str;
  }
  
  public int getStrength() {
    return strength;
  }
} 
