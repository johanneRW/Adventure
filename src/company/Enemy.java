package company;

import java.util.ArrayList;

public class Enemy {

    public ArrayList<Item> enemyInventory;
    private String enemieName;
    public Room currentRoom;
    private int health;
    private Weapon weaponName;
    private boolean isDead;

    public Enemy(String enemyName, int health, Weapon weaponName) {
        this.isDead = isDead;
        isDead = false;
        this.enemyInventory = new ArrayList<>();
        enemyInventory.add(weaponName);
        this.enemieName = enemieName;
        this.currentRoom = currentRoom;
        this.health = health;
        this.weaponName = weaponName;

    }
    public Weapon getWeaponName(){
        return weaponName;
    }

    public int getEnemyHealth(){
        return health;
    }

    public int loseHealth(int healthPoint) {
        this.health = health - healthPoint;
        return getEnemyHealth();
    }




    public Enemy getEnemyCurrentRoom(){
        Enemy enemy =currentRoom.getEnemy();
        return enemy;
    }
}
//TODO:oprette en enemy der har et fast våben
//TODO: når enemies dør skal våbenet ligges i rummet.
//TODO: Våbnet skal fungere som et Item. dvs. det skal kunne samles op når enemies er død
//TODO: attack metoder
//TODO: hit metoder
//TODO: enemies skal opdage når den er død.
//TODO: skal det være bestemte items/våben der skal benyttes på forskellige enemies?




