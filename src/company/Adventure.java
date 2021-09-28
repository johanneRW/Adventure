package company;

import java.util.Scanner;

public class Adventure {

    private static boolean gameRunning = true;
    private static Map spaceMap =new Map();

    public static void main(String[] args) {

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
                                
                How to play:
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                If you want to look around, just type "look" or 'l'. Type "help" or 'h' for help.
                If you want to quit the game, type "exit".
                                
                At this moment you are on Earth. Take a look around.
                What do you want to do first?""");
        while (gameRunning) {
            String command = input.nextLine();
            command = command.toLowerCase();

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
                if (spaceMap.currentRoom.getConnectionNorth() != null) {
                    spaceMap.currentRoom = spaceMap.currentRoom.getConnectionNorth();
                    System.out.println("Teleporting north...\n");
                    System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION());
                   checkIfFinal();
                } else {
                    System.out.println("You can't go that way - there isn't a teleporter.");
                }
            } else if (command.equals("go west") || command.equals("w") || command.equals("west")) {
                if (spaceMap.currentRoom.getConnectionWest() != null) {
                    spaceMap.currentRoom = spaceMap.currentRoom.getConnectionWest();
                    System.out.println("Teleporting west...\n");
                    System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION());
                    checkIfFinal();
                } else {
                    System.out.println("You can't go that way - there isn't a teleporter.");
                }
            } else if (command.equals("go east") || command.equals("e") || command.equals("east")) {
                if (spaceMap.currentRoom.getConnectionEast() != null) {
                    spaceMap.currentRoom = spaceMap.currentRoom.getConnectionEast();
                    System.out.println("Teleporting east...\n");
                    System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION());
                    checkIfFinal();
                } else {
                    System.out.println("You can't go that way - there isn't a teleporter.");
                }
            } else if (command.equals("go south") || command.equals("s") || command.equals("south")) {
                if (spaceMap.currentRoom.getConnectionSouth() != null) {
                    spaceMap.currentRoom = spaceMap.currentRoom.getConnectionSouth();
                    System.out.println("Teleporting south...\n");
                    System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION());
                    checkIfFinal();
                } else {
                    System.out.println("You can't go that way - there isn't a teleporter.");
                }
            } else if (command.equals("look") || command.equals("l")) {
                System.out.println("Looking around...");
                System.out.println(spaceMap.currentRoom.getROOM_DESCRIPTION());
            } else if (command.equals("help") || command.equals("h")) {
                System.out.println("Want help? Type \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")) {
                    System.out.println("Okay, I'll call back to headquarters, they will help you - maybe...");
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
    private static void checkIfFinal() {
        if (spaceMap.currentRoom.equals(spaceMap.getFinalRoom())) {
            gameRunning = false;
            System.out.println("\nYou have reached the end of the game. CONGRATULATIONS!!!\nThe End\nNow, go out and look at the sky.");
        }
    }
}




