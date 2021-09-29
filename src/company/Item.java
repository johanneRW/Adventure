package company;

import java.util.ArrayList;

public class Item {

//TODO: liste af items.
//TODO: items skal placeres i rum
    //TODO: et item skal have en currentRoom værdi der skal kunne ændres så det kan flyttes
    //TODO: skal der kunne interageres med ting?

    //metoder fra LampToggler3000 kan formentlig bruges til items
    //brug marrid til at flytte rundt mellem arrylister(inventory)
    private String itemName;
    private String itemDecription;
    private Room currentLocation;


    public Item(String itemName, String itemDecription, Room currentLocation) {
        this.itemName = itemName;
        this.itemDecription = itemDecription;
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

    public String getItemDecription() {
        return itemDecription;
    }

    @Override
    public String toString() {
        return  itemName;

    }
}

