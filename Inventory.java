/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 13.12.2021
  * @author 
  */
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;
import java.awt.image.BufferedImage;

public class Inventory extends RenderAbstract {
  private final int MAX_ITEMS_IN_INVENTORY = 18;
  private int itemCount = 0;
  private Item[] itemArray = new Item[MAX_ITEMS_IN_INVENTORY];
  
  
  
  public Inventory() {
    
  }

  public void addItem(Item itm) {
    itemArray[itemCount] = itm;
    itemCount++;
  }
  
  public Item getItem(int itemNumber) {
    return itemArray[itemNumber];
  }
  
  public Weapon getFirstWeapon(){
    Weapon weapon = null;
    
    for (int i = 0; i < itemCount; i++) {
      if ((itemArray[i] instanceof Weapon) && (weapon == null)) {
        weapon = (Weapon)itemArray[i];
      }
    } // end of for
    
    return weapon;
  }
  
  public void removeItemNumber(int itemNumber) {
    itemCount--;
    
    for (int i = itemNumber; i <= itemCount; i++) {
      if (i < itemCount) {
        itemArray[i] = itemArray[i + 1];
      } else if (i == itemCount) {
        itemArray[itemCount] = null;
      }
    }     
  }
  
  public int getItemCount() {
    return itemCount;
  }
  
  @Override
  public void tick() {
    
  }
  
  @Override
  public void render(Graphics g) {
    g.setColor(Color.black);
    //g.fillRect(635, 0, 360, 415);
    
    g.setColor(Color.lightGray);
    
    g.setColor(Color.lightGray);
    /*for (int x = 640; x <= 885; x += 120) {
      for (int y = 50; y <= 170; y += 120) {
        g.fillRoundRect(x, y, 110, 110, 45, 45);
      } // end of for
      
    } // end of for*/
  }
}
