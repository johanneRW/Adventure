package company;

import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private Map spaceMap = new Map();
    public ArrayList<Item> inventory;
    private Room currentRoom;
    private int health;
    private Weapon currentWeapon;
    private Item radio = new Item("radio", "unfortunately it ran out of batteries...Better find some new.", spaceMap.getWorkingRadio());


    //Spiller starter altid med en radio med når han/hun starter;
    public Player() {
        this.inventory = new ArrayList<>();
        inventory.add(radio);
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

    public void changeInHealth(int healthPoint) {
        this.health = health + healthPoint;
    }


    public Weapon getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Weapon currentWeapon) {
        this.currentWeapon = currentWeapon;

    }

    public boolean takeHit(Weapon weapon) {
        if (currentRoom.getEnemy() != null) {
            int damage = weapon.getDamage();
            changeInHealth(damage);
            return true;
        } else {
            return false;
        }
    }
}

