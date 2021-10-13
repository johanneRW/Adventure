package company;

import java.util.ArrayList;

public class Enemy {

    public ArrayList<Item> enemyInventory;
    private String enemyName;
    private String description;
    private int health;
    private Weapon weaponName;
    private boolean isDead;

    public Enemy(String enemyName, String description, int health, Weapon weaponName) {
        isDead = false;
        this.description=description;
        this.enemyInventory = new ArrayList<>();
        enemyInventory.add(weaponName);
        this.enemyName = enemyName;
        this.health = health;
        this.weaponName = weaponName;

    }

    public String getEnemyName() {
        return enemyName;
    }

    public Weapon getWeaponName() {
        return weaponName;
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
        return enemyName +", "+ description+
                ". Armed with: "+weaponName+", "+ weaponName.getItemDescription();
    }
}




