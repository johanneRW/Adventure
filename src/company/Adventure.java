package company;

import java.util.Scanner;

public class Adventure {

    public static void main(String[] args) {
        boolean gameRunning = true;
        Scanner input = new Scanner(System.in);
        //TODO: spillet skal tale engelsk med brugeren

        //Lav de ønskede rum
        //TODO: Giv rum og hjælp bedre beskrivelser
        Room theEarth1 = new Room("the Earth (1)", """
                Dette er det første rum""","Hjælp til rum1");
        Room saturn2 = new Room("Saturn (2)", """
                Dette er det andet rum""","Hjælp til rum2");
        Room neptune3 = new Room("Neptune (3)", """
                Dette er det tredje rum""","Hjælp til rum3");
        Room jupiter4 = new Room("Jupiter (4)", """
                Dette er det fjerde rum""","Hjælp til rum4");
        Room pluto5 = new Room("Pluto (5)", """
                Dette er det femte rum""","Hjælp til rum5");
        Room mercury6 = new Room("Mercury (6)", """
                Dette er det sjette rum""","Hjælp til rum6");
        Room venus7 = new Room("Venus (7)", """
                Dette er det syvende rum""","Hjælp til rum7");
        Room mars8 = new Room("Mars (8)", """
                Dette er det ottende rum""","Hjælp til rum8");
        Room uranus9 = new Room(" Uranus (9)", """
                Dette er det niende rum""","Hjælp til rum9");

        //lav forbindelse mellem rummene
        theEarth1.createDoorEast(saturn2);
        theEarth1.createDoorSouth(jupiter4);
        saturn2.createDoorEast(neptune3);
        neptune3.createDoorSouth(mercury6);
        jupiter4.createDoorSouth(venus7);
        pluto5.createDoorSouth(mars8);
        mercury6.createDoorSouth(uranus9);
        venus7.createDoorEast(mars8);
        mars8.createDoorEast(uranus9);

        //I følge instruktionen skal bruger starte i rum 1, derfor er dette startværdien.
        Room currentRoom = theEarth1;

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

                What do you want to do first?""");
        while (gameRunning) {
            String command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Are you sure you want to leave the game? \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")) {
                    System.out.println("Goodbye");
                    gameRunning = false;
                } else if (answer.equals("no")) {
                    System.out.println("Alright! Let's keep going then :)");
                }
            } else if (command.equals("go north") || command.equals("n") || command.equals("north")) {
                if (currentRoom.getDoorNorth() != null) {
                    currentRoom = currentRoom.getDoorNorth();
                    System.out.println("Going north");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go west") || command.equals("w") || command.equals("west")) {
                if (currentRoom.getDoorWest() != null) {
                    currentRoom = currentRoom.getDoorWest();
                    System.out.println("Going west");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go east") || command.equals("e") || command.equals("east")) {
                if (currentRoom.getDoorEast() != null) {
                    currentRoom = currentRoom.getDoorEast();
                    System.out.println("Going east");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("go south") || command.equals("s") || command.equals("south")) {
                if (currentRoom.getDoorSouth() != null) {
                    currentRoom = currentRoom.getDoorSouth();
                    System.out.println("Going south");
                } else {
                    System.out.println("You can't go that way");
                }
            } else if (command.equals("look") || command.equals("l")) {
                System.out.println("Looking around");
                System.out.println(currentRoom.getROOM_DESCRIPTION());
            } else if (command.equals("help") || command.equals("h")) {
                System.out.println("Want help? Type \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")){
                    System.out.println(" Okay, here's help");
                    System.out.println(currentRoom.getROOM_HELP());
                } else if (answer.equals("no")) {
                    System.out.println("Okay, suit yourself! :)");
                }
            } else {
                //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                System.out.println("I don't know how to \"" + command + "\", try typing something else");
            }
        }
    }
}


