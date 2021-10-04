package company;

import java.util.ArrayList;

public class Player {
    private Formating formating = new Formating();
    private static Map spaceMap = new Map();
    public ArrayList<Item> inventory;
    private static Item radio = new Item("radio", "unfortunately it ran out of batteries...", spaceMap.getWorkingRadio());

    //Spiller starter altid med en radio med når han/hun starter;
    public Player() {
        this.inventory = new ArrayList<>();
        inventory.add(radio);
    }

    public String getInventory() {
        if (inventory != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < inventory.size(); i++) {
                Item currentItem = inventory.get(i);
                stringBuilder.append(currentItem.getItemName());
                stringBuilder.append(", ");
            }
            return "These items are currently in your inventory: " + formating.getStringsCapitalized(stringBuilder.toString());
        } else return "There doesn't seem to be anything in your inventory";
    }

    public String getInventoryDescription() {
        if (inventory != null) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < inventory.size(); i++) {
                Item currentItem = inventory.get(i);
                stringBuilder.append(currentItem.getItemName() + ", " + currentItem.getItemDescription());
                stringBuilder.append(", ");
            }
            return "These items are currently in your inventory: " + formating.getStringsCapitalized(stringBuilder.toString());
        } else return "There doesn't seem to be anything in your inventory";
    }

}