public class Interaction {
    String interactionType;

    Map map;
    Inventory inventory;
    Assets assets = new Assets();



    public Interaction(Map map, Inventory inventory) {
        this.map = map;
        this.inventory = inventory;
    }

    public void interact(String viewDirection, int x, int y) {
        if ((viewDirection.equals("north")) && (y != 0)) {
            if (map.getRoom(x, y - 1).getItem() != null) {
                inventory.addItem(map.getRoom(x, y - 1).getItem());
                map.getRoom(x, y - 1).deleteItem();
            }

            if (map.getRoom(x, y - 1).getRoomOverlayImage() != null) {
                if (map.getRoom(x, y - 1).getRoomOverlayImage().equals("chest")) {
                    map.getRoom(x, y - 1).setRoomOverlayImage("opened_chest");
                } else if (map.getRoom(x, y - 1).getRoomOverlayImage().equals("key")) {
                    if (InputClass.readInput("Safe Code:").equals("3754")) {
                        inventory.addItem(new Item("key", "key", false));
                    }
                }
            }
          
            if (map.getRoom(x, y - 1).getWallTextureName() != null) {
                if (map.getRoom(x, y - 1).getWallTextureName().equals("door_top") && inventory.searchItem("key")) {
                    map.getRoom(x, y - 1).setWallTextureName("door_opened_top");
                    map.nextLevel();
                } else if (map.getRoom(x, y - 1).getWallTextureName().equals("door_bottom") && inventory.searchItem("key")) {
                    map.getRoom(x, y - 1).setWallTextureName("door_opened_bottom");
                    map.nextLevel();
                } else if (map.getRoom(x, y - 1).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x, y - 1).setWallTextureName("door_opened_right");
                    map.nextLevel();
                } else if (map.getRoom(x, y - 1).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x, y - 1).setWallTextureName("door_opened__right");
                    map.nextLevel();
                }
            }
        } else if ((viewDirection.equals("east")) && (x != (map.getMAP_WIDTH() - 1))) {
            if (map.getRoom(x + 1, y).getItem() != null) {
                inventory.addItem(map.getRoom(x + 1, y).getItem());
                map.getRoom(x + 1, y).deleteItem();
            }

            if (map.getRoom(x + 1, y).getRoomOverlayImage() != null) {
                if (map.getRoom(x + 1, y).getRoomOverlayImage().equals("chest")) {
                    map.getRoom(x + 1, y).setRoomOverlayImage("opened_chest");
                } else if (map.getRoom(x + 1, y).getRoomOverlayImage().equals("key")) {
                    if (InputClass.readInput("Safe Code:").equals("3754")) {
                        inventory.addItem(new Item("key", "key", false));
                    }
                }
            }

            if (map.getRoom(x + 1, y).getWallTextureName() != null) {
                if (map.getRoom(x + 1, y).getWallTextureName().equals("door_top") && inventory.searchItem("key")) {
                    map.getRoom(x + 1, y).setWallTextureName("door_opened_top");
                    map.nextLevel();
                } else if (map.getRoom(x + 1, y).getWallTextureName().equals("door_bottom") && inventory.searchItem("key")) {
                    map.getRoom(x + 1, y).setWallTextureName("door_opened_bottom");
                    map.nextLevel();
                } else if (map.getRoom(x + 1, y).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x + 1, y).setWallTextureName("door_opened_right");
                    map.nextLevel();
                } else if (map.getRoom(x + 1, y).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x + 1, y).setWallTextureName("door_opened__right");
                    map.nextLevel();
                }
            }
        } else if ((viewDirection.equals("south")) && (y != (map.getMAP_HEIGHT() - 1))) {
            if (map.getRoom(x, y + 1).getItem() != null) {
                inventory.addItem(map.getRoom(x, y + 1).getItem());
                map.getRoom(x, y + 1).deleteItem();
            }

            if (map.getRoom(x, y + 1).getRoomOverlayImage() != null) {
                if (map.getRoom(x, y + 1).getRoomOverlayImage().equals("chest")) {
                    map.getRoom(x, y + 1).setRoomOverlayImage("opened_chest");
                } else if (map.getRoom(x, y + 1).getRoomOverlayImage().equals("key")) {
                    if (InputClass.readInput("Safe Code:").equals("3754")) {
                        inventory.addItem(new Item("key", "key", false));
                    }
                }
            }

            if (map.getRoom(x, y + 1).getWallTextureName() != null) {
                if (map.getRoom(x, y + 1).getWallTextureName().equals("door_top") && inventory.searchItem("key")) {
                    map.getRoom(x, y + 1).setWallTextureName("door_opened_top");
                    map.nextLevel();
                } else if (map.getRoom(x, y + 1).getWallTextureName().equals("door_bottom") && inventory.searchItem("key")) {
                    map.getRoom(x, y + 1).setWallTextureName("door_opened_bottom");
                    map.nextLevel();
                } else if (map.getRoom(x, y + 1).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x, y + 1).setWallTextureName("door_opened_right");
                    map.nextLevel();
                } else if (map.getRoom(x, y + 1).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x, y + 1).setWallTextureName("door_opened__right");
                    map.nextLevel();
                }
            }
        } else if ((viewDirection.equals("west")) && (x != 0)) {
            if (map.getRoom(x - 1, y).getItem() != null) {
                inventory.addItem(map.getRoom(x - 1, y).getItem());
                map.getRoom(x - 1, y).deleteItem();
            }

            if (map.getRoom(x - 1, y).getRoomOverlayImage() != null) {
                if (map.getRoom(x - 1, y).getRoomOverlayImage().equals("chest")) {
                    map.getRoom(x - 1, y).setRoomOverlayImage("opened_chest");
                } else if (map.getRoom(x - 1, y).getRoomOverlayImage().equals("key")) {
                    if (InputClass.readInput("Safe Code:").equals("3754")) {
                        inventory.addItem(new Item("key", "key", false));
                    }
                }
            }

            if (map.getRoom(x - 1, y).getWallTextureName() != null) {
                if (map.getRoom(x - 1, y).getWallTextureName().equals("door_top") && inventory.searchItem("key")) {
                    map.getRoom(x - 1, y).setWallTextureName("door_opened_top");
                    map.nextLevel();
                } else if (map.getRoom(x - 1, y).getWallTextureName().equals("door_bottom") && inventory.searchItem("key")) {
                    map.getRoom(x - 1, y).setWallTextureName("door_opened_bottom");
                    map.nextLevel();
                } else if (map.getRoom(x - 1, y).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x - 1, y).setWallTextureName("door_opened_right");
                    map.nextLevel();
                } else if (map.getRoom(x - 1, y).getWallTextureName().equals("door_right") && inventory.searchItem("key")) {
                    map.getRoom(x - 1, y).setWallTextureName("door_opened__right");
                    map.nextLevel();
                }
            }
        } else {
            if (map.getRoom(x, y).getItem() != null) {
                inventory.addItem(map.getRoom(x, y).getItem());
                map.getRoom(x, y).deleteItem();
            }

            if (map.getRoom(x, y).getRoomOverlayImage() != null) {
                if (map.getRoom(x, y).getRoomOverlayImage().equals("chest") && inventory.searchItem("key")) {
                    map.getRoom(x, y).setRoomOverlayImage("opened_chest");
                    map.levelCompleted();
                } else if (map.getRoom(x, y).getRoomOverlayImage().equals("key")) {
                    if (InputClass.readInput("Safe Code:").equals("3754")) {
                        inventory.addItem(new Item("key", "key", false));
                    }
                }
            }
        }
    }
}