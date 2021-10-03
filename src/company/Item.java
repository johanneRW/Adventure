package company;

import java.util.ArrayList;

public class Item {

    //metoder fra LampToggler3000 kan formentlig bruges til items
    //brug married til at flytte rundt mellem array-lister(inventory)
    private String itemName;
    private String itemDescription;
    private Room currentLocation;

    public Item(String itemName, String itemDescription) {
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.currentLocation = currentLocation;
    }

    public Item(String itemName) {
        this.itemName = itemName;
    }

    public Room getCurrentLocationOfItem() {
        return currentLocation;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    @Override
    public String toString() {
        return itemName +", "+ itemDescription;
    }
}

