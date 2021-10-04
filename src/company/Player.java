package company;

import java.util.ArrayList;

public class Player {
    public ArrayList<Item> inventory;
    private Item radio = new Item("radio","unfortunately it's rund out of batteries");

    //Spiller starter altid med en radio med når han/hun starter;

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

