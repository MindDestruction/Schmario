import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Player extends Character {
  
  Weapon currentWeapon = null;
  
  private int curXPos = 0;
  private int curYPos = 0;
  private int curXPosP2 = 300;
  private int curYPosP2 = 300;
  private int playerWidth = 26;
  private int playerHeight = 30;
  
  private Game game;
  private Map map;
  private Inventory inventory;
  private Interaction interaction;
  private Assets assets = new Assets();

  private boolean isPlayerMoving = false;
  
  private String lastDirection = "south";
  private String lastDirectionP2 = "south";
  private BufferedImage currentPlayerImage;
  private BufferedImage currentPlayerImageP2;
  
  
  
  public Player(Game game, Map map, Inventory inventory, int x, int y) {
    curXPos = x;
    curYPos = y;

    this.game = game;
    this.map = map;
    this.inventory = inventory;

    interaction = new Interaction(map, inventory);
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
  
  
  
  public void player2Move(int x, int y) {
    if (x < curXPosP2) {
      lastDirectionP2 = "west";
    } else if (x > curXPosP2) {
      lastDirectionP2 = "east";
    } else if (y < curYPosP2) {
      lastDirectionP2 = "north";
    } else if (y > curYPosP2) {
      lastDirectionP2 = "south";
    }
    curXPosP2 = x;
    curYPosP2 = y;
  }
  
  
  
  @Override
  public void tick() {
    isPlayerMoving = false;
    
    int currentRoomX = (int)(((curXPos + (playerWidth / 2)) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomY = (int)(((curYPos + (playerHeight / 2)) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    
    int currentRoomXAfterLeft = (int)((((curXPos - 3) + (playerWidth / 2)) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomXAfterRight = (int)((((curXPos + 3) + (playerWidth / 2)) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    
    int currentRoomYAfterUp = (int)((((curYPos - 3) + (playerHeight / 2)) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomYAfterDown = (int)((((curYPos + 3) + (playerHeight / 2)) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
  
    if (!isPlayerMoving && game.getKeyManager().up) {
      lastDirection = "north";
    
      if ((game.getMap().getReachableYMin() <= (curYPos - 3)) && (map.getRoom(currentRoomX, currentRoomYAfterUp).isRoomReachable() == true) && (map.getRoom(currentRoomX, currentRoomY).isNallowed())) {
        curYPos -= 3;
        Game.sendPlayerMovement();
        isPlayerMoving = true;
      } else if (game.getKeyManager().up && (game.getMap().getReachableYMin() > (curYPos - 3))) {
        curYPos = game.getMap().getReachableYMin();
        isPlayerMoving = true;
      } // end of if-else
    }
     
    if (!isPlayerMoving && game.getKeyManager().down) { 
      lastDirection = "south";
     
      if ((game.getMap().getReachableYMax() >= ((curYPos + 3) + playerHeight)) && (map.getRoom(currentRoomX, currentRoomYAfterDown).isRoomReachable() == true) && (map.getRoom(currentRoomX, currentRoomY).isSallowed())) {
        curYPos += 3;
        Game.sendPlayerMovement();
        isPlayerMoving = true;
      } else if (game.getMap().getReachableYMax() < (curYPos + 3)) {
        curYPos = game.getMap().getReachableYMax() - playerHeight;
        isPlayerMoving = true;
      }
    }
      
    if (!isPlayerMoving && game.getKeyManager().left) {
      lastDirection = "west";
    
      if ((game.getMap().getReachableXMin() <= (curXPos - 3)) && (map.getRoom(currentRoomXAfterLeft, currentRoomY).isRoomReachable() == true) && (map.getRoom(currentRoomX, currentRoomY).isWallowed())) {
        curXPos -= 3;
        Game.sendPlayerMovement();
        isPlayerMoving = true;
      } else if (game.getMap().getReachableXMin() > (curXPos - 3)) {
        curXPos = game.getMap().getReachableXMin();
        isPlayerMoving = true;
      }
    }
    
    if (!isPlayerMoving && game.getKeyManager().right) {
      lastDirection = "east";
    
      if ((game.getMap().getReachableXMax() >= ((curXPos + 3) + playerWidth)) && (map.getRoom(currentRoomXAfterRight, currentRoomY).isRoomReachable() == true) && (map.getRoom(currentRoomX, currentRoomY).isEallowed())) {
        curXPos += 3;
        Game.sendPlayerMovement();
        isPlayerMoving = true;
      } else if (game.getMap().getReachableXMax() < (curXPos + 3)) {
        curXPos = game.getMap().getReachableXMax() - playerWidth;
        isPlayerMoving = true;
      } // end of if-else
    }
    
    boolean eLocked = false;
    
    if (!isPlayerMoving && game.getKeyManager().btne && !eLocked) {
      interaction.interact(lastDirection, currentRoomX, currentRoomY);
      isPlayerMoving = true;
    }
  }
  
  @Override
  public void render(Graphics g) {
    switch (lastDirection) {
      case "north": 
        currentPlayerImage = assets.getTexture("p1n");
        break;
      case "east": 
        currentPlayerImage = assets.getTexture("p1e");
        break;
      case "south": 
        currentPlayerImage = assets.getTexture("p1s");
        break;
      case "west": 
        currentPlayerImage = assets.getTexture("p1w");
        break;
    } // end of switch (last)
    
    switch (lastDirectionP2) {
      case "north": 
        currentPlayerImageP2 = assets.getTexture("p2n");
        break;
      case "east": 
        currentPlayerImageP2 = assets.getTexture("p2e");
        break;
      case "south": 
        currentPlayerImageP2 = assets.getTexture("p2s");
        break;
      case "west": 
        currentPlayerImageP2 = assets.getTexture("p2w");
        break;
    } // end of switch (last)

    g.drawImage(currentPlayerImage, curXPos, curYPos, playerWidth, playerHeight, null);
    g.drawImage(currentPlayerImageP2, curXPosP2, curYPosP2, playerWidth, playerHeight, null);
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
