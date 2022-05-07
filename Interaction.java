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