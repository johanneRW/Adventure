package company;

import java.util.ArrayList;

public class Enemy {
    public ArrayList<Item> enemyInventory;
    private String enemyName;
    private String description;
    private int health;
    private Weapon weapon;

    public Enemy(String enemyName, String description, int health, Weapon weapon) {
        this.enemyName = enemyName;
        this.description = description;
        this.health = health;

        // læg våben i fjendens inventory, så det fungerer sammen med dropItem og pickUpItem
        // i Adventure.
        this.enemyInventory = new ArrayList<>();
        enemyInventory.add(weapon);

        this.weapon = weapon;
    }

    public String getEnemyName() {
        return enemyName;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public int getEnemyHealth() {
        return health;
    }

    public int changeInHealth(int healthPoint) {
        this.health = health + healthPoint;
        return health;
    }

    @Override
    public String toString() {
        return enemyName + ", " + description +
                ". Armed with: "+ weapon +", "+ weapon.getItemDescription();
    }
}




