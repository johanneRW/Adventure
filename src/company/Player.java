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
        this.currentRoom=currentRoom;
        this.currentWeapon=currentWeapon;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom=currentRoom;
    }

    public void setHealth(int healthPoint){
        this.health=health+healthPoint;
    }

    public int getHealth(){
        return health;
    }

    public String loseHealth(int healthPoint){
        this.health= health-healthPoint;
        return "You lost "+healthPoint+" points\n"+getHealth();
    }
    public String gainingHealth(int healthPoint){
        this.health=health+healthPoint;
        return "You added "+healthPoint+" points\n"+getHealth();
    }

    public Weapon getCurrentWeapon(){
        return currentWeapon;
    }


    //TODO:metode til at skifte våben.
//TODO: tilføj attack
    //TODO:tilføj hit
}

