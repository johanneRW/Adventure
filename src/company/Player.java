package company;

import java.util.ArrayList;

public class Player {
    private String playerName;
    public ArrayList<Item> inventory;
    private Item radio = new Item("radio","Unfortunately it's rund out of batteries");

    //Spiller starter altid med en Radio;
    public Player(String playerName) {
        this.playerName = playerName;
        this.inventory = new ArrayList<>();
       inventory.add(radio);

    }

    public Player() {
        this.inventory = new ArrayList<>();
        inventory.add(radio);
    }

    public String getInventory() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < inventory.size(); i++) {
            Item currentItem = inventory.get(i);
            stringBuilder.append(currentItem.getItemName());
            stringBuilder.append(", ");
        }
        return "These items are currently in your inventory : " + stringBuilder;
    }
}

