package company;

import java.util.ArrayList;

public class Player {
    private Formatting formatting = new Formatting();
    private Map spaceMap = new Map();
    public ArrayList<Item> inventory;
    private Item radio = new Item("radio", "unfortunately it ran out of batteries...Better find some new.", spaceMap.getWorkingRadio());

    //Spiller starter altid med en radio med når han/hun starter;
    public Player() {
        this.inventory = new ArrayList<>();
        inventory.add(radio);
    }

    public String getInventory() {
        if (inventory.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < inventory.size(); i++) {
                Item currentItem = inventory.get(i);
                stringBuilder.append(currentItem.getItemName());
                stringBuilder.append(", ");
            }
            return "These items are currently in your inventory: " + formatting.getStringsCapitalized(stringBuilder.toString());
        } else return "Your inventory seems to be empty";
    }


    public String getInventoryWhitDescription() {
        if (inventory.size() > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < inventory.size(); i++) {
                Item currentItem = inventory.get(i);
                stringBuilder.append(currentItem.getItemName() + ", " + currentItem.getItemDescription());
                stringBuilder.append("\n");
            }
            return "These items are currently in your inventory: \n" + formatting.getStringsCapitalized(stringBuilder.toString());
        } else return "Your inventory seems to be empty";
    }
}

