package company;

import java.util.ArrayList;

public class Player {
    private Map spaceMap = new Map();
    public ArrayList<Item> inventory;
    private String playerName;
    public Room currentRoom;
    private int health;
    private Weapon currentWeapon;
    private Item radio = new Item("radio", "unfortunately it ran out of batteries...Better find some new.", spaceMap.getWorkingRadio());


    //TODO: skal spilleren starte med et våben?
    //Spiller starter altid med en radio med når han/hun starter;
    public Player() {
        this.inventory = new ArrayList<>();
        inventory.add(radio);
        this.currentRoom = currentRoom;
        this.currentWeapon = currentWeapon;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public void setHealth(int healthPoint) {
        this.health = health + healthPoint;
    }

    public int getHealth() {
        return health;
    }

    public String changeInHealth(int healthPoint) {
        this.health = health + healthPoint;
        String result = null;
        if (healthPoint < 0) {
            result = "You lost " + healthPoint + " points\n" + getHealth();
        } else if (healthPoint > 0) {
            result = "You added " + healthPoint + " points\n" + getHealth();
        }
        return result;
    }



    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;

    }

    //TODO: equip og unequip metoder
    public Item equipWeapon(Weapon weapon) {
        return weapon;
    }

    public Item unequipWeapon(Weapon currentWeapon) {
        return currentWeapon;
    }


//TODO: tilføj attack


    //TODO:tilføj hit
}

