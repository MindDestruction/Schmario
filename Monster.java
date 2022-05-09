import java.awt.Graphics;

public class Monster extends Character {
  private int health, damage, xPos, yPos, finalCoordinate, startCoordinate, targetCoordinate;
  private int oldXPos = 0;
  private int oldYPos = 0;
  private String textureName, direction;
  private Assets assets;
  private Map map;
  private int width = 26;
  private int height = 30;
  private int waitCounter = 0;
  
  public Monster(int x, int y, String direction, int finalCoordinate, int health, int damage, String textureName, Assets assets, Map map) {
    this.xPos = x;
    this.yPos = y;
    this.direction = direction;
    this.finalCoordinate = finalCoordinate;
    this.health = health;
    this.damage = damage;
    this.textureName = textureName;
    this.assets = assets;
    this.map = map;

    this.targetCoordinate = finalCoordinate;

    if (direction.equals("north") || direction.equals("south")) startCoordinate = y;
    if (direction.equals("east") || direction.equals("west")) startCoordinate = x;
  }
  


  @Override
  public void tick() {
    oldXPos = xPos;
    oldYPos = yPos;

    map.getRoom(((xPos + (width / 2)) / map.getTILE_WIDTH_AND_HEIGHT()), ((yPos + (height / 2)) / map.getTILE_WIDTH_AND_HEIGHT())).setIsRoomReachable(true);

    if ((direction.equals("north")) && ((yPos - 3) >= targetCoordinate)) {
      yPos -= 3;
    }
    
    if ((direction.equals("east")) && ((xPos + 3) <= targetCoordinate)) {
      xPos += 3;
    } else 
    
    if ((direction.equals("south")) && ((yPos + 3) <= targetCoordinate)) {
      yPos += 3;
    } else 
    
    if ((direction.equals("west")) && ((xPos - 3) >= targetCoordinate)) {
      xPos -= 3;
    }

    if ((xPos == oldXPos) && (yPos == oldYPos)) {
      waitCounter++;

      if (waitCounter == 120) {
        switch (direction) {
          case "north":
            direction = "south";

            if (yPos == finalCoordinate) {
              targetCoordinate = startCoordinate;
            } else { 
              targetCoordinate = finalCoordinate;
            }

            break;
          case "east":
            direction = "west";

            if (xPos == finalCoordinate) {
              targetCoordinate = startCoordinate;
            } else { 
              targetCoordinate = finalCoordinate;
            }

            break;
          case "south":
            direction = "north";

            if (yPos == finalCoordinate) {
              targetCoordinate = startCoordinate;
            } else { 
              targetCoordinate = finalCoordinate;
            }

            break;
          case "west":
            direction = "east";

            if (xPos == finalCoordinate) {
              targetCoordinate = startCoordinate;
            } else { 
              targetCoordinate = finalCoordinate;
            }

            break;
        }

        waitCounter = 0;
      }
    }

    map.getRoom(((xPos + (width / 2)) / map.getTILE_WIDTH_AND_HEIGHT()), ((yPos + (height / 2)) / map.getTILE_WIDTH_AND_HEIGHT())).setIsRoomReachable(false);
  }

  @Override
  public void render(Graphics g) {
    g.drawImage(assets.getTexture(textureName), xPos, yPos, width, height, null);
  }
} // end of class Monster
