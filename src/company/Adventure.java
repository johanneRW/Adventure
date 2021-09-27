package company;

import java.util.Scanner;

public class Adventure {

    public static void main(String[] args) {
        boolean gameRunning = true;
        Scanner input = new Scanner(System.in);

        //Lav de ønskede rum
        //TODO: Giv rum og hjælp bedre beskrivelser
        Room theEarth1 = new Room("the Earth (1)\n", """
                Earth—our home planet—is the only place we know of so far that’s inhabited by living things.
                It's also the only planet in our solar system with liquid water on the surface.""","Hjælp til rum1");
        Room saturn2 = new Room("Saturn (2)\n", """
                Adorned with a dazzling, complex system of icy rings, Saturn is unique in our solar system.
                The other giant planets have rings, but none are as spectacular as Saturn's.""","Hjælp til rum2");
        Room neptune3 = new Room("Neptune (3)\n", """
                Neptune —the eighth and most distant major planet orbiting our Sun—
                is dark, cold and whipped by supersonic winds.
                It was the first planet located through mathematical calculations, rather than by telescope.""","Hjælp til rum3");
        Room jupiter4 = new Room("Jupiter (4)\n", """
                Jupiter is more than twice as massive than the other planets of our solar system combined.
                The giant planet's Great Red spot is a centuries-old storm bigger than Earth.""","Hjælp til rum4");
        Room pluto5 = new Room("Pluto (5)\n", """
                Pluto is a complex world of ice mountains and frozen plains.
                Once considered the ninth planet, Pluto is the largest member of the Kuiper Belt.
                and the best known of a new class of worlds called dwarf planets.""","Hjælp til rum5");
        Room mercury6 = new Room("Mercury (6)\n", """
                Mercury—the smallest planet in our solar system and closest to the Sun—
                is only slightly larger than Earth's Moon.
                Mercury is the fastest planet, zipping around the Sun every 88 Earth days.""","Hjælp til rum6");
        Room venus7 = new Room("Venus (7)\n", """
                Venus spins slowly in the opposite direction from most planets.
                A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system.""","Hjælp til rum7");
        Room mars8 = new Room("Mars (8)\n", """
                Mars is a dusty, cold, desert world with a very thin atmosphere.
                There is strong evidence Mars was —billions of years ago— wetter and warmer, with a thicker atmosphere.""","Hjælp til rum8");
        Room uranus9 = new Room(" Uranus (9)\n", """
                Uranus—seventh planet from the Sun—rotates at a nearly 90-degree angle from the plane of its orbit.
                This unique tilt makes Uranus appear to spin on its side.""","Hjælp til rum9");

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
                Welcome to 'the rediscovering of Pluto'-Adventure.
                In the year 2006 we lost our smallest planet.
                You are on a mission to rediscover our small friend.
                On you quest you will travel among the planets, on each planets you will find a number of teleportes that will send you to other plannets.
                            
                You can move around in the game by typing "go" followed by "north", "south", "east", "west".
                You can also type the first letter of the direction you want to move.
                If you want to look around, just type "look" or 'l', type "help" or 'h' for help.
                If you want to quit the game, type "exit".
                
                You are at this moment placed on Earth.
                What do you want to do first?""");
        while (gameRunning) {
            String command = input.nextLine();
            command = command.toLowerCase();

            if (command.equals("exit")) {
                System.out.println("Are you sure you want to leave the game? \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")) {
                    System.out.println("Sending you back to your home planet -Goodbye!");
                    gameRunning = false;
                } else if (answer.equals("no")) {
                    System.out.println("Alright! Let's keep going then :)");
                }
            } else if (command.equals("go north") || command.equals("n") || command.equals("north")) {
                if (currentRoom.getDoorNorth() != null) {
                    currentRoom = currentRoom.getDoorNorth();
                    System.out.println("Teleporting north...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way, ther isn't a teleporter");
                }
            } else if (command.equals("go west") || command.equals("w") || command.equals("west")) {
                if (currentRoom.getDoorWest() != null) {
                    currentRoom = currentRoom.getDoorWest();
                    System.out.println("Teleporting west...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way, ther isn't a teleporter");
                }
            } else if (command.equals("go east") || command.equals("e") || command.equals("east")) {
                if (currentRoom.getDoorEast() != null) {
                    currentRoom = currentRoom.getDoorEast();
                    System.out.println("Teleporting east...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way, ther isn't a teleporter");
                }
            } else if (command.equals("go south") || command.equals("s") || command.equals("south")) {
                if (currentRoom.getDoorSouth() != null) {
                    currentRoom = currentRoom.getDoorSouth();
                    System.out.println("Teleporting south...");
                    System.out.println(currentRoom.getROOM_DESCRIPTION());
                } else {
                    System.out.println("You can't go that way, ther isn't a teleporter");
                }
            } else if (command.equals("look") || command.equals("l")) {
                System.out.println("Looking around");
                System.out.println(currentRoom.getROOM_DESCRIPTION());
            } else if (command.equals("help") || command.equals("h")) {
                System.out.println("Want help? Type \"yes\" or \"no\"");
                String answer = input.nextLine();
                answer = answer.toLowerCase();
                if (answer.equals("yes")){
                    System.out.println(" Okay, here's some help");
                    System.out.println(currentRoom.getROOM_HELP());
                } else if (answer.equals("no")) {
                    System.out.println("Okay, suit yourself then! :)");
                }
            } else {
                //hvis spilleren taster en ugyldig kommando, beder spillet om en ny, dette er for at Adventure ikke crasher ved ugyldigt indput.
                System.out.println("I don't know how to \"" + command + "\", try typing something else");
            }
        }
    }
}


