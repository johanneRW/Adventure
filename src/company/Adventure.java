package company;

import java.util.Scanner;

public class Adventure {

    public static boolean gameRunning = true;
    private static Map spaceMap = new Map();
    private static Player player = new Player();

    public static void main(String[] args) {
/*
        //sout til at tjekke om ting bliver taget op og lagt tilbage
        System.out.println(pickUpItem("kage"));
        System.out.println(player.getInventory());
       System.out.println(dropItem("kage"));
        System.out.println(spaceMap.currentRoom.items);
        System.out.println(player.getInventory());
        System.out.println( pickUpItem("søm"));
        pickUpItem("placeholder");
        System.out.println(player.getInventory());*/

        Scanner input = new Scanner(System.in);
        //kommandoer: "go north", "go east", "go south","go west", "n", "e", "s", "w",
        // exit - for at afbryde spillet helt, og afslutte programmet
        //help - for at få en instruktion og oversigt over mulige kommandoer
        //look - for at få gentaget beskrivelsen af det rum man er i

        System.out.println("""
                Welcome to The Rediscovering of Pluto.
                                
                In the year 2006 we lost our smallest planet. It was demoted, and categorised as a dwarf planet.
                But we believe that Pluto belongs to the real planets, so your mission is:
                To rediscover our small friend - Pluto.
                On you quest you will find a number of teleporters that will send you to other planets.
                With you, you'll have a device to tell about the planets, track items, and helping you on your quest.
                                
                How to play:
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                If you want to look around, just type "look" or 'l'. Type "help" or 'h' for help.
                If you want to quit the game, type "exit".
                                
                At this moment you are on Earth. Take a look around.""");
        System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION()+"\nWhat do you want to do first?");

        while (gameRunning) {
            String command = input.nextLine();
            command = command.toLowerCase();
//TODO:der skal tilføjes dialog til at tage og ligge ting (inventory, take, drop)
            if (command.equals("exit")) {
                System.out.println("Are you sure you want to leave the game? \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")) {
                    System.out.println("Sending you back to your home planet - Goodbye!!!");
                    gameRunning = false;
                } else if (answer.equals("no")) {
                    System.out.println("Alright! Let's keep going then :)");
                }
            } else if (command.equals("go north") || command.equals("n") || command.equals("north")) {
                System.out.println(requestDirection(spaceMap.currentRoom.getConnectionSouth(),"north"));

            } else if (command.equals("go west") || command.equals("w") || command.equals("west")) {
                System.out.println(requestDirection(spaceMap.currentRoom.getConnectionSouth(),"west"));

            } else if (command.equals("go east") || command.equals("e") || command.equals("east")) {
                System.out.println(requestDirection(spaceMap.currentRoom.getConnectionSouth(),"east"));

            } else if (command.equals("go south") || command.equals("s") || command.equals("south")) {
                System.out.println(requestDirection(spaceMap.currentRoom.getConnectionSouth(),"south"));

            } else if (command.equals("look") || command.equals("l")) {
                System.out.println("Looking around...");
                System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION());
                System.out.println("Items on this plannet:"+ spaceMap.currentRoom.items);
            } else if (command.equals("help") || command.equals("h")) {
                System.out.println("Want help? Type \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")) {
                    System.out.println("Okay, I'll scan the planet and see if I can help you.....");
                    System.out.println(spaceMap.currentRoom.getROOM_HELP());
                } else if (answer.equals("no")) {
                    System.out.println("Okay, suit yourself! :)");
                }
            } else {
                //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                System.out.println("I don't know how to \"" + command + "\", try typing something else");
            }
        }
    }

    public static String requestDirection(Room requestedRoom, String directionName){
        if (requestedRoom != null) {
            spaceMap.currentRoom = requestedRoom;
            spaceMap.checkIfFinal();
            return "Teleporting "+directionName +"...." + "\n\n" + enteringRoom();
        } else {
            return "You can't go that way - there isn't a teleporter.";
        }
    }
    public static String enteringRoom(){
              return spaceMap.currentRoom.getROOM_NAME()+spaceMap.currentRoom.getROOM_DESCRIPTION()+
                                    "\nItems on this plannet:"+ spaceMap.currentRoom.items;
    }
    public static boolean findItemInInventory(String itemName) {
        boolean found = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                found = true;
            }
        }
       return found;
    }

    public static String pickUpItem(String itemName) {
        boolean found = false;

        for (int i = 0; i < spaceMap.currentRoom.items.size(); i++) {
            Item currentItem = spaceMap.currentRoom.items.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                spaceMap.currentRoom.items.remove(i);
                found = true;
                player.inventory.add(currentItem);
            }
        }
        if (!found) {
            return "Can't find a \"" + itemName + "\" on this planet";
        } else
            return itemName + " is added to your inventory";
    }

    public static String dropItem(String itemName) {
        boolean found = false;
        for (int i = 0; i < player.inventory.size(); i++) {
            Item currentItem = player.inventory.get(i);
            if (currentItem.getItemName().equals(itemName)) {
                player.inventory.remove(i);
                found = true;
                spaceMap.currentRoom.items.add(currentItem);
            }
        }
        if (!found) {
            return "Can't find a \"" + itemName + "\" in your inventory";
        } else
            return itemName + " is placed carefully on " + spaceMap.currentRoom;
    }
}




