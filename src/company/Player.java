package company;

import java.util.ArrayList;

public class Player {
    private String playerName;
    public ArrayList<Item> inventory;

    //TODO: en spiller skal kunne samle items op og ligge dem i rum.
    //TODO: skal der være en max grænse for items?
    //TODO: inventorylist

    //når en spiller dropper noget fra sit inventory skal det overføres til rummet.
    // når spiller tager item skal det overførs fra rummet.


    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new ArrayList<>();
    }

    public Player() {
        this.inventory = new ArrayList<>();
    }

    public String getInventory() {
        return "These items are currently in your inventory : " + inventory;
    }

    public void takeItem(String itemName) {
        Item item = new Item("itemName");
        inventory.add(item);
    }

    public String dropItem(String itemName) {
        boolean found = false;
        for (int i = 0; i < inventory.size(); i++) {
            Item currentItem = inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                inventory.remove(i);
                found = true;
            }
        }
        if (!found) {
            return "ther doesn't seem to be a \"" + itemName + "\" in your inventory";
        }
        return "dropping" + itemName;
    }
}

