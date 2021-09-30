package company;

import java.util.ArrayList;

public class Player {
    private String playerName;
    public ArrayList<Item> inventory;
    public StringBuilder stringBuilder = new StringBuilder();

    //Spiller starter altid med en Radio;
    private Item radio = new Item("radio","Unfortunately it's rund out of batteries");

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
        for (int i = 0; i < inventory.size(); i++) {
            Item currentItem = inventory.get(i);
            stringBuilder.append(currentItem.getItemName());
            stringBuilder.append(", ");
        }
        return "These items are currently in your inventory : " + stringBuilder;
    }
}

