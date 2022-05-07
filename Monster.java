import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Monster extends Character {
  private int health, damage, xPos, yPos;
  private String textureName;
  private Assets assets;
  
  public Monster(int x, int y, int health, int damage, String textureName, Assets assets) {
    this.xPos = x;
    this.yPos = y;
    this.health = health;
    this.damage = damage;
    this.textureName = textureName;
    this.assets = assets;
  }
  


  @Override
  public void tick() {

  }

  @Override
  public void render(Graphics g) {
    g.drawImage(assets.getTexture(textureName), xPos, yPos, 26, 30, null);
  }
} // end of class Monster
