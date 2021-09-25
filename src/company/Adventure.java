package company;

import java.util.Scanner;

public class Adventure {

    public static void main(String[] args) {
        boolean gameRunning = true;
        //kommandoer: "go north", "go east", "go south" og "go west", "n", "e", "s", "w",
        // exit - for at afbryde spillet helt, og afslutte programmet
        //help - for at få en instruktion og oversigt over mulige kommandoer
        //look - for at få gentaget beskrivelsen af det rum man er i


        //Tjek af at der kan laves døre/forbindelse til andre rum.
        Room room1 = new Room("room1");
        Room room2 = new Room("room2");
        room1.createDoorEast(room2);
        System.out.println(room1);
        System.out.println(room2);


        Room currentRoom = room1;

        Scanner input = new Scanner(System.in);


        System.out.println("""
                Welcome to Adventure. 
                You can move around i the game by, typing go+"direction" 
                You can also type the first letter of the direction you want to move.
                If you want to look around just type "look" or l, type "help" or h for help.
                If you want to quit the game type exit.

                What do you want to do first?""");
        while (gameRunning) {
            String command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("go north") || command.equals("n") || command.equals("north")) {
                if (currentRoom.getdoorNorth() != null) {
                    currentRoom = currentRoom.getdoorNorth();
                    System.out.println("going north");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go west") || command.equals("w") || command.equals("west")) {
                if (currentRoom.getDoorWest() != null) {
                    currentRoom = currentRoom.getDoorWest();
                    System.out.println("going west");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go east") || command.equals("e") || command.equals("east")) {
                if (currentRoom.getDoorEast() != null) {
                    currentRoom = currentRoom.getDoorEast();
                    System.out.println("going east");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go south") || command.equals("s") || command.equals("south")) {
                if (currentRoom.getDoorSouth() != null) {
                    currentRoom = currentRoom.getDoorSouth();
                    System.out.println("going south");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("look") || command.equals("l")) {
                System.out.println("looking around");
                System.out.println(currentRoom.getRoomDescription());
            } else if (command.equals("help") || command.equals("h")) {
                System.out.println("want help?");
            } else if (command.equals("exit")) {
                System.out.println("goodbye!");
                gameRunning = false;
            } else {
                System.out.println("I don't know to \"" + command + "\" try typing something else");
            }
        }
    }
}

