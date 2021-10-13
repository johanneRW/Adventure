package company;

import java.util.ArrayList;

public class Player {
    public ArrayList<Item> inventory;
    private Map spaceMap = new Map();
    private Room currentRoom;
    private int health;
    private Weapon currentWeapon;
    // Spiller starter altid med en radio med når han/hun starter;
    private Item radio = new Item("radio", "unfortunately it ran out of batteries...Better find some new.", spaceMap.getWorkingRadio());

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

    public boolean takeHit() {
        Enemy enemy = getEnemy();
        if (enemy != null) {
            Weapon weapon = enemy.getWeapon();
            int damage = weapon.getDamage();
            changeInHealth(damage);
            return true;
        } else {
            return false;
        }
    }

    public Enemy getEnemy() {
        Enemy enemy = this.currentRoom.getEnemyInRoom();
        return enemy;
    }
}

