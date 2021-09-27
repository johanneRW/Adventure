package company;

import java.util.Scanner;

public class Adventure {

    public static void main(String[] args) {
        boolean gameRunning = true;
        Scanner input = new Scanner(System.in);
        //TODO: Rumnavne og beskrivelser skal være på engelsk, og sjovere end room1

        //Lav de ønskede rum
        //TODO: Opret alle rum.
        //TODO: Giv rum bedre beskrivelser
        Room room1 = new Room("room1", """
                Dette er der første rum""");
        Room room2 = new Room("room2", """
                Dette er det andet rum""");
        //lav forbindelse mellem rummene
        room1.createDoorEast(room2);

        //Tjek af at der kan laves døre/forbindelse til andre rum.
        //TODO:sout skal fjernes når rum og forbindelse er oprettet og dobbelttjekket.
        System.out.println(room1);
        System.out.println(room2);

        //I følge instruktionen skal bruger starte i rum 1, derfor er dette startværdien.
        Room currentRoom = room1;

        //kommandoer: "go north", "go east", "go south","go west", "n", "e", "s", "w",
        // exit - for at afbryde spillet helt, og afslutte programmet
        //help - for at få en instruktion og oversigt over mulige kommandoer
        //look - for at få gentaget beskrivelsen af det rum man er i

        System.out.println("""
                Welcome to Adventure.
                                
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                If you want to look around, just type "look" or 'l', type "help" or 'h' for help.
                If you want to quit the game, type "exit".
                """);
                System.out.println(currentRoom.getROOM_DESCRIPTION());
        System.out.println("What do you want to do first?");

        while (gameRunning) {
            String command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("go north") || command.equals("n") || command.equals("north")) {
                if (currentRoom.getDoorNorth() != null) {
                    currentRoom = currentRoom.getDoorNorth();
                    System.out.println("Going north...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go west") || command.equals("w") || command.equals("west")) {
                if (currentRoom.getDoorWest() != null) {
                    currentRoom = currentRoom.getDoorWest();
                    System.out.println("Going west...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go east") || command.equals("e") || command.equals("east")) {
                if (currentRoom.getDoorEast() != null) {
                    currentRoom = currentRoom.getDoorEast();
                    System.out.println("Going east...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go south") || command.equals("s") || command.equals("south")) {
                if (currentRoom.getDoorSouth() != null) {
                    currentRoom = currentRoom.getDoorSouth();
                    System.out.println("Going south...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("look") || command.equals("l")) {
                System.out.println("Looking around...");
                System.out.println(currentRoom.getROOM_DESCRIPTION());
            } else if (command.equals("help") || command.equals("h")) {
                System.out.println("Want help?");
                //TODO: Måske skal der indsættes endnu en if-sætning, hvor brugeren kan sige ja eller nej? ellers skal "help" kalde en hjælpe tekst.
            } else if (command.equals("exit")) {
                System.out.println("Goodbye!");
                System.out.println("see you another time");
                gameRunning = false;
            } else {
                //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                System.out.println("I don't know how to \"" + command + "\", try typing something else");
            }
        }
    }
}

