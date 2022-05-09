import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends Character {
  
  Weapon currentWeapon = null;
  
  private int curXPos = 0;
  private int curYPos = 0;
  private int curXPosP2 = 300;
  private int curYPosP2 = 300;
  private int playerWidth = 26;
  private int playerHeight = 30;
  private int healthPoints = 30;
  private int damage = 10;
  
  private Game game;
  private Map map;
  private Inventory inventory;
  private Interaction interaction;
  private Assets assets = new Assets();

  private boolean isPlayerMoving = false;
  private boolean isInFight = false;
  
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

    interaction = new Interaction(map, inventory, this);
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



  public void nextLevel() {
    curXPos = map.getStartX();
    curYPos = map.getStartY();
  }
  
  
  
  public void player2Move(int x, int y) {
    map.getRoom((curXPosP2 + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPosP2 + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(true);

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
    curXPosP2 = y;

    map.getRoom((curXPosP2 + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPosP2 + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
  }
  
  
  
  @Override
  public void tick() {
    player2Move(curXPosP2, curYPosP2);
    isPlayerMoving = false;
    map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(true);
    
    int currentRoomX = (int)(((curXPos + (playerWidth / 2)) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
    int currentRoomY = (int)(((curYPos + (playerHeight / 2)) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT());
  
    if (!isPlayerMoving && game.getKeyManager().up) {
      lastDirection = "north";
    
      if ((game.getMap().getReachableYMin() <= (curYPos - 3)) && (map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT() - 1).isRoomReachable() == true)) {
        if ((map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT()).isNallowed() == true) && (map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT() - 1).isSallowed() == true)) {
          if (map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName() != null) {
            if ((!map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("wall_bottom")) && (!map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("door_closed_bottom"))) {
              curYPos -= 3;
              isPlayerMoving = true;
              Game.sendPlayerMovement();
              map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
            }
          } else {
            curYPos -= 3;
            isPlayerMoving = true;
            Game.sendPlayerMovement();
            map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
          }
        } else {
          //curYPos -= 3;
          //isPlayerMoving = true;
          //map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
        }
      }
    }
     
    if (!isPlayerMoving && game.getKeyManager().down) { 
      lastDirection = "south";
      
      if ((game.getMap().getReachableYMin() <= (curYPos + 3)) && (map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + playerHeight - map.getYAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT()).isRoomReachable() == true)) {
        if ((map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos - map.getYAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT()).isSallowed() == true) && (map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos - map.getYAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT() + 1).isNallowed() == true)) {
          if (map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos - map.getYAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName() != null) {
            if ((!map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos - map.getYAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("wall_bottom")) && (!map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos - map.getYAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("door_closed_bottom"))) {
              curYPos += 3;
              isPlayerMoving = true;
              Game.sendPlayerMovement();
              map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
            }
          } else {
            curYPos += 3;
            isPlayerMoving = true;
            Game.sendPlayerMovement();
            map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
          }
        } else {
          //curYPos += 3;
          //sPlayerMoving = true;
          //map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
        }
      }
        
      if (!isPlayerMoving && game.getKeyManager().left) {
        lastDirection = "west";
      
    if (!isPlayerMoving && game.getKeyManager().left) {
      lastDirection = "west";
    
      if ((game.getMap().getReachableXMin() <= (curXPos - 3)) && (map.getRoom((curXPos - map.getXAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).isRoomReachable() == true)) {
        if ((map.getRoom((curXPos - map.getXAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).isWallowed() == true) && (map.getRoom((curXPos - map.getXAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT() - 1).isEallowed() == true)) {
          if (map.getRoom((curXPos - map.getXAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName() != null) {
            if ((!map.getRoom((curXPos - map.getXAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("wall_bottom")) && (!map.getRoom((curXPos - map.getXAdd() - 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("door_closed_bottom"))) {
              curXPos -= 3;
              isPlayerMoving = true;
              Game.sendPlayerMovement();
              map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
            }
          } else {
            curXPos -= 3;
            isPlayerMoving = true;
            Game.sendPlayerMovement();
            map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
          }
        } else {
          //curXPos -= 3;
          //isPlayerMoving = true;
          //map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
        }
      }
    }
    
    if (!isPlayerMoving && game.getKeyManager().right) {
      lastDirection = "east";

      if ((game.getMap().getReachableXMin() <= (curXPos + 3)) && (map.getRoom((curXPos + playerWidth - map.getXAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).isRoomReachable() == true)) {
        if ((map.getRoom((curXPos + playerWidth - map.getXAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).isEallowed() == true) && (map.getRoom((curXPos + playerWidth - map.getXAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT() + 1).isWallowed() == true)) {
          if (map.getRoom((curXPos + playerWidth - map.getXAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName() != null) {
            if ((!map.getRoom((curXPos + playerWidth - map.getXAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("wall_bottom")) && (!map.getRoom((curXPos + playerWidth - map.getXAdd() + 3) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).getWallTextureName().equals("door_closed_bottom"))) {
              curXPos += 3;
              isPlayerMoving = true;
              Game.sendPlayerMovement();
              map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
            }
          } else {
            curXPos += 3;
            isPlayerMoving = true;
            Game.sendPlayerMovement();
            map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
          }
        } else {
          //curXPos += 3;
          //isPlayerMoving = true;
          //map.getRoom((curXPos + (playerWidth / 2) - map.getXAdd()) / map.getTILE_WIDTH_AND_HEIGHT(), (curYPos + (playerHeight / 2) - map.getYAdd()) / map.getTILE_WIDTH_AND_HEIGHT()).setIsRoomReachable(false);
        }
      }
    }
    
    boolean eLocked = false;
    
    if (!isPlayerMoving && game.getKeyManager().btne && !eLocked) {
      interaction.interact(lastDirection, currentRoomX, currentRoomY);
      isPlayerMoving = true;
    } else {

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

  public void fight(int currentRoomX, int currentRoomY) {

    // temporary
    Monster testMonster = new Monster(300, 360, 100, 12, "goblin", assets);
 
    switch (lastDirection) {
      case "north": // if monster on tile next to player in direction north or one of the two next to it
        if (map.getRoom(currentRoomX, currentRoomY-1) != null || map.getRoom(currentRoomX-1, currentRoomY-1) != null || map.getRoom(currentRoomX+1, currentRoomY-1) != null) {
          //isInFight = true;
          // player attack
          Timer pTimer = new Timer();
          TimerTask pAttackTask = new TimerTask() {
              @Override
              public void run() {
                  monsterHit(testMonster);
              }
          };
          pTimer.schedule(pAttackTask, 1000, 1000);

          // monster attack
          Timer mTimer = new Timer();
          TimerTask mAttackTask = new TimerTask() {
              @Override
              public void run() {
                  monsterHit(testMonster);
              }
          };
          mTimer.schedule(mAttackTask, 1000, 1000);
        }
        break;
      case "east": // if monster on tile next to player in direction east or one of the two next to it
        if (map.getRoom(currentRoomX+1, currentRoomY) != null || map.getRoom(currentRoomX+1, currentRoomY+1) != null || map.getRoom(currentRoomX+1, currentRoomY-1) != null) {
          // player attack
        }
        break;
      case "south": // if monster on tile next to player in direction south or one of the two next to it
        if (map.getRoom(currentRoomX, currentRoomY+1) != null || map.getRoom(currentRoomX-1, currentRoomY+1) != null || map.getRoom(currentRoomX+1, currentRoomY+1) != null) {
        }
        break;
      case "west": // if monster on tile next to player in direction west or one of the two next to it
        if (map.getRoom(currentRoomX-1, currentRoomY) != null || map.getRoom(currentRoomX-1, currentRoomY+1) != null || map.getRoom(currentRoomX-1, currentRoomY-1) != null) {
          // player attack
        }
        break;
    } // end of switch (lastDirection)
  }

  public void monsterHit(Monster monster) {
    System.out.println(monster.getDamage());
    healthPoints = healthPoints - monster.getDamage();
    if (healthPoints <= 0) {
      System.out.println(" -- PLAYER DEAD -- ");
      isInFight = false;
    }
    System.out.println(healthPoints);
  }

  public void playerHit(Monster monster) {
    System.out.println("playerHit");
    monster.setHealth(monster.getHealth() - damage);
    if (monster.getHealth() <= 0) {
      System.out.println(" -- MONSTER DEAD -- ");
      isInFight = false;
    }
  }

} // end of class Player
