/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 14.02.2022
 * @author 
 */
import java.util.Random;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Character {
  
  Weapon currentWeapon = null;
  
  private int curXPos = 0;
  private int curYPos = 0;
  private int playerWidth = 26;
  private int playerHeight = 30;
  
  private Game game;
  private Map map;
  
  private boolean isPlayerMoving = false;
  
  private String lastDirection = "south";
  private BufferedImage currentPlayerImage;
  
  
  
  public Player(Game game, Map map, int x, int y) {
    super(game, x, y);
    
    curXPos = x;
    curYPos = y;
    this.game = game;
    this.map = map;
  }
  
  
  
  public int getCurrentXPos() {
    return curXPos;
  }
  
  public int getCurrentYPos() {
    return curYPos;
  }
  
  
  
  public Weapon getCurrentWeapon() {
    return currentWeapon;
  }
  
  public void setCurrentWeapon(Weapon weapon) {
    currentWeapon = weapon;
  }
  
  
  
  public static void wait(int time){
    try {
      Thread.sleep(time);
    } catch(Exception e) {
      e.printStackTrace();
    } 
  }
  
  
  
  @Override
  public void tick() {
    isPlayerMoving = false;
    
    int currentRoomX = (int)((curXPos - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomY = (int)((curYPos - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    
    int currentRoomXAfterLeft = (int)(((curXPos - 3) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomXAfterRight = (int)(((curXPos + 3) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    
    int currentRoomYAfterUp = (int)(((curYPos - 3) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomYAfterDown = (int)(((curYPos + 3) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
  
    if (!isPlayerMoving && game.getKeyManager().up) {
      lastDirection = "north";
    
      if ((game.getMap().getReachableYMin() <= (curYPos - 3)) && (map.getRoom(currentRoomX, currentRoomYAfterUp).isRoomReachable() == true)/* && (map.getRoom(currentRoomX, currentRoomY).isNallowed())*/) {
        curYPos -= 3;
        isPlayerMoving = true;
      } else if (game.getKeyManager().up && (game.getMap().getReachableYMin() > (curYPos - 3))) {
        curYPos = game.getMap().getReachableYMin();
        isPlayerMoving = true;
      } // end of if-else
    }
     
    if (!isPlayerMoving && game.getKeyManager().down) { 
      lastDirection = "south";
     
      if ((game.getMap().getReachableYMax() >= ((curYPos + 3) + playerHeight)) && (map.getRoom(currentRoomX, currentRoomYAfterDown).isRoomReachable() == true)/* && (map.getRoom(currentRoomX, currentRoomY).isSallowed())*/) {
        curYPos += 3;
        isPlayerMoving = true;
      } else if (game.getMap().getReachableYMax() < (curYPos + 3)) {
        curYPos = game.getMap().getReachableYMax() - playerHeight;
        isPlayerMoving = true;
      }
    }
      
    if (!isPlayerMoving && game.getKeyManager().left) {
      lastDirection = "west";
    
      if ((game.getMap().getReachableXMin() <= (curXPos - 3)) && (map.getRoom(currentRoomXAfterLeft, currentRoomY).isRoomReachable() == true)/* && (map.getRoom(currentRoomX, currentRoomY).isWallowed())*/) {
        curXPos -= 3;
        isPlayerMoving = true;
      } else if (game.getMap().getReachableXMin() > (curXPos - 3)) {
        curXPos = game.getMap().getReachableXMin();
        isPlayerMoving = true;
      }
    }
    
    if (!isPlayerMoving && game.getKeyManager().right) {
      lastDirection = "east";
    
      if ((game.getMap().getReachableXMax() >= ((curXPos + 3) + playerWidth)) && (map.getRoom(currentRoomXAfterRight, currentRoomY).isRoomReachable() == true)/* && (map.getRoom(currentRoomX, currentRoomY).isEallowed())*/) {
        curXPos += 3;
        isPlayerMoving = true;
      } else if (game.getMap().getReachableXMax() < (curXPos + 3)) {
        curXPos = game.getMap().getReachableXMax() - playerWidth;
        isPlayerMoving = true;
      } // end of if-else
    }
    
    boolean eLocked = false;
    
    if (!isPlayerMoving && game.getKeyManager().e && !eLocked) {
      if (lastDirection.equals("north")) {
        map.getRoom(currentRoomX, (currentRoomY - 1));
        isPlayerMoving = true;
      } // end of if-else
    }
  }
  
  @Override
  public void render(Graphics g) {
    switch (lastDirection) {
      case "north": 
        currentPlayerImage = Assets.getTexture("p1n");
        break;
      case "east": 
        currentPlayerImage = Assets.getTexture("p1e");
        break;
      case "south": 
        currentPlayerImage = Assets.getTexture("p1s");
        break;
      case "west": 
        currentPlayerImage = Assets.getTexture("p1w");
        break;
    } // end of switch (last)
    
    g.drawImage(currentPlayerImage, curXPos, curYPos, playerWidth, playerHeight, null);
  }
  
  
  
  
  
  public void goNorth() {
    curYPos--;
  }
  
  public void goEast() {
    curXPos++;
  }
  
  public void goSouth() {
    curYPos++;
  }
  
  public void goWest() {
    curXPos--;
  }
} // end of class Player
