package company;

import java.util.Scanner;
public class Adventure {

    public static void main(String[] args) {
        //kommandoer: "go north", "go east", "go south" og "go west", "n", "e", "s", "w",
        // exit - for at afbryde spillet helt, og afslutte programmet
        //
        //help - for at få en instruktion og oversigt over mulige kommandoer
        //
        //look - for at få gentaget beskrivelsen af det rum man er i

        int currentRoom;

      Scanner input = new Scanner(System.in);
        String kommando = input.nextLine();




            if (kommando.equals("go north")) {
                System.out.println("going north");
            }
            if (kommando.equals("go west")) {
                System.out.println("going west");
            }
            if (kommando.equals("go east")) {
                System.out.println("going east");
            }
            if (kommando.equals("go south")) {
                System.out.println("going south");
            }
            if (kommando.equals("look")) {
                System.out.println("looking around");
            }
            if (kommando.equals("help")) {
                System.out.println("want help?");
            }
            if (kommando.equals("exit")) {
                System.out.println("goodbye!");
            }

        //Tjek af at der kan laves døre/forbindelse til andre rum.
        Room room1 =new Room("room1");
            Room room2 = new Room("room2");
            room1.createDoor(room2);
        System.out.println(room1);
        System.out.println(room2);
        }
    }

