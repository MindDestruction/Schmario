import java.awt.Graphics;
import java.awt.*;

public class Map extends RenderAbstract {
  private final int MAP_WIDTH = 21;
  private final int MAP_HEIGHT = 21;
  private final int TILE_WIDTH_AND_HEIGHT = 30;
  
  private int xAdd = 0;
  private int yAdd = 0;
  private int wallWidth = 10;
  
  private Room[][] rooms = new Room[MAP_WIDTH][MAP_HEIGHT];
  private Monster[] monsters = new Monster[5];
  private Assets assets = new Assets();

  private int[][] startCoordinates = {{8, 8}, {3, 10}, {4, 16}};
  
  private int waterCount = 0;

  private int currentLevel = 1;
  
  
  
  public Map() {
    
  }
  
  
  
  public void createRooms() {

    for (int i = 0; i < 3; i++) {
      monsters[i] = null;
    }


    if (currentLevel == 1) {
      for (int x = 0; x <= 8; x++) {
        for (int y = 0; y <= 6; y++) {
          rooms[x][y] = new Room("water", false, this);
        }
      }
      
      for (int x = 9; x <= 11 ; x++) {
        for (int y = 0; y <= 6; y++) {
          rooms[x][y] = new Room("dirt", false, this);
        }
      }
      
      for (int x = 12; x <= 20; x++) {
        for (int y = 0; y <= 6; y++) {
          rooms[x][y] = new Room("grass", false, this);
        }
      }
      
      for (int x = 0; x <= 4; x++) {
        for (int y = 7; y <= 13; y++) {
          rooms[x][y] = new Room("grass", false, this);
        }
      }
      
      
      for (int x = 15; x <= 20; x++) {
        for (int y = 7; y <= 13; y++) {
          rooms[x][y] = new Room("grass", false, this);
        }
      }
      
      for (int x = 0; x <= 20; x++) {
        for (int y = 14; y <= 20; y++) {
          rooms[x][y] = new Room("grass", false, this);

        }
      }
      
      for (int x = 5; x <= 14; x++) {
        for (int y = 7; y <= 13; y++) {
          if (x == 5) {
            rooms[x][y] = new Room("bookshelf_left", false, this);
          } else if (x == 14) {
            rooms[x][y] = new Room("bookshelf_right", false, this);
          } else {
            rooms[x][y] = new Room("wooden_floor", true, this);
          }
        }    
      } 
      
      for (int x = 5; x <= 14; x++) {
        for (int y = 7; y <= 13; y++) {
          if (x != 11) {
            try {
              if (x == 5) addWall("e", 14, y);
              if (x == 14) addWall("w", 5, y);
              if (y == 7) addWall("n", x, 7);
              if (y == 13) addWall("s", x, 13);
            } catch(Exception e) {
              System.out.println("ERROR");
            }
          } 
          
        }    
      }

      addWall("door_top", 11, 7);
      addWall("s", 11, 13);
      
      rooms[7][7].addItem(new Item("A3", "note_a3", false));
      addOverlay("chest", false, 7, 7);
      rooms[7][10].addItem(new Item("B7", "note_b7", false));
      addOverlay("carpet", true, 7, 10);
      rooms[5][13].addItem(new Item("C5", "note_c5", false));
      rooms[12][10].addItem(new Item("D4", "note_d4", false));
      addOverlay("carpet", true, 12, 10);
      addOverlay("key", true, 11, 13);
      
      monsters[0] = new Monster(300, 360, 100, 12, "goblin", assets);
    } else if (currentLevel == 2) {
      for (int x = 0; x < MAP_WIDTH; x++) {
        for (int y = 0; y < MAP_HEIGHT; y++) {
          rooms[x][y] = new Room("stone_floor", true, this);
        }
      }
    }
  }



  public void nextLevel() {
    currentLevel++;
    createRooms();
  }
  
  
  
  public void addWall(String wallName, int x, int y) {
    switch (wallName) {
      case "n": 
        getRoom(x, y).setIsNallowed(false);
        
        if (y > 0) {
          getRoom(x, (y - 1)).setIsSallowed(false);
          getRoom(x, (y - 1)).setWallTextureName("wall_bottom");
        } // end of if
        break;
      case "e": 
        getRoom(x, y).setIsEallowed(false);
        
        if (x < (getMAP_WIDTH() - 1)) {
          getRoom((x + 1), y).setIsWallowed(false);
          getRoom((x + 1), y).setWallTextureName("wall_left");
        } // end of if
        break;
      case "s": 
        getRoom(x, y).setIsSallowed(false);
        
        if (y < (getMAP_HEIGHT() - 1)) {
          getRoom(x, (y + 1)).setIsNallowed(false);
          getRoom(x, (y + 1)).setWallTextureName("wall_top");
        } // end of if
        break;
      case "w": 
        getRoom(x, y).setIsWallowed(false);
        
        if (x > 0) {
          getRoom((x - 1), y).setIsEallowed(false);
          getRoom((x - 1), y).setWallTextureName("wall_right");
        } // end of if
        break;



      case "door_top": 
        getRoom(x, y).setIsWallowed(false);
        
        if (y > 0) {
          getRoom(x, (y - 1)).setIsSallowed(false);
          getRoom(x, (y - 1)).setWallTextureName("door_bottom");
        } // end of if
        break;
      case "door_right": 
        getRoom(x, y).setIsWallowed(false);
        
        if (x < (getMAP_WIDTH() - 1)) {
          getRoom((x + 1), y).setIsWallowed(false);
          getRoom((x + 1), y).setWallTextureName("door_left");
        } // end of if
        break;
      case "door_bottom": 
        getRoom(x, y).setIsWallowed(false);
        
        if (y < (getMAP_HEIGHT() - 1)) {
          getRoom(x, (y + 1)).setIsNallowed(false);
          getRoom(x, (y + 1)).setWallTextureName("door_top");
        } // end of if
        break;
      case "door_left": 
        getRoom(x, y).setIsWallowed(false);
        
        if (x> 0) {
          getRoom((x - 1), y).setIsEallowed(false);
          getRoom((x - 1), y).setWallTextureName("door_right");
        } // end of if
        break;
    } // end of switch
  }
 
  public void addOverlay(String overlayName, boolean isReachable, int x, int y) {
    getRoom(x, y).setIsRoomReachable(isReachable);
    getRoom(x, y).setRoomOverlayImage(overlayName);
  }



  public int getStartX() {
    return startCoordinates[currentLevel - 1][0];
  }

  public int getStartY() {
    return startCoordinates[currentLevel - 1][1];
  }



  public void levelCompleted() {
    currentLevel++;
    createRooms();
  }
  
  
  
  public boolean isGoNorthPossible(int x, int y) {
    if ((rooms[x][y].isNallowed() == true) && (y > 0)) {
      return true;
    }
    
    return false;
  }
  
  public boolean isGoEastPossible(int x, int y) {
    if ((rooms[x][y].isEallowed() == true) && (x < (MAP_WIDTH - 1))) {
      return true;
    }
    
    return false;
  }
  
  public boolean isGoSouthPossible(int x, int y) {
    if ((rooms[x][y].isSallowed() == true) && (y < (MAP_HEIGHT - 1))) {
      return true;
    }
    
    return false;
  }
  
  public boolean isGoWestPossible(int x, int y) {
    if ((rooms[x][y].isWallowed() == true) && (x > 0)) {
      return true;
    }
    
    return false;
  }
  
  public int getReachableXMin() {
    return xAdd;
  }
  
  public int getReachableXMax() {
    return MAP_WIDTH * TILE_WIDTH_AND_HEIGHT;
  }
  
  public int getReachableYMin() {
    return yAdd;
  }
  
  public int getReachableYMax() {
    return MAP_HEIGHT * TILE_WIDTH_AND_HEIGHT;
  }
  
  public int getWallWidth() {
    return wallWidth;
  }
  
  public Room getRoom(int x, int y) {
    return rooms[x][y];
  }
  
  public int getMAP_WIDTH() {
    return MAP_WIDTH;
  }
  
  public int getMAP_HEIGHT() {
    return MAP_HEIGHT;
  }
  
  
  
  public int getXAdd() {
    return xAdd;
  }
  
  public int getYAdd() {
    return yAdd;
  }
  
  
  
  public int getTILE_WIDTH_AND_HEIGHT() {
    return TILE_WIDTH_AND_HEIGHT;
  }
  
  
  
  @Override
  public void tick() {
    for (int i = 0; i < 5; i++) {
      if (monsters[i] != null) monsters[i].tick();
    }
  }
  
  @Override
  public void render(Graphics g) {
    for (int x = 0; x < MAP_WIDTH; x++) {
      for (int y = 0; y < MAP_HEIGHT; y++) {
        if (rooms[x][y] != null) {
          g.setColor(Color.black);
          g.fillRect(630, 0, 5, 630);
          
          if (rooms[x][y].getRoomImageName().equals("water")) {
            if (waterCount <= 119) {
              g.drawImage(assets.getTexture("water_0"), (x * TILE_WIDTH_AND_HEIGHT), (y * TILE_WIDTH_AND_HEIGHT), TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, null);
            } else {
              g.drawImage(assets.getTexture("water_1"), (x * TILE_WIDTH_AND_HEIGHT), (y * TILE_WIDTH_AND_HEIGHT), TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, null);
            } // end of if-else
              
            if (waterCount == 239) {
              waterCount = 0;
            }
          } else {
            g.drawImage(assets.getTexture(rooms[x][y].getRoomImageName()), (x * TILE_WIDTH_AND_HEIGHT), (y * TILE_WIDTH_AND_HEIGHT), TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, null);
          } // end of if-else
          
          if (rooms[x][y].getWallTextureName() != null) {
            g.drawImage(assets.getTexture(rooms[x][y].getWallTextureName()), (x * TILE_WIDTH_AND_HEIGHT), (y * TILE_WIDTH_AND_HEIGHT), TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, null);
          }

          if (rooms[x][y].getRoomOverlayImage() != null) {
            g.drawImage(assets.getTexture(rooms[x][y].getRoomOverlayImage()), (x * TILE_WIDTH_AND_HEIGHT), (y * TILE_WIDTH_AND_HEIGHT), TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, null);
          }

          if ((rooms[x][y].getItem() != null) && (rooms[x][y].getItem().isVisible())) {
            g.drawImage(assets.getTexture(rooms[x][y].getItem().getTextureName()), (x * TILE_WIDTH_AND_HEIGHT), (y * TILE_WIDTH_AND_HEIGHT), TILE_WIDTH_AND_HEIGHT, TILE_WIDTH_AND_HEIGHT, null);
          }
        }
      } // end of for
    } // end of for
    
    for (int i = 0; i < 5; i++) {
      if (monsters[i] != null) monsters[i].render(g);
    }

    waterCount++;

    g.drawImage(assets.getTexture("inventory"), 635, 0, 400, 630, null);

  }
  
  
                
  public String getItemTextFromCurrentRoom(int x, int y) {
    return rooms[x][y].getItem().getText();
  }
  
  public Item getCurrentRoomItem(int x, int y) {
    Item itm = rooms[x][y].getItem();
    
    return itm;
  }
  
  public Item getAndDeleteCurrentRoomItem(int x, int y) {
    Item item = rooms[x][y].getItem();
    rooms[x][y].deleteItem();
    
    return item;
  }
  
  public int getMonsterCountFromCurrentRoom(int x, int y) {
    return rooms[x][y].getMonsterCountFromCurrentRoom();
  }
  
  public Monster[] getAllMonsterFromCurrentRoom(int x, int y) {
    return rooms[x][y].getAllMonstersInRoom();
  }

}

