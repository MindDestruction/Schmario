/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.02.2022
 * @author 
 */
import java.awt.Graphics;

public abstract class Character {
  
  private int health;
  private int maxHealth; 
  private int armor;
  private int damage; 
  private int dex;
  
  public Character() {
    
  }
  
  public int getHealth() {
    return health;
  }
  
  public void setHealth(int health) {
    this.health = health;
  }
  
  public void addHealth(int healHealth) {
    if ((health + healHealth) <= maxHealth) {
      this.health += healHealth;
    } else {
      this.health = maxHealth;  
    } // end of if-else
  }
  
  public int getMaxHealth() {
    return maxHealth;
  }
  
  public int getArmor() {
    return armor;
  }
  
  public int getDamage() {
    return damage;
  }
  
  public void getHurt(int damageToGet) {
    health -= damageToGet;
  }
  
  public int getDex() {
    return dex;
  }
  
  public abstract void tick();
  
  public abstract void render(Graphics g);
} // end of class Character
