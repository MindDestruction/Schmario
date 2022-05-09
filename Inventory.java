import java.awt.Graphics;

public class Inventory extends RenderAbstract {
  private final int MAX_ITEMS_IN_INVENTORY = 54;

  private int itemCount = 0;

  private Item[] itemArray = new Item[MAX_ITEMS_IN_INVENTORY];
  private Assets assets = new Assets();
  
  
  
  public Inventory() {
    
  }

  public void addItem(Item itm) {
    itemArray[itemCount] = itm;
    itemCount++;
  }

  public boolean searchItem(String wantedItem) {
    boolean hasWantedItem = false;

    for (int i = 0; i < itemCount; i++) {
      if (wantedItem.equals(itemArray[i].getText())) {
        hasWantedItem = true;
      }
    }

    return hasWantedItem;
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
  public void tick() {}
  
  @Override
  public void render(Graphics g) {
    for (int i = 0; i < itemCount; i++) {
      if (itemArray[i] != null) {
        if (i < 2) {
          g.drawImage(assets.getTexture(itemArray[i].getTextureName()), (640 + (i * 160)), 10, 150, 150, null);
        } else if (i < 4) {
          g.drawImage(assets.getTexture(itemArray[i].getTextureName()), (640 + ((i - 2) * 160)), 170, 150, 150, null);
        } else {
          g.drawImage(assets.getTexture(itemArray[i].getTextureName()), (640 + ((i - 4) * 160)), 330, 150, 150, null);
        }
      }
    }
  }
}
