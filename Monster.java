/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.02.2022
 * @author 
 */

public class Monster  {
  
  private String text;
  
  public Monster(int eine_r, String txt, int health, int maxHealth, int armor, int damage, int dex) {
    //super(health, maxHealth, armor, damage, dex);
    
    this.text = txt;
  }
  
  public String getMonsterText() {
    return text;
  }

} // end of class Monster
