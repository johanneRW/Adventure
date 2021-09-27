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
                Earth—our home planet—is the only place we know of so far that’s inhabited by living things. 
                It's also the only planet in our solar system with liquid water on the surface.""","You are standing on Earth! There are two teleporters, one is at west, and the other at east. Try, and see where you can go!");
        Room saturn2 = new Room("Saturn (2)", """
                Adorned with a dazzling, complex system of icy rings, Saturn is unique in our solar system. 
                The other giant planets have rings, but none are as spectacular as Saturn's.""","You can just pass by this one, there's nothing to see here! Winter on Earth suddenly doesn't seem so bad anymore!");
        Room neptune3 = new Room("Neptune (3)", """
                Neptune —the eighth and most distant major planet orbiting our Sun—
                is dark, cold and whipped by supersonic winds. 
                It was the first planet located through mathematical calculations, rather than by telescope.""","Hurry up and get out of here, before you get blown away by these powerful winds! Quick! Go south!");
        Room jupiter4 = new Room("Jupiter (4)", """
                Jupiter is more than twice as massive than the other planets of our solar system combined. 
                The giant planet's Great Red spot is a centuries-old storm bigger than Earth.""","Oh God! The storm! I can't see a thing! Hurry! Get out of here!");
        Room pluto5 = new Room("Pluto (5)", """
                Pluto is a complex world of ice mountains and frozen plains. 
                Once considered the ninth planet, Pluto is the largest member of the Kuiper Belt 
                and the best known of a new class of worlds called dwarf planets. ""","We are finally here! Oh it's so peaceful here. So quiet, let's stay here for ever.");
        Room mercury6 = new Room("Mercury (6)", """
                Mercury—the smallest planet in our solar system and closest to the Sun—
                is only slightly larger than Earth's Moon. 
                Mercury is the fastest planet, zipping around the Sun every 88 Earth days.""","From freezing cold to flaming HOT! This is killing me! Hurry! Find your way to a safer place!");
        Room venus7 = new Room("Venus (7)", """
                Venus spins slowly in the opposite direction from most planets. 
                A thick atmosphere traps heat in a runaway greenhouse effect, making it the hottest planet in our solar system.""","If you want to continue getting my help, you better get your butt out of here, cause my motherboard is quickly melting :(!");
        Room mars8 = new Room("Mars (8)", """
                Mars is a dusty, cold, desert world with a very thin atmosphere. 
                There is strong evidence Mars was —billions of years ago— wetter and warmer, with a thicker atmosphere.""","I can sense it! WE ARE SO CLOSE!!! Don't lose it now! \nOH NO! Some aliens are blocking the portal, quick! Do something! ");
        Room uranus9 = new Room(" Uranus (9)", """
                Uranus—seventh planet from the Sun—rotates at a nearly 90-degree angle from the plane of its orbit. 
                This unique tilt makes Uranus appear to spin on its side.""","BRRR! Not exactly Utopia, is it?");

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
                I the year 2006 we lost our smallest planet.
                You are on a mission to rediscover our small friend.
                On you quest you will find some teleportes that will send you to other plannets.
                
                                
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


