package company;

import java.util.ArrayList;

public class Player {
    private Map spaceMap = new Map();
    public ArrayList<Item> inventory;
    private String playerName;
    public Room currentRoom;
    private Item radio = new Item("radio", "unfortunately it ran out of batteries...Better find some new.", spaceMap.getWorkingRadio());



    //Spiller starter altid med en radio med når han/hun starter;
    public Player() {
        this.inventory = new ArrayList<>();
        inventory.add(radio);
        this.currentRoom=currentRoom;
    }

    public Room getCurrentRoom(){
        return currentRoom;
    }
    public void setCurrentRoom(Room currentRoom){
        this.currentRoom=currentRoom;
    }
}

