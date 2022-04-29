/**
  *
  * Beschreibung
  *
  * @version 1.0 vom 05.11.2021
  * @author 
  */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;



public class Room {
  private boolean isNorthAllowed;
  private boolean isEastAllowed;
  private boolean isSouthAllowed;
  private boolean isWestAllowed;
  private String roomImageName;
  private String roomWall;
  private String wallTextureName;
  private boolean isReachable = true;
  
  private Item item;
  private Monster monster;
  private Map map;
  
  private int monsterCount = 0;
  private int monsterCountFromCurrentRoom = 0;
  
  public Monster[] monstersInRoom = new Monster[3];
  public Monster[] allMonsters = new Monster[27];
  // Ende Attribute
  
  
  
  // Anfang Methoden
  public Room(String imgName, boolean isReachable, Map map) {
    roomImageName = imgName;
    this.map = map;
    this.isReachable = isReachable;
    item = null;
    monster = null;
  }
  
  public void addItem(Item itmToAdd) {
    item = itmToAdd;
  }
  
  public Item getItem() {
    return item;
  }
  
  public void deleteItem() {
    item = null;
  }
  
  
  
  public boolean isNallowed() {
    return isNorthAllowed;
  }
    
  public boolean isEallowed() {
    return isEastAllowed;
  }
    
  public boolean isSallowed() {
    return isSouthAllowed;
  }
    
  public boolean isWallowed() {
    return isWestAllowed;
  }
  
  public void setIsNallowed(boolean isNa) {
    isNorthAllowed = isNa;
  }
  
  public void setIsEallowed(boolean isEa) {
    isEastAllowed = isEa;
  }
  
  public void setIsSallowed(boolean isSa) {
    isSouthAllowed = isSa;
  }
  
  public void setIsWallowed(boolean isWa) {
    isWestAllowed = isWa;
  }
  
  public boolean isRoomReachable() {
    return isReachable;
  }

  public String getRoomImageName() {
    return roomImageName;
  }
  
  
  
  public void setRoomWall(String wall) {
    roomWall = wall;
  }
  
  public String getRoomWall() {
    return roomWall;
  }
  
  
  
  public void addMonster(Monster monster) {
    int monstersAdded = 0;
    
    for (int i = 0; i <= 2; i++) {
      if ((monstersInRoom[i] == null) && (monstersAdded == 0)) {
        this.monstersInRoom[i] = monster;
        monsterCount++;
        monsterCountFromCurrentRoom++;
        monstersAdded++;
      }
    } // end of for
  }
  
  public boolean isMonsterInRoom() {
    if (monster != null) return true; else return false;
  }
  
  public void removeMonsterNo(int monsterNo) {
    monster = null;
  }
  
  public int getMonsterCount() {
    return monsterCount;
  }
  
  public int getMonsterCountFromCurrentRoom() {
    return monsterCountFromCurrentRoom;
  }

  public Monster getMonsterInCurrentRoomNo(int wantedMonster) {
    return monstersInRoom[wantedMonster];
  }
  
  public String getMonsterTextInCurrentRoomNo(int wantedMonster) {
    if (monstersInRoom[wantedMonster] != null) {
      return monstersInRoom[wantedMonster].getMonsterText();
    }
    
    return "";
  }
  
  public Monster[] getAllMonstersInRoom() {
    return monstersInRoom;
  }
  
  
  
  public void addWall(String wallName, int x, int y) {
    switch (wallName) {
      case "n": 
        map.getRoom(x, y).setIsNallowed(false);
        
        if (y > 0) {
          map.getRoom(x, (y - 1)).setIsSallowed(false);
          map.getRoom(x, (y - 1)).setWallTextureName(wallName);
        } // end of if
        break;
      case "e": 
        map.getRoom(x, y).setIsEallowed(false);
        
        if (x < (map.getMAP_WIDTH() - 1)) {
          map.getRoom((x + 1), y).setIsWallowed(false);
          map.getRoom((x + 1), y).setWallTextureName(wallName);
        } // end of if
        break;
      case "s": 
        map.getRoom(x, y).setIsSallowed(false);
        
        if (y < (map.getMAP_HEIGHT() - 1)) {
          map.getRoom(x, (y - 1)).setIsNallowed(false);
          map.getRoom(x, (y - 1)).setWallTextureName(wallName);
        } // end of if
        break;
      case "w": 
        map.getRoom(x, y).setIsWallowed(false);
        
        if (y > 0) {
          map.getRoom((x - 1), y).setIsEallowed(false);
          map.getRoom((x - 1), y).setWallTextureName(wallName);
        } // end of if
        break;
    } // end of switch
  }
  
  public void setWallTextureName(String imgName) {
    wallTextureName = imgName;
  }
  
  public String getWallTextureName() {
    return wallTextureName;
  }
  
  
  
  public void render(Graphics g, BufferedImage img, int x, int y, int width, int height) {
    g.drawImage(img, x, y, width, height, null);
  }
} // end of Rooms
